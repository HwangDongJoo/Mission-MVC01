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
		
		//�α��� ����
		if(userVO.getId() == null){
			//���н� �ٽ� �α��� �������� �Űܾ���
			msg = "���̵� �Ǵ� �н����尡 �߸��Ǿ����ϴ�.\\n�ٽ� �õ��� �ּ���";
			url = request.getContextPath() + "/loginForm.do";
		} else {	//�α��� ����
			//���� ���ǿ� ���
			//�α��� ������ index��
			msg = userVO.getName() + "�� ȯ���մϴ�.";
			url = request.getContextPath();
			HttpSession session = request.getSession();
			session.setAttribute("userVO", userVO);
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return "/jsp/member/login.jsp";
	}
	
}
