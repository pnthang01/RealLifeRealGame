<?php
	
	include_once 'base_service.php';
	include_once '../domain/user_vo.php';
	include_once '../domain/base_vo.php';

	class UserService extends BaseService {


		function __construct() {

       		parent::__construct();
       		
   		}


		public function exeLogin($userVO){

			$request = $this->getResquet();
			$request->setParam('username',$userVO->getUsername());
			$request->setParam('password',$userVO->getPassword());
			$request->http_post(HOT_SERVER."/user/login");

		}

		public function exeRegister($user){

			$request = $this->getResquet();
			$request->setParam(RESTOBJECT,$this->dataSendRegister($user));
			$request->http_post(HOT_SERVER."/user/signup");

		}

		function dataSendRegister($user){
			
			$result = new BaseVO();
			$result->data['User'] =	$user;
			return json_encode($result);
		}


	}



	