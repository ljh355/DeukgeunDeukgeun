package com.legend.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.legend.board.model.dao.FavoriteDao;
import com.legend.board.model.dto.Board;
import com.legend.board.model.dto.Favorite;
import com.legend.board.model.dto.SearchCondition;
import com.legend.board.model.dto.User;

@Service
public class FavoriteServiceImpl implements FavoriteService {

	private FavoriteDao favoriteDao;

	@Autowired
	public void setFavoriteDao(FavoriteDao favoriteDao) {
		this.favoriteDao = favoriteDao;
	}

	@Override
	public List<Favorite> searchFavorite(SearchCondition condition) {
		return favoriteDao.searchFavorite(condition);
	}

	@Override
	public Favorite checkFavorite(Favorite favorite) {
		return favoriteDao.checkFavorite(favorite);
	}

	@Override
	public void insertFavorite(Favorite favorite) {
		favoriteDao.insertFavorite(favorite);
	}

	@Override
	public void increaseFavCount(int boardNo) {
		favoriteDao.increaseFavCount(boardNo);
	}

	@Override
	public void increaseUserExp(String userId) {
		favoriteDao.increaseUserExp(userId);
	}

	@Override
	public void deleteFavorite(Favorite favorite) {
		favoriteDao.deleteFavorite(favorite);
	}

	@Override
	public void decreaseFavCount(int boardNo) {
		favoriteDao.decreaseFavCount(boardNo);
	}

	@Override
	public void decreaseUserExp(String userId) {
		favoriteDao.decreaseUserExp(userId);
	}

	@Override
	public User selectOneUser(String userId) {
		return favoriteDao.selectOneUser(userId);
	}

	@Override
	public Board selectOneBoard(int boardNo) {
		return favoriteDao.selectOneBoard(boardNo);
	}
}
