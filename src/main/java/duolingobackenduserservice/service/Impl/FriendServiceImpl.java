package duolingobackenduserservice.service.Impl;


import duolingobackenduserservice.mapper.FriendMapper;
import duolingobackenduserservice.model.Friend;
import duolingobackenduserservice.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    FriendMapper friendMapper;

    @Override
    public List<Friend> getAllFriends() {

        return friendMapper.getAllFriends();
    }

    @Override
    public void insertFriend(Friend friend) {
        friendMapper.insertFriend(friend);
    }
}
