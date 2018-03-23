<?php
	
	$servername = "127.0.0.1";
	$username = "root";
	$password = "";
	$dbname = "ebest_db";

	$_INPUT = $_POST;
	$status=array("errorcode"=>0, "message"=>"SUCCESS");
	
	$email = isset($_INPUT["email"])?trim($_INPUT["email"]):"";
	
	if($email == "" || !filter_var($email, FILTER_VALIDATE_EMAIL)) {
		$status=array("errorcode"=>-1, "message"=>"Invalid Email ID");
	}
	
	if($status["errorcode"] == 0) {
		$conn = new mysqli($servername, $username, $password, $dbname);
	
		if ($conn->connect_error) {
			die("Connection failed: " . $conn->connect_error);
		}
		
		$sql1 = "SELECT Email from tbl_user_registration WHERE email = '$email'";
		$result = $conn->query($sql1);
		if ($result->num_rows > 0) {
			$tz = new DateTimeZone('Asia/Kolkata');
			$password = (new DateTime("now", $tz))->format("YmdHis").rand(1000,9999);
			$reset_password = substr(md5($password), 0, 8);
			$sql = "UPDATE tbl_user_registration SET temp_password='$reset_password' WHERE email='$email'";
			if ($conn->query($sql) == TRUE) {
				$errorcode = 0;
				$message = "Your temporary password is emailed to your registered email ID";
			} else {
				$errorcode = -1;
				$message = "Error generating temp password";
			}
		} else {
			$errorcode = -1;
			$message = "Invalid user";
		}
		$status=array("errorcode"=> $errorcode, "message"=>$message);
	}
	echo json_encode($status);	
?>