<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>
	<img src="<c:url value="/resources/images/icons/error.png"/>"
		alt="Error" />Error
</h2>
<div id="login">
	<div class="content-box">
		<div class="content-box-header">
			<h3>Error message</h3>
		</div>
		<div class="content-box-content">
			<c:if test="${not empty errorMessage}">
				<h5 style="color: red">
					<c:out value="${errorMessage}" />
				</h5>
			</c:if>
			<c:if test="${empty errorMessage}">
				<h5 style="color: red">
					<p>Sorry, you're not authorised to view this action.</p>
					<p>If you have any enquiry, please contact the administrator 
					at <a href="# class="error-email-address">test@mail.com</a></p>
				</h5>
			</c:if>
			<a href="<c:url value="/dashboard.html"/>"><input type="button"
				value="Click here to return home." /></a>
		</div>
	</div>
	<!-- end .content-box -->
</div>
<!-- end #login -->
<div id="push"></div>