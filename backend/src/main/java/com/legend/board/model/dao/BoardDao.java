package com.legend.board.model.dao;

import java.util.List;

import com.legend.board.model.dto.Board;
import com.legend.board.model.dto.SearchCondition;
import com.legend.board.model.dto.User;

public interface BoardDao {
	
	// 게시글 목록 전체 불러오기
	public List<Board> selectAllBoard();
	
	// 게시글 목록 조건에 맞춰서 불러오기
	public List<Board> searchBoardList(SearchCondition condition);

	// 게시글 하나 불러오기 (조회수 상승)
	public Board selectOneBoard(int boardNo);
	
	// 게시글 읽을 때 조회수 1 상승시키기
	public void updateViewCount(int boardNo);

	// 게시글 등록하기
	public void insertBoard(Board board);

	// 게시글 수정하기
	public void updateBoard(Board board);
	
	// 게시글 삭제하기
	public void deleteBoard(int boardNo);
	
	// 유저 검색
	public User selectOneUser(String userId);
	
	// 글쓴 유저 경험치 증가
	public void increaseUserExp(String userId);

	// 글쓴 유저 경험치 감소
	public void decreaseUserExp(String userId);

	// 게시물 수 조회
	public int countBoardList(SearchCondition condition);
	
	// 유저의 게시물 조회
	public List<Board> searchMyBoardList(String userId);
}
