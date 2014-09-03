<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=utf-8"%>
<script type="text/javascript">
	function loadAchievements() {
		$.ajax({
			type : "GET",
			headers : { 'Profile_Token' : document.cookie },
			url : "<c:url value="/achievement/getAchievements" ><c:param name="pageNumber" value="1"/> </c:url>",
			success : function(response) {
				var popuData = '';
				for ( var i = 0; i < response.list.length; i++) {
					popuData += '<li><a href="#' + response.list[i].id + '"><img src="' + response.list[i].badge.fileName
							+ '" alt="' + response.list[i].badge.id
							+ '" /></a></li>';
				}
				$("#loader-left ul").html(popuData);
			}
		});
	}
</script>
<div>
	<!-- Start Badge block -->
	<div class="btn_style lightgreen">
		<h4>Huy hiệu</h4>
	</div>
	<div id="loader-left" class="loader_left">
		<div class="badge-photos">
			<ul>			

			</ul>
		</div>
		<div class="clear"></div>
		<a href="#">See more >></a>
	</div>
</div>
<!-- End Badge block -->