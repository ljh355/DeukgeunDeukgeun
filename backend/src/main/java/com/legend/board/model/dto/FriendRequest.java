package com.legend.board.model.dto;

public class FriendRequest {
	private int friendReqNo;
	private String userId;
	private String userNickname;
	private String friendId;
	private String friendNickname;
	
	public FriendRequest () {
		
	}

	public int getFriendReqNo() {
		return friendReqNo;
	}

	public void setFriendReqNo(int friendReqNo) {
		this.friendReqNo = friendReqNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
	
	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getFriendNickname() {
		return friendNickname;
	}

	public void setFriendNickname(String friendNickname) {
		this.friendNickname = friendNickname;
	}
}
