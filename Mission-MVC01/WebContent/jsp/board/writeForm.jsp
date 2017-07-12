<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	function doList(){
		location.href = request.getContextPath() + "/list.do";
	}
	function doWrite(){
		var w = document.writeForm;
		if(w.title.value == ""){
			alert('제목을 입력하세요');
			w.title.focus();
			return false;
		}
		/* 
		if(w.writer.value == ""){
			alert('작성자을 입력하세요');
			w.writer.focus();
			return false;
		} */
		
		if(w.content.value == ""){
			alert('내용을 입력하세요');
			w.content.focus();
			return false;
		}
		//파일 확장자 체크
		if(checkExt(w.attachfile1)){
			return false;
		}
		if(checkExt(w.attachfile2)){
			return false;
		}
		return true;
	}
	
	function checkExt(obj){
		var forbidName=['exe', 'java', 'jsp', 'js', 'class', 'css'];
		var fileName = obj.value;
		var ext = fileName.substring(fileName.lastIndexOf('.')+1);
		console.log(ext);
		
		for(var i = 0; i<forbidName.length; i++){
			if(forbidName[i] == ext){
				alert(ext + " 확장자는 파일업로드 정책에 위배됩니다.");
				return true;
			}
		}
		return false;
	}
	
</script>
</head>
<body>
	<div align="center">
	<hr width="80%"/>
	<h2>새글 등록</h2>
	<hr width="80%"/>
	<br/>
	
	<form action="write.do" method="post" onsubmit="return doWrite()" name="writeForm" enctype="multipart/form-data">
		<table width="80%" border="1">
			<tr>
				<th width="23%">제목</th>
				<td><input type="text" name="title" size="150"/></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer" readonly="readonly" value="${ userVO.name }" size="150"/></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="7" cols="50" name="content"></textarea></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<input type="file" name="attachfile1" size="40"/><br/>
					<input type="file" name="attachfile2" size="40"/><br/>
				</td>
			</tr>
		</table>
		<br/><br/>
		<input type="submit" value="등록"/>&nbsp;&nbsp;
		<input type="button" value="목록" onclick="doList()" />
	</form>
	
	</div>
</body>
</html>