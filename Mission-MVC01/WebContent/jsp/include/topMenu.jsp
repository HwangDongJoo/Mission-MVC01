<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<style>
	img{
		height:45px;
	}
</style>
<script>
	function addFavorite(){
		try{
			window.external.AddFavorite('http://localhost:8000/Mission-MVC01', '두번째 나의 웹');
		} catch(e){
			alert("현재 브라우저에서는 사용할 수 없습니다.\n크롬에서는 ctrl+d를 사용해주세요");
		}
	}
</script>
<table border="1" width="100%">
	<tr>
		<td rowspan="2" style=" width:100px;">
			<a href="<%= request.getContextPath() %>">
				<img src="/Mission-MVC01/images/logo.jpg"/>
			</a></td>
		<td align="right" colspan="5">
		<!-- <a href="javascript:window.external.addFavorite('http://localhost:8000/Mission-Web', '첫번째 나의 웹')">즐겨찾기</a> -->
		<a href="#" onclick="addFavorite()">즐겨찾기</a>
		<c:if test="${ not empty userVO }">
			[${ sessionScope.userVO.id }님으로 로그인중]
		</c:if>
		</td>
		
	</tr>
	<tr>
		<c:if test="${ userVO.type eq 's' }">
			<td><a href="<%= request.getContextPath() %>/memberList.do">회원관리</a></td>
		</c:if>
		<td><a href="<%= request.getContextPath() %>/list.do">게시판</a></td>
		<c:if test="${ empty userVO }">
			<td><a href="<%= request.getContextPath() %>/joinForm.do">회원가입</a></td>
		</c:if>
		<c:if test="${ not empty userVO }">
			<td><a href="<%= request.getContextPath() %>/myPageForm.do">마이페이지</a></td>
		</c:if>
		<td>
		<c:choose>
			<c:when test="${ not empty userVO }">
				<a href="<%= request.getContextPath() %>/logout.do">로그아웃</a>
			</c:when>
			<c:otherwise>
				<a href="<%= request.getContextPath() %>/loginForm.do">로그인</a>
			</c:otherwise>
		</c:choose>
		</td>
	</tr>
</table>