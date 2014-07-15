<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul id="main-nav">
	<li><a href="<c:url value="/dashboard.html"/>"> Dashboard </a></li>

	<li><a href="#">
			<!-- use href="#" to indicate there's a submenu --> Module
	</a>

		<ul>
			<li><a href="<c:url value="/category/manage.html"/>">Task</a></li>
			<li><a href="#">Language</a></li>
			<li><a href="<c:url value="/badge/manage.html"/>">Point-Badge-Leaderboard</a></li>
			<li><a href="#">Profile</a></li>
		</ul></li>

	<li><a href="#"> Layout </a>

		<ul>
			<li><a href="#">Edit CSS</a></li>
			<li><a href="#">Manage Themes</a></li>
		</ul></li>

	<li><a href="#"> Users </a>

		<ul>
			<li><a href="#">Permissions</a></li>
			<li><a href="#" class="current">Manage Users</a></li>
			<li><a href="#">Manage Usergroups</a></li>
		</ul></li>

	<li><a href="#"> Settings </a>

		<ul>
			<li><a href="#">Account settings</a></li>
			<li><a href="#">Domain settings</a></li>
		</ul></li>
</ul>