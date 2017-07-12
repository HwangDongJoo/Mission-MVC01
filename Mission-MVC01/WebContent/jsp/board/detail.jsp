<%@page import="kr.co.bit.board.vo.BoardFileVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.bit.board.vo.BoardVO"%>
<%@page import="kr.co.bit.board.dao.BoardDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.co.bit.util.ConnectionFacotry"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
	function onAction(type){
		switch(type){
		case 'U':
			location.href="/Mission-MVC01/updateForm.do?no=${ param.no }";
			break;
		case 'D':
			console.log(${(userVO.name eq '관리자') || (userVO.name eq board.writer)});
			if(${(userVO.name eq '관리자') || (userVO.name eq board.writer)}){
				if(confirm("${ param.no }번 게시물을 삭제하시겠습니까?"))
					location.href="/Mission-MVC01/delete.do?no=${ param.no }";
			}
			break;
		case 'L':
			location.href="/Mission-MVC01/list.do";
			break;
		}
	}
</script>
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
	<h2>상세 페이지</h2>
	<hr width="100%"/>
	<table border='1' width="100%">
		<tr>
			<th width="25%">번호</th>
			<td>${ board.no }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td><c:out value="${ board.title }"/></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${ board.writer }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><c:out value="${ board.content }"/></td>
		</tr>
		<tr>
			<th width="25%">조회수</th>
			<td>${ board.viewCnt }</td>
		</tr>
		<tr>
			<th>등록일</th>
			<td>${ board.regDate }</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<c:forEach items="${ fileList }" var="file">
					<a href="<%= request.getContextPath() %>/download.do?no=${ file.no }">
					${ file.fileOriName }(${ file.fileSize } bytes)<br/>
					</a>
				</c:forEach>
			</td>
		</tr>
	</table>
	<br/><br/>
	<input type="button" value="목록" onclick="onAction('L')" />&nbsp;&nbsp;
	<input type="button" value="수정" onclick="onAction('U')" />&nbsp;&nbsp;
	<input type="button" value="삭제" onclick="onAction('D')" />
	</div>
	</section>
	<footer>
		<%@ include file="/jsp/include/bottom.jsp" %>
	</footer>
</body>
</html>
