<?php 
 class A {
 	public $name1;
 	public $name2;
 }

 class B {
 	public $abc;
 	public $dasda;
 }

$a= new A();
$a->name1="anhdt";
$b = new B();
$b->abc="cai gi do";
$b->dasda="asdas";
$a->name2=$b;

echo json_encode($a);