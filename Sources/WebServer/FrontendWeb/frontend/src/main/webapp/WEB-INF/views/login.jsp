<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="social-icons">
	<div class="col_1_of_f span_1_of_f">
		<a href="#">
			<ul class='facebook'>
				<i class="fb"> </i>
				<li>Đăng nhập bằng Facebook</li>
				<div class='clear'></div>
			</ul>
		</a>
	</div>
	<div class="col_1_of_f span_1_of_f">
		<a href="#">
			<ul class='twitter'>
				<i class="tw"> </i>
				<li>Đăng nhập bằng Twitter</li>
				<div class='clear'></div>
			</ul>
		</a>
	</div>
	<div class="clear"></div>
</div>
<h2>hoặc đăng nhập bằng Tài khoản</h2>
<form:form method="POST" commandName="loginForm" action="login.do">
	<div class="lable-2">
		<form:input path="username" class="text" value="testacc" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Tài khoản ';}"/>
		<form:password path="password" class="text" value="123456" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Mật khẩu ';}"/>
	</div>
	<div class="submit">
		<input type="submit" onclick="myFunction()" value="Đăng nhập">
	</div>
	<div class="clear"></div>
</form:form>