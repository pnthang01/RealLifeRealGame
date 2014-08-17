<?php
 include 'base_service.php';
 class Categories  extends BaseService {

 	function __construct() {
       		parent::__construct();		
   	}

   	public function exeGetCategoriesByID($id){
   		$request = $this->getResquet();
   		$request->http_get(HOT_SERVER."/category/getAllCategories?pageNumber=".$id);
   	}



 }