<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- <div class="social-icons"> -->
<!-- 	<div class="col_1_of_f span_1_of_f"> -->
<!-- 		<a href="#"> -->
<!-- 			<ul class='facebook'> -->
<!-- 				<i class="fb"> </i> -->
<!-- 				<li>Tạo bằng Facebook</li> -->
<!-- 				<div class='clear'></div> -->
<!-- 			</ul> -->
<!-- 		</a> -->
<!-- 	</div> -->
<!-- 	<div class="col_1_of_f span_1_of_f"> -->
<!-- 		<a href="#"> -->
<!-- 			<ul class='twitter'> -->
<!-- 				<i class="tw"> </i> -->
<!-- 				<li>Tạo bằng Twitter</li> -->
<!-- 				<div class='clear'></div> -->
<!-- 			</ul> -->
<!-- 		</a> -->
<!-- 	</div> -->
<!-- 	<div class="clear"></div> -->
<!-- </div> -->
<!-- <h2>hoặc Đăng ký</h2> -->

<script type="text/javascript">
	$(function() {
		var username = $("#username"), firstname = $("#firstname"), email = $("#email"), password = $("#password"), confirmPassword = $("#confirmPassword");
		var signupForm = $("#signupForm");
		$("#signupForm").submit(function() {
			$(".error").empty();
			var valid = "";
			if(!checkLength(username, 3, 16)){
				valid += "Tài khoản có độ dài từ 3 - 16.<br/>";
			}
			if(!checkUsername(username)){
				valid += "Tài khoản chỉ bao gồm chữ A-Z, a-z, 0-9, gạch dưới _, bắt đầu bằng chữ cái.<br/>";
			}
			if(!checkLength(firstname, 2, 20)){
				valid += "Tên có độ dài từ 2 - 20.<br/>";
			}
			if(!checkEmail(email)){
				valid += "Hãy nhập chính xác email: ví dụ myemail@email.com<br/>";
			}
			if(!checkPassword(password)){
				valid += "Mật khẩu chỉ bao gồm a-z, 0-9, bắt đầu bằng chữ cái và có độ dài từ 6-18.<br/>";
			}
			if(!checkMatch(password, confirmPassword)){
				valid += "Xác nhận mật khẩu phải giống với mật khẩu.";
			}
			if (valid.length == 0) {
				signupForm.submit();
			} else {				
				$(".error").html(valid);
				return false;
			}
		});
	});
</script>
<form:form method="POST" commandName="signUpForm" action="signup.do" id="signupForm">
	<label class="error"></label>
	<form:errors path="*" cssClass="error" element="label"/> 
	<div class="lable-2">
		<span class="label">Tài khoản</span>
		<form:input path="username" id="username" class="text"/>
		<span class="label">Tên</span>
		<form:input path="firstname" id="firstname" class="text"/>
		<span class="label">Email</span>
		<form:input path="email" id="email" class="text"/>
		<span class="label">Mật khẩu:</span>
		<form:password path="password" id="password" class="text"/>
		<span class="label">Xác nhận mật khẩu</span>
		<form:password path="confirmPassword" id="confirmPassword" class="text"/>
	</div>
	<h3>
		Bằng việc đăng ký tài khoản, bạn đã đồng ý <span class="term"><a
			href="#">các điều khoản sử dụng</a></span>
	</h3>
	<div class="submit">
		<input type="submit" id="submit"  value="Đăng ký">
	</div>
	<div class="clear"></div>
</form:form>