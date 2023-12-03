package com.legend.board.model.dto;

import io.swagger.annotations.ApiModelProperty;

public class Comment {
	private int commentNo;
	private int boardNo;
	private String userId;
	private String userNickname;
	private String commentContent;
	@ApiModelProperty(hidden = true)
	private String commentRegDate;
	@ApiModelProperty(hidden = true)
	private String commentLikeCount;
	@ApiModelProperty(hidden = true)
	private boolean commentModified;

	public Comment() {

	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
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

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getCommentRegDate() {
		return commentRegDate;
	}

	public void setCommentRegDate(String commentRegDate) {
		this.commentRegDate = commentRegDate;
	}

	public String getCommentLikeCount() {
		return commentLikeCount;
	}

	public void setCommentLikeCount(String commentLikeCount) {
		this.commentLikeCount = commentLikeCount;
	}

	public boolean isCommentModified() {
		return commentModified;
	}

	public void setCommentModified(boolean commentModified) {
		this.commentModified = commentModified;
	}


}
