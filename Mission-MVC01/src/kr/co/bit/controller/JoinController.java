package kr.co.bit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.bit.member.dao.MemberDAO;

public class JoinController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("pw");
		String emailId = request.getParameter("emailId");
		String emailDomain[] = request.getParameterValues("emailDomain");
		String tel1[] = request.getParameterValues("tel1");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		String post = request.getParameter("post");
		String basicAddr = request.getParameter("basicAddr");
		String detailAddr = request.getParameter("detailAddr");
		
		MemberDAO dao = new MemberDAO();
		dao.insertMember(id, name, password, emailId, emailDomain[0], tel1[0], tel2, tel3, post, basicAddr, detailAddr);
		
		return "/jsp/member/join.jsp";
	}

}
