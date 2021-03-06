CREATE TABLE `l1bg_duan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `san_pham` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `kenh` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `kho` varchar(255) DEFAULT NULL,
  `ngay_sx` DATETIME DEFAULT NULL,
  `ngay_bg` DATETIME DEFAULT NULL,
  `luong` varchar(255) DEFAULT NULL,
  `luong_nhan_bg` varchar(255) DEFAULT NULL,
  `gc1` varchar(255) DEFAULT NULL,
  `gc2` varchar(255) DEFAULT NULL,
  `gc3` varchar(255) DEFAULT NULL,
  `spreadsheets_id` varchar(255) DEFAULT NULL,
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
