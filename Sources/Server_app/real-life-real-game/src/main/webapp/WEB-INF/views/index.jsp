<%-- 
    Document   : index
    Created on : Mar 12, 2014, 9:08:29 AM
    Author     : Anh Dinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
<title>Home</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/style.css"/>" />

</head>
<body>
	<div id="wrap">

		<div class="header">
			<div class="logo">
				<a href="index.html"><img
					src="<c:url value="/resources/images/logo.gif"/>" alt="" title=""
					border="0" /></a>
			</div>
			<div id="menu">
				<ul>
					<li class="selected"><a href="index.html">home</a></li>
					<li><a href="about.html">about us</a></li>
					<li><a href="category.html">books</a></li>
					<li><a href="specials.html">specials books</a></li>
					<li><a href="myaccount.html">my accout</a></li>
					<li><a href="register.html">register</a></li>
					<li><a href="details.html">prices</a></li>
					<li><a href="contact.html">contact</a></li>
				</ul>
			</div>
		</div>
		<div class="center_content">
			<div class="left_content">

				<div class="title">
					<span class="title_icon"><img
						src="<c:url value="/resources/images/bullet1.gif"/>" alt=""
						title="" /></span>Featured books
				</div>

				<div class="feat_prod_box">

					<div class="prod_img">
						<a href="details.html"><img
							src="<c:url value="/resources/images/prod1.gif"/>" alt=""
							title="" border="0" /></a>
					</div>

					<div class="prod_det_box">
						<div class="box_top"></div>
						<div class="box_center">
							<div class="prod_title">Product name</div>
							<p class="details">Lorem ipsum dolor sit amet, consectetur
								adipisicing elit, sed do eiusmod tempor incididunt ut labore et
								dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
								exercitation.</p>
							<a href="details.html" class="more">- more details -</a>
							<div class="clear"></div>
						</div>

						<div class="box_bottom"></div>
					</div>
					<div class="clear"></div>
				</div>


				<div class="feat_prod_box">

					<div class="prod_img">
						<a href="details.html"><img
							src="<c:url value="/resources/images/prod2.gif"/>" alt=""
							title="" border="0" /></a>
					</div>

					<div class="prod_det_box">
						<div class="box_top"></div>
						<div class="box_center">
							<div class="prod_title">Product name</div>
							<p class="details">Lorem ipsum dolor sit amet, consectetur
								adipisicing elit, sed do eiusmod tempor incididunt ut labore et
								dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
								exercitation.</p>
							<a href="details.html" class="more">- more details -</a>
							<div class="clear"></div>
						</div>

						<div class="box_bottom"></div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="title">
					<span class="title_icon"><img
						src="<c:url value="/resources/images/bullet2.gif"/>" alt=""
						title="" /></span>New books
				</div>

				<div class="new_products">

					<div class="new_prod_box">
						<a href="details.html">product name</a>
						<div class="new_prod_bg">
							<span class="new_icon"><img
								src="<c:url value="/resources/images/new_icon.gif"/>" alt=""
								title="" /></span> <a href="details.html"><img
								src="<c:url value="/resources/images/thumb1.gif"/>" alt=""
								title="" class="thumb" border="0" /></a>
						</div>
					</div>

					<div class="new_prod_box">
						<a href="details.html">product name</a>
						<div class="new_prod_bg">
							<span class="new_icon"><img
								src="<c:url value="/resources/images/new_icon.gif"/>" alt=""
								title="" /></span> <a href="details.html"><img
								src="<c:url value="/resources/images/thumb2.gif"/>" alt=""
								title="" class="thumb" border="0" /></a>
						</div>
					</div>

					<div class="new_prod_box">
						<a href="details.html">product name</a>
						<div class="new_prod_bg">
							<span class="new_icon"><img
								src="<c:url value="/resources/images/new_icon.gif"/>" alt=""
								title="" /></span> <a href="details.html"><img
								src="<c:url value="/resources/images/thumb3.gif"/>" alt=""
								title="" class="thumb" border="0" /></a>
						</div>
					</div>

				</div>


				<div class="clear"></div>
			</div>
			<!--end of left content-->

			<div class="right_content">
				<div class="languages_box">
					<span class="red">Languages:</span> <a href="#" class="selected"><img
						src="<c:url value="/resources/images/gb.gif"/>" alt="" title=""
						border="0" /></a> <a href="#"><img
						src="<c:url value="/resources/images/fr.gif"/>" alt="" title=""
						border="0" /></a> <a href="#"><img
						src="<c:url value="/resources/images/de.gif"/>" alt="" title=""
						border="0" /></a>
				</div>
				<div class="currency">
					<span class="red">Currency: </span> <a href="#">GBP</a> <a href="#">EUR</a>
					<a href="#" class="selected">USD</a>
				</div>
				<div class="cart">
					<div class="title">
						<span class="title_icon"><img
							src="<c:url value="/resources/images/cart.gif"/>" alt="" title="" /></span>My
						cart
					</div>
					<div class="home_cart_content">
						3 x items | <span class="red">TOTAL: 100$</span>
					</div>
					<a href="cart.html" class="view_cart">view cart</a>
				</div>
				<div class="title">
					<span class="title_icon"><img
						src="<c:url value="/resources/images/bullet3.gif"/>" alt=""
						title="" /></span>About Our Store
				</div>
				<div class="about">
					<p>
						<img src="<c:url value="/resources/images/about.gif"/>" alt=""
							title="" class="right" /> Lorem ipsum dolor sit amet,
						consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
						labore et dolore magna aliqua. Ut enim ad minim veniam, quis
						nostrud.
					</p>
				</div>
				<div class="right_box">
					<div class="title">
						<span class="title_icon"><img
							src="<c:url value="/resources/images/bullet4.gif"/>" alt=""
							title="" /></span>Promotions
					</div>
					<div class="new_prod_box">
						<a href="details.html">product name</a>
						<div class="new_prod_bg">
							<span class="new_icon"><img
								src="<c:url value="/resources/images/promo_icon.gif"/>" alt=""
								title="" /></span> <a href="details.html"><img
								src="<c:url value="/resources/images/thumb1.gif"/>" alt=""
								title="" class="thumb" border="0" /></a>
						</div>
					</div>

					<div class="new_prod_box">
						<a href="details.html">product name</a>
						<div class="new_prod_bg">
							<span class="new_icon"><img
								src="<c:url value="/resources/images/promo_icon.gif"/>" alt=""
								title="" /></span> <a href="details.html"><img
								src="<c:url value="/resources/images/thumb2.gif"/>" alt=""
								title="" class="thumb" border="0" /></a>
						</div>
					</div>

					<div class="new_prod_box">
						<a href="details.html">product name</a>
						<div class="new_prod_bg">
							<span class="new_icon"><img
								src="<c:url value="/resources/images/promo_icon.gif"/>" alt=""
								title="" /></span> <a href="details.html"><img
								src="<c:url value="/resources/images/thumb3.gif"/>" alt=""
								title="" class="thumb" border="0" /></a>
						</div>
					</div>

				</div>

				<div class="right_box">

					<div class="title">
						<span class="title_icon"><img
							src="<c:url value="/resources/images/bullet5.gif"/>" alt=""
							title="" /></span>Categories
					</div>
					<!-- list categories -->	
						<ul class="list">
							<c:forEach  var="cate" items="${listCat}">
								<li><a href="<c:url value="/categories/id-${cate.idCategories}"/>">${cate.cateName}</a></li>
							</c:forEach>
						</ul>

					<!-- 					<ul class="list"> -->
					<!-- 						<li><a href="#">accesories</a></li> -->
					<!-- 						<li><a href="#">books gifts</a></li> -->
					<!-- 						<li><a href="#">specials</a></li> -->
					<!-- 						<li><a href="#">hollidays gifts</a></li> -->
					<!-- 						<li><a href="#">accesories</a></li> -->
					<!-- 						<li><a href="#">books gifts</a></li> -->
					<!-- 						<li><a href="#">specials</a></li> -->
					<!-- 						<li><a href="#">hollidays gifts</a></li> -->
					<!-- 						<li><a href="#">accesories</a></li> -->
					<!-- 						<li><a href="#">books gifts</a></li> -->
					<!-- 						<li><a href="#">specials</a></li> -->
					<!-- 					</ul> -->
					<div class="title">
						<span class="title_icon"><img
							src="<c:url value="/resources/images/bullet6.gif"/>" alt=""
							title="" /></span>Partners
					</div>
					<ul class="list">
						<li><a href="#">accesories</a></li>
						<li><a href="#">books gifts</a></li>
						<li><a href="#">specials</a></li>
						<li><a href="#">hollidays gifts</a></li>
						<li><a href="#">accesories</a></li>
						<li><a href="#">books gifts</a></li>
						<li><a href="#">specials</a></li>
						<li><a href="#">hollidays gifts</a></li>
						<li><a href="#">accesories</a></li>
					</ul>

				</div>


			</div>
			<!--end of right content-->




			<div class="clear"></div>
		</div>
		<!--end of center content-->


		<div class="footer">
			<div class="left_footer">
				<img src="<c:url value="/resources/images/footer_logo.gif"/>" alt=""
					title="" /><br /> <a href="http://csscreme.com/freecsstemplates/"
					title="free templates"><img
					src="<c:url value="/resources/images/csscreme.gif"/>"
					alt="free templates" title="free templates" border="0" /></a>
			</div>
			<div class="right_footer">
				<a href="#">home</a> <a href="#">about us</a> <a href="#">services</a>
				<a href="#">privacy policy</a> <a href="#">contact us</a>

			</div>


		</div>


	</div>

</body>
</html>