<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="<%= request.getContextPath() %>/js/checkForm.js"></script>
<script>
	function checkInput(){
		var check = document.confirmForm;
		
		if(isNull(check.pw, "패스워드를 입력하세요")){
			return false;
		}
		return true;
	}
</script>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/layout.css"/>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/board.css"/>
</head>
<body>
	<header>
		<jsp:include page="/jsp/include/topMenu.jsp"></jsp:include>
	</header>
	<section>
		<div align="center">
			<hr/>
			<h1>회원확인</h1>
			<hr/>
		</div>
		<div align="center">
			<form action="<%= request.getContextPath() %>/confirm.do" method="post" onsubmit="return checkInput()" name="confirmForm">
				<table width="80%">
					<tr>
						<th width="50%">아이디</th>
						<td><input type="text" name="id" readonly="readonly" value="${ userVO.id }"/></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="pw"/></td>
					</tr>
				</table>
				<br/><br/>
				<input type="submit" value="로그인" />
			</form>
		</div>
	</section>
	<footer>
		<%@ include file="/jsp/include/bottom.jsp" %>
	</footer>
</body>
</html>