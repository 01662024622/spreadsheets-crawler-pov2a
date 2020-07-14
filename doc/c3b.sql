CREATE TABLE `c3b_duan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `san_pham` varchar(255) DEFAULT NULL,
  `submit_time` DATETIME DEFAULT NULL,
  `ho_ten` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `tuoi` varchar(255) DEFAULT NULL,
  `contact_channel` varchar(255) DEFAULT NULL,
  `ads_link` text,
  `utm_source` varchar(255) DEFAULT NULL,
  `utm_team` varchar(255) DEFAULT NULL,
  `utm_term` varchar(255) DEFAULT NULL,
  `utm_medium` varchar(255) DEFAULT NULL,
  `utm_agent` varchar(255) DEFAULT NULL,
  `utm_campaign` varchar(255) DEFAULT NULL,
  `utm_content` varchar(255) DEFAULT NULL,
  `utm_flow` varchar(255) DEFAULT NULL,
  `gc1` varchar(255) DEFAULT NULL,
  `gc2` varchar(255) DEFAULT NULL,
  `gc3` varchar(255) DEFAULT NULL,
  `spreadsheets_id` varchar(255) DEFAULT NULL,
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;