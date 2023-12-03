package com.legend.board.model.service;

import java.util.List;

import com.legend.board.model.dto.Board;
import com.legend.board.model.dto.SearchCondition;
import com.legend.board.model.dto.User;

public interface BoardService {
	
	// 게시글 전체 조회
	List<Board> getBoardList();
	
	// 게시글 검색
	List<Board> searchBoardList(SearchCondition condition);
	
	// 게시글 상세 조회
	Board getBoard(int boardNo);

	// 게시글 등록
	void insertBoard(Board board);

	// 게시글 수정
	void updateBoard(Board board);

	// 게시글 삭제
	void deleteBoard(int boardNo);
		
	// 글쓴이 경험치 증가
	void increaseUserExp(String userId);
	
	// 글쓴이 경험치 감소
	void decreaseUserExp(String userId);
	
	// 유저 검색
	User selectOneUser(String userId);

	// 게시물 수 조회
	int countBoardList(SearchCondition condition);
	
	// 유저의 게시물 조회
	List<Board> searchMyBoardList(String userId);
}
