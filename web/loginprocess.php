<?php
	include_once __DIR__ . "/../pathdefinitions.php";
	include_once APP_CONFIGURATION . "/myconfig.php";

	$email = $_POST["email"];
	$password = $_POST["password"];
	$user = new User();

	$result = $user->user_login($email, $password);
	if($result) {
		$errorcode = 0;
		$message = "Successfully logged in";
	} else {
		$errorcode = -1;
		$message = "Failed to log in";
	}
	$status=array("errorcode"=>$errorcode, "message"=>$message);
	echo json_encode($status);
?>