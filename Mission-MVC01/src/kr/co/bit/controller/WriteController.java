package kr.co.bit.controller;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import kr.co.bit.board.dao.BoardDAO;
import kr.co.bit.board.vo.BoardFileVO;
import kr.co.bit.board.vo.BoardVO;
import kr.co.bit.util.BitFileNamePolicy;

public class WriteController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");

		String saveFolder = "D:\\dongjoo\\web-workspace\\Mission-MVC01\\WebContent\\upload";
		
		MultipartRequest multi = new MultipartRequest(
				request,
				saveFolder,
				1024 * 1024 * 3,
				"utf-8",
				new BitFileNamePolicy()
				);
		
		String title = multi.getParameter("title");
		String writer = multi.getParameter("writer");
		String content = multi.getParameter("content");
		
		BoardDAO dao = new BoardDAO();
		
		//0. 등록할 게시글의 번호(seq_t_board_no) 를 추출
		int no = dao.selectNo();
		
		//1. 게시글 저장
		BoardVO board = new BoardVO();
		board.setNo(no);
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);

		
		dao.insertBoard(board);
		
		//2. 첨부파일 저장
		BoardFileVO boardFile = new BoardFileVO();
		Enumeration files = multi.getFileNames();
		
		while(files.hasMoreElements()){
			String fileName = (String)files.nextElement();
			System.out.println(fileName);
			
			File f = multi.getFile(fileName);
			if(f != null){
				
				String fileOriName = multi.getOriginalFileName(fileName);
				String fileSaveName = multi.getFilesystemName(fileName);
				int fileSize = (int)f.length();
				
				BoardFileVO fileVO = new BoardFileVO();
				fileVO.setBoradNo(no);
				fileVO.setFileOriName(fileOriName);
				fileVO.setFileSaveName(fileSaveName);
				fileVO.setFileSize(fileSize);
				
				System.out.println(fileVO);
				
				//DB에 저장
				dao.insertFile(fileVO);
			}
		}
		
		return "/jsp/board/write.jsp";
	}
	
}
