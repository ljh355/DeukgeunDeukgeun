package com.legend.board.model.dto;

import io.swagger.annotations.ApiModelProperty;

public class Board {
	private int boardNo;
	private String userId;
	private String userNickname;
	private String boardClassification;
	private String boardTitle;
	private String boardContent;
	@ApiModelProperty(hidden = true)
	private String boardRegDate;
	@ApiModelProperty(hidden = true)
	private String boardOriginalImg;
	@ApiModelProperty(hidden = true)
	private String boardImg;
	@ApiModelProperty(hidden = true)
	private int boardViewCount;
	@ApiModelProperty(hidden = true)
	private int boardFavCount;
	@ApiModelProperty(hidden = true)
	private boolean boardModified;
	
	public Board() {

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

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getBoardClassification() {
		return boardClassification;
	}

	public void setBoardClassification(String boardClassification) {
		this.boardClassification = boardClassification;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardOriginalImg() {
		return boardOriginalImg;
	}

	public void setBoardOriginalImg(String boardOriginalImg) {
		this.boardOriginalImg = boardOriginalImg;
	}

	public String getBoardImg() {
		return boardImg;
	}

	public void setBoardImg(String boardImg) {
		this.boardImg = boardImg;
	}

	public int getBoardViewCount() {
		return boardViewCount;
	}

	public void setBoardViewCount(int boardViewCount) {
		this.boardViewCount = boardViewCount;
	}

	public String getBoardRegDate() {
		return boardRegDate;
	}

	public void setBoardRegDate(String boardRegDate) {
		this.boardRegDate = boardRegDate;
	}

	public int getBoardFavCount() {
		return boardFavCount;
	}

	public void setBoardFavCount(int boardFavCount) {
		this.boardFavCount = boardFavCount;
	}

	public boolean isBoardModified() {
		return boardModified;
	}

	public void setBoardModified(boolean boardModified) {
		this.boardModified = boardModified;
	}
}
