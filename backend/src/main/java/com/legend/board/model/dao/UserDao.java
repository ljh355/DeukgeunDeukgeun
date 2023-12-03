package com.legend.board.model.dao;

import java.util.List;

import com.legend.board.model.dto.SearchCondition;
import com.legend.board.model.dto.User;

public interface UserDao {

	public List<User> selectAllUser();

	public List<User> searchUser(SearchCondition condition);
	
	public User selectOneUser(String userId);

	public int insertUser(User user);
	
	public void deleteUser(String userId);
	
	public void updateUser(User user);
	
}