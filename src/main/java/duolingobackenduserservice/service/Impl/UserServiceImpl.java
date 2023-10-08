package duolingobackenduserservice.service.Impl;

import duolingobackenduserservice.config.JwtService;
import duolingobackenduserservice.dto.AuthenticationResponse;
import duolingobackenduserservice.dto.CheckLoginRequest;
import duolingobackenduserservice.dto.RegistryUserRequest;
import duolingobackenduserservice.mapper.UserMapper;
import duolingobackenduserservice.model.User;
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

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public void insertUser(RegistryUserRequest registryUserRequest) {

        User user = User.builder()
                .id(String.valueOf(userMapper.getAllUsers().size() + 1))
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
                .filter(user -> checkLoginRequest.getUsername().equals(user.getUsername())
                    && passwordEncoder.encode(checkLoginRequest.getPassword()).matches(checkLoginRequest.getPassword())
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

        System.out.println(registryUserRequest.getPassword());

        //        Check user is exist
        CheckLoginRequest checkedUser = CheckLoginRequest.builder()
                .password(registryUserRequest.getPassword())
                .username(registryUserRequest.getUsername())
                .build();
        User oldUser = this.checkLogin(checkedUser);
        if (oldUser != null) {
            return AuthenticationResponse.builder()
                    .token(null)
                    .message("This user is exist")
                    .build();
        }

        String hashedPassword = passwordEncoder.encode(CharBuffer.wrap(registryUserRequest.getPassword()));

        registryUserRequest.setPassword(hashedPassword);

        this.insertUser(registryUserRequest);

        User newUser = User.builder()
                .username(registryUserRequest.getUsername())
                .password(registryUserRequest.getPassword())
                .roleId("1")
                .avatar(registryUserRequest.getAvatar())
                .build();

        String token = jwtService.generateToken(newUser);



        return new AuthenticationResponse(token, "Register is successfully");
    }


//    Below method is for authentication of user when start use website

}
