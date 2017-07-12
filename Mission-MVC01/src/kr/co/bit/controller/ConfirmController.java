package kr.co.bit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.bit.member.dao.MemberDAO;
import kr.co.bit.member.vo.MemberVO;

public class ConfirmController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");

		String id = (String)request.getAttribute("id");
		String pw = (String)request.getAttribute("pw");
		
		MemberDAO dao = new MemberDAO();
		MemberVO user = dao.loginSelect(id, pw);
		
		String msg = null;
		String url = null;
		if(user ==  null){
			msg = "아이디 또는 비밀번호가 잘못되었습니다.";
			url = request.getContextPath() + "/confirmForm.do";
		} else {
			msg = "확인되었습니다.";
			url = request.getContextPath() + "/updateMemberForm.do";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return "/jsp/member/confirm.jsp";
	}

}
