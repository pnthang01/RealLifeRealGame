<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=utf-8"%>
<script type="text/javascript">
	$(function() {
		function createTask(){
			var valid = true;
			valid = valid && checkLength($("#name"), 3, 70);
			if(valid){
				$.ajax({
					type : "POST",
					headers : { 'Profile_Token' : document.cookie },
					url : $("#taskForm").attr( "action"),
					data: $("#taskForm").serialize(),
					success : function(response) {
						location.reload();
					}
				});
	            $(this).dialog("close");
			}
		}
		
		dialog = $("#create-task-form").dialog({
			autoOpen: false,
		    modal: false,
		    draggable: true,
		    resizable: true,
		    appendTo: "#create-task",
		    dialogClass: "create-task-form",
		    width: 300,
		    height:340,
		    position: { my: "right bottom", at: "left top", of: "#create-task" },
		    buttons: {
		        "Tạo nhiệm vụ": function() {
		        	createTask();
		        }
		    },
			close : function() {
				$('#taskForm')[0].reset();
			}
		});
		
		$("#create-task").on("click", function() {
    		$("#completeTime").datepicker("setDate", new Date());
			dialog.dialog("open");
		});
		
		$( "#completeTime" ).datepicker({
			minDate: 0, 
			maxDate: null,
			changeMonth: true,
			changeYear: true,
		});
		$.datepicker.setDefaults( $.datepicker.regional[ "vi" ] );

		
	});
</script>
<div class="btn_style">
	<script type="text/javascript">
		$(function() {
			var dd = new DropDown($('#dropdown'));
			$(document).click(function() {
				$('.wrapper-dropdown-3').removeClass('active');
			});
		});
	</script>
	<div class="icon-dropdown">
		<div id="dropdown" class="wrapper-dropdown-3">
			<i class="dropdown-icon"> </i>
			<ul class="dropdown">
				<li><a id="create-task" href="#"> Thêm nhiệm vụ</a></li>
<!-- 				<li><a href="#"> Tùy chỉnh</a></li> -->
			</ul>
		</div>
	</div>

	<div id="create-task-form" title="Tạo nhiệm vụ mới"
		class="create-task-form">
		<form action="<c:url value="task/createNewTask" />" method="POST" id="taskForm">
			<fieldset>
			 	<label for="name">Tên nhiệm vụ</label>
				<input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all">
				<label for="email">Thời gian hoàn thành</label>
				<input type="text" name="completeTime" id="completeTime" class="text ui-widget-content ui-corner-all">
				<label for="email">Loại</label>
				<select id="categories" name="category" class="select ui-widget-content ui-corner-all">
					<option value="5k23e7zB">Thể lực</option>
					<option value="HUesJMM6">Tri thức</option>
					<option value="WpxxS9ZS">Nghệ thuật</option>
					<option value="wYaviD74">Quan hệ - Giao tiếp</option>
					<option value="5nrGZSco">Nghề nghiệp</option>
				</select>
			</fieldset>
		</form>
	</div>

	<div class="top-search-box">
		<input type="text" value="Tìm kiếm nhiệm vụ" onfocus="this.value = '';"
			onblur="if (this.value == '') {this.value = 'Tìm kiếm nhiệm vụ';}">
		<input type="submit" value=" " />
	</div>
</div>