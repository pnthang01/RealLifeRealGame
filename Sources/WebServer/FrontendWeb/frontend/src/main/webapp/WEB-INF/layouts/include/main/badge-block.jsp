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
					popuData += '<li><a href="#"><img src="' + response.list[i].badgeImage
							+ '" alt="' + response.list[i].id   + response.list[i].badgeId
							+ '" /></a></li>';
				}
				$("#loader-left div ul").html(popuData);
			}
		});
	}
</script>
<div>
	<!-- Start Badge block -->
	<div class="btn_style lightgreen">
		<h4>badge</h4>
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