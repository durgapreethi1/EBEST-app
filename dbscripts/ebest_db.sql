-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Feb 17, 2018 at 04:18 PM
-- Server version: 5.7.19
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ebest_db`
--

DROP TABLE IF EXISTS `tbl_city_services`;
CREATE TABLE IF NOT EXISTS `tbl_city_services` (
  `city_service_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `city_id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  `is_enabled` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`city_service_id`),
  UNIQUE KEY `idx_city_services_uk` (`city_id`,`service_id`) USING BTREE,
  CONSTRAINT `idx_city_id_fk` FOREIGN KEY(city_id) REFERENCES tbl_cities(city_id),
  CONSTRAINT `idx_service_id_fk` FOREIGN KEY(service_id) REFERENCES tbl_services(service_id)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `tbl_cities`;
CREATE TABLE IF NOT EXISTS `tbl_cities` (
  `city_id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `country_code` varchar(6) NOT NULL DEFAULT 'IN',
  `is_enabled` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`city_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


INSERT INTO `tbl_cities` (`city`) (SELECT DISTINCT city FROM tbl_zip_code);

DROP TABLE IF EXISTS `tbl_services`;
CREATE TABLE IF NOT EXISTS `tbl_services` (
  `service_id` int(11) NOT NULL AUTO_INCREMENT,
  `service_name` varchar(150) NOT NULL,
  `is_available` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`service_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `tbl_services` (`service_name`, `is_available`) VALUES
('Driving School', 1),
('Movers & Packers', 1),
('Appliance Maintenance', 1),
('Electrician', 1),
('Pest Control', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_address`
--

DROP TABLE IF EXISTS `tbl_address`;
CREATE TABLE IF NOT EXISTS `tbl_address` (
  `Address_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `User_Id` bigint(11) NOT NULL,
  `Line_1` varchar(50) DEFAULT NULL,
  `Line_2` varchar(50) DEFAULT NULL,
  `City` varchar(20) DEFAULT NULL,
  `State` varchar(20) DEFAULT NULL,
  `Pincode` int(10) DEFAULT NULL,
  PRIMARY KEY (`Address_id`)
) ENGINE=MyISAM AUTO_INCREMENT=52 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_address`
--

INSERT INTO `tbl_address` (`Address_id`, `User_Id`, `Line_1`, `Line_2`, `City`, `State`, `Pincode`) VALUES
(1, 0, 'BVRIT HYDERABAD', 'Nizampet', 'Hyderabad', 'Telangana', 500049),
(2, 0, 'Nizampet Road', 'Kukatpally', 'Hyderabad', 'Telangana', 500047),
(3, 0, 'Nizampet Road', 'Kukatpally', 'Hyderabad', 'Telangana', 500047),
(51, 62, 'ghjkl', 'hjkl', 'funknkj', 'buhklml', 987456),
(50, 61, 'dfg', 'dfg', 'dfg', 'dfgh', 123456),
(49, 60, 'dfg', 'dfg', 'dfg', 'dfgh', 123456),
(48, 59, 'dfghjk', 'dfghj', 'fghj', 'gh', 500072),
(47, 58, 'dfghjk', 'dfghj', 'fghj', 'gh', 500072),
(46, 57, 'fghj', 'fghj', 'fghj', 'fghj', 123456),
(38, 49, NULL, NULL, NULL, NULL, NULL),
(39, 50, 'fghjkl', 'fghj', 'hjk', 'gh', 123456),
(40, 51, 'fghjkl', 'fghj', 'hjk', 'gh', 123456),
(41, 52, 'fh', 'bb', 'nk', 'nk', 123456),
(42, 53, 'fghj', 'fghj', 'fghj', 'fghj', 123456),
(43, 54, 'fghj', 'fghj', 'fghj', 'fghj', 123456),
(44, 55, 'fghj', 'fghj', 'fghj', 'fghj', 123456),
(45, 56, 'fghj', 'fghj', 'fghj', 'fghj', 123456),
(34, 0, 'BVRIT HYDERABAD', 'Nizampet', 'Hyderabad', 'Telangana', 500049),
(35, 13, 'BVRIT HYDERABAD', 'Nizampet', 'Hyderabad', 'Telangana', 500049),
(36, 14, 'bvrith', 'bvrith', 'hyderabad', 'telangana', 123456),
(37, 15, 'bvrith', '', 'hyderabad', 'telangana', 123456);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_users`
--

