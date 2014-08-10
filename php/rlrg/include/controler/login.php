<?php
	include '../model/login_service.php';
	$user = new UserVO();

	$user->setUsername("testacc");
	$user->setPassword("123456");
	
	$userSV = new UserService();
	$userSV->exeLogin($user);

	
