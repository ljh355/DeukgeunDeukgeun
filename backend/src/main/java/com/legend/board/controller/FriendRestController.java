package com.legend.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.legend.board.model.dto.Board;
import com.legend.board.model.dto.Comment;
import com.legend.board.model.dto.Friend;
import com.legend.board.model.dto.FriendRequest;
import com.legend.board.model.dto.SearchCondition;
import com.legend.board.model.service.FriendService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags = "친구 컨트롤러")
public class FriendRestController {
	@Autowired
	private FriendService friendService;

	@GetMapping("/friend")
	@ApiOperation(value = "친구목록 조회", notes = "친구 목록을 조회합니다")
	public ResponseEntity<?> friendList(SearchCondition condition) {
		List<Friend> friendList = friendService.searchFriendList(condition);
		return new ResponseEntity<List<Friend>>(friendList, HttpStatus.OK);
	}
	
	@GetMapping("/friend/{friendNo}")
	@ApiOperation(value = "friendNo로 친구 조회")
	public ResponseEntity<Friend> friendSearch(@PathVariable int friendNo) {
		Friend friend = friendService.searchFriend(friendNo);
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}
	
	@PostMapping("/friend/{friendReqNo}")
	@ApiOperation(value = "친구 등록")
	public ResponseEntity<Void> friendInsert(@PathVariable int friendReqNo) {
		if (friendService.searchFriendReq(friendReqNo) != null) {
			FriendRequest friendReq = friendService.searchFriendReq(friendReqNo);
			String userId = friendReq.getUserId();
			String userNickname = friendReq.getUserNickname();
			String friendId = friendReq.getFriendId();
			String friendNickname = friendReq.getFriendNickname();
			friendService.deleteFriendRequest(friendReqNo);
			
			Friend friend = new Friend();
			friend.setUserId(userId);
			friend.setFriendId(friendId);
			friend.setUserNickname(userNickname);
			friend.setFriendNickname(friendNickname);
			friendService.insertFriend(friend);
			
			friend.setUserId(friendId);
			friend.setFriendId(userId);
			friend.setUserNickname(friendNickname);
			friend.setFriendNickname(userNickname);
			friendService.insertFriend(friend);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} else
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/friend/{friendNo}")
	@ApiOperation(value = "친구 삭제")
	public ResponseEntity<Void> friendDelete(@PathVariable int friendNo) {
		Friend friend = friendService.searchFriend(friendNo);
		Friend anotherFriend = friendService.searchFriendByUserId(friend);
		friendService.deleteFriend(friendNo);
		friendService.deleteFriend(anotherFriend.getFriendNo());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/friend/board/{userId}")
	@ApiOperation(value = "친구 게시글 목록 조회")
	public ResponseEntity<List<Board>> friendBoardList(@PathVariable String userId) {
		List<Board> boardList = friendService.searchFriendBoardList(userId);
		return new ResponseEntity<List<Board>>(boardList, HttpStatus.OK);
	}
	
	@GetMapping("/friend/comment/{userId}")
	@ApiOperation(value = "친구 댓글 목록 조회")
	public ResponseEntity<List<Comment>> friendCommentList(@PathVariable String userId) {
		List<Comment> commentList = friendService.searchFriendCommentList(userId);
		return new ResponseEntity<List<Comment>>(commentList, HttpStatus.OK);
	}
}
