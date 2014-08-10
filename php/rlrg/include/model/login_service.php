<?php
	
	include 'base_service.php';
	include '../domain/user_vo.php';

	class UserService extends BaseService {


		function __construct() {
       		parent::__construct();
   		}


		public function exeLogin($userVO){

			$request = $this->getResquet();
			$request->setParam('username',$userVO->getUsername());
			$request->setParam('password',$userVO->getPassword());
			$request->http_post(HOT_SERVER."login");

		}

		public function exeRegister(){



		}


	}



	