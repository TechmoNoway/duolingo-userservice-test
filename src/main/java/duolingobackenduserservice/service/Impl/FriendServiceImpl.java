package duolingobackenduserservice.service.Impl;


import duolingobackenduserservice.mapper.FriendMapper;
import duolingobackenduserservice.model.Friend;
import duolingobackenduserservice.model.User;
import duolingobackenduserservice.service.CommonService;
import duolingobackenduserservice.service.FriendService;
import duolingobackenduserservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    FriendMapper friendMapper;

    @Autowired
    CommonService commonService;

    @Autowired
    UserService userService;

    @Override
    public List<User> getAllFriends(String userId) {
        List<Friend> friends = friendMapper.getAllFriends(userId);
        List<User> friendsData = new ArrayList<>();

        for(int i = 0; i < friends.size(); i++) {
            User friendDataItem = userService.getUserByUserId(friends.get(i).getUserId());
            friendsData.add(friendDataItem);
        }

        return friendsData;
    }

    @Override
    public String insertFriend(Friend friend) {
        Friend checkedFriend = friendMapper.getFriendByFriendId(friend.getFriendId(), friend.getUserId());
        if(checkedFriend != null) {
            return "Add friend is failure, This friend is added";
        }
        String id = commonService.generateRandomNumber(10);
        String startDate = commonService.createCurrentDate();
        friend.setId(id);
        friend.setStartDate(startDate);

        friendMapper.insertFriend(friend);


        return "Add friend is success";
    }

    @Override
    public String unfriend(Friend friend) {
        Friend checkedFriend = friendMapper.getFriendByFriendId(friend.getFriendId(), friend.getUserId());
        if(checkedFriend == null) {
            return "This friend is not exist";
        }
        friendMapper.unfriend(friend.getFriendId(), friend.getUserId());
        return "Unfriend is successfully";
    }
}
