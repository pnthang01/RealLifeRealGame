<?php
	include 'base_service.php';// cái này dùng để import nó vào 

 	class TestService  extends BaseService {

 	function __construct() {
       		parent::__construct();		
   	}
   	//nhận một tham số giửi từ client là code mình thêm tham số đó vào 
   public function exeTodo($code){

   		$request = $this->getResquet();// cái này trả về cho cậu một đối tượng là Resquest;
   		// vìậu muốn lấy dữ liệu theo get nên việc tiếp theo là gọi một phương thức của nó ra 
   		//cái đối tượng này nhận 1 tham số duy nhất là url
   		// nỗi chuỗi trong php là đẫu . cái HOT_SERVER được define ở file common.php


   		$request->http_get(HOT_SERVER.'/category/getCategoryByCode?code='.$code);
   		//thế là xong phần service

   	}



 }