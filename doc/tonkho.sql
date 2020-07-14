CREATE TABLE `ton_kho` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tk_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
   `sdt` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `kenh` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
   `kho` varchar(255) DEFAULT NULL,
  `ngay_cutoff` date DEFAULT NULL,
  `ngay_dk` date DEFAULT NULL,
  `gc1` varchar(255) DEFAULT NULL,
  `gc2` varchar(255) DEFAULT NULL,
  `gc3` varchar(255) DEFAULT NULL,
  `spreadsheets_id` varchar(255) DEFAULT NULL,
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