DROP TABLE IF EXISTS `tbl_users`;
CREATE TABLE IF NOT EXISTS `tbl_users` (
  `id` bigint(10) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `mobile_number` bigint(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_users`
--

INSERT INTO `tbl_users` (`id`, `first_name`, `last_name`, `email`, `mobile_number`) VALUES
(1, 'Susmitha', 'Sanikommu', '15@gmail.com', 1234567892);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user_registration`
--

DROP TABLE IF EXISTS `tbl_user_registration`;
CREATE TABLE IF NOT EXISTS `tbl_user_registration` (
  `User_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `First_Name` varchar(250) DEFAULT NULL,
  `Last_Name` varchar(250) DEFAULT NULL,
  `Email` varchar(250) DEFAULT NULL,
  `Mobile` varchar(10) DEFAULT NULL,
  `Password` varchar(50) DEFAULT NULL,
  `temp_password` varchar(10) DEFAULT NULL,
  `Last_Modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Is_Active` tinyint(1) DEFAULT NULL,
  `Created` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`User_id`)
) ENGINE=MyISAM AUTO_INCREMENT=63 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_user_registration`
--

INSERT INTO `tbl_user_registration` (`User_id`, `First_Name`, `Last_Name`, `Email`, `Mobile`, `Password`, `temp_password`, `Last_Modified`, `Is_Active`, `Created`) VALUES
(1, 'Susmitha', 'Sanikommu', 'susmithasanikommu@gmail.com', '1234567890', 'susmitha', NULL, '2018-02-17 16:15:08', NULL, '2018-02-17 21:45:08'),
(2, 'Durga ', 'Preethi', 'durga@gmail.com', '12345', '12345678', '', '2018-01-27 10:41:54', NULL, '2018-01-27 16:11:54'),
(4, 'Devajyoti', 'Ds', 'ebest@gg', '9999000', '1112333', '', '2018-01-16 12:29:29', NULL, NULL),
(5, 'Devajyoti', 'Ds', 'ebest@gg', '9999000', '1112333', '', '2018-01-16 12:30:23', NULL, NULL),
(6, 'Devajyoti', 'Ds', 'ebest@gg', '9999000', '1112333', '', '2018-01-16 12:30:27', NULL, NULL),
(7, 'Devajyoti', 'Ds', 'ebest@gg', '9999000', '1112333', '', '2018-01-16 12:39:24', NULL, NULL),
(8, 'Devajyoti', 'Ds', 'ebest@gg', '9999000', '1112333', '', '2018-01-16 12:40:01', NULL, NULL),
(9, '', 'Ds', 'ebest@gg', '9999000', '', '', '2018-01-16 12:40:16', NULL, NULL),
(10, 'Susmitha', 'Sanikommu', 'durga1@gmail.com', '9542043558', '67676767676', '', '2018-01-16 13:58:55', NULL, NULL),
(16, 'sa', 'sa', 'sa@sa.com', '1236547563', 'asdfghjkl', '', '2018-02-05 15:29:03', NULL, NULL),
(12, 'Susmitha', 'Sanikommu', '787878@gmal.com', '9542043558', 'jkjkjkjkjk', '', '2018-01-16 14:08:21', NULL, NULL),
(13, 'Susmitha', 'Sanikommu', '787878@gmal.comi', '9542043558', 'klklklklk', '', '2018-01-16 14:10:10', NULL, NULL),
(14, 'ebst', 'ebest', 'ebest@gmail.com', '1234567890', 'mnbvcxz', '', '2018-01-19 14:24:23', NULL, NULL),
(15, 'ebst', 'ebest', 'ebest1@gmail.com', '1234567890', 'qwerty', '', '2018-01-19 14:26:31', NULL, NULL),
(17, 'sa', 'sa', 'sa@sa.com', '1236547563', 'zxcvbnm,.fghjk', '', '2018-02-05 15:33:25', NULL, NULL),
(18, 'sa', 'sa', 'sa@gmail.com', '1236547890', 'asdfghjkl;', '', '2018-02-06 11:17:23', NULL, NULL),
(52, NULL, NULL, 'fg@gmail.com', '1234567890', 'asdfghjklbn', '', '2018-02-14 10:53:27', NULL, NULL),
(62, 'sanjana', 'sanikommu', 'sanjanasanikommu@gmail.com', '9701394013', 'sanjana', '', '2018-02-14 15:55:02', NULL, NULL),
(61, NULL, NULL, 'fg@gmail.com', '1234567890', 'asdfghj', '', '2018-02-14 11:10:47', NULL, NULL),
(60, NULL, NULL, 'fg@gmail.com', '1234567890', 'asdfghj', '', '2018-02-14 11:10:02', NULL, NULL),
(59, NULL, NULL, 'fg@gmail.com', '1234567890', 'asdfghjkl', '', '2018-02-14 11:09:39', NULL, NULL),
(58, NULL, NULL, 'fg@gmail.com', '1234567890', 'asdfghjkl', '', '2018-02-14 11:07:49', NULL, NULL),
(57, NULL, NULL, 'fg@gmail.com', '1234567890', 'asdfghj', '', '2018-02-14 11:07:17', NULL, NULL),
(56, NULL, NULL, 'fg@gmail.com', '1234567890', 'asdfghjk', '', '2018-02-14 11:07:04', NULL, NULL),
(55, NULL, NULL, 'fg@gmail.com', '1234567890', 'asdfghjk', '', '2018-02-14 11:05:52', NULL, NULL),
(54, NULL, NULL, 'fg@gmail.com', '1234567890', 'asdfghjkl', '', '2018-02-14 11:05:16', NULL, NULL),
(53, NULL, NULL, 'fg@gmail.com', '1234567890', 'asdfghjkl', '', '2018-02-14 11:02:17', NULL, NULL);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
