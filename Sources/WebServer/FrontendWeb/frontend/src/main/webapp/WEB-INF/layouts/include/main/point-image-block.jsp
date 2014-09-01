<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=utf-8"%>
<script type="text/javascript">
	function loadPoint() {
		$.ajax({
			url : "<c:url value="/point/getUserPoint"/>",
			type : "GET",
			success : function(response) {
				$("#point_image").attr('alt', response.point).attr('src', response.display);
				$("#point_title span").text(response.ranked);
			}
		});
	}
</script>
<img id="point_image" src="<c:url value="/main/images/pic1.jpg" />" alt="0" />
<div id="point_title" class="point_title">
	<span>apple pie</span>
</div>
