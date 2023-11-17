package duolingobackenduserservice.service;

import duolingobackenduserservice.dto.*;
import duolingobackenduserservice.model.User;

import java.rmi.registry.Registry;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserByUserId(String userId);
    void insertUser(RegistryUserRequest registryUserRequest);

    User checkLogin(CheckLoginRequest checkLoginRequest);

    User getUser(String username);

    AuthenticationResponse login(CheckLoginRequest checkLoginRequest);
    AuthenticationResponse register(RegistryUserRequest registryUserRequest);

    AuthenticationResponse updateUser(UpdatedRequest request);

    List<User> getUserExceptPlayerId(String userId);

    String sendEmailForResetPassword(InputSendEmailData data);


    CheckedForResetPasswordResponse checkTokenForResetPassword(String token);
}
