<%@page import="kr.co.bit.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.bit.board.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.co.bit.util.ConnectionFacotry" %>
<%@ page import="java.sql.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	function goWriteForm(){
		
		//location.href = "/Mission-Web/jsp/board/writeForm.jsp";
		<c:choose>
			<c:when test="${ not empty userVO }">
				location.href = "/Mission-MVC01/writeForm.do";
			</c:when>
			<c:otherwise>
				if(confirm("로그인 후 사용할 수 있습니다.\n로그인페이지로 이동하시겠습니까?")){
					location.href = "/Mission-MVC01/loginForm.do";
				}
			</c:otherwise>
		</c:choose>
	}
	
	function doAction(boardNo){
		<c:choose>
			<c:when test="${ not empty userVO }">
				location.href="/Mission-MVC01/detail.do?no="+boardNo+"&check=true";
			</c:when>
			<c:otherwise>
				if(confirm("로그인 후 사용할 수 있습니다.\n로그인페이지로 이동하시겠습니까?")){
					location.href = "/Mission-MVC01/loginForm.do";;
				}
			</c:otherwise>
		</c:choose>
	}
</script>


<link rel="stylesheet" href="/Mission-MVC01/css/layout.css"/>
<link rel="stylesheet" href="/Mission-MVC01/css/board.css"/>
</head>
<body>
	<header>
		<jsp:include page="/jsp/include/topMenu.jsp"></jsp:include>
	</header>
	<section>
		<div align="center">
	<br/>
	<hr width="100%"/>
		<h2>게시판 목록</h2>
	<hr width="100%"/>
	<br/>
	
	<table border="1" width="100%" class="list">
		<tr>
			<th width="7%">번호</th>
			<th>제목</th>
			<th width="16%">작성자</th>
			<th width="20%">등록일</th>
		</tr>
		<c:forEach items="${ list }" var="board" varStatus="loop">
			<tr <c:if test="${ loop.count mod 2 == 0 }">class="even" </c:if>>
				<td>${ board.no }</td>
				<td>
					<input type="hidden" name="check" value="true"/>
					<!-- href="detail.jsp?no=${ board.no }&check=true" -->
					<a href="javascript:doAction('${ board.no }')">
						<c:out value="${ board.title }" />
					</a>
				</td>
				<td>${ board.writer }</td>
				<td>${ board.regDate }</td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<input type="button" value="새글등록" onclick="goWriteForm()"/>
	</div>
	</section>
	<footer>
		<%@ include file="/jsp/include/bottom.jsp" %>
	</footer>
</body>
</html>