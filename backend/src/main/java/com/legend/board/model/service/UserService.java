package com.legend.board.model.service;

import java.util.List;

import com.legend.board.model.dto.SearchCondition;
import com.legend.board.model.dto.User;

public interface UserService {

	List<User> getUserList();
	
	User selectOneUser(String userId);

	int signUpUser(User user);

	User loginUser(User user);

	void updateUser(User user);

	void removeUser(String userId);
	
	List<User> searchUser(SearchCondition condition);
}