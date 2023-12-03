package com.legend.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.legend.board.model.dto.Board;
import com.legend.board.model.dto.Favorite;
import com.legend.board.model.dto.SearchCondition;
import com.legend.board.model.service.FavoriteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags = "좋아요 컨트롤러")
public class FavoriteRestController {

	@Autowired
	private FavoriteService favoriteService;

	@GetMapping("/fav")
	@ApiOperation(value = "좋아요 조회")
	public ResponseEntity<?> favoriteList(SearchCondition condition) {
		List<Favorite> favList = favoriteService.searchFavorite(condition);
		if (favList == null || favList.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Favorite>>(favList, HttpStatus.OK);
	}

	@PostMapping("/fav")
	@ApiOperation(value = "좋아요 확인 후 실행")
	public ResponseEntity<?> favoriteCheck(@RequestBody Favorite favorite) {
		String userId = favorite.getUserId();
		int boardNo = favorite.getBoardNo();
		// userId 정보가 없을 경우 : -1 반환
		// boardNo 정보가 없을 경우(글이 없는데도 추천 시도) : 400 Bad Request
		if (favoriteService.selectOneUser(userId) != null) {
			if (favoriteService.selectOneBoard(boardNo) != null) {
				Board board = favoriteService.selectOneBoard(boardNo);

				// 자신이 쓴 글을 추천하려는 경우(자추) : 3 반환
				// 이미 추천한 글을 다시 추천하려는 경우(재추천은 추천 취소) : 2 반환
				// 아직 추천하지 않은 글을 추천하는 경우 : 1 반환

				if (userId.equals(board.getUserId()))
					return new ResponseEntity<Integer>(3, HttpStatus.OK);

				else if (favoriteService.checkFavorite(favorite) != null) {
					favoriteService.deleteFavorite(favorite); // favorite 정보를 DB에서 지움
					favoriteService.decreaseFavCount(boardNo); // 게시글의 favorite 수 1 감소
					// favoriteService.decreaseUserExp(board.getUserId()); // 게시글 작성 유저의 경험치 감소
					return new ResponseEntity<Integer>(2, HttpStatus.OK);
				} else {
					favoriteService.insertFavorite(favorite); // favorite 정보를 DB에 등록함
					favoriteService.increaseFavCount(boardNo); // 게시글의 favorite 수 1 증가
					// favoriteService.increaseUserExp(board.getUserId()); // 게시글 작성 유저의 경험치 증가
					return new ResponseEntity<Integer>(1, HttpStatus.CREATED);
				}
			} else
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Integer>(-1, HttpStatus.OK);
	}
}
