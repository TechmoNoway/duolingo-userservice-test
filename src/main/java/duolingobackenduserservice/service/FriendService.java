package duolingobackenduserservice.service;


import duolingobackenduserservice.model.Friend;

import java.util.List;

public interface FriendService {
    List<Friend> getAllFriends();
    void insertFriend(Friend friend);
}
