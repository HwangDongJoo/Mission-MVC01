package kr.co.bit.controller;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.bit.board.dao.BoardDAO;
import kr.co.bit.board.vo.BoardFileVO;

public class DownloadController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		int fileNo = Integer.parseInt(request.getParameter("no"));

		BoardDAO dao = new BoardDAO();
		BoardFileVO fileVO = dao.selectFileByFileNo(fileNo);
		
		
		String fileName = fileVO.getFileSaveName();
		String filePath = "D:\\dongjoo\\eclipse-work\\wtpwebapps\\Mission-MVC01\\upload\\" + fileName;
		String oriName = fileVO.getFileOriName();
		
		System.out.println(filePath);
		
		File file = null;
		try{
			
			file = new File(filePath);
			byte b[] = new byte[1024*1024*3];
			
			response.reset();
			response.setContentType("application/octet-stream");
			
			String Encoding = new String(oriName.getBytes("utf-8"), "8859_1");
			response.setHeader("Content-Disposition", "attatchment; filename = " + Encoding);
			
			FileInputStream fis = new FileInputStream(file);
			ServletOutputStream sos = response.getOutputStream();
			
			int numRead = 0;
			while((numRead=fis.read(b, 0, b.length)) != -1){
				sos.write(b,0,numRead);
			}
			
			sos.flush();
			sos.close();
			fis.close();
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return "/jsp/board/download.jsp";
	}

}
