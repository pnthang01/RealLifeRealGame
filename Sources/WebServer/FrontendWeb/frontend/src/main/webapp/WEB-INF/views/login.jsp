<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- <div class="social-icons"> -->
<!-- 	<div class="col_1_of_f span_1_of_f"> -->
<!-- 		<a href="#"> -->
<!-- 			<ul class='facebook'> -->
<!-- 				<i class="fb"> </i> -->
<!-- 				<li>Đăng nhập bằng Facebook</li> -->
<!-- 				<div class='clear'></div> -->
<!-- 			</ul> -->
<!-- 		</a> -->
<!-- 	</div> -->
<!-- 	<div class="col_1_of_f span_1_of_f"> -->
<!-- 		<a href="#"> -->
<!-- 			<ul class='twitter'> -->
<!-- 				<i class="tw"> </i> -->
<!-- 				<li>Đăng nhập bằng Twitter</li> -->
<!-- 				<div class='clear'></div> -->
<!-- 			</ul> -->
<!-- 		</a> -->
<!-- 	</div> -->
<!-- 	<div class="clear"></div> -->
<!-- </div> -->
<!-- <h2>hoặc đăng nhập bằng Tài khoản</h2> -->
<script type="text/javascript">
	$(function() {
		var username = $("#username"), password = $("#password");
		$("#loginForm").submit(function() {
			$(".error").empty();
			var valid = "";
			if(!checkLength(username, 3, 16)){
				valid += "Tài khoản có độ dài từ 3 - 16.<br/>";
			}
			if(!checkUsername(username)){
				valid += "Tài khoản chỉ bao gồm chữ A-Z, a-z, 0-9, gạch dưới _, bắt đầu bằng chữ cái.<br/>";
			}
			if(!checkPassword(password)){
				valid += "Mật khẩu chỉ bao gồm a-z, 0-9, bắt đầu bằng chữ cái và có độ dài từ 6-18.<br/>";
			}
			if (valid.length == 0) {
				$("#loginForm").submit();
			} else {				
				$(".error").html(valid);
				return false;
			}
		});
	});
</script>
<form:form method="POST" commandName="loginForm" action="login.do" id="loginForm">
	<label class="error"></label>
	<form:errors path="*" cssClass="error" element="label"/> 
	<div class="lable-2">
		<span class="label">Tài khoản</span>
		<form:input path="username" class="text"/>
		<span class="label">Mật khẩu:</span>
		<form:password path="password" class="text"/>
		<span class="label">Ghi nhớ đăng nhập:</span>
		<form:checkbox path="rememberMe" class="text"/>
	</div>
	<div class="submit">
		<input type="submit" value="Đăng nhập">
	</div>
	<div class="clear"></div>
</form:form>