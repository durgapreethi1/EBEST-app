<?php

$servername = "127.0.0.1";
$username = "root";
$password = "";
$dbname = "ebest_db";

	$_INPUT = $_POST;
	$status=array("errorcode"=>0, "message"=>"SUCCESS");
	
	$email = isset($_INPUT["email"])?trim($_INPUT["email"]):"";
	$currentpassword = isset($_INPUT["currentpassword"])?trim($_INPUT["currentpassword"]):"";
	$newpassword = isset($_INPUT["newpassword"])?trim($_INPUT["newpassword"]):"";
	$confirmpassword = isset($_INPUT["confirmpassword"])?trim($_INPUT["confirmpassword"]):"";
	if($email == "" || !filter_var($email, FILTER_VALIDATE_EMAIL)) {
		$status=array("errorcode"=>-1, "message"=>"Invalid Email ID");
	}else if($newpassword == "" || strlen($newpassword) <= 5) {
		$status=array("errorcode"=>-1, "message"=>"Password length is less than 6 characters");
	}
	else if($currentpassword == $newpassword){
		$status=array("errorcode"=>-1, "message"=>"You entered old password.Please enter new password");
	}
	else if($confirmpassword == "" || strlen($confirmpassword) <= 5) {
		$status=array("errorcode"=>-1, "message"=>"Password length is less than 6 characters");
	}
	else if($newpassword != $confirmpassword){
		$status=array("errorcode"=>-1, "message"=>"Passwords do not match");
	}


	if($status["errorcode"] == 0) {
		$conn = new mysqli($servername, $username, $password, $dbname);
	
		if ($conn->connect_error) {
			die("Connection failed: " . $conn->connect_error);
		}
		
		$sql1 = "SELECT Email,Password from tbl_user_registration WHERE email = '$email' and (password = '$currentpassword' or temp_password = '$currentpassword')";
		$result = $conn->query($sql1);
		if ($result->num_rows > 0) {
			$sql = "UPDATE tbl_user_registration SET password='$newpassword', temp_password=NULL WHERE email='$email'";
			if ($conn->query($sql) == TRUE) {
				$errorcode = 0;
				$message = "Password changed successfully";
			} else {
				$errorcode = -1;
				$message = "Error updating record: " . $conn->error;
			}
		
		} else {
			$errorcode = -1;
			$message = "Invalid credentials, cannot change password";
		}
		$status=array("errorcode"=> $errorcode, "message"=>$message);
	}
	
	echo json_encode($status);
?>