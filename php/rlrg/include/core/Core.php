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


		public function http_get($url){
			$ch = curl_init();
			curl_setopt($ch,CURLOPT_URL, $url);
			$result = curl_exec($ch);
			curl_close($ch);
		}

		public function setParam($name,$value){
			$this->array_param[$name] = urlencode($value);
		}

		public function getTest(){

			return $this->array_param ;
		}
	}



