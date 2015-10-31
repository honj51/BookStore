<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="page_nav">
	<%--分三种情况 --%>
	<c:choose>
		<c:when test="${page.totalPage<5 }">
			<c:set var="begin" value="1"></c:set>
			<c:set var="end" value="${page.totalPage }"></c:set>
		</c:when>
		<c:when test="${page.pageNumber<=3 }">
			<c:set var="begin" value="1"></c:set>
			<c:set var="end" value="5"></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="begin" value="${page.pageNumber-2 }"></c:set>
			<c:set var="end" value="${page.pageNumber+2 }"></c:set>
		</c:otherwise>
	</c:choose>
	<%--最后得判断一下end是否大于总页数 --%>
	<c:if test="${end>page.totalPage }">
		<c:set var="begin" value="${page.totalPage-4 }"></c:set>
		<c:set var="end" value="${page.totalPage }"></c:set>
	</c:if>
	<a href="${page.path}&pageNumber=1">首页</a> <a
		href="${page.path}&pageNumber=${page.pageNumber-1}">上一页</a>
	<c:forEach begin="${begin }" end="${end}" var="index">
		<c:choose>
			<c:when test="${page.pageNumber==index }">
											【${index}】
										</c:when>
			<c:otherwise>
				<a href="${page.path}&pageNumber=${index}">${index}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<a href="${page.path}&pageNumber=${page.pageNumber+1 }">下一页</a> <a
		href="${page.path}&pageNumber=${page.totalPage}">末页</a>
	共${page.totalPage }页，${page.totalRecord}条记录 到第<input
		value="${page.pageNumber }" name="pn" id="pn_input" />页 <input
		type="button" id="pg_btn" value="确定">
	<script type="text/javascript">
		$("#pg_btn").click(function() {
			var pageNumber = $("#pn_input").val();
			window.location = "${page.path}&pageNumber=" + pageNumber;
		});
	</script>
</div>