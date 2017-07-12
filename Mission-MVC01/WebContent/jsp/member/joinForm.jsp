<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="/Mission-MVC01/js/jquery-3.2.1.min.js"></script>
<script>

	$(document).ready(function(){
			
		$('#id').keyup(function(){
		
			$.ajax({
				url: "/Mission-MVC01/jsp/member/checkId.jsp",
				type: "GET",
				data: {'id': $('#id').val()},
				success: callback
			});
		});
	});
	
	function callback(data){
		console.log(data);
		$("#checkid").html(data);
	}
	
	function joinCancel(){
		location.href="memberList.jsp";
	}
	
	function joinMember(){
		var temp = document.joinForm;
		if(temp.id.value == ""){
			alert("ID를 입력하세요");
			temp.id.focus();
			return false;
		}
		if(temp.name.value==""){
			alert("이름을 입력하세요");
			temp.name.focus();
			return false;
		}
		if(temp.pw.value==""){
			alert("비밀번호를 입력하세요");
			temp.pw.focus()
			return false;
		}
		if(temp.repw.value==""){
			alert("비밀번호 확인을 입력하세요");
			temp.repw.focus();
			return false;
		}
		if(temp.pw.value!=temp.repw.value){
			alert("비밀번호가 일치하지 않습니다. 비밀번호를 확인하세요");
			temp.pw.focus();
			return false;
		}
		
		return true;
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
	<hr width="100%"/>
	<h2 align="center">회원 가입 하기</h2>
	<hr width="100%"/>
	</div>
		
	<form action="<%= request.getContextPath() %>/join.do" method="post" name="joinForm" onsubmit="return joinMember()">
	<div align="center">
	<table width="100%">
		<tr>
			<th width="30%">ID</th>
			<td><input type="text" name="id" id="id"/><span id="checkid"></span></td>
		<tr>
		<tr>
			<th>이름</th>
			<td><input type="text" name="name"/></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="password" name="pw"/></td>
		</tr>
		<tr>
			<th>비밀번호 확인</th>
			<td><input type="password" name="repw"/></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input type="text" name="emailId"/>
				&nbsp;&nbsp;
				<select name="emailDomain">
					<option value="">도메인</option>
					<option value="@naver.com">네이버</option>
					<option value="@daum.net">다음</option>
					<option value="@nate.com">네이트</option>
					<option value="@gmail.com">구글</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>
				<select name="tel1">
					<option value="">지역번호</option>
					<option value="010">010</option>
					<option value="02">02</option>
					<option value="031">031</option>
					<option value="032">032</option>
				</select>
				&nbsp;-&nbsp; 
				<input type="text" name="tel2" size="4"/>
				&nbsp;-&nbsp;
				<input type="text" name="tel3" size="4"/>
			</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td><input type="text" name="post" size="7"/></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><input type="text" name="basicAddr" size="100"/></td>
		</tr>
		<tr>
			<th>상세주소</th>
			<td><input type="text" name="detailAddr" size="100"/></td>
		</tr>
		</table>
		<br/>
		<input type="submit" value="가입하기" />
		<input type="button" value="취소" onclick="joinCancel()"/>
		</div>
	</form>
	</section>
	<footer>
		<%@ include file="/jsp/include/bottom.jsp" %>
	</footer>
</body>
</html>
