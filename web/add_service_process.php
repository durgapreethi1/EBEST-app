<?php

	$_INPUT = $_POST;
	$status=array("errorcode"=>0, "message"=>"SUCCESS");
	$sname = isset($_INPUT["sname"])?trim($_INPUT["sname"]):"";
	$city = isset($_INPUT["city"])?trim($_INPUT["city"]):"";
	$pincode = isset($_INPUT["pincode"])?trim($_INPUT["pincode"]):"";
	if($sname == "") {
		$status=array("errorcode"=>-1, "message"=>"Servicetname is empty");
	} else if($city == "") {
		$status=array("errorcode"=>-1, "message"=>"Invalid City");
	} else if($pincode == "" || strlen($pincode) != 6  || !is_numeric($pincode)) {
		$status=array("errorcode"=>-1, "message"=>"Invalid Pin code");
	}
	
	if($status["errorcode"] == 0) {
		
		$conn = new mysqli("127.0.0.1","root","","ebest_db");
        
		if($status["errorcode"] == 0) {
			 if(!$conn){
				$status=array("errorcode"=>-1, "message"=>"No DB Connection");
			} else {
				$pst = $conn->prepare("insert into tbl_services(service_name,is_available) values(?,1)");
				$pst->bind_param("s",$sname);
				$result = $pst->execute();
				if(!$result) {
					$status=array("errorcode"=>-1, "message"=>mysqli_error($conn));
				} else {
					$status=array("errorcode"=>0, "message"=>"Service Added");
				}
				$pst->close();
				
				$pst1 = $conn->prepare("insert into tbl_cities(city_name,is_available) values(?,1)");
				$pst1->bind_param("s",$city);
				$result = $pst1->execute();
				if(!$result) {
					$status=array("errorcode"=>-1, "message"=>mysqli_error($result));
				} else {
					$status=array("errorcode"=>0, "message"=>"City Added");
				}
				$pst1->close();
				
				$pst2 = $conn->prepare("insert into tbl_zipcode(city_name,pincode) values(?,?)");
				$pst2->bind_param("ss",$city,$pincode);
				$result = $pst2->execute();
				if(!$result) {
					$status=array("errorcode"=>-1, "message"=>mysqli_error($result));
				} else {
					$status=array("errorcode"=>0, "message"=>"Pincode Added");
				}
				$pst2->close();
				
				$service_id = 0;
				$city_id = 0;
				
				$sql = "select service_id as sid from tbl_services where service_name='" .$sname . "'";
				$result_1 = $conn->query($sql);
				$resulttype = true;
				$row1 = mysqli_fetch_array ($result_1);
				$sql1 = "select city_id  as cid from tbl_cities where city_name='" .$city . "' and is_available = 1";
				$result_2 = $conn->query($sql1);
				$resulttype = true; 
				$row2 = mysqli_fetch_array ($result_2);
				if(!$result_1 || !$result_2) {
					$status=array("errorcode"=>-1, "message"=>mysqli_error($result_1));
					$status=array("errorcode"=>-1, "message"=>mysqli_error($result_2));
				} else if($result_1 && $result_2){
					$service_id = $row1["sid"];
					$city_id = $row2["cid"];
					
					$pst3 = $conn->prepare("insert into tbl_city_services(city_id,service_id,is_available) values(" .$city_id . "," .$service_id .",1)");
					
					$result2 = $pst3->execute();
					
					if(!$result2) {
						$status=array("errorcode"=>-1, "message"=>mysqli_error($conn));
					} else {
						$status=array("errorcode"=>0, "message"=>"Service Added Successfully");
					}
				}
			}
		}
		$conn->close();
	}

	echo json_encode($status);
?>