package duolingobackenduserservice.service;


import duolingobackenduserservice.model.Friend;
import duolingobackenduserservice.model.User;

import java.util.List;

public interface FriendService {
    List<User> getAllFriends(String userId);
    String insertFriend(Friend friend);



    String unfriend(Friend friend);
}
