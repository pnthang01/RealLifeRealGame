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

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="<c:url value="/main/js/main.js" />" type="text/javascript"></script>

</head>
<body>
	<header>
		<tiles:insertAttribute name="header" ignore="false" />
	</header>
	<div class="container columns">
		<div class="clear add-bottom"></div>
		<div class="six columns">
		<div><!--Start User block -->
						<div class="btn_style blue">
							<h4>Profile</h4>
						</div>
						<div class="cover">
							<img src="images/pic2.jpg" alt=""/>
						</div>
						<div class="user">
							<div class="user-info">
								<div class="avatar">
									<a href="#"><img src="images/user.jpg" alt=""/></a>
								</div>
								<div class="user-detail">
									<h4><a href="#">john doe</a></h4>
									<span>newyork, NY</span>
								</div>
							</div>
						</div>
						<div class="profile-menu">
							<a href="">Profile</a>
							<a href="">Achievement</a>
							<a href="">Statistic</a>				
						<div class="clear"></div>
						</div>
					</div><!--End User block -->
		</div>
		<div class="ten columns">
					<div>
						<div class="btn_style blue">
							<h4>Achievement</h4>
						</div>
						<div class="menu_box_list">
							<div class="loader_right">
								<div class="badge-photos-big">
									<ul>
										<li><a href="#"><img src="images/cartton-img1.jpg" alt="" /></a></li>
										<li><a href="#"><img src="images/cartton-img2.jpg" alt="" /></a></li>
										<li><a href="#"><img src="images/cartton-img3.jpg" alt="" /></a></li>
										<li><a href="#"><img src="images/cartton-img4.jpg" alt="" /></a></li>
										<li><a href="#"><img src="images/cartton-img5.jpg" alt="" /></a></li>
										<li><a href="#"><img src="images/cartton-img6.jpg" alt="" /></a></li>
										<li><a href="#"><img src="images/cartton-img7.jpg" alt="" /></a></li>
										<li><a href="#"><img src="images/cartton-img8.jpg" alt="" /></a></li>
										<li><a href="#"><img src="images/cartton-img9.jpg" alt="" /></a></li>
									</ul>
								</div>
								<div class="clear"></div>
							</div>
						</div>
					</div>
				</div>
		<div class="clear add-bottom"></div>
		<div class="sixteen columns">
			<tiles:insertAttribute name="footer" ignore="false" />
		</div>	
	</div>
</body>
</html>