package com.legend.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.legend.board.model.dto.Comment;
import com.legend.board.model.dto.SearchCondition;
import com.legend.board.model.dto.User;
import com.legend.board.model.service.CommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags = "댓글 컨트롤러")
public class CommentRestController {

	@Autowired
	private CommentService commentService;

	@GetMapping("/comment")
	@ApiOperation(value = "댓글목록 조회")
	public ResponseEntity<?> commentList(SearchCondition condition) {
		List<Comment> commentList = commentService.searchComment(condition);
		if (commentList == null || commentList.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Comment>>(commentList, HttpStatus.OK);
	}

	@PostMapping("/comment")
	@ApiOperation(value = "댓글 등록")
	public ResponseEntity<?> commentInsert(@RequestBody Comment comment) {
		User user = commentService.selectOneUser(comment.getUserId());
		if (user != null) {
			comment.setUserNickname(user.getUserNickname());
			commentService.insertComment(comment);
			commentService.increaseUserExp(comment.getUserId());
			return new ResponseEntity<Comment>(comment, HttpStatus.CREATED);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}

	@DeleteMapping("/comment/{commentNo}")
	@ApiOperation(value = "댓글 삭제")
	public ResponseEntity<Void> commentDelete(@PathVariable int commentNo) {
		if (commentService.getComment(commentNo) != null) {
			Comment comment = commentService.getComment(commentNo);
			commentService.decreaseUserExp(comment.getUserId());
			commentService.deleteComment(commentNo);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/comment")
	@ApiOperation(value = "댓글 수정")
	public ResponseEntity<Void> commentUpdate(@RequestBody Comment comment) {
		commentService.updateComment(comment);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/comment/list/{userId}")
	@ApiOperation(value = "유저의 댓글 목록 조회")
	public ResponseEntity<?> myCommentList(@PathVariable String userId) {
		List<Comment> commentList = commentService.searchMyCommentList(userId);
		return new ResponseEntity<List<Comment>>(commentList, HttpStatus.OK);
	}
}
