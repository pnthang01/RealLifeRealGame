<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=utf-8"%>
<div class="tasks">
	<ul>
		<c:choose>
			<c:when test="${empty data.list}">
				Chưa có nhiệm vụ nào. Bạn hãy tạo nhiệm vụ nhé!				
			</c:when>
			<c:otherwise>
				<c:forEach var="task" items="${data.list}" begin="0" end="${fn:length(data.list)}">
					<li class="task_bg">
						<div class="task_img">
							<img src="<c:url value="/main/images/commen	t_author.jpg" />" alt="" />
						</div>
						<div class="task">
							<a href="#"><c:out value="${task.name}">Không tên</c:out></a>
							<span>
								<fmt:formatDate type="both" value="${task.completeTime}" pattern="dd-MM-yyyy"></fmt:formatDate>
							</span>
							<p>
								Thể loại: <a href="#"> <c:out value="${task.category.name}">Không có thể loại</c:out> </a>
<!-- 							</p> -->
<%-- 							<p>Mô tả : <c:out value="${task.description}"></c:out></p> --%>
<!-- 							<p> -->
<%-- 								Mức độ: <a href="#"> <c:out value="${task.difficultyLevel}">Không có mức độ</c:out> </a> --%>
<!-- 							</p> -->
						</div>

						<div class="clear"></div>
					</li>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</ul>
</div>