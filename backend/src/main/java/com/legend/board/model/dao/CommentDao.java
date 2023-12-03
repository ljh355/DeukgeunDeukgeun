package com.legend.board.model.dao;

import java.util.List;

import com.legend.board.model.dto.Comment;
import com.legend.board.model.dto.SearchCondition;
import com.legend.board.model.dto.User;

public interface CommentDao {
	public List<Comment> selectAllComment();

	public Comment selectOneComment(int commentNo);

	public void insertComment(Comment comment);

	public void deleteComment(int commentNo);

	public void updateComment(Comment comment);

	public List<Comment> searchComment(SearchCondition condition);
	
	// 유저 검색
	public User selectOneUser(String userId);
	
	// 댓글 쓴 유저 경험치 증가
	public void increaseUserExp(String userId);

	// 댓글 쓴 유저 경험치 감소
	public void decreaseUserExp(String userId);

	// 유저의 댓글 조회
	public List<Comment> searchMyCommentList(String userId);
}
