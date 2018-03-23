<?php

	$_INPUT = $_POST;
	$status=array("errorcode"=>0, "message"=>"SUCCESS");
	$fname = isset($_INPUT["fname"])?trim($_INPUT["fname"]):"";
	$lname = isset($_INPUT["lname"])?trim($_INPUT["lname"]):"";
	$email = isset($_INPUT["email"])?trim($_INPUT["email"]):"";
	$mobile = isset($_INPUT["mobile"])?trim($_INPUT["mobile"]):"";
	$password = isset($_INPUT["password"])?trim($_INPUT["password"]):"";
	$line1 = isset($_INPUT["line1"])?trim($_INPUT["line1"]):"";
	$line2 = isset($_INPUT["line2"])?trim($_INPUT["line2"]):"";
	$city = isset($_INPUT["city"])?trim($_INPUT["city"]):"";
	$state = isset($_INPUT["state"])?trim($_INPUT["state"]):"";
	$pincode = isset($_INPUT["pincode"])?trim($_INPUT["pincode"]):"";
	if($fname == "") {
		$status=array("errorcode"=>-1, "message"=>"Firstname is empty");
	} else if($lname == "") {
		$status=array("errorcode"=>-1, "message"=>"Lastname is empty");
	} else if($email == "" || !filter_var($email, FILTER_VALIDATE_EMAIL)) {
		$status=array("errorcode"=>-1, "message"=>"Invalid Email ID");
	} else if($mobile == "" || !is_numeric($mobile) || strlen($mobile) != 10) {
		$status=array("errorcode"=>-1, "message"=>"Invalid Mobile number");
	} else if($password == "" || strlen($password) <= 5) {
		$status=array("errorcode"=>-1, "message"=>"Password length is less than 6 characters");
	} else if($line1 == "") {
		$status=array("errorcode"=>-1, "message"=>"Address line 1 is empty");
	} else if($city == "") {
		$status=array("errorcode"=>-1, "message"=>"Invalid City");
	} else if($state == "") {
		$status=array("errorcode"=>-1, "message"=>"Invalid State");
	} else if($pincode == "" || strlen($pincode) != 6  || !is_numeric($pincode)) {
		$status=array("errorcode"=>-1, "message"=>"Invalid Pin code");
	}
	
	if($status["errorcode"] == 0) {
		//$obj=new database;
		//$obj1 =  new user;
		
		//call_user_method("MySQLdb", $obj);
		//call_user_method("user_registration", $obj1,$fname, $lname, $email, $mobile, $password,$line1,$line2,$city,$state,$pincode);
		
		$conn = new mysqli("127.0.0.1","root","","ebest_db");
        if(!$conn){
            $status=array("errorcode"=>-1, "message"=>"No DB Connection");
        } else {
			$sql = "select count(*) as cnt from tbl_user_registration where Email='" .$email . "'";
			$result = $conn->query($sql);
			$resulttype = true;
			$row = mysqli_fetch_array ($result);
			//if($row = mysqli_fetch_array($result)) {
				if($row["cnt"]>0) {
					$status=array("errorcode"=>-1, "message"=>"Another user exists with this email ID");
				}
			//}
		}
		if($status["errorcode"] == 0) {
			 if(!$conn){
				$status=array("errorcode"=>-1, "message"=>"No DB Connection");
			} else {
				$userid = 0;
				$pst = $conn->prepare("insert into tbl_user_registration(First_Name,Last_Name,Email,Mobile,Password) values(?,?,?,?,?)");
				$pst->bind_param("sssis",$fname,$lname,$email,$mobile,$password);
				$result = $pst->execute();
				if(!$result) {
					$status=array("errorcode"=>-1, "message"=>mysqli_error($conn));
				} else {
					$userid = mysqli_insert_id($conn);
				}
				$pst->close();
				if($status["errorcode"] == 0 && $userid>0) {
					$pst1 = $conn->prepare("insert into tbl_address(User_Id,Line_1,Line_2,City,State,Pincode) values(?,?,?,?,?,?)");
					$pst1->bind_param("issssi",$userid,$line1,$line2,$city,$state,$pincode);
					$result = $pst1->execute();
					echo $result?"true":"false";
					$pst1->close();
				}
			}
		}
		$conn->close();
	}

	echo json_encode($status);
?>