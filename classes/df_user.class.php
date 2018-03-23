<?php
	class User {
		var $conn;
		public function __construct() {
			$this->conn = new MySQLdb();
		}

		function user_registration($firstname, $lastname, $email, $mobile, $password,$Line_1,$Line_2,$City,$State,$Pincode) {
			$sql = "select count(*) as cnt from tbl_user_registration where Email='" .$email . "'";
			$result = $this->conn->query($sql);
			$resulttype = true;

			$row = mysqli_fetch_array ($result,true );
			if($row = mysqli_fetch_array($result)) {
				if($row["cnt"]>0) {
					$status=array("errorcode"=>-1, "message"=>"Another user exists with this email ID");
				}
			}
			$res1 = false;
			$res2 = false;
			$status = array("errorcode"=>0, "message"=>"Success");

			$query = $this->conn->prepare("INSERT INTO tbl_user_registration(First_Name,Last_Name,Email,Mobile,Password) VALUES(?,?,?,?,?);");
			$query->bind_param("sssis", $firstname, $lastname,$email,$mobile,$password);
			$result = $query->execute();
			if($query->num_rows ==1) {
				$res1 = true;
			} else {
				$res1  = false;
			}
			$query->close();


			if($status["errorcode"] == 0) {
				$userid = mysqli_insert_id($this->conn);
				$query = $this->conn->prepare("insert into tbl_address(User_Id,Line_1,Line_2,City,State,Pincode) values(?,?,?,?,?,?)");
				$query->bind_param("issssi",$userid,$Line_1,$Line_2,$City,$State,$Pincode);
				$result = $query->execute();
				if($query->num_rows ==1) {
					$res2 = true;
					echo("inserted!!");
				} else {
					$res2  = false;
				}
				$query->close();
				if(!$result) {
					$status=array("errorcode"=>-1, "message"=>"Registration failed");
				}
			}
			if($res1 == true && $res2 == true){
				$result = true;
			}
			else{
				$result = false;
			}
			return $result;
		}

		function user_login($email, $password) {
			$res = false;
			$result = $this->conn->query("SELECT Email,Password from tbl_user_registration WHERE email = '$email' and (password = '$password' OR  temp_password = '$password')");
			if($row = mysqli_fetch_array($result)) {
				$res = true;
			} else {
				$res  = false;
			}
			return $res;
		}

		function reset_password($email){
			$res = false;
			$sql1 = "SELECT Email from tbl_user_registration WHERE email = '$email'";
			$result = $conn->query($sql1);
			if ($result->num_rows > 0) {
				$tz = new DateTimeZone('Asia/Kolkata');
				$password = (new DateTime("now", $tz))->format("YmdHis").rand(1000,9999);
				$reset_password = substr(md5($password), 0, 8);
				$sql = "UPDATE tbl_user_registration SET temp_password='$reset_password' WHERE email='$email'";
				if ($conn->query($sql) == TRUE) {
					$res = true;
					} else {
					$res = false;
				}
			} else {
				$res = false;
				}
			return $res;
		}

		function change_password($email, $newpassword, $currentpassword, $confirmpassword){
			$res = false;
			$sql1 = "SELECT Email,Password from tbl_user_registration WHERE email = '$email' and (password = '$currentpassword' or temp_password = '$currentpassword')";
			$result = $conn->query($sql1);
			if ($result->num_rows > 0) {
				$sql = "UPDATE tbl_user_registration SET password='$newpassword', temp_password=NULL WHERE email='$email'";
				if ($conn->query($sql) == TRUE) {
					$res = true;
				} else {
					$res = false;
				}

			} else {
				$res = false;
			}
			return $res;
		}
	}

?>