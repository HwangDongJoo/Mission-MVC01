<%@page import="kr.co.bit.member.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String id = request.getParameter("id");
	MemberDAO dao = new MemberDAO();
	int result = dao.checkID(id);
	
	pageContext.setAttribute("result", result);
%>
<c:if test="${ result == 1 }">
	중복된 ID 입니다.
</c:if>
<c:if test="${ result == 0 }">
	사용가능한 ID 입니다.
</c:if>