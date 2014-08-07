<?php
	class Core {

		private $array_param  = array();

		public function http_post($url){

			$fields_string="";
			$fields = $this->array_param;
			foreach($fields as $key=>$value) { $fields_string .= $key.'='.$value.'&'; }
			rtrim($fields_string, '&');

			//open connection
			$ch = curl_init();

			//set the url, number of POST vars, POST data
			curl_setopt($ch,CURLOPT_URL, $url);
			curl_setopt($ch,CURLOPT_POST, count($fields));
			curl_setopt($ch,CURLOPT_POSTFIELDS, $fields_string);
		    
			//execute post
			$result = curl_exec($ch);

			//close connection
			curl_close($ch);
		}

		public function setParam($name,$value){
			$this->array_param[$name] = urlencode($value);
		}

		public function getTest(){

			return $this->array_param ;
		}
	}


	$anhdt = new Core();
	$anhdt->setParam("number","anh dinh dep trai");
	$anhdt->setParam("number2","anh dinh dep traidasd");
	$anhdt->setParam("number3","anh dinh dep tradasdasi");
	$arrayResult =  $anhdt -> getTest();

	echo "<pre>";
		print_r($arrayResult);
	echo "<pre>";
