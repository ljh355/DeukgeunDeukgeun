package com.legend.board.model.service;

import java.util.List;

import com.legend.board.model.dto.Comment;
import com.legend.board.model.dto.SearchCondition;
import com.legend.board.model.dto.User;

public interface CommentService {
	
	// 댓글 전체 조회
	List<Comment> getCommentList();
	
	// 댓글 조건에 따라 조회
	List<Comment> searchComment(SearchCondition condition);
	
	// 댓글 상세 조회
	Comment getComment(int commentNo);

	// 댓글 등록
	void insertComment(Comment comment);

	// 댓글 수정
	void updateComment(Comment comment);

	// 댓글 삭제
	void deleteComment(int commentNo);
	
	// 댓글 쓴 사람 경험치 증가
	void increaseUserExp(String userId);
	
	// 댓글 쓴 사람 경험치 감소
	void decreaseUserExp(String userId);
	
	// 유저 검색
	User selectOneUser(String userId);

	// 유저의 댓글 조회
	List<Comment> searchMyCommentList(String userId);
}
