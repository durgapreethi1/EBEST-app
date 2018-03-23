<?php
///////////////////////////////////////////////////////////////                                                          //
//      Dynaform                                             //
//      Release 1.0                                          //
//      Copyright (c) 2015 Devajyoti Das                     //
//      Website: http://form.globalhorizon.org               //
//                                                           //
///////////////////////////////////////////////////////////////

global $DATABASE;

// local (http://localhost:81/dynaform
$__instances['local192'] = array(
	// should be a unique part of the url (or the entire url if you wish)
	'prefix' => '192.168.1.6',
	'db_host' => 'localhost',
	'db_port' => 3306,
	'db_user' => 'root',
	'db_password' => '',
	'db_name' => 'ebest_db',
	'db_type' => 'mysql',
	'db_prefix' => ''
);


// local (http://localhost:81/dynaform
$__instances['local1'] = array(
	// should be a unique part of the url (or the entire url if you wish)
	'prefix' => 'localhost:81',
	'db_host' => 'localhost',
	'db_port' => 3306,
	'db_user' => 'root',
	'db_password' => '',
	'db_name' => 'ebest_db',
	'db_type' => 'mysql',
	'db_prefix' => ''
);

// local (http://localhost:81/dynaform
$__instances['local80'] = array(
	// should be a unique part of the url (or the entire url if you wish)
	'prefix' => 'localhost',
	'db_host' => 'localhost',
	'db_port' => 3306,
	'db_user' => 'root',
	'db_password' => '',
	'db_name' => 'ebest_db',
	'db_type' => 'mysql',
	'db_prefix' => ''
);

$__instances['local2'] = array(
	// should be a unique part of the url (or the entire url if you wish)
	'prefix' => 'localhost:8080',
	'db_host' => 'localhost',
	'db_port' => 3306,
	'db_user' => 'root',
	'db_password' => '',
	'db_name' => 'crm',
	'db_type' => 'mysql',
	'db_prefix' => ''
);
$__instances['local3'] = array(
	'prefix' => '127.0.0.1',
	'db_host' => 'localhost',
	'db_port' => 3306,
	'db_user' => 'root',
	'db_password' => '',
	'db_name' => 'crm',
	'db_type' => 'mysql',
	'db_prefix' => ''
);

// live (http://devajyoti-w7-02:8080)
$__instances['home'] = array(
	'prefix' => '192.168.0.100',
	'db_host' => 'localhost',
	'db_port' => 3306,
	'db_user' => 'root',
	'db_password' => '',
	'db_name' => 'crm',
	'db_type' => 'mysql',
	'db_prefix' => ''
);

// live (http://form.user.com)
$__instances['live1'] = array(
	'prefix' => 'user.com',
	'db_host' => 'deadlane.ipowermysql.com',
	'db_port' => 3306,
	'db_user' => 'user',
	'db_password' => 'pass',
	'db_name' => 'db_cms',
	'db_type' => 'mysql',
	'db_prefix' => 'db_'
);

// live (http://www.globalhorizon.org)
$__instances['live2'] = array(
	'prefix' => 'globalhorizon.org',
	'db_host' => 'deadlane.ipowermysql.com',
	'db_port' => 3306,
	'db_user' => 'user',
	'db_password' => 'pass',
	'db_name' => 'db_cms',
	'db_type' => 'mysql',
	'db_prefix' => 'db_'
);

//if(isset($_SESSION['DATABASE']) || !isset($DATABASE) && !isset($DATABASE['NAME']))
{
	// setup current instance
	foreach ($__instances as $__instance)
	{
		// http requests
		if (isset($_SERVER['HTTP_HOST']))
		{
			$_compare_to = $_SERVER['HTTP_HOST'];
		}
		if (stristr($_compare_to, $__instance['prefix']))
		{
			$DATABASE['HOST'] = $__instance['db_host'];
			$DATABASE['PORT'] = $__instance['db_port'];
			$DATABASE['USER'] = $__instance['db_user'];
			$DATABASE['PASS'] = $__instance['db_password'];
			$DATABASE['NAME'] = $__instance['db_name'];
			$DATABASE['TYPE'] = $__instance['db_type'];
			$DATABASE['TBL_PREFIX'] = $__instance['db_prefix'];
			$_SESSION['DATABASE'] = $DATABASE;
			break;
		}
	}
}
$_SESSION['DATABASE'] = $DATABASE;
?>