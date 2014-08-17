<?php
	header('Content-Type: application/json; charset=utf-8');
	include_once '../model/categories_service.php';
	$Categories = 	new Categories();
	$Categories->exeGetCategoriesByID('1');