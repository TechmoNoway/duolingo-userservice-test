package duolingobackenduserservice.mapper;

import duolingobackenduserservice.model.Friend;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FriendMapper {
    List<Friend> getAllFriends();
    void insertFriend(Friend friend);
}
