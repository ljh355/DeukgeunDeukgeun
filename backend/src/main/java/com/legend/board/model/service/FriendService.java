package com.legend.board.model.service;

import java.util.List;

import com.legend.board.model.dto.Board;
import com.legend.board.model.dto.Comment;
import com.legend.board.model.dto.Friend;
import com.legend.board.model.dto.FriendRequest;
import com.legend.board.model.dto.SearchCondition;

public interface FriendService {
	List<Friend> searchFriendList(SearchCondition condition);
	
	Friend searchFriend(int friendNo);
	
	FriendRequest searchFriendReq(int friendReqNo);
	
	Friend searchFriendByUserId(Friend friend);
	
	void insertFriend(Friend friend);
	
	void deleteFriend(int friendNo);
	
	void deleteFriendRequest(int friendReqNo);

	List<Board> searchFriendBoardList(String userId);

	List<Comment> searchFriendCommentList(String userId);
}
