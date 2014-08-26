<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=utf-8"%>
<script type="text/javascript">
	function loadUserData() {
		$.ajax({
			url : "<c:url value="/user/getUserProfile"/>",
			type : "GET",
			success : function(response) {
				$("#user-detail h4 a").text(response.firstName);
				$("#user-statistic-task span").text(response.completedTask);
			}
		});	
	}
</script>
<div>
	<!--Start User block -->
	<div class="btn_style blue">
		<h4>Profile</h4>
	</div>
	<div class="cover">
		<img src="<c:url value="/main/images/pic2.jpg" />" alt="" />
	</div>
	<div class="user">
		<div class="user-info">
			<div class="avatar">
				<a href="#"><img src="<c:url value="/main/images/user.jpg" />" alt="" /></a>
			</div>
			<div id="user-detail" class="user-detail">
				<h4>
					<a href="#">john doe</a>
				</h4>
<!-- 				<span>newyork, NY</span> -->
			</div>
		</div>
	</div>
	<div class="user-statistics">
		<div id="user-statistic-task" class="user-statistic">
			<p>Completed Tasks</p>
			<span>345</span>
		</div>
		<div id="user-statistic-point" class="user-statistic">
			<p>Points</p>
			<span>498</span>
		</div>
		<div class="clear"></div>
	</div>
</div>
<!--End User block -->