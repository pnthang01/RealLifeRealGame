				<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
				<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
				<h2><img src="<c:url value="/resources/images/icons/tools_32.png"/>" alt="Manage Users" />Manage Users</h2>
			
				<div class="notification information">
					This is an informative notification. Click me to hide me.
				</div>
				
				<div class="content-box column-left sidebar"><!-- use the class .sidebar in combination with .column-left to create a sidebar --><!-- using .closed makes sure the content box is closed by default -->
					<div class="content-box-header">
						<h3>Work to do</h3>
					</div>
					
					<div class="content-box-content">
						<p>This content box uses 1/3rd of the total width.</p>
						<p>The total width can be easily adjusted in main.css.</p>
					</div>
				</div>
				
				<div class="content-box column-right main">
					<div class="content-box-header">
						<h3>Statistics</h3>
						
						<!-- You can create tabs with unordered lists -->
						<ul>															
							<li>
								<a href="#area">Area chart</a>
							</li>
						</ul>
					</div>
					
					<div class="content-box-content">												
						<div class="tab-content" id="area">
							<table class="areagraph">
								<caption>Login Statistics</caption>
								<thead>
									<tr>
										<td></td>
										<c:forEach var="label" items="${statisticDTO.labels}" begin="0" end="${fn:length(statisticDTO.labels)}">
											<th scope="col"><c:out value="${label}" /></th>
										</c:forEach>
									</tr>
								</thead>

								<tbody>
									<tr>
										<th scope="row">Login times</th>
										<c:forEach var="value" items="${statisticDTO.values}" begin="0" end="${fn:length(statisticDTO.values)}">
											<td><c:out value="${value}" /></td>
										</c:forEach>
									</tr>
								</tbody>
							</table>
						</div><!-- end .tab-content -->
				
					</div><!-- end .content-box-content -->
				</div>
		