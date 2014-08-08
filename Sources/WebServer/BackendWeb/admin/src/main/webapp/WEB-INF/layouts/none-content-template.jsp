<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<!-- Make IE8 behave like IE7, necessary for charts -->
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		
		<link rel="shortcut icon" href="<c:url value="/resources/favicon.ico"/>" type="image/x-icon"/>
		
		<title><tiles:insertAttribute name="title" ignore="true" /></title>
		
		<tiles:insertAttribute name="javascript_css" ignore="false" />
	</head>

	<body>
		<div id="bokeh"><div id="container">
			
			<div id="header">
				<h1 id="logo">Admin Control Panel</h1>
			</div><!-- end #header -->
			
			<div id="content">
			
				<tiles:insertAttribute name="content" ignore="false" />	
											
			</div><!-- end #content -->
			
			<div id="push"></div><!-- push footer down -->
			
		</div></div><!-- end #container -->
		
		<div id="footer">
			<tiles:insertAttribute name="footer" ignore="true" />	
		</div><!-- end #footer and #bokeh -->
		
	</body>
</html>