package com.legend.board.model.service;

import java.util.List;

import com.legend.board.model.dto.Board;
import com.legend.board.model.dto.Favorite;
import com.legend.board.model.dto.SearchCondition;
import com.legend.board.model.dto.User;

public interface FavoriteService {
	
	// 추천 목록 조회
	List<Favorite> searchFavorite(SearchCondition condition);
	
	// 추천 여부 확인
	Favorite checkFavorite(Favorite favorite);
	
	// 추천여부를 DB에 등록
	void insertFavorite(Favorite favorite);
	
	// 게시물 추천 카운트 증가
	void increaseFavCount(int boardNo);
	
	// 게시물 작성자 경험치 증가
	void increaseUserExp(String userId);
	
	// 추천여부를 DB에서 삭제
	void deleteFavorite(Favorite favorite);
	
	// 게시물 추천 카운트 감소
	void decreaseFavCount(int boardNo);
	
	// 게시물 작성자 경험치 회수(감소)
	void decreaseUserExp(String userId);
	
	// 유저 검색
	User selectOneUser(String userId);
	
	// 게시물 검색
	Board selectOneBoard(int boardNo);
}
