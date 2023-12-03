package com.legend.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.legend.board.model.dto.Board;
import com.legend.board.model.dto.SearchCondition;
import com.legend.board.model.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags = "게시판 컨트롤러")
public class BoardRestController {

	@Autowired
	private BoardService boardService;

	@GetMapping("/board")
	@ApiOperation(value = "게시글 목록 조회")
	public ResponseEntity<?> boardList(SearchCondition condition) {
		System.out.println(condition.getKey());
		System.out.println(condition.getWord());
		List<Board> boardList = boardService.searchBoardList(condition);
		return new ResponseEntity<List<Board>>(boardList, HttpStatus.OK);
	}

	@GetMapping("/board/{boardNo}")
	@ApiOperation(value = "게시글 상세보기", notes = "게시글 상세보기시 조회수가 올라갑니다")
	public ResponseEntity<Board> boardDetail(@PathVariable int boardNo) {
		Board board = boardService.getBoard(boardNo);
		return new ResponseEntity<Board>(board, HttpStatus.OK);
	}

	@PostMapping("/board")
	@ApiOperation(value = "게시글 등록하기")
	public ResponseEntity<?> boardInsert(@RequestBody Board board) {
		String userNickname = boardService.selectOneUser(board.getUserId()).getUserNickname();
		board.setUserNickname(userNickname);
		boardService.insertBoard(board);
		boardService.increaseUserExp(board.getUserId());
		return new ResponseEntity<Board>(board, HttpStatus.CREATED);
	}

	@DeleteMapping("/board/{boardNo}")
	@ApiOperation(value = "게시글 삭제하기")
	public ResponseEntity<Void> boardDelete(@PathVariable int boardNo) {
		if (boardService.getBoard(boardNo) != null) {
			Board board = boardService.getBoard(boardNo);
			boardService.decreaseUserExp(board.getUserId());
			boardService.deleteBoard(boardNo);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/board")
	@ApiOperation(value = "게시글 수정하기")
	public ResponseEntity<Void> boardUpdate(@RequestBody Board board) {
		boardService.updateBoard(board);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/boardcount")
	@ApiOperation(value = "게시글 수 조회")
	public ResponseEntity<Integer> boardListCount(SearchCondition condition) {
		int boardCount = boardService.countBoardList(condition);
		return new ResponseEntity<Integer>(boardCount, HttpStatus.OK);
	}
	
	@GetMapping("/board/list/{userId}")
	@ApiOperation(value = "유저의 게시글 목록 조회")
	public ResponseEntity<?> myBoardList(@PathVariable String userId) {
		List<Board> boardList = boardService.searchMyBoardList(userId);
		return new ResponseEntity<List<Board>>(boardList, HttpStatus.OK);
	}
}
