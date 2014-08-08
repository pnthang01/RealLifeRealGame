<?php
 	include '../lib/common.php';

 	class BaseService {

 		private $request;

 		public function __construct() {
       		$this->request = new Core();
   		}


   		public getResquet(){
   			return $this->request;
   		}

 	}