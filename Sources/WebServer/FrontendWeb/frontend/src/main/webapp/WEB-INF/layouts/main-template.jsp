<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<tiles:useAttribute id="key" name="title" />
<title><spring:message code="${key}" /></title>
<link href="<c:url value="/main/css/base.css" />" rel="stylesheet" type="text/css" media="all" />
<link href="<c:url value="/main/css/layout.css" />" rel="stylesheet" type="text/css" media="all" />
<link href="<c:url value="/main/css/skeleton.css" />" rel="stylesheet" type="text/css" media="all" />
<link href="<c:url value="/main/css/custom.css" />" rel="stylesheet" type="text/css" media="all" />
<link href="<c:url value="/main/css/accordion.css" />" rel="stylesheet" type="text/css" media="all" />

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/jquery-ui.min.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<script src="<c:url value="/main/js/main.js" />" type="text/javascript"></script>
<script src="<c:url value="/main/js/jquery.ui.datepicker-vi.js" />" type="text/javascript"></script>
<style>
</style>
<script type="text/javascript">

	$(document).ready(function() {
		loadPoint();
		loadUserData();
		loadAchievements();
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

					<tiles:insertAttribute name="task-block" ignore="false" />
					
					<tiles:insertAttribute name="content-block" ignore="false" />
				
			</div>
			<!-- End task -->
		</div>
		<div class="clear add-bottom"></div>
		<div class="sixteen columns">
			<tiles:insertAttribute name="footer" ignore="false" />
		</div>
	</div>
</body>
</html>