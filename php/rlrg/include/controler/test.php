<?php 
 header('Content-Type: application/json; charset=utf-8');
 include_once '../model/test_service.php';

 //khi đã include một file vào cậu new một object khai báo tù file đó
 //trong php để bắt data get từ client cậu dùng một biến đặc biệt $_GET['tên parame']
 //ở đây mình sẽ check có biến đó không trước
if(isset($_GET['code'])){
	 $testSV = new TestService();// php không có datatype nên biến $testSV là một object
	 $testSV->exeTodo($_GET['code']); // gọi đến phương thức mà nó hỗ trợ
} else {
	echo ERROR_SERVER;
}

