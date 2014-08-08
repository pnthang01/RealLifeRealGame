<html>
	<head>
		<meta charset="UTF-8">
		<title>home</title>
		<script type="text/javascript" src="/js/common.js"></script>
		<script src="/js/jquery-2.1.0.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			var token =   getCookie(TOKEN);
	       	if(token==""){
	          window.location.href = '/rlrg/login/index.html'; 
	       	}
			$(document).ready(function(){
				var firstname = getCookie(FIRST_NAME);
				var lastname = getCookie(LAST_NAME);
				$('#name').text(lastname);
				$("#logout").click(function(){
					var x = setCookie(TOKEN,"",0);
					window.location.href = '/login/index.html'; 
					return false;
				});
			});
		</script>
	</head>
	<body>
		<div id="content">	
			<div id="banner">Xin Chào <span id="name"></span></div>
			<br/>
			<a href="#" id="logout"> LOGOUT </a>
		</div>
		
	</body>

</html>