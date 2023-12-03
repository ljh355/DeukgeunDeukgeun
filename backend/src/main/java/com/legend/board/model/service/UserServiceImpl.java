package com.legend.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.legend.board.model.dao.UserDao;
import com.legend.board.model.dto.SearchCondition;
import com.legend.board.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	
	@Override
	public List<User> getUserList() {
		return userDao.selectAllUser();
	}
	
	@Override
	public User selectOneUser(String userId) {
		return userDao.selectOneUser(userId);
	}

	@Override
	public int signUpUser(User user) {
		return userDao.insertUser(user);
	}

	@Override
	public User loginUser(User user) {
		User dbUser = userDao.selectOneUser(user.getUserId());
		if(dbUser != null && dbUser.getUserPassword().equals(user.getUserPassword()))
			return dbUser;
		else return null;
	}
	
	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public void removeUser(String userId) {
		userDao.deleteUser(userId);
	}

	@Override
	public List<User> searchUser(SearchCondition condition) {
		return userDao.searchUser(condition);
	}
}
