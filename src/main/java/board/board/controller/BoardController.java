package board.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import board.board.dto.BoardDto;
import board.board.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	/*
	 * 게시판 조회
	 */
	@RequestMapping("/board/openBoardList.do")
	public ModelAndView openBoardList() throws Exception{
		ModelAndView mv = new ModelAndView("/board/boardList");
		
		List<BoardDto> list = boardService.selectBoardList();
		mv.addObject("list",list);
		
		return mv;
	}
	
	/*
	 * 게시판 작성 페이지 이동
	 */
	@RequestMapping("/board/openBoardWrite.do")
	public String openBoardWrite() throws Exception{
		return "/board/boardWrite";
	}
	
	/*
	 * 게시판 작성
	 */
	@RequestMapping("/board/insertBoard.do")
		public String insertBoard(BoardDto board) throws Exception{
			boardService.insertBoard(board);
			return "redirect:/board/openBoardList.do";
	}
	
	/*
	 * 게시판 상세조회
	 */
	@RequestMapping("/board/openBoardDetail.do")
	public ModelAndView openBoardDetail(@RequestParam int board_idx) throws Exception{
		
		ModelAndView mv = new ModelAndView("/board/boardDetail");
		
		BoardDto board = boardService.selectBoardDetail(board_idx);
		mv.addObject("board",board);
		
		return mv;
	}
}
