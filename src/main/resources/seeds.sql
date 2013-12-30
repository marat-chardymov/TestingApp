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
-- Dumping data for table testingappdb.answers: ~0 rows (approximately)
/*!40000 ALTER TABLE `answers` DISABLE KEYS */;
INSERT INTO `answers` (`answer_id`, `content`, `is_right`, `fk_question_id`) VALUES
	(1, 'Answer 1', 1, 1),
	(2, 'Answer 2', 0, 1),
	(3, 'Answer 3', 0, 1),
	(4, 'Answer 2_1', 0, 2),
	(5, 'Answer 2_2', 1, 2),
	(6, 'Answer 2_3', 0, 2),
	(7, 'Answer 3_1', 0, 3),
	(8, 'Answer 3_2', 0, 3),
	(9, 'Answer 3_3', 1, 3);
/*!40000 ALTER TABLE `answers` ENABLE KEYS */;

-- Dumping data for table testingappdb.questions: ~0 rows (approximately)
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` (`question_id`, `fk_quiz_id`, `content`) VALUES
	(1, 1, 'Question 1'),
	(2, 1, 'Question 2'),
	(3, 1, 'Question 3');
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;

-- Dumping data for table testingappdb.quizzes: ~3 rows (approximately)
/*!40000 ALTER TABLE `quizzes` DISABLE KEYS */;
INSERT INTO `quizzes` (`quiz_id`, `fk_subject_id`, `name`) VALUES
	(1, 314, 'Algebra'),
	(2, 314, 'Geometry'),
	(3, 314, 'Analysis');
/*!40000 ALTER TABLE `quizzes` ENABLE KEYS */;

-- Dumping data for table testingappdb.subjects: ~1 rows (approximately)
/*!40000 ALTER TABLE `subjects` DISABLE KEYS */;
INSERT INTO `subjects` (`subject_id`, `name`) VALUES
	(314, 'Math');
/*!40000 ALTER TABLE `subjects` ENABLE KEYS */;

-- Dumping data for table testingappdb.tests_results: ~0 rows (approximately)
/*!40000 ALTER TABLE `tests_results` DISABLE KEYS */;
/*!40000 ALTER TABLE `tests_results` ENABLE KEYS */;

-- Dumping data for table testingappdb.users: ~0 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
