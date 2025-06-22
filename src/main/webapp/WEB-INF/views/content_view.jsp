<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>content_view</h3>
<table width="500" border="1">
	<form action="modify" method="post">
	<input type="hidden" name="bid" value="${content_view.bid }" />
	<tr>
		<td>번호</td>
		<td>${content_view.bid }</td>
	</tr>
	<tr>
		<td>히트</td>
		<td>${content_view.bhit }</td>
	</tr>
	<tr>
		<td>이름</td>
		<td>
			<input type="text" name="bname" value="${content_view.bname }" />
		</td>
	</tr>
	<tr>
		<td>제목</td>
		<td>
			<input type="text" name="btitle" value="${content_view.btitle }" />
		</td>
	</tr>
	<tr>
		<td>내용</td>
		<td>
			<textarea name="bcontent"  rows="10">${content_view.bcontent }</textarea>
		</td>
	</tr>
	<tr>
		<td>첨부</td>
		<td>
			<c:forEach items="${imgList }" var="imgdto">
				<a href="download?f=${imgdto.rebchgfile }&bid=${content_view.bid }">${imgdto.rebchgfile }</a> <br />
			</c:forEach>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="수정" /> &nbsp;&nbsp;
			<a href="list">목록</a>&nbsp;&nbsp;
			<a href="delete?bid=${content_view.bid }">삭제</a>&nbsp;&nbsp;
			<a href="reply_view?bid=${content_view.bid }">답변</a>&nbsp;&nbsp;
		</td>
		
	</tr>
	</form>

</table>
<hr />
<c:forEach items="${imgList }" var="imgdto">
		<img src="\\files\\${imgdto.rebchgfile }" width="300" height=230 alt="" /><br />	
</c:forEach>


</body>
</html>