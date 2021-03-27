package com.springbook.view.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

public class GetBoardListController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("getBoardList");
		
		BoardVO vo = new BoardVO();
		BoardDAO boardDAO = new BoardDAO();
		List<BoardVO> boardList = boardDAO.getBoardList(vo);
		
		
		HttpSession session = request.getSession();
		session.setAttribute("boardList", boardList);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList", boardList); // boardList Model 정보 저장
		mav.setViewName("getBoardList");   // 이동할 페이지의 View 정보 저장
		return mav;
	}
}
