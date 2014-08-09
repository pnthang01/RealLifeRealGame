<?php
	
	include 'base_service.php';

	class UserService extends BaseService {

	
		private $username;
		private $password;
		private $sex;

		function __construct() {
       		parent::__construct();
   		}



		private function exeLogin(){

			$request = $this->getResquet();
			$request->setParam('username',$this->username);
			$request->setParam('password',$this->password);
			$request->http_post(HOT_SERVER."login");

		}

		private function exeRegister(){



		}


	}



	