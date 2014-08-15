<?php

include '../model/login_service.php';
$user = new UserVO();
if (isset($_POST['user'])) {
    $user->setUsername($_POST['user']);
    $user->setPassword($_POST['password']);
    $user->Email = $_POST['email'];
    $user->FirstName = $_POST['firstName'];
    $userSV = new UserService();
    $userSV->exeRegister($user);
} else {
    echo ERROR_SERVER;
}
		
	