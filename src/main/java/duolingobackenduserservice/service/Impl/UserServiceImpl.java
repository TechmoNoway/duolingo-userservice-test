package duolingobackenduserservice.service.Impl;

import duolingobackenduserservice.config.JwtService;
import duolingobackenduserservice.dto.*;
import duolingobackenduserservice.mapper.PlayerMapper;
import duolingobackenduserservice.mapper.UserMapper;
import duolingobackenduserservice.model.Player;
import duolingobackenduserservice.model.User;
import duolingobackenduserservice.service.CommonService;
import duolingobackenduserservice.service.EmailService;
import duolingobackenduserservice.service.PlayerService;
import duolingobackenduserservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    JwtService jwtService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    PlayerService playerService;

    @Autowired
    CommonService commonService;

    @Autowired
    EmailService emailService;

    @Autowired
    UserDetailsService userDetailsService;



    @Value("${frontend.url}")
    private String frontendPath;

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public User getUserByUserId(String userId) {
        return userMapper.getUserByUserId(userId);
    }

    @Override
    public void insertUser(RegistryUserRequest registryUserRequest) {

        User user = User.builder()
                .id(registryUserRequest.getId())
                .username(registryUserRequest.getUsername())
                .email(registryUserRequest.getEmail())
                .password(registryUserRequest.getPassword())
                .avatar(registryUserRequest.getAvatar())
                .roleId("2")
                .createdAt(registryUserRequest.getCreatedAt())
                .updatedAt(registryUserRequest.getUpdatedAt())
                .build();

        userMapper.insertUser(user);
    }

    @Override
    public User checkLogin(CheckLoginRequest checkLoginRequest) {

        return userMapper.getAllUsers().stream()
                .filter(user -> (checkLoginRequest.getUsername().equals(user.getUsername())
                    && passwordEncoder.encode(user.getPassword()).matches(checkLoginRequest.getPassword()))
                    || (user.getUsername().matches(checkLoginRequest.getUsername()))

                )
                .findAny()
                .orElse(null);
    }

    @Override
    public User getUser(String username) {
        return userMapper.getUser(username);
    }

    @Override
    public AuthenticationResponse login(CheckLoginRequest checkLoginRequest) {
//        Check user is exist
        User oldUser = userMapper.getUser(checkLoginRequest.getUsername());
        if (oldUser == null) {
            return AuthenticationResponse.builder()
                    .token(null)
                    .message("Username or Password is incorrect !!")
                    .build();
        }
        boolean checkedPassword = passwordEncoder.matches(CharBuffer.wrap(checkLoginRequest.getPassword()), oldUser.getPassword());

        if(!checkedPassword){
            return AuthenticationResponse.builder()
                    .token(null)
                    .message("Username or Password is incorrect !!")
                    .build();
        }



        String token = jwtService.generateToken(oldUser);

        return new AuthenticationResponse(token, "Login is successfully");
    }

    @Override
    public AuthenticationResponse register(RegistryUserRequest registryUserRequest) {
        //        Check user is exist
        User oldUser = userMapper.getUserByEmail(registryUserRequest.getEmail());
        User oldUserWithUsername = userMapper.getUser(registryUserRequest.getUsername());
        if (oldUser != null || oldUserWithUsername != null) {
            String token = null;
            if(registryUserRequest.getSocial().matches("social")){
                token = jwtService.generateToken(oldUser);
            }

            return AuthenticationResponse.builder()
                    .token(token)
                    .message("This user is exist")
                    .build();
        }

        String hashedPassword = passwordEncoder.encode(CharBuffer.wrap(registryUserRequest.getPassword()));

        registryUserRequest.setPassword(hashedPassword);

        String id = commonService.generateRandomNumber(10);
        String createdAt = commonService.createCurrentDate();

        //  Create new player

        Player player = Player.builder().userId(id).language("English").build();
        boolean isSuccess = playerService.insertPlayer(player);

        User newUser = User.builder()
                .id(id)
                .username(registryUserRequest.getUsername())
                .password(registryUserRequest.getPassword())
                .roleId("1")
                .createdAt(createdAt)
                .avatar(registryUserRequest.getAvatar())
                .build();


        registryUserRequest.setCreatedAt(createdAt);
        registryUserRequest.setId(id);
        this.insertUser(registryUserRequest);
        if(!isSuccess){
            return new AuthenticationResponse(null, "Player is created is failure");
        }


        String token = jwtService.generateToken(newUser);
        return new AuthenticationResponse(token, "Register is successfully");
    }

    @Override
    public AuthenticationResponse updateUser(UpdatedRequest request) {
        try {
            if(request.getOldPassword() != null && !request.getOldPassword().matches("")){
                User oldUser = userMapper.getUserByEmail(request.getEmail());

                boolean checkedOldPassword = passwordEncoder.matches(CharBuffer.wrap(request.getOldPassword()), oldUser.getPassword());

                if(!checkedOldPassword){
                    return new AuthenticationResponse(null, "Current Password is incorrect!!");
                }
            }

            if(request.getPassword().length() < 15) {
                request.setPassword(passwordEncoder.encode(CharBuffer.wrap(request.getPassword())));
            }
            String updatedAt = commonService.createCurrentDate();
            System.out.println(request.getPassword());
            request.setUpdatedAt(updatedAt);
            userMapper.updateUser(request);

            String token = jwtService.generateToken(request);

            return new AuthenticationResponse(token, "Update is successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return new AuthenticationResponse(null, "Error Page");
        }

    }

    @Override
    public List<User> getUserExceptPlayerId(String userId) {
        return userMapper.getUserExceptPlayerId(userId);
    }

    @Override
    public String sendEmailForResetPassword(InputSendEmailData data) {
        String message = "Send Email is successfully";
        User user = userMapper.getUserByEmail(data.getEmail());
        try {
//            Create token
            String token = jwtService.generateToken(user);

//           End path
            String endPath = frontendPath+"signin/"+token+"/changePassword";

            InputEmailData emailData = new InputEmailData();
            EmailDetail details = new EmailDetail();
            details.setRecipient(data.getEmail());
            details.setSubject(message);
            EmailVariable variables = EmailVariable.builder()
                    .path(endPath)
                    .build();
            details.setVariables(variables);
            emailData.setDetail(details);

            emailService.sendSimpleMail(emailData, "resetPasswordLetter");
        } catch (Exception e) {
            e.printStackTrace();
            message = "Send email is failed";
        }

        return message;
    }

    @Override
    public CheckedForResetPasswordResponse checkTokenForResetPassword(String token) {
        String username = jwtService.extractUsername(token);
        if(username != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            return CheckedForResetPasswordResponse.builder()
                    .isExpired(false)
                    .userDetails(userDetails)
                    .build();
        }
        return CheckedForResetPasswordResponse.builder()
                .isExpired(true)
                .userDetails(null)
                .build();
    }


//    Below method is for authentication of user when start use website


}
