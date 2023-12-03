package com.legend.board.model.dao;

import java.util.List;

import com.legend.board.model.dto.Board;
import com.legend.board.model.dto.Comment;
import com.legend.board.model.dto.Friend;
import com.legend.board.model.dto.FriendRequest;
import com.legend.board.model.dto.SearchCondition;

public interface FriendDao {
	public List<Friend> searchFriendList(SearchCondition condition);
	
	public Friend searchFriend(int friendNo);
	
	public FriendRequest searchFriendReq(int friendReqNo);
	
	public Friend searchFriendByUserId(Friend friend);
	
	public void insertFriend(Friend friend);
	
	public void deleteFriend(int friendNo);
	
	public void deleteFriendRequest(int friendReqNo);

	public List<Board> searchFriendBoardList(String userId);

	public List<Comment> searchFriendCommentList(String userId);
}
