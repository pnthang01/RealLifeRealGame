<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="content-box column-right main">
	<div class="content-box-header">
		<h3>Manage Categories</h3>
	</div>

	<div class="content-box-content">
		<div>
			<form:form method="GET" commandName="searchForm" action="manage.html">
				<form:errors path="*" cssClass="notification error" element="span"/>
				<table>
					<tr>
						<td style="width: 12%"><h6>Search key:</h6></td>
						<td style="width: 20%"><form:input path="keyword" /></td>
						<td style="width: 68%"><input type="submit" value="Search"></td>
					</tr>
				</table>
			</form:form>
		</div>
		<table>
			<!-- add the class .pagination to dynamically create a working pagination! The rel-attribute will tell how many items there are per page -->
			<thead>
				<tr>
					<th><input type="checkbox" /></th>
					<th>Code</th>
					<th>Name</th>
					<th>Description</th>
					<th>Status</th>
					<th>Action</th>
				</tr>
			</thead>

			<tfoot>
				<tr>
					<td colspan="5">
						<div class="bulk-actions">
							<a href="#" class="graybutton">Add new category</a>
						</div>
						<div class="pagination">
						<c:if test="${not empty totalPage || not empty categories}">
							<c:url value="" var="url">
								<c:if test="${not empty param.keyword}">
                                	<c:param name="keyword" value="${param.keyword}"/>
                                </c:if>
							</c:url>

							<c:forEach begin="1" end="${totalPage}" var="count">
								<c:url value="${url}" var="final_url">
									<c:param name="page" value="${count}"/>
								</c:url>
								<c:if test="${count == 1}">
									<a href="<c:url value="/category/manage.html${final_url}"/>">First</a>
								</c:if>
								<c:choose>
									<c:when test="${count == param.page}">
										<a href="<c:url value="/category/manage.html${final_url}"/>" class="graybutton pagelink active">
											<c:out value="${count}" />
										</a>
									</c:when>
									<c:otherwise>
										<a href="<c:url value="/category/manage.html${final_url}"/>" class="graybutton pagelink">
											<c:out value="${count}" />
										</a>
									</c:otherwise>
								</c:choose>
								<c:if test="${count == totalPage}">
									<a href="<c:url value="/category/manage.html${final_url}"/>">Last</a>
								</c:if>
							</c:forEach>
						</c:if>
						</div>
					</td>
				</tr>
			</tfoot>

			<tbody>
				<c:choose>
					<c:when test="${empty categories}">
						<tr>
							<td colspan="5">Data is empty!</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="category" items="${categories}" begin="0"
							end="${fn:length(categories)}">
							<tr>
								<td><input type="checkbox" /></td>
								<td><c:out value="${category.code}">No code</c:out></td>
								<td><c:out value="${category.name}">No name</c:out></td>
								<td style="width: 45%"><c:out value="${category.description}">No description</c:out></td>
								<td><c:out value="${category.status}">No status</c:out></td>
								<td>
									<a href="<c:url value="/category/edit.html"><c:param name="code" value="${category.code}"/></c:url>">
										<img src="<c:url value="/resources/images/icons/pencil.png"/>" alt="Edit">
									</a>
								</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
	<!-- end .content-box-content -->
</div>
<!-- end .content-box -->