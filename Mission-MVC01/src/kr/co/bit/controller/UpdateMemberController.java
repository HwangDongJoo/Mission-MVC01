package kr.co.bit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.bit.member.dao.MemberDAO;
import kr.co.bit.member.vo.MemberVO;

public class UpdateMemberController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");

		String id = (String)request.getParameter("id");
		String name = (String)request.getParameter("name");
		String password = (String)request.getParameter("pw");
		String emailId = (String)request.getParameter("emailId");
		String emailDomain = (String)request.getParameter("emailDomain");
		String tel1 = (String)request.getParameter("tel1");
		String tel2 = (String)request.getParameter("tel2");
		String tel3 = (String)request.getParameter("tel3");
		String post = (String)request.getParameter("post");
		String basicAddr = (String)request.getParameter("basicAddr");
		String detailAddr = (String)request.getParameter("detailAddr");

		MemberDAO dao = new MemberDAO();
		dao.updateMember(id, name, password, emailId, emailDomain, tel1, tel2, tel3, post, basicAddr, detailAddr);
		
		MemberVO userVO = dao.loginSelect(id, password);
		HttpSession session = request.getSession();
		session.setAttribute("userVO", userVO);
		
		return "/jsp/member/updateMember.jsp";
	}

}
