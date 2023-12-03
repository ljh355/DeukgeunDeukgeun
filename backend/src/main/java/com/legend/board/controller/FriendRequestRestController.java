package com.legend.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.legend.board.model.dto.FriendRequest;
import com.legend.board.model.dto.SearchCondition;
import com.legend.board.model.dto.User;
import com.legend.board.model.service.FriendRequestService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags = "친구요청 컨트롤러")
public class FriendRequestRestController {

	@Autowired
	private FriendRequestService friendRequestService;

	@GetMapping("/friendreq")
	@ApiOperation(value = "친구요청 목록 조회")
	public ResponseEntity<?> friendRequestList(SearchCondition condition) {
		List<FriendRequest> friendReqList = friendRequestService.searchFriendRequestList(condition);
		return new ResponseEntity<List<FriendRequest>>(friendReqList, HttpStatus.OK);
	}

	@PostMapping("/friendreq")
	@ApiOperation(value = "친구요청")
	// 친구요청은 친구요청하는 회원의 ID, 친구요청받는 회원의 ID를 파라미터로 받는다.
	public ResponseEntity<?> friendRequestInsert(@RequestBody FriendRequest friendRequest) {

		// 친구요청을 보낸 ID가 DB에 존재하지 않는 ID일 경우 400 BAD REQUEST 리턴
		// 공백을 넣은 경우 -1 리턴
		// 친구요청을 받은 ID가 DB에 존재하지 않는 경우 -2 리턴
		// 자기 자신에게 친구요청을 보낼 경우 -3 리턴,
		// 이미 친구신청 중인 경우 -4 리턴,
		// 이미 친구관계인 경우 -5 리턴
		
		if (friendRequestService.selectOneUser(friendRequest.getUserId()) == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

		if (friendRequest.getFriendId() == "") {
			return new ResponseEntity<Integer>(-1, HttpStatus.OK);
		}

		if (friendRequestService.selectOneUser(friendRequest.getFriendId()) == null) {
			return new ResponseEntity<Integer>(-2, HttpStatus.OK);
		}

		if (friendRequest.getUserId().equals(friendRequest.getFriendId())) {
			return new ResponseEntity<Integer>(-3, HttpStatus.OK);
		}

		if (friendRequestService.searchFriendRequest(friendRequest) != null) {
			return new ResponseEntity<Integer>(-4, HttpStatus.OK);
		}

		if (friendRequestService.searchFriendConnection(friendRequest).size() > 0) {
			return new ResponseEntity<Integer>(-5, HttpStatus.OK);
		}

		User user = friendRequestService.selectOneUser(friendRequest.getUserId());
		User friend = friendRequestService.selectOneUser(friendRequest.getFriendId());
		friendRequest.setUserNickname(user.getUserNickname());
		friendRequest.setFriendNickname(friend.getUserNickname());
		friendRequestService.insertFriendRequest(friendRequest);
		return new ResponseEntity<Integer>(1, HttpStatus.CREATED);
	}

	@DeleteMapping("/friendreq/{friendReqNo}")
	@ApiOperation(value = "친구요청 취소/거부")
	// 친구요청 취소는 친구요청의 번호를 파라미터로 받는다.
	public ResponseEntity<Void> friendRequestDelete(@PathVariable int friendReqNo) {
		friendRequestService.deleteFriendRequest(friendReqNo);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
