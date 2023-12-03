package com.legend.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.legend.board.model.dao.FriendDao;
import com.legend.board.model.dto.Board;
import com.legend.board.model.dto.Comment;
import com.legend.board.model.dto.Friend;
import com.legend.board.model.dto.FriendRequest;
import com.legend.board.model.dto.SearchCondition;

@Service
public class FriendServiceImpl implements FriendService{

	private FriendDao friendDao;
	
	@Autowired
	public void setFriendDao(FriendDao friendDao) {
		this.friendDao = friendDao;
	}
	
	@Override
	public List<Friend> searchFriendList(SearchCondition condition) {
		return friendDao.searchFriendList(condition);
	}
	
	@Override
	public Friend searchFriend(int friendNo) {
		return friendDao.searchFriend(friendNo);
	}
	
	@Override
	public FriendRequest searchFriendReq(int friendReqNo) {
		return friendDao.searchFriendReq(friendReqNo);
	}
	
	@Override
	public Friend searchFriendByUserId(Friend friend) {
		return friendDao.searchFriendByUserId(friend);
	}

	@Override
	public void insertFriend(Friend friend) {
		friendDao.insertFriend(friend);
	}

	@Override
	public void deleteFriend(int friendNo) {
		friendDao.deleteFriend(friendNo);
	}

	@Override
	public void deleteFriendRequest(int friendReqNo) {
		friendDao.deleteFriendRequest(friendReqNo);
	}

	@Override
	public List<Board> searchFriendBoardList(String userId) {
		return friendDao.searchFriendBoardList(userId);
	}

	@Override
	public List<Comment> searchFriendCommentList(String userId) {
		return friendDao.searchFriendCommentList(userId);
	}
}
