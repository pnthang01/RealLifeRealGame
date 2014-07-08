<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="content-box column-right main">
	<div class="content-box-header">
		<h3>Category Adding Form</h3>
	</div>
	
	<div class="content-box-content">
		<form:form method="POST" commandName="categoryForm" action="add">
			<fieldset>
				<p>
					<h6>Category Name:
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
					<h6>Description:
						<form:errors path="description" cssClass="notification error" element="span"/> 
					</h6>
                    <form:textarea path="description" cssClass="wysiwyg"/>              
				</p>
			</fieldset>
			<input type="submit" value="Add new"/>
		</form:form>
		
	</div><!-- end .content-box-content -->
</div>