-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 06, 2020 at 07:29 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mywallpa_android`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `name`, `email`, `password`) VALUES
(1, 'admin', 'admin@gmail.com', 'e10adc3949ba59abbe56e057f20f883e');

-- --------------------------------------------------------

--
-- Table structure for table `config`
--

CREATE TABLE `config` (
  `id` int(11) NOT NULL,
  `type` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `froms` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `config`
--

INSERT INTO `config` (`id`, `type`, `froms`) VALUES
(1, 'email', 'piyush.d@gmail.com'),
(2, 'point', '2000');

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `id` int(11) NOT NULL,
  `redeem_point` varchar(50) NOT NULL,
  `payment_mode` varchar(50) NOT NULL,
  `payment_info` varchar(100) NOT NULL,
  `user_id` varchar(20) NOT NULL,
  `payment_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`id`, `redeem_point`, `payment_mode`, `payment_info`, `user_id`, `payment_time`) VALUES
(3, '500', 'Phonepay', '1234567890', '3', '2020-11-29 19:41:48'),
(5, '150', 'Paytm', 'paytm_number', '3', '2020-11-30 08:46:36'),
(6, '150', 'Paytm', 'paytm_number', '3', '2020-11-30 08:46:54'),
(7, '150', 'Paytm', 'paytm_number', '3', '2020-11-30 08:47:40'),
(8, '150', 'Paytm', 'paytm_number', '2', '2020-11-30 09:42:28'),
(9, '150', 'Paytm', 'paytm_number', '2', '2020-11-30 10:10:09'),
(10, '150', 'Paytm', 'paytm_number', '2', '2020-11-30 11:00:50'),
(11, '150', 'Paytm', 'paytm_number', '2', '2020-11-30 11:01:06'),
(12, '150', 'Paytm', 'paytm_number', '2', '2020-11-30 11:04:22'),
(13, '150', 'Paytm', 'paytm_number', '2', '2020-11-30 11:04:27'),
(14, '150', 'Paytm', 'paytm_number', '2', '2020-12-02 08:16:10'),
(15, '5000', 'paytm', '8265979746', '4', '2020-12-02 08:18:42'),
(16, '150', 'Paytm', 'paytm_number', '2', '2020-12-02 22:58:05'),
(17, '150', 'Paytm', 'paytm_number', '2', '2020-12-02 22:59:21'),
(18, '150', 'Paytm', 'paytm_number', '2', '2020-12-02 22:59:51'),
(19, '150', 'Paytm', 'paytm_number', '2', '2020-12-02 23:00:06'),
(20, '150', 'Paytm', 'paytm_number', '2', '2020-12-02 23:24:15');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(255) NOT NULL,
  `number` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `image` varchar(100) NOT NULL,
  `points` varchar(50) NOT NULL,
  `referraled_with` varchar(25) NOT NULL,
  `referral_code` varchar(20) NOT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `number`, `password`, `image`, `points`, `referraled_with`, `referral_code`, `status`) VALUES
(4, 'aadesh', 'aadesh1234@gmail.com', '8265979746', 'e10adc3949ba59abbe56e057f20f883e', '', '15000', 'piyush1234', 'aadesh1234', '0'),
(5, 'aadesh dhiman', 'aadesh56@gmail.com', '7252023032', 'e10adc3949ba59abbe56e057f20f883e', '', '200', 'piyush1234', 'aadesh1234', '0'),
(19, 'aadesh Dhiman', 'aadesh12@gmail.com', '7906416500', 'b6bbe691426492effe91574777aa0e56', '', '137', '', 'aadeshDhiman7906', '1'),
(22, 'aadesh', 'aadesh098@gmail.com', '0987654321', 'b6bbe691426492effe91574777aa0e56', '', '120', 'aadeshDhiman7906', 'aadesh0987', '1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `config`
--
ALTER TABLE `config`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `config`
--
ALTER TABLE `config`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
