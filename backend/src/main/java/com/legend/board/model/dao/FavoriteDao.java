package com.legend.board.model.dao;

import java.util.List;

import com.legend.board.model.dto.Board;
import com.legend.board.model.dto.Favorite;
import com.legend.board.model.dto.SearchCondition;
import com.legend.board.model.dto.User;

public interface FavoriteDao {
	
	// 게시글 추천 조회
	public List<Favorite> searchFavorite(SearchCondition condition);
	
	// 게시글 추천 여부 확인후 추천/추천취소
	public Favorite checkFavorite(Favorite favorite);
	
	// 게시글 추천 DB에 등록
	public void insertFavorite(Favorite favorite);
	
	// 게시글 추천 DB에서 삭제
	public void deleteFavorite(Favorite favorite);
	
	// 게시글 추천수 증가
	public void increaseFavCount(int boardNo);
	
	// 게시글 추천수 감소
	public void decreaseFavCount(int boardNo);
	
	// 유저 경험치 증가
	public void increaseUserExp(String userId);
	
	// 유저 경험치 감소
	public void decreaseUserExp(String userId);
	
	// 유저 검색
	public User selectOneUser(String userId);
	
	// 게시글 검색
	public Board selectOneBoard(int boardNo);
}
