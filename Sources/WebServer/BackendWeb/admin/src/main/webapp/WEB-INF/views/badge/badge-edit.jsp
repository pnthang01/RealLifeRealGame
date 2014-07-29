<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="content-box column-right main">
	<div class="content-box-header">
		<h3>Badge Updating Form</h3>
	</div>
	
	<!-- 	Include upload library -->
	<script src="<c:url value="/library/upload/jquery.uploadify.min.js"/>" type="text/javascript"></script>
	<!-- 	End include upload library -->
	
	<div class="content-box-content">
		<form:form method="POST" commandName="badgeForm" action="edit" enctype="multipart/form-data">
			<form:hidden path="id" />
			<fieldset>
				<p>
					<h6>Badge Name:
						<form:errors path="name" cssClass="notification error" element="span"/> 
					</h6> 
                    <form:input path="name" cssClass="small"/>            
				</p>
				<p>
					<h6>Status:
						<form:errors path="status" cssClass="notification error" element="span"/> 
					</h6>
          			<form:select path="status">
    					<form:option value="" label="*** Select Option ***" />
    					<form:options items="${badgeStatus}" />
					</form:select>          
				</p>
				<p>
					<h6>Image:
						<form:errors path="file" cssClass="notification error" element="span"/> 					
					</h6>
                	<input type="file" name="file" />             
				</p>
				<p>
					<h6>Eligibility:
						<form:errors path="eligibility" cssClass="notification error" element="span"/> 
					</h6>
                    <form:textarea path="eligibility" cssClass="wysiwyg"/>                   
				</p>
				<p>
					<h6>Description:
						<form:errors path="description" cssClass="notification error" element="span"/> 
					</h6>
                    <form:textarea path="description" cssClass="wysiwyg"/>              
				</p>
			</fieldset>
			<input type="submit" value="Update"/>
		</form:form>
	</div><!-- end .content-box-content -->
</div>