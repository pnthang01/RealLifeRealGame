<?php
//set POST variables
if(isset($_POST['user']) && isset ($_POST['pass'])){
	$username = $_POST['user'];
	$password = $_POST['pass'];
	$url = 'http://localhost:9090/data/user/login';
	$fields_string="";
	$fields = array(
		'username' => urlencode($username),
		'password' => urlencode($password)					
	);

	//url-ify the data for the POST
	foreach($fields as $key=>$value) { $fields_string .= $key.'='.$value.'&'; }
	rtrim($fields_string, '&');

	//open connection
	$ch = curl_init();

	//set the url, number of POST vars, POST data
	curl_setopt($ch,CURLOPT_URL, $url);
	curl_setopt($ch,CURLOPT_POST, count($fields));
	curl_setopt($ch,CURLOPT_POSTFIELDS, $fields_string);
    
	//execute post
	$result = curl_exec($ch);

	//close connection
	curl_close($ch);
} else {
	echo '{"ErrorCode": "1", "Msg" : "HTTP Method is not support or param is not corect"}';
}
