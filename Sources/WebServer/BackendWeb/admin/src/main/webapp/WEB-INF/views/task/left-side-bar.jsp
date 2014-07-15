<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>
	<img src="<c:url value="/resources/images/icons/tools_32.png"/>"
		alt="Task Module" />Task Module
</h2>

<div class="content-box column-left sidebar">
	<!-- use the class .sidebar in combination with .column-left to create a sidebar -->
	<!-- using .closed makes sure the content box is closed by default -->
	<div class="content-box-header">
		<h3>Task Module</h3>
	</div>

	<div class="content-box-content">
		<ul>
				<li><a href="<c:url value="/category/manage.html"/>">Manage Categories</a></li>
				<li><a href="<c:url value="/task/manage.html"/>">Manage Tasks</a></li>
		</ul>
	</div>
</div>