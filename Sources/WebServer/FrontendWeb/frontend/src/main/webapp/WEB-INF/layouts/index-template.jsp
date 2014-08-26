<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><tiles:insertAttribute name="title" ignore="false" /></title>
<link rel="stylesheet" type="text/css" href="<c:url value="/index/css/bootstrap.css" />" />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- start-smoth-scrolling -->
<script type="text/javascript" src="<c:url value="/index/js/move-top.js" />"></script>
<script type="text/javascript" src="<c:url value="/index/js/easing.js" />"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop : $(this.hash).offset().top
			}, 1000);
		});
	});
</script>
<!-- start-smoth-scrolling -->
<!-- Custom Theme files -->
<link rel="stylesheet" type="text/css" href="<c:url value="/index/css/style.css" />" />
<!-- Custom Theme files -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<!-- webfonts -->
<link href='http://www.google.com/fonts/#UsePlace:use/Collection:Roboto+Slab:400,300,700/Script:vietnamese' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Droid+Serif' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,600' rel='stylesheet' type='text/css'>
<!-- //webfonts -->
<!-- start-top-nav-script -->
<script>
	$(function() {
		var pull = $('#pull');
		menu = $('nav ul');
		menuHeight = menu.height();
		$(pull).on('click', function(e) {
			e.preventDefault();
			menu.slideToggle();
		});
		$(window).resize(function() {
			var w = $(window).width();
			if (w > 320 && menu.is(':hidden')) {
				menu.removeAttr('style');
			}
		});
	});
</script>
<!--//End-top-nav-script-->
</head>
<body>
	<!--start-container-->
	<!-- start-header-->
	<div id="home" class="header">
		<tiles:insertAttribute name="header" ignore="false" />
	</div>
	<!-- //End-header-->
	<!--start-slider-script-->
	<script src="<c:url value="/index/js/responsiveslides.min.js" />" type="text/javascript"></script>
	<script>
		// You can also use "$(window).load(function() {"
		$(function() {
			// Slideshow 4
			$("#slider4").responsiveSlides({
				auto : true,
				pager : true,
				nav : true,
				speed : 500,
				namespace : "callbacks",
				before : function() {
					$('.events').append("<li>before event fired.</li>");
				},
				after : function() {
					$('.events').append("<li>after event fired.</li>");
				}
			});

		});
	</script>
	<!--//End-slider-script-->
	<!-- Slideshow 4 -->
	<div id="top" class="callbacks_container">
		<tiles:insertAttribute name="slider" ignore="false" />
	</div>
	<div class="clearfix"></div>
	<!-- //End-slider-->
	<!--start-fearures-->
	<div class="features" id="features">
		<tiles:insertAttribute name="features" ignore="false" />
	</div>
	<!--//End-fearures-->
	<!--//End-container-->
	<div class="footer">
		<div class="container">
			<tiles:insertAttribute name="footer-top" ignore="true" />
			<tiles:insertAttribute name="footer-bottom" ignore="false" />
			<script type="text/javascript">
				$(document).ready(function() {
					/*
					var defaults = {
						containerID: 'toTop', // fading element id
						containerHoverID: 'toTopHover', // fading element hover id
						scrollSpeed: 1200,
						easingType: 'linear' 
					};
					 */

					$().UItoTop({
						easingType : 'easeOutQuart'
					});

				});
			</script>
			<a href="#" id="toTop" style="display: block;"> <span
				id="toTopHover" style="opacity: 1;">top </span></a>
		</div>
	</div>
	<!--//End-footer-->
	<!--//End-container-->
</body>
</html>