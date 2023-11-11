package duolingobackenduserservice.mapper;

import duolingobackenduserservice.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getAllUsers();
    void insertUser(User user);

    User getUser(@Param("username") String username);

    User getUserByUserId(@Param("userId") String userId);

    List<User> getUserExceptPlayerId(@Param("userId") String userId);

    void updateUser(User user);
}
