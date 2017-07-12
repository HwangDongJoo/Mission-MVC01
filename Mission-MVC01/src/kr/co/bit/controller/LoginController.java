package kr.co.bit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.bit.member.dao.MemberDAO;
import kr.co.bit.member.vo.MemberVO;

public class LoginController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		/*MemberVO memberVO = new MemberVO();
		memberVO.setId(id);
		memberVO.setPassword(pw);*/
		
		MemberDAO dao = new MemberDAO();
		MemberVO userVO = dao.loginSelect(id, pw);
		
		String msg = "";
		String url = "";
		
		//로그인 실패
		if(userVO.getId() == null){
			//실패시 다시 로그인 페이지로 옮겨야함
			msg = "아이디 또는 패스워드가 잘못되었습니다.\\n다시 시도해 주세요";
			url = request.getContextPath() + "/loginForm.do";
		} else {	//로그인 성공
			//정보 세션에 등록
			//로그인 성공시 index로
			msg = userVO.getName() + "님 환영합니다.";
			url = request.getContextPath();
			HttpSession session = request.getSession();
			session.setAttribute("userVO", userVO);
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return "/jsp/member/login.jsp";
	}
	
}
