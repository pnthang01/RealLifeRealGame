<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<!-- Make IE8 behave like IE7, necessary for charts -->
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		
		<title><tiles:insertAttribute name="title" ignore="true" /></title>
		
		<tiles:insertAttribute name="javascript_css" ignore="false" />
	</head>

	<body>
		<div id="bokeh">
		<div id="container">
			
			<div id="header">
				<h1 id="logo">Admin Control Panel </h1>
				
				<tiles:insertAttribute name="header_buttons" ignore="true" />

				<!-- Navigation -->
				<tiles:insertAttribute name="navigation" ignore="true" />					
				<!-- End #Navigation -->
			</div><!-- end #header -->
			
			<div id="content">
			
				<tiles:insertAttribute name="left-content" ignore="true" />	
			
				<tiles:insertAttribute name="content" ignore="false" />	
			
			</div><!-- end #content -->
							
			<div id="push"></div><!-- push footer down -->
			
		</div></div><!-- end #bokeh and #container -->
		
		<div id="footer">
			<tiles:insertAttribute name="footer" ignore="true" />	
		</div><!-- end #footer -->

	</body>
</html>