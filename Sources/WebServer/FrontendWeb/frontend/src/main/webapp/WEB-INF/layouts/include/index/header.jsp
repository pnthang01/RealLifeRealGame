<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page pageEncoding="UTF-8"%>
<div class="container">
	<div class="logo">
		<a href="<c:url value="/welcome"/>"><img src="<c:url value="/index/images/logo.png" />" title="logo" /></a>
	</div>
	<!--start-top-nav-->
	<nav class="top-nav">
		<ul class="top-nav">
			<li class="page-scroll"><a href="<c:url value="/signup"/>">Đăng ký</a></li>
			<li class="page-scroll"><a href="<c:url value="/login"/>">Đăng nhập</a></li>
			<li class="page-scroll"><a href="#features" class="scroll">Tìm hiểu thêm</a></li>
			<li class="contatct-active" class="page-scroll"><a
				href="#contact" class="scroll">Liên hệ</a></li>
		</ul>
		<a href="#" id="pull"><img src="<c:url value="/index/images/nav-icon.png" />" title="menu" /></a>
	</nav>
	<div class="clearfix"></div>

</div>