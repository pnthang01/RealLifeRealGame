<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=utf-8"%>
<script type="text/javascript">
	function loadUserData() {
		$("#user-detail h4 a").text('${sessionScope.FirstNameSession}');

		$.ajax({
			url : "<c:url value="/user/getBasicUserStatistics"/>",
			type : "GET",
			success : function(response) {
				$("#user-statistic-task span").text(response.values[0]);
				$("#user-statistic-point span").text(response.values[1]);
			}
		});	
	}
</script>
<div>
	<!--Start User block -->
	<div class="btn_style lightgreen">
		<h4> &nbsp</h4>
	</div>
	<div class="cover">
		<img src="<c:url value="/main/images/default-cover.jpg" />" alt="" />
	</div>
	<div class="user">
		<div class="user-info">
			<div class="avatar">
				<a href="#"><img src="<c:url value="/main/images/default-male.jpg" />" alt="" /></a>
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
			<p>N.vụ hoàn thành</p>
			<span>0</span>
		</div>
		<div id="user-statistic-point" class="user-statistic">
			<p>Tổng số điểm</p>
			<span>0</span>
		</div>
		<div class="clear"></div>
	</div>
</div>
<!--End User block -->