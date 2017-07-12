package kr.co.bit.controller;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.bit.board.dao.BoardDAO;
import kr.co.bit.board.vo.BoardFileVO;

public class DeleteController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		BoardDAO dao = new BoardDAO();
		//파일삭제
		List<BoardFileVO> list = dao.selectFileByNo(no);
		if(!list.isEmpty()){
			for(int i=0; i<list.size(); i++){
				String fileName = list.get(i).getFileSaveName();
				
				String filePath = "D:\\dongjoo\\eclipse-work\\wtpwebapps\\Mission-MVC01\\upload\\" + fileName;
				System.out.println(filePath);		
				
				File f = new File(filePath);
				if(f.exists()) f.delete();
			}
		}
		dao.deleteBoard(no);
		
		return "/jsp/board/delete.jsp";
	}

}
