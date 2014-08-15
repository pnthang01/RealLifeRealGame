<?php 
	$test =	array();
	$test['ErrorCode'] = 0;
	$test['Msg']='Success';


	class User {

		public $userName;
		public $email;

		function tet(){

		}


	}

	
	$user = new User();
	$user->userName = "anh dt";
	$user->email = "anhdt@yahoo.com";

	$test['data']=array('User'=>$user);
	echo json_encode($test);