package com.legend.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.legend.board.model.dao.BoardDao;
import com.legend.board.model.dto.Board;
import com.legend.board.model.dto.SearchCondition;
import com.legend.board.model.dto.User;

@Service
public class BoardServiceImpl implements BoardService {

	private BoardDao boardDao;
	
	@Autowired
	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	@Override
	public List<Board> getBoardList() {
		return boardDao.selectAllBoard();
	}
	
	@Override
	public List<Board> searchBoardList(SearchCondition condition) {
		return boardDao.searchBoardList(condition);
	}

	@Override
	public Board getBoard(int boardNo) {
		boardDao.updateViewCount(boardNo);
		return boardDao.selectOneBoard(boardNo);
	}
	
	@Transactional
	@Override
	public void insertBoard(Board board) {
		boardDao.insertBoard(board);
	}

	@Transactional
	@Override
	public void updateBoard(Board board) {
		boardDao.updateBoard(board);
	}

	@Override
	public void deleteBoard(int boardNo) {
		boardDao.deleteBoard(boardNo);
	}

	@Override
	public void increaseUserExp(String userId) {
		boardDao.increaseUserExp(userId);
	}

	@Override
	public void decreaseUserExp(String userId) {
		boardDao.decreaseUserExp(userId);
	}

	@Override
	public User selectOneUser(String userId) {
		return boardDao.selectOneUser(userId);
	}

	@Override
	public int countBoardList(SearchCondition condition) {
		return boardDao.countBoardList(condition);
	}

	@Override
	public List<Board> searchMyBoardList(String userId) {
		return boardDao.searchMyBoardList(userId);
	}
}
