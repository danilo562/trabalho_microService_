﻿CREATE TABLE `investimento` (
  `id` INT(10) AUTO_INCREMENT PRIMARY KEY,
  `saldo` decimal(65,2) NOT NULL,
  `tipo` CHAR(20) NOT NULL,
  `doc` CHAR(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
