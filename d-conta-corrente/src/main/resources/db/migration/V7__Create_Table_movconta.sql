CREATE TABLE `movconta` (
  `id` INT(10) AUTO_INCREMENT PRIMARY KEY,
  `saldo` decimal(65,2) NOT NULL,
  `tipo` CHAR(50) NOT NULL,
  `doc` CHAR(20) NOT NULL,
  `id_cont` INT(10) NOT NULL,
  `acao` CHAR(150) NOT NULL,
  `data_mov` date
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
