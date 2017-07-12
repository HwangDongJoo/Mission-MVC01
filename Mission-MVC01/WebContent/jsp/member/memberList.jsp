<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/layout.css"/>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/board.css"/>
</head>
<body>
	<header>
		<jsp:include page="/jsp/include/topMenu.jsp"></jsp:include>
	</header>
	<section>
		<div align="center">
		<hr width="100%"/>
		<h2>회원 목록</h2>
		<hr width="100%"/>
		<table border="1" width="100%">
			<tr>
				<th width="10%">ID</th>
				<th width="8%">이름</th>
				<th width="15%">Email</th>
				<th width="13%">전화번호</th>
				<th width="13%">우편번호</th>
				<th>주소</th>
				<th width="5%">타입</th>
				<th width="10%">가입일</th>
			</tr>
			<c:forEach items="${ list }" var="member">
				<tr>
					<td>${ member.id }</td>
					<td>${ member.name }</td>
					<td>${ member.emailId}${ member.emailDomain }</td>
					<td>${ member.tel1 }-${ member.tel2 }-${ member.tel3 }</td>
					<td>${ member.post }</td>
					<td>${ member.basicAddr }&nbsp;${ member.detailAddr }</td>
					<td>${ member.type }</td>
					<td>${ member.regDate }</td>
				</tr>
			</c:forEach>
		</table><br/>
	</div>
	</section>
	<footer>
		<%@ include file="/jsp/include/bottom.jsp" %>
	</footer>
</body>
</html>

