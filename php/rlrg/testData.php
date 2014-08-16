<?php 
	class BaseVO{

		public $ErrorCode=1;
		public $Msg="ok";
		public $data;

	}

	class User {

		public $userName;
		public $email;

	}

	$test = new BaseVO();
	$user =  new User();
	$user->userName="anhdinh";
	$user->email="dta89uct@gmail.com";
	$test->data['user']=$user;

	echo json_encode($test);