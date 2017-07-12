<%@page import="kr.co.bit.board.vo.BoardVO"%>
<%@page import="kr.co.bit.board.dao.BoardDAO"%>
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
<script>
	function doList(){
		location.href="/Mission-MVC01/list.do";
	}
</script>
</head>
<body>
	<header>
		<jsp:include page="/jsp/include/topMenu.jsp"></jsp:include>
	</header>
	<section>
		<div align="center">
			<hr/>
			<h2>게시판 수정</h2>
			<hr/>
			<form action="<%= request.getContextPath() %>/update.do" method="post">
			<input type="hidden" name="no" value="${ param.no }" />
			<table width="100%">
				<tr>
					<th width="25%">번호</th>
					<td>${ param.no }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" size="50" name="title" value="<c:out value='${ board.title }' />"/></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea rows="7" cols="50" name="content" >${ board.content }</textarea></td>
				</tr>
			</table><br/><br/>
			<input type="submit" value="수정"/>&nbsp;&nbsp;
			<input type="button" value="삭제" onclick="" />&nbsp;&nbsp;
			<input type="button" value="목록" onclick="doList()" />
			</form>
		</div>
	</section>
	<footer>
		<%@ include file="/jsp/include/bottom.jsp" %>
	</footer>
</body>
</html>