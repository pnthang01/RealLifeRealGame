<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="content-box column-right main">
	<div class="content-box-header">
		<h3>Badge Updating Form</h3>
	</div>
	
	<div class="content-box-content">
		<form:form method="POST" commandName="badgeDTO" action="edit">
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