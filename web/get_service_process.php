<?php

	$_INPUT = $_POST;
	$status=array("errorcode"=>0, "message"=>"SUCCESS");
	$pincode = isset($_INPUT["pincode"])?trim($_INPUT["pincode"]):"";
	if($pincode == "" || strlen($pincode) != 6  || !is_numeric($pincode)) {
		$status=array("errorcode"=>-1, "message"=>"Invalid Pin code");
	}

	if($status["errorcode"] == 0) {

		$conn = new mysqli("127.0.0.1","root","","ebest_db");

		if($status["errorcode"] == 0) {
			 if(!$conn){
				$status=array("errorcode"=>-1, "message"=>"No DB Connection");
			} else {
				$sql = "select distinct S.service_id, S.service_name from tbl_services S, tbl_city_services CS, tbl_cities C, tbl_zip_code Z where zip_code='$pincode' AND Z.city=C.city AND C.city_id=CS.city_id AND CS.service_id=S.service_id";
				$result = $conn->query($sql);
				$resulttype = true;
				$arData = array();
				$cnt = 0;
				while($row = mysqli_fetch_array($result)) {
					$arData[] = array("id"=>$row["service_id"], "name"=>$row["service_name"]);
					$cnt++;
				}

				if(!$result) {
					$status=array("errorcode"=>-1, "message"=>mysqli_error($conn));
				}else if($cnt == 0){
					$status=array("errorcode"=>0, "message"=>"Service Not Available", "data"=>$arData);
				} else {
					$status=array("errorcode"=>0, "message"=>"Service Available", "data"=>$arData);
				}
			}
		}
	}
	echo json_encode($status);
?>