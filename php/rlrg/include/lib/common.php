<?php
	define("HOT_SERVER", "http://localhost:9090/data");
	
	define("ERROR_SERVER", '{"ErrorCode": "1", "Msg" : "HTTP Method is not support or param is not corect"}');
	//autoload class on core
	function __autoload($class_name) {
    		include "../core/"+$class_name . '.php';
	}


