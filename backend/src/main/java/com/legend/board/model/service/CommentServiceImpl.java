package com.legend.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.legend.board.model.dao.CommentDao;
import com.legend.board.model.dto.Comment;
import com.legend.board.model.dto.SearchCondition;
import com.legend.board.model.dto.User;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentDao commentDao;

	@Autowired
	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	@Override
	public List<Comment> getCommentList() {
		return commentDao.selectAllComment();
	}

	@Override
	public List<Comment> searchComment(SearchCondition condition) {
		return commentDao.searchComment(condition);
	}

	@Override
	public Comment getComment(int id) {
		return commentDao.selectOneComment(id);
	}

	@Transactional
	@Override
	public void insertComment(Comment comment) {
		commentDao.insertComment(comment);
	}

	@Transactional
	@Override
	public void updateComment(Comment comment) {
		commentDao.updateComment(comment);
	}

	@Override
	public void deleteComment(int id) {
		commentDao.deleteComment(id);
	}

	@Override
	public void increaseUserExp(String userId) {
		commentDao.increaseUserExp(userId);
	}

	@Override
	public void decreaseUserExp(String userId) {
		commentDao.decreaseUserExp(userId);
	}
	
	@Override
	public User selectOneUser(String userId) {
		return commentDao.selectOneUser(userId);
	}

	@Override
	public List<Comment> searchMyCommentList(String userId) {
		return commentDao.searchMyCommentList(userId);
	}
}
