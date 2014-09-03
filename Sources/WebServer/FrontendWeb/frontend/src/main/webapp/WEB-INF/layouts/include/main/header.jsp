<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=utf-8"%>
<div class="wrapper">
	<a href="<c:url value="/home" />" alt="Trang chủ"><img class="logo" src="<c:url value="/main/images/logo.jpg" />" alt="" /></a>
	<div class="user-task-bar">
		<ul class="user-task">
			<li><a href="#"><img src="<c:url value="/main/icons/user-32.png" />" title="profile" /></a>
				<ul class="sub">
<!-- 					<li><a href="#">Profile</a></li> -->
<!-- 					<li><a href="#">Settings</a></li> -->
					<li><a href="<c:url value="/logout.do" />">Logout</a></li>
				</ul>
			</li>
		</ul>
	</div>
</div>