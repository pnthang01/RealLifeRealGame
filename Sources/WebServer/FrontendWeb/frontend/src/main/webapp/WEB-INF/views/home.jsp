<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=utf-8"%>
<script src="<c:url value="/main/js/smk-accordion.js" />" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function () {
	
	$(".accordion").smk_Accordion({
		showIcon: true, //boolean
		animation: true, //boolean
		closeAble: false, //boolean
		slideSpeed: 300 //integer, miliseconds
	});
	
	function changeStatus(o, value){
		if(o.attr("value") == "COMPLETED"){
			o.closest('.task').find(".title").removeClass("titleCompleted");
		}
		o.attr( "value", value );
		if(value == "COMPLETED" || value == "NOTCOMPLETED"){
			o.attr( "checked", "checked" );
		}
		if(value == "PROGRESSING"){
			o.attr( "class", "" );
		}
		if(value == "COMPLETED"){
			o.attr( "class", "completed" );
			var parent = o.closest('.task');
			parent.find(".title").addClass("titleCompleted");
		}
		if(value == "NOTCOMPLETED"){
			o.attr( "class", "notcompleted" );
		}
	}
	$( "input[type=checkbox]" ).on( 'click', function() {
		var component = $(this);
		var params = { 'id' : $(this).attr('id') };
		$.ajax({
			type : "POST",
			headers : { 'Profile_Token' : document.cookie },
			url : '<c:url value="/task/updateTaskStatus" />',
			data: params,
			success : function(response) {
				location.reload();
			}
		});
	});
})
</script>
<div class="tasks">
	<div class="accordion"> 
		<div class="accordion_in acc_active">
			<div class="acc_head"><span>Ngày hôm nay</span> <label class="digits active">5</label> <div class="clear"></div></div>
			<div class="acc_content">
				<ul>
					<c:choose>
						<c:when test="${empty today.list}">
							<li class="task_bg">
								Chưa có nhiệm vụ nào. Bạn hãy tạo nhiệm vụ nhé!
							</li>				
						</c:when>
						<c:otherwise>
							<c:forEach var="task" items="${today.list}" begin="0" end="${fn:length(today.list)}">
								<li class="task_bg">
									<div class="task_img">
										<img src="<c:url value="${task.category.fileName}" />" alt="" />
									</div>
									<div class="task">
										<div class="rel_post_check">
											<label class="checkbox">
												<input type="checkbox" name="checkbox" value="<c:out value="${task.status}"/>" id="<c:out value="${task.id}"/>"
												class="<c:if test="${task.status == 'COMPLETED'}">completed titleCompleted</c:if>
														<c:if test="${task.status == 'NOTCOMPLETED'}">notcompleted</c:if>"
												checked="<c:if test="${task.status == 'COMPLETED' || task.status == 'NOTCOMPLETED' } ">checked</c:if>"
												value="1">
												<i> </i>
											</label>
										</div>
										<span class="title <c:if test="${task.status == 'COMPLETED'}">titleCompleted</c:if>">
											<c:out value="${task.name}">Không tên</c:out> 
										</span>     
										<span class="time">
											<fmt:formatDate type="both" value="${task.completeTime}" pattern="dd-MM-yyyy"></fmt:formatDate>
										</span>
										<p>
											Thể loại: <a href="#"> <c:out value="${task.category.name}">Không có thể loại</c:out> </a>
										</p>
									</div>		
									<div class="clear"></div>
								</li>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
	   	</div>
	   	
   		<div class="accordion_in">
			<div class="acc_head"><span>Từ ngày mai</span> <label class="digits active">21</label> <div class="clear"></div></div>
			<div class="acc_content">
				<ul>
					<c:choose>
						<c:when test="${empty future.list}">
							<li class="task_bg">
								Chưa có nhiệm vụ nào. Bạn hãy tạo nhiệm vụ nhé!
							</li>				
						</c:when>
						<c:otherwise>
							<c:forEach var="task" items="${future.list}" begin="0" end="${fn:length(future.list)}">
								<li class="task_bg">
									<div class="task_img">
										<img src="<c:url value="${task.category.fileName}" />" alt="" />
									</div>
									<div class="task">
										<div class="rel_post_check">
											<label class="checkbox">
												<input type="checkbox" name="checkbox" value="<c:out value="${task.status}"/>" id="<c:out value="${task.id}"/>"
												class="<c:if test="${task.status == 'COMPLETED'}">completed titleCompleted</c:if>
														<c:if test="${task.status == 'NOTCOMPLETED'}">notcompleted</c:if>"
												checked="<c:if test="${task.status == 'COMPLETED' || task.status == 'NOTCOMPLETED' } ">checked</c:if>"
												value="1">
												<i> </i>
											</label>
										</div>
										<span class="title <c:if test="${task.status == 'COMPLETED'}">titleCompleted</c:if>">
											<c:out value="${task.name}">Không tên</c:out> 
										</span>     
										<span class="time">
											<fmt:formatDate type="both" value="${task.completeTime}" pattern="dd-MM-yyyy"></fmt:formatDate>
										</span>
										<p>
											Thể loại: <a href="#"> <c:out value="${task.category.name}">Không có thể loại</c:out> </a>
										</p>
									</div>		
									<div class="clear"></div>
								</li>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
		
   		<div class="accordion_in">
			<div class="acc_head"><span>Quá khứ</span> <label class="digits active">21</label> <div class="clear"></div></div>
			<div class="acc_content">
				<ul>
					<c:choose>
						<c:when test="${empty past.list}">
							<li class="task_bg">
								Bạn không có nhiệm vụ nào ở quá khứ.
							</li>				
						</c:when>
						<c:otherwise>
							<c:forEach var="task" items="${past.list}" begin="0" end="${fn:length(past.list)}">
								<li class="task_bg">
									<div class="task_img">
										<img src="<c:url value="${task.category.fileName}" />" alt="" />
									</div>
									<div class="task">
										<div class="rel_post_check">
											<label class="checkbox">
												<input type="checkbox" name="checkbox" value="<c:out value="${task.status}"/>" id="<c:out value="${task.id}"/>"
												class="<c:if test="${task.status == 'COMPLETED'}">completed titleCompleted</c:if>
														<c:if test="${task.status == 'NOTCOMPLETED'}">notcompleted</c:if>"
												checked="<c:if test="${task.status == 'COMPLETED' || task.status == 'NOTCOMPLETED' } ">checked</c:if>"
												value="1">
												<i> </i>
											</label>
										</div>
										<span class="title <c:if test="${task.status == 'COMPLETED'}">titleCompleted</c:if>">
											<c:out value="${task.name}">Không tên</c:out> 
										</span>     
										<span class="time">
											<fmt:formatDate type="both" value="${task.completeTime}" pattern="dd-MM-yyyy"></fmt:formatDate>
										</span>
										<p>
											Thể loại: <a href="#"> <c:out value="${task.category.name}">Không có thể loại</c:out> </a>
										</p>
									</div>		
									<div class="clear"></div>
								</li>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
   	</div>
</div>