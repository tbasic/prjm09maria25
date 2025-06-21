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
<h3>list</h3>
<table width="700" border="1">
	<tr>
		<td>번호</td>
		<td>이름</td>
		<td>제목</td>
		<td>날자</td>
		<td>히트</td>
	</tr>
	<c:forEach items="${list }" var="dto">
		<tr>
			<td>${dto.bid }</td>
			<td>${dto.bname }</td>
			<td>
			<%-- <c:forEach begin="1" end="${dto.bindent }">-</c:forEach> --%>
			<c:set value="${dto.bindent }" var="endIndent"/>
			<c:forEach begin="1" end="${dto.bindent }" var="cnt">
				<c:if test="${cnt ne 1 }">&nbsp;</c:if>
				<c:if test="${cnt eq endIndent }">	
					<img src="/static/images/reply.gif" alt="" />
				</c:if>
			</c:forEach>
				<a href="content_view?bid=${dto.bid }">${dto.btitle }</a>
			</td>
			<td>${dto.bdate }</td>
			<td>${dto.bhit }</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="5"><a href="write_view">글작성</a></td>
	</tr>
</table>
<hr />
totCnt : ${totRowcnt } <br />
현재페이지/토탈페이지 : ${searchVO.page } / ${searchVO.totPage }
<hr />
<c:if test="${searchVO.totPage>1 }">
	<c:if test="${searchVO.page>1 }">
		<a href="list?page=1">[처음]</a>
		<a href="list?page=${searchVO.page-1 }">[이전]</a>
	</c:if>
	
	<c:forEach begin="${searchVO.pageStart }" end="${searchVO.pageEnd }" var="i">
		<c:choose>
			<c:when test="${i eq searchVO.page }">
				<span style="color:red; font-weight:bold;">${i } &nbsp;&nbsp;</span>
			</c:when>
			<c:otherwise>
				<a href="list?page=${i }" style="text-decoration:none;">${i }</a> &nbsp;&nbsp;
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:if test="${searchVO.totPage>searchVO.page }">
		<a href="list?page=${searchVO.page+1 }">[다음]</a>
		<a href="list?page=${searchVO.totPage }">[마지막]</a>
	</c:if>

</c:if>
<!-- end page -->
<form action="list" method="post">
	<div>
	<c:choose>
		<c:when test="${btitle }">
			<input type="checkbox" name="searchType" value="btitle" checked />
		</c:when>
		<c:otherwise>
			<input type="checkbox" name="searchType" value="btitle" />
		</c:otherwise>
	
	</c:choose>
		<label>제목</label>
	<c:choose>
		<c:when test="${bcontent }">
			<input type="checkbox" name="searchType" value="bcontent" checked />
		</c:when>
		<c:otherwise>
			<input type="checkbox" name="searchType" value="bcontent" />
		</c:otherwise>
	</c:choose>
		<label>내용</label>
	
		<input type="text" name="sk" value="${searchKeyword }" />
		<input type="submit" value="검색" />
	
	</div>
</form>

</body>
</html>