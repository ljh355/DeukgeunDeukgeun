package com.legend.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.legend.board.model.dao.FriendRequestDao;
import com.legend.board.model.dto.Friend;
import com.legend.board.model.dto.FriendRequest;
import com.legend.board.model.dto.SearchCondition;
import com.legend.board.model.dto.User;

@Service
public class FriendRequestServiceImpl implements FriendRequestService {

	private FriendRequestDao friendRequestDao;
	
	@Autowired
	public void setFriendRequestDao(FriendRequestDao friendRequestDao) {
		this.friendRequestDao = friendRequestDao;
	}
	
	@Override
	public List<FriendRequest> searchFriendRequestList(SearchCondition condition) {
		return friendRequestDao.searchFriendRequestList(condition);
	}

	@Override
	public FriendRequest searchFriendRequest(FriendRequest friendRequest) {
		return friendRequestDao.searchFriendRequest(friendRequest);
	}
	
	@Override
	public List<Friend> searchFriendConnection(FriendRequest friendRequest) {
		return friendRequestDao.searchFriendConnection(friendRequest);
	}
	
	@Override
	public void insertFriendRequest(FriendRequest friendRequest) {
		friendRequestDao.insertFriendRequest(friendRequest);
	}

	@Override
	public void deleteFriendRequest(int friendReqNo) {
		friendRequestDao.deleteFriendRequest(friendReqNo);
	}

	@Override
	public User selectOneUser(String userId) {
		return friendRequestDao.selectOneUser(userId);
	}


}
