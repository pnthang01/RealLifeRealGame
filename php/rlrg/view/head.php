<html>
	<head>
		<title>demo</title>
		<script type="text/javascript" src="/js/common.js"></script>
		<script src="/js/jquery-2.1.0.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(document).ready(function($) {
				console.log("ok");
				var url = "/include/controler/test.php?code=5k23e7zB";
				$.ajax({
					url: url,
					 success: function(response){
					 	// khi gui thanh cong thi lam cai gi do trong nay
					 	console.log(response.data.Category);
					 	$('#content').text(response.data.Category.Name);
					 }
					
				});
				
			});
		</script>

	</head>
	<body>