<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="social-icons">
	<div class="col_1_of_f span_1_of_f">
		<a href="#">
			<ul class='facebook'>
				<i class="fb"> </i>
				<li>Tạo bằng Facebook</li>
				<div class='clear'></div>
			</ul>
		</a>
	</div>
	<div class="col_1_of_f span_1_of_f">
		<a href="#">
			<ul class='twitter'>
				<i class="tw"> </i>
				<li>Tạo bằng Twitter</li>
				<div class='clear'></div>
			</ul>
		</a>
	</div>
	<div class="clear"></div>
</div>
<h2>hoặc Đăng ký</h2>
<form:form method="POST" commandName="signUpForm" action="signup.do">
	<div class="lable-2">
		<form:input path="username" class="text" value="username" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Tài khoản ';}"/>
		<form:input path="firstname" class="text" value="firstname" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Tài khoản ';}"/>
		<form:input path="email" class="text" value="email@email.com" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'email@email.com ';}"/>
		<form:password path="password" class="text" value="123456" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Password ';}"/>
		<form:password path="confirmPassword" class="text" value="123456" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Password ';}"/>
	</div>
	<h3>
		Bằng việc đăng ký tài khoản, bạn đã đồng ý <span class="term"><a
			href="#">các điều khoản sử dụng</a></span>
	</h3>
	<div class="submit">
		<input type="submit" onclick="myFunction()" value="Đăng ký">
	</div>
	<div class="clear"></div>
</form:form>