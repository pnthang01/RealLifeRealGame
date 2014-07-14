<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="content-box column-right main">
	<div class="content-box-header">
		<h3>User Updating Form</h3>
	</div>
	
	<div class="content-box-content">
		<form:form method="POST" commandName="userDTO" action="edit">
			<form:hidden path="code" />
			<fieldset>
				<p>
					<h6>User Name:
						<form:errors path="name" cssClass="notification error" element="span"/> 
					</h6> 
                    <form:input path="name" cssClass="small"/>            
				</p>
				<p>
					<h6>Position:
						<form:errors path="position" cssClass="notification error" element="span"/> 
					</h6>
                    <form:input path="position" cssClass="small"/>                   
				</p>
				<p>
					<h6>Position:
						<form:errors path="status" cssClass="notification error" element="span"/> 
					</h6>
          			<form:radiobutton path="status" value="true" label="Active" /><span>Active</span>
					<form:radiobutton path="status" value="false" label="Deactive" /><span>Deactive</span>             
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