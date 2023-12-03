package com.legend.board.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.legend.board.model.dto.SearchCondition;
import com.legend.board.model.dto.User;
import com.legend.board.model.service.UserService;
import com.legend.board.util.JwtUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags = "유저 컨트롤러")
public class UserRestController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtil jwtUtil;

	@GetMapping("user/{userId}")
	@ApiOperation(value = "유저 조회")
	public ResponseEntity<?> searchOneUser(@PathVariable String userId) {
		User user = userService.selectOneUser(userId);
		if (user == null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		user.setUserPassword("");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping("users")
	@ApiOperation(value = "유저 목록조회")
	public ResponseEntity<?> userList(SearchCondition condition) {
		List<User> userList = userService.searchUser(condition);
		if (userList == null || userList.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}

	@PostMapping("signup")
	@ApiOperation(value = "회원가입")
	public ResponseEntity<Integer> userSignUp(@RequestBody User user) {
		user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
		int result = userService.signUpUser(user);
		return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	@ApiOperation(value = "로그인")
	public ResponseEntity<Map<String, Object>> login(@RequestBody User user, HttpSession session)
			throws UnsupportedEncodingException {
		Map<String, Object> result = new HashMap<String, Object>();
		HttpStatus status = null;

		if (user.getUserId() != null && userService.selectOneUser(user.getUserId()) != null) {
			if (passwordEncoder.matches(user.getUserPassword(),
					userService.selectOneUser(user.getUserId()).getUserPassword())) {
				
				User loginUser = userService.selectOneUser(user.getUserId());
				result.put("access-token", jwtUtil.createToken("id", user.getUserId()));
				result.put("login-userid", loginUser.getUserId());
				result.put("login-usernickname", loginUser.getUserNickname());
				result.put("message", "SUCCESS");
				status = HttpStatus.ACCEPTED;
			}
		} else {
			result.put("message", "FAIL");
			status = HttpStatus.NO_CONTENT;
		}

		return new ResponseEntity<Map<String, Object>>(result, status);
	}

	@GetMapping("logout")
	@ApiOperation(value = "로그아웃")
	public ResponseEntity<Void> userLogout() {
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PutMapping("update")
	@ApiOperation(value = "회원정보 수정")
	public ResponseEntity<Void> userUpdate(User user) {
		System.out.println("1" + user.getUserId());
		System.out.println("2" + user.getUserNickname());
		userService.updateUser(user);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping("delete")
	@ApiOperation(value = "탈퇴")
	public ResponseEntity<Void> userDelete(String userId) {
		userService.removeUser(userId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}