-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 15, 2013 at 12:38 PM
-- Server version: 5.5.31
-- PHP Version: 5.3.10-1ubuntu3.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `scavengerscram`
--

-- --------------------------------------------------------

--
-- Table structure for table `answers`
--

CREATE TABLE IF NOT EXISTS `answers` (
  `answer_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID of the answer',
  `clue_id` bigint(20) unsigned NOT NULL COMMENT 'ID of the clue',
  `submitted_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Timestamp it was submitted',
  `picture` text NOT NULL COMMENT 'Filepath (?) of the picture',
  `player_id` int(10) unsigned NOT NULL COMMENT 'ID of the player that submitted',
  `game_id` int(10) unsigned NOT NULL COMMENT 'ID of the game it is associated with',
  `correct` tinyint(1) NOT NULL COMMENT 'T if the answer is correct',
  PRIMARY KEY (`answer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `clues`
--

CREATE TABLE IF NOT EXISTS `clues` (
  `clue_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID of the clue',
  `title` varchar(100) NOT NULL COMMENT 'Summary of the clue',
  `description` mediumtext NOT NULL COMMENT 'Longer description of the clue',
  `game_id` int(10) unsigned NOT NULL COMMENT 'ID the clue is associated with',
  PRIMARY KEY (`clue_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `game`
--

CREATE TABLE IF NOT EXISTS `game` (
  `game_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID of the game',
  `name` varchar(50) NOT NULL COMMENT 'Title of the game',
  `description` mediumtext NOT NULL COMMENT 'Longer description for the game',
  `gamemaster_id` int(10) unsigned NOT NULL COMMENT 'ID of the creator of the game',
  `code` varchar(6) NOT NULL COMMENT 'Short code the gamemaster provides to players',
  `locked` tinyint(1) NOT NULL COMMENT 'T if clues have to be played in order',
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Timestamp of when the game begins',
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT 'Timestamp of when the game ends',
  PRIMARY KEY (`game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `participation`
--

CREATE TABLE IF NOT EXISTS `participation` (
  `player_id` int(10) unsigned NOT NULL COMMENT 'ID of the player playing the game',
  `game_id` int(10) unsigned NOT NULL COMMENT 'ID of the game the player is playing'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `players`
--

CREATE TABLE IF NOT EXISTS `players` (
  `player_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID of the player',
  `email` varchar(50) NOT NULL COMMENT 'Email address of the player',
  `username` varchar(30) NOT NULL COMMENT 'Name of the player',
  `password` varchar(20) NOT NULL COMMENT 'SHA1 of their password',
  PRIMARY KEY (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;
