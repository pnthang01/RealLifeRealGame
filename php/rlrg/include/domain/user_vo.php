
 <?php
 class UserVO {
		private $username;
		private $password;
		private $sex;

		public function setUsername($username){
			$this->username=$username;
		}

		public function setPassword($password){
			$this->password=$password;

		}

		public function setSex($sex){
			$this->sex=$sex;
		}

		public function getUsername(){
			return $this->username;

		}

		public function getPassword(){
			return $this->password;
		}

		public function getSex(){
			return $this->sex;
			
		}

	}