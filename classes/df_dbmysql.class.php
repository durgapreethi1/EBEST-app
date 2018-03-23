<?php
///////////////////////////////////////////////////////////////                                                          //
//      Dynaform Development                                 //
//      Release 1.0                                          //
//      Copyright (c) 2015 Devajyoti Das                     //
//      Website: http://dynaform.globalhorizon.org           //
//                                                           //
///////////////////////////////////////////////////////////////

    class Transaction {
        var $dbh;

        function Transaction() {
            global $DATABASE;
            $this->dbh = mysqli_connect($DATABASE['127.0.0.1'], $DATABASE['root'], $DATABASE[''], $DATABASE['ebest_db']);
        }

        function _Transaction() {
            mysqli_disconnect($this->dbh);
        }

        function begin() {
            mysql_query("BEGIN", $this->dbh);
        }

        function rollback() {
            mysql_query("ROLLBACK", $this->dbh);
        }

        function commit() {
            mysql_query("COMMIT", $this->dbh);
        }
    }

    class MySQLdb {
        var $dbh;
        var $error=null;

        function MySQLdb() {
            global $DATABASE;

            try {
            	$this->dbh = mysqli_connect($DATABASE['HOST'], $DATABASE['USER'], $DATABASE['PASS'], $DATABASE['NAME']);
            } catch(Exception $e) {
            	die('error:' . $e->getMessage() . "<br/>" . mysqli_error($this->dbh));
            }
            if (!$this->dbh) {
                die('Could not connect: ' . mysql_error());
            }
        }

        function _MySQLdb($host, $username, $password) {
            try {
            	mysqli_close($this->dbh);
            } catch(Exception $e) {
            	die('error:' . $e->getMessage() . "<br/>" . mysqli_error($this->dbh));
            }
        }

        function escape_string($sql) {
        	return mysqli_real_escape_string($this->dbh, $sql);
        }

        function query($sql, $dieonerror=true, &$error=null) {
            $result = mysqli_query($this->dbh, $sql);
            if($result != null && stristr($sql, 'insert into')) {
				$result = mysqli_insert_id($this->dbh);
				return($result);
            }
            if (!$result) {
                if($dieonerror) {
                	if(strstr(mysqli_error($this->dbh), "max_questions")) {
                		die("<font name='verdana' color=orange><h1>It looks like our system is too much loaded with queries, we will be up in next 1 hour</h1><p>-administrator</p></font>");
                		$this->error=mysqli_error($this->dbh);
                	} else {
	                	die('Invalid query: "' . $sql . '"' . mysqli_error($this->dbh));
	                	$this->error=mysqli_error($this->dbh);
	                }
                } else {
                	$this->error=mysqli_error($this->dbh);
                }
            }
            $error = $this->error;
			return($result);
		}

        function prepare($sql) {
			return mysqli_prepare($this->dbh, $sql);
		}
		
        function select_db($dbname) {
            if (!mysqli_select_db($this->dbh, $dbname)) {
                die('Could not select database: $dbname, ' . mysqli_error($this->dbh));
            }
        }
    }
?>