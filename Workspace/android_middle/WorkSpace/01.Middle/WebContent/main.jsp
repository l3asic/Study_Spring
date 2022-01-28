<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<input type="text" value="${fn:length(list) }">
	<form action="grading.exam" method="post">

	<input type="hidden" id="tt_cnt" name="tt_cnt" value="${fn:length(list) }"> 
	<c:forEach items="${list}" var="vo">
		<p>${vo.no } </p>
		<p>${vo.exam_content }</p>
		<c:if test="${vo.exam_cnt eq 0 }">
			<input type="text" name="${vo.no}" id="${vo.no}">
		</c:if>
		<c:if test="${vo.exam_cnt ne 0 }">
			<c:forEach var="i" begin="1" end="${vo.exam_cnt }">
				<a>${i}</a>
				<input type="radio" name="${vo.no }" id="${vo.no }">
			</c:forEach>
		</c:if>
	</c:forEach>
		<input type="submit" value="전송">
	</form>
</body>
</html>