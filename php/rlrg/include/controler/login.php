<?php
	include '../model/login_service.php';
	$user = new UserVO();
	if(isset($_POST['user']) || isset($_POST['pass'])){
		$user->setUsername($_POST['user']);
		$user->setPassword($_POST['pass']);
		$userSV = new UserService();
		$userSV->exeLogin($user);
	} else {
		echo ERROR_SERVER;
	}
