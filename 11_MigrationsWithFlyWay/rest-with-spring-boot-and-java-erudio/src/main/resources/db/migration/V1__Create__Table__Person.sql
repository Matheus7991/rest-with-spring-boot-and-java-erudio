CREATE TABLE `person` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `firs_name` varchar(80) NOT NULL,
  `last_name` varchar(80) DEFAULT NULL,
  `address` varchar(100) NOT NULL,
  `gender` varchar(6) NOT NULL,
  PRIMARY KEY (`id`)
);
