package com.legend.board.model.dto;

import io.swagger.annotations.ApiModelProperty;

public class Favorite {
	@ApiModelProperty(hidden = true)
	private int favoriteNo;
	private int boardNo;
	private String userId;
	
	public Favorite() {

	}

	public int getFavoriteNo() {
		return favoriteNo;
	}

	public void setFavoriteNo(int favoriteNo) {
		this.favoriteNo = favoriteNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
