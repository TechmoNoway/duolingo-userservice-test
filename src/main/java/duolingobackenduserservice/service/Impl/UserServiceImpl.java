package duolingobackenduserservice.service.Impl;

import duolingobackenduserservice.config.JwtService;
import duolingobackenduserservice.dto.AuthenticationResponse;
import duolingobackenduserservice.dto.CheckLoginRequest;
import duolingobackenduserservice.dto.RegistryUserRequest;
import duolingobackenduserservice.dto.UpdatedRequest;
import duolingobackenduserservice.mapper.PlayerMapper;
import duolingobackenduserservice.mapper.UserMapper;
import duolingobackenduserservice.model.Player;
import duolingobackenduserservice.model.User;
import duolingobackenduserservice.service.CommonService;
import duolingobackenduserservice.service.PlayerService;
import duolingobackenduserservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
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
        User oldUser = this.checkLogin(checkLoginRequest);
        if (oldUser == null) {
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
        CheckLoginRequest checkedUser = CheckLoginRequest.builder()
                .password(registryUserRequest.getPassword())
                .username(registryUserRequest.getUsername())
                .build();
        User oldUser = this.checkLogin(checkedUser);
        if (oldUser != null) {
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

        Player player = Player.builder().userId(id).currentLevel(1).language("English").expPoint(0).build();
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
            if(!request.getOldPassword().matches("")){
                User oldUser = userMapper.getUser(request.getUsername());
                if(!passwordEncoder.encode(oldUser.getPassword()).matches(request.getOldPassword())){
                    return new AuthenticationResponse(null, "Current Password is incorrect!!");
                }
            }

            if(request.getPassword().length() < 15) {
                request.setPassword(passwordEncoder.encode(CharBuffer.wrap(request.getPassword())));
            }
            String updatedAt = commonService.createCurrentDate();
            request.setUpdatedAt(updatedAt);
            userMapper.updateUser(request);

            String token = jwtService.generateToken(request);

            return new AuthenticationResponse(token, "Update is successfully");
        } catch (Exception e) {
            return new AuthenticationResponse(null, "Error Page");
        }

    }


//    Below method is for authentication of user when start use website


}
