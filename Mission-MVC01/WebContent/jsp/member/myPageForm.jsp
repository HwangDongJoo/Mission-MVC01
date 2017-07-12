<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/layout.css"/>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/board.css"/>
<script>
	function updateMember(){
		location.href="/Mission-MVC01/confirmForm.do";
	}
</script>
</head>
<body>
	<header>
		<jsp:include page="/jsp/include/topMenu.jsp"></jsp:include>
	</header>
	<section>
		<div align="center">
		<hr width="100%"/>
		<h1>마이페이지</h1>
		<hr width="100%"/>
		</div>
		
		<div align="center">
		<table width="100%">
			<tr>
				<th width="30%">ID</th>
				<td>${ userVO.id }</td>
			<tr>
			<tr>
				<th>이름</th>
				<td>${ userVO.name }</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${ userVO.emailId }${ userVO.emailDomain }</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td>
					${ userVO.tel1 }
					&nbsp;-&nbsp; 
					${ userVO.tel2 }
					&nbsp;-&nbsp;
					${ userVO.tel3 }
				</td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td>${ userVO.post }</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>${ userVO.basicAddr }</td>
			</tr>
			<tr>
				<th>상세주소</th>
				<td>${ userVO.detailAddr }</td>
			</tr>
			</table>
			<br/>
			<input type="button" value="수정하기" onclick="updateMember()"/>
			</div>
	</section>
	<footer>
		<%@ include file="/jsp/include/bottom.jsp" %>
	</footer>
</body>
</html>