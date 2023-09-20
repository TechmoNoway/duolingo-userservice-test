package duolingobackenduserservice.service.Impl;

import duolingobackenduserservice.dto.CheckLoginRequest;
import duolingobackenduserservice.dto.RegistryUserRequest;
import duolingobackenduserservice.mapper.UserMapper;
import duolingobackenduserservice.model.User;
import duolingobackenduserservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

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
                .filter(user -> checkLoginRequest.getUsername().equals(user.getUsername()) && checkLoginRequest.getPassword().equals(user.getPassword()))
                .findAny()
                .orElse(null);
    }
}
