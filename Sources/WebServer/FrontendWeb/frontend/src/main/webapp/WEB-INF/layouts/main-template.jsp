<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<tiles:useAttribute id="key" name="title"/>
<title><spring:message code="${key}" /></title>
<link href="<c:url value="/main/css/base.css" />" rel="stylesheet" type="text/css" media="all" />
<link href="<c:url value="/main/css/layout.css" />" rel="stylesheet" type="text/css" media="all" />
<link href="<c:url value="/main/css/skeleton.css" />" rel="stylesheet" type="text/css" media="all" />
<link href="<c:url value="/main/css/custom.css" />" rel="stylesheet" type="text/css" media="all" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="//raw.githubusercontent.com/phstc/jquery-dateFormat/master/dist/jquery-dateFormat.min.js"></script>
<script type="text/javascript">
	$( document ).ready(function(){
		loadPoint();
		loadUserData();
		loadAchievements();
		loadTasks();
	});
</script>
</head>
<body>
	<header>
		<tiles:insertAttribute name="header" ignore="false" />
	</header>
	<div class="container columns">
		<div class="clear add-bottom"></div>
		<div class="six columns">
			<tiles:insertAttribute name="user-block" ignore="false" />
			<div class="clear add-bottom"></div>
			<tiles:insertAttribute name="badge-block" ignore="false" />
		</div>
		<div class="ten columns">	
			<div class="point_img">
				<tiles:insertAttribute name="point-image-block" ignore="false" />
			</div>
			<div class="clear add-bottom"></div>
			<div>
				<!-- Start task -->
				<div class="btn_style blue">
					<h4>task</h4>
				</div>
				<div class="menu_box_list">
					<tiles:insertAttribute name="task-block" ignore="false" />
				</div>
			</div>
			<!-- Start task -->
		</div>
		<div class="clear add-bottom"></div>
		<div class="sixteen columns">
			<tiles:insertAttribute name="footer" ignore="false" />
		</div>
	</div>
</body>
</html>