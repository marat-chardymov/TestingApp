-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.15 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             8.1.0.4545
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for testingappdb
CREATE DATABASE IF NOT EXISTS `testingappdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `testingappdb`;


-- Dumping structure for table testingappdb.answers
CREATE TABLE IF NOT EXISTS `answers` (
  `answer_id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `is_right` tinyint(1) NOT NULL,
  `fk_question_id` int(11) NOT NULL,
  PRIMARY KEY (`answer_id`),
  KEY `fk_answers_question1` (`fk_question_id`),
  CONSTRAINT `fk_answers_question1` FOREIGN KEY (`fk_question_id`) REFERENCES `questions` (`question_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8;

-- Dumping data for table testingappdb.answers: ~24 rows (approximately)
/*!40000 ALTER TABLE `answers` DISABLE KEYS */;
INSERT INTO `answers` (`answer_id`, `content`, `is_right`, `fk_question_id`) VALUES
	(67, '4', 1, 40),
	(68, '2^2', 1, 40),
	(69, '42', 0, 40),
	(90, 'новый ответ', 1, 46),
	(91, 'новый ответ 2', 0, 46),
	(92, 'новый ответ 3', 1, 46),
	(93, 'новый ответ 4', 0, 46),
	(94, 'новый ответ 5', 1, 46),
	(109, 'новый ответ', 1, 51),
	(110, 'новый ответ 2', 1, 52),
	(111, 'новый ответ 12', 0, 52),
	(112, 'новый ответ 3', 1, 53),
	(113, 'новый ответ 3', 1, 53),
	(114, 'новый ответ 17', 0, 53),
	(122, 'новый ответ 1', 0, 68),
	(123, 'новый ответ 12', 0, 68),
	(124, 'новый ответ 13', 0, 68),
	(132, 'Переменная', 0, 83),
	(134, 'Величина прямо пропорциональная массе', 0, 83),
	(141, 'фыва', 1, 87),
	(143, 'чсмимси', 0, 87),
	(144, 'супер новый ответ 1', 1, 88),
	(145, 'супер новый ответ 2', 1, 88),
	(146, 'супер новый ответ 3', 0, 88);
/*!40000 ALTER TABLE `answers` ENABLE KEYS */;


-- Dumping structure for table testingappdb.questions
CREATE TABLE IF NOT EXISTS `questions` (
  `question_id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_quiz_id` int(11) NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY (`question_id`),
  KEY `fk_questions_tests1_idx` (`fk_quiz_id`),
  CONSTRAINT `fk_questions_tests1` FOREIGN KEY (`fk_quiz_id`) REFERENCES `quizzes` (`quiz_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;

-- Dumping data for table testingappdb.questions: ~9 rows (approximately)
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` (`question_id`, `fk_quiz_id`, `content`) VALUES
	(40, 1, '2x2?'),
	(46, 33, 'Новый вопрос'),
	(51, 40, 'Новый вопрос 1'),
	(52, 40, 'Новый вопрос 2'),
	(53, 40, 'Новый вопрос 3'),
	(68, 30, 'новый вопрос 1'),
	(83, 78, 'Что такое сила?'),
	(87, 1, 'фыва'),
	(88, 79, 'супер новый вопрос');
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;


-- Dumping structure for table testingappdb.quizzes
CREATE TABLE IF NOT EXISTS `quizzes` (
  `quiz_id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_subject_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`quiz_id`),
  KEY `fk_tests_subjects1_idx` (`fk_subject_id`),
  CONSTRAINT `fk_quizzes_subjects1` FOREIGN KEY (`fk_subject_id`) REFERENCES `subjects` (`subject_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;

-- Dumping data for table testingappdb.quizzes: ~6 rows (approximately)
/*!40000 ALTER TABLE `quizzes` DISABLE KEYS */;
INSERT INTO `quizzes` (`quiz_id`, `fk_subject_id`, `name`) VALUES
	(1, 314, 'Algebra'),
	(30, 314, 'Новый тест'),
	(33, 316, 'Новый тест'),
	(40, 316, 'Супер новый тест'),
	(78, 314, 'Физика'),
	(79, 317, 'Супер новый тест');
/*!40000 ALTER TABLE `quizzes` ENABLE KEYS */;


-- Dumping structure for table testingappdb.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table testingappdb.roles: ~2 rows (approximately)
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`role_id`, `role_name`) VALUES
	(1, 'admin'),
	(2, 'tutor'),
	(3, 'student');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;


-- Dumping structure for table testingappdb.subjects
CREATE TABLE IF NOT EXISTS `subjects` (
  `subject_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=318 DEFAULT CHARSET=utf8;

-- Dumping data for table testingappdb.subjects: ~3 rows (approximately)
/*!40000 ALTER TABLE `subjects` DISABLE KEYS */;
INSERT INTO `subjects` (`subject_id`, `name`) VALUES
	(314, 'Math'),
	(316, 'Новый предмет'),
	(317, 'Супер новый предмет');
/*!40000 ALTER TABLE `subjects` ENABLE KEYS */;


-- Dumping structure for table testingappdb.tests_results
CREATE TABLE IF NOT EXISTS `tests_results` (
  `test_results_id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_user_id` int(11) NOT NULL,
  `fk_quiz_id` int(11) NOT NULL,
  `scores` int(11) NOT NULL,
  `date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`test_results_id`),
  KEY `fk_tests_results_students_idx` (`fk_user_id`),
  KEY `fk_tests_results_tests1_idx` (`fk_quiz_id`),
  CONSTRAINT `fk_tests_results_students` FOREIGN KEY (`fk_user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tests_results_tests1` FOREIGN KEY (`fk_quiz_id`) REFERENCES `quizzes` (`quiz_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table testingappdb.tests_results: ~0 rows (approximately)
/*!40000 ALTER TABLE `tests_results` DISABLE KEYS */;
/*!40000 ALTER TABLE `tests_results` ENABLE KEYS */;


-- Dumping structure for table testingappdb.users
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `surname` varchar(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `password` text,
  `fk_role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  KEY `fk_role_id` (`fk_role_id`),
  CONSTRAINT `fk_users_roles1` FOREIGN KEY (`fk_role_id`) REFERENCES `roles` (`role_id`) ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table testingappdb.users: ~2 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`user_id`, `name`, `surname`, `email`, `username`, `create_time`, `password`, `fk_role_id`) VALUES
	(1, 'Marat', 'Chardymov', 'marat.chardymov@gmail.com', 'admin', '2014-01-10 01:23:31', '$2a$10$wy2gn4jZifOaYcfg1W7zN.RAd9t3sm177yWOQ.RGDMWAOYNrafaBy', 1),
	(2, 'tutorName', 'Tutor', NULL, 'tutor', '2014-01-10 14:59:25', '$2a$10$Rc4/0uAsspG97owHekl14Of2sVGFoHs9Fj/oA6hdg8w127jepkHve', 2),
	(3, 'studentName', 'Student', NULL, 'student', '2014-01-10 15:00:35', '$2a$10$S9I732C.GjJAmkUp89v.JuIz/Aw6DFvGaeZlMGGCs0iyEhW.xPMKq', 3);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
