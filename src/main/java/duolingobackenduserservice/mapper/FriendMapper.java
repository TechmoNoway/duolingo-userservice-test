package duolingobackenduserservice.mapper;

import duolingobackenduserservice.model.Friend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FriendMapper {
    List<Friend> getAllFriends(@Param("userId") String userId);

    Friend getFriendByFriendId(@Param("friendId") String friendId, @Param("userId") String userId);
    void insertFriend(Friend friend);

    void unfriend(@Param("friendId") String friendId, @Param("userId") String userId);
}
