-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 25, 2022 at 02:09 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `animedatabase`
--

--
-- Dumping data for table `studio`
--

INSERT INTO `studio` (`id`, `name`, `founded`, `headquarters`, `type`) VALUES
(1, 'BestAnime', '2021-08-11', 'Emmen', 'private'),
(2, 'Anime Discovery', '2015-12-16', 'New York', 'public'),
(3, 'Anime Ultima Power', '2018-01-01', 'Amsterdam', 'public'),
(4, 'Dragon Studio', '2005-03-11', 'Rotterdam', 'private');


--
-- Dumping data for table `actor`
--

INSERT INTO `actor` (`id`, `name`, `birth_date`, `birth_place`, `age`) VALUES
(1, 'Robbie', '2000-04-27', 'Emmen', 21),
(2, 'Jackson Donders', '2008-08-06', 'Rotterdam', 14),
(3, 'Petra', '2000-04-27', 'New York', 31),
(4, 'Boss Baby', '2020-07-30', 'Leiden', 2);

--
-- Dumping data for table `anime`
--

INSERT INTO `anime` (`id`, `studio_id`, `name`, `critic_score`, `release_date`) VALUES
(1, 2, 'Attack on Titan', 10, '2014-09-03'),
(2, 3, 'Boku No Hero', 7, '2019-08-16'),
(3, 4, 'Dragon Ball', 5, '2017-11-02'),
(4, 2, 'Attack on Titan 2', 9, '2022-03-01'),
(5, 1, 'The Best Anime Ever', 1, '1994-03-04');

--
-- Dumping data for table `featuredin`
--

INSERT INTO `featuredin` (`id`, `anime_id`, `actor_id`) VALUES
(1, 1, 4),
(2, 4, 4),
(3, 3, 4),
(4, 1, 2),
(5, 5, 2),
(6, 1, 3),
(7, 4, 3),
(8, 2, 3),
(9, 3, 3),
(10, 5, 3),
(11, 5, 1);
COMMIT;


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
