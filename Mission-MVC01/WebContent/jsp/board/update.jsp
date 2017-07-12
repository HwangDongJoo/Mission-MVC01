<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	alert("게시물 번호 ${param.no} 수정완료되었습니다.");
	location.href="/Mission-MVC01/detail.do?no=${param.no}&check=false";
</script>