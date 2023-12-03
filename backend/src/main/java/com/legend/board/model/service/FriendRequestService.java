package com.legend.board.model.service;

import java.util.List;

import com.legend.board.model.dto.Friend;
import com.legend.board.model.dto.FriendRequest;
import com.legend.board.model.dto.SearchCondition;
import com.legend.board.model.dto.User;

public interface FriendRequestService {
	// 친구신청 목록 검색하기
	List<FriendRequest> searchFriendRequestList(SearchCondition condition);
	
	// 어느 두 유저의 친구신청 정보 찾기
	FriendRequest searchFriendRequest(FriendRequest friendRequest);
	
	// 어느 두 유저가 친구 관계인지 확인하기
	List<Friend> searchFriendConnection(FriendRequest friendRequest);
		
	// 친구신청 DB에 등록
	void insertFriendRequest(FriendRequest friendRequest);
	
	// 친구신청 DB에서 삭제
	void deleteFriendRequest(int friendReqNo);
	
	// 유저정보 DB에서 찾기
	User selectOneUser(String userId);
}
