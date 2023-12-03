package com.legend.board.model.dao;

import java.util.List;

import com.legend.board.model.dto.Friend;
import com.legend.board.model.dto.FriendRequest;
import com.legend.board.model.dto.SearchCondition;
import com.legend.board.model.dto.User;

public interface FriendRequestDao {	
	public FriendRequest searchFriendRequest(FriendRequest friendRequest);
	
	public List<FriendRequest> searchFriendRequestList(SearchCondition condition);
	
	public List<Friend> searchFriendConnection(FriendRequest friendRequest);
	
	public void insertFriendRequest(FriendRequest friendRequest);
	
	public void deleteFriendRequest(int friendReqNo);
	
	public User selectOneUser(String userId);
}
