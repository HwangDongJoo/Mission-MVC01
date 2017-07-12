package kr.co.bit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.bit.board.dao.BoardDAO;
import kr.co.bit.board.vo.BoardFileVO;
import kr.co.bit.board.vo.BoardVO;

public class DetailController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int no = Integer.parseInt(request.getParameter("no"));
		boolean check = Boolean.parseBoolean(request.getParameter("check"));

		BoardDAO dao = new BoardDAO();
		dao.updateViewCnt(no, check);	//조회수 증가
		BoardVO board = dao.selectByNo(no);	//화면에 출력할 데이터 BoardVO에 저장
		
		List<BoardFileVO> fileList = dao.selectFileByNo(no);	//첨부파일조회
		
		request.setAttribute("board", board);
		request.setAttribute("fileList", fileList);
		
		return "/jsp/board/detail.jsp";
	}

}
