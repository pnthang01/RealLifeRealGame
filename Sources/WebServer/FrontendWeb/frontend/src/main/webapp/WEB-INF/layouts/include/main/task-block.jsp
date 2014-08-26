<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=utf-8"%>
<script type="text/javascript">
	function loadTasks(processUrl) {
		$.ajax({
			type : "GET",
			headers: {'Profile_Token' : document.cookie },
			url : "<c:url value="/task/getAvailableTasks" ><c:param name="pageNumber" value="1"/> </c:url>",
			success : function(response) {
				var popuData = '';
				for ( var i = 0; i < response.list.length; i++) {
					popuData += '<li class="task_bg">';
						popuData += '<div class="task_img">	';
							popuData += '<img src="<c:url value="/main/images/comment_author.jpg" />" alt="" />';
						popuData += '</div>';
						popuData += '<div class="task">';
							popuData += '<h4><a href="#">'+ response.list[i].name + '</a> - ' + response.list[i].completeTime + '</h4>';
							popuData += '<p>Thể loại: <a href="#">' + response.list[i].category.name + '</a></p>' ;
							popuData += '<p>Mô tả: ' + response.list[i].description + '</p>';
							popuData += '<p>Mức độ: <a href="#">' + response.list[i].difficultyLevel + '</a></p>';
						popuData += '</div>';
						popuData += '<div class="clear"></div>';
						popuData += '</li>';
				}
				$("#tasks ul").html(popuData);
			}
		});
	};
</script>
<div id="tasks" class="tasks">
	<ul>
		<li class="task_bg">
			<div style="margin: auto; text-align:center;" >
				<img style="height:100px;width:100px;" src="<c:url value="/main/icons/loading.gif" />" alt="" />
			</div>
		</li>
	</ul>
</div>