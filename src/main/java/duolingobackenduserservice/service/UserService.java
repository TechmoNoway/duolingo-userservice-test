package duolingobackenduserservice.service;

import duolingobackenduserservice.dto.AuthenticationResponse;
import duolingobackenduserservice.dto.CheckLoginRequest;
import duolingobackenduserservice.dto.RegistryUserRequest;
import duolingobackenduserservice.model.User;

import java.rmi.registry.Registry;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void insertUser(RegistryUserRequest registryUserRequest);

    User checkLogin(CheckLoginRequest checkLoginRequest);

    User getUser(String username);

    AuthenticationResponse login(CheckLoginRequest checkLoginRequest);
    AuthenticationResponse register(RegistryUserRequest registryUserRequest);
}
