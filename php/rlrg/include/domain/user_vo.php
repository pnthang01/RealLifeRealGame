
 <?php
 class UserVO {

		public $Username;
		public $Password;
		public $Email;
		public $FirstName;

		public function setUsername($username){
			$this->Username=$username;
		}

		public function setPassword($password){
			$this->Password=$password;

		}

		

		public function getUsername(){
			return $this->Username;

		}

		public function getPassword(){
			return $this->Password;
		}


	}