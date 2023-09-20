package duolingobackenduserservice.mapper;

import duolingobackenduserservice.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getAllUsers();
    void insertUser(User user);
}
