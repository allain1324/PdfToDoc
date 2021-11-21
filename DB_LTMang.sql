-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 21, 2021 at 08:05 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `DB_LTMang`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `idAccount` int(11) NOT NULL,
  `username` text NOT NULL,
  `password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`idAccount`, `username`, `password`) VALUES
(1, 'admin', 'admin'),
(2, 'client1', 'client1');

-- --------------------------------------------------------

--
-- Table structure for table `fileInfor`
--

CREATE TABLE `fileInfor` (
  `iD` int(11) NOT NULL,
  `idAccount` int(11) NOT NULL,
  `dateUpload` date NOT NULL,
  `linkFile` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`idAccount`),
  ADD UNIQUE KEY `username` (`username`) USING HASH;

--
-- Indexes for table `fileInfor`
--
ALTER TABLE `fileInfor`
  ADD PRIMARY KEY (`iD`),
  ADD KEY `idAccount_foreignkey` (`idAccount`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `fileInfor`
--
ALTER TABLE `fileInfor`
  ADD CONSTRAINT `idAccount_foreignkey` FOREIGN KEY (`idAccount`) REFERENCES `account` (`idAccount`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
