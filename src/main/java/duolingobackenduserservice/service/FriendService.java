package duolingobackenduserservice.service;


import duolingobackenduserservice.model.Friend;

import java.util.List;

public interface FriendService {
    List<Friend> getAllFriends(String userId);
    String insertFriend(Friend friend);

    String unfriend(Friend friend);
}
