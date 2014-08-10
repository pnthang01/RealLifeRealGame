<?php
	include '../model/login_service.php';
	$user = new UserVO();

	$user->setUsername("anh dinh");
	if(isset($_POST['user'])&& isset ($_POST['pass'])){
		//to do something
	} else {
		echo ERROR_SERVER;
	}
