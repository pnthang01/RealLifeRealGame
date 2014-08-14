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
			$request->http_post(HOT_SERVER."/user/login");

		}

		public function exeRegister($user){

			$request = $this->getResquet();
			$request->setParam('restobject',$this->dataSendRegister($user));
			$request->http_post(HOT_SERVER."/user/signup");

		}

		function dataSendRegister($user){

			$dataSend =	array();
			$dataSend['ErrorCode'] = 0;
			$dataSend['Msg']='Success';
			$dataSend['data']=array('User'=>$user);
			return json_encode($dataSend);

		}


	}



	