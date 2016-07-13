# Host: 127.0.0.1  (Version: 5.6.24)
# Date: 2016-07-13 23:55:02
# Generator: MySQL-Front 5.3  (Build 4.214)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "balance"
#

DROP TABLE IF EXISTS `balance`;
CREATE TABLE `balance` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `balance` double(20,2) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `remark` varchar(20) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

#
# Structure for table "blotter"
#

DROP TABLE IF EXISTS `blotter`;
CREATE TABLE `blotter` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `szzs` float(10,2) DEFAULT NULL,
  `balance` float(10,2) DEFAULT NULL,
  `balance_yy` float(10,2) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index_create_date` (`create_date`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

#
# Structure for table "citys"
#

DROP TABLE IF EXISTS `citys`;
CREATE TABLE `citys` (
  `id` varchar(6) NOT NULL COMMENT '主键',
  `name` varchar(30) NOT NULL COMMENT '名称',
  `parentId` varchar(11) NOT NULL COMMENT '父id',
  `shortName` varchar(30) NOT NULL COMMENT '简称',
  `levelType` varchar(1) NOT NULL COMMENT '级别（0中国，1省份，2市区...）',
  `cityCode` varchar(4) NOT NULL COMMENT '城市编码',
  `zipCode` varchar(6) NOT NULL COMMENT '邮政编码',
  `mergerName` varchar(50) NOT NULL COMMENT '地理全称',
  `lng` varchar(15) NOT NULL COMMENT '经度',
  `lat` varchar(15) NOT NULL COMMENT '纬度',
  `pinyin` varchar(30) NOT NULL COMMENT '拼音',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ind_citys_id` (`id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Structure for table "depot"
#

DROP TABLE IF EXISTS `depot`;
CREATE TABLE `depot` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `stock_name` varchar(20) NOT NULL,
  `shizhi` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "gainian"
#

DROP TABLE IF EXISTS `gainian`;
CREATE TABLE `gainian` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "rongquan"
#

DROP TABLE IF EXISTS `rongquan`;
CREATE TABLE `rongquan` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `amount` int(11) NOT NULL,
  `used` int(11) NOT NULL,
  `price` double(10,2) NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "stock_bk_data_day"
#

DROP TABLE IF EXISTS `stock_bk_data_day`;
CREATE TABLE `stock_bk_data_day` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `b` varchar(20) NOT NULL,
  `c` varchar(20) NOT NULL,
  `d` double NOT NULL,
  `e` double NOT NULL,
  `r` double NOT NULL,
  `p` double NOT NULL,
  `q` double NOT NULL,
  `s` double NOT NULL,
  `o` double NOT NULL,
  `m` double NOT NULL,
  `m5` double NOT NULL,
  `m10` double NOT NULL,
  `m20` double NOT NULL,
  `m30` double NOT NULL,
  `m60` double NOT NULL,
  `m120` double NOT NULL,
  `m250` double NOT NULL,
  `create_date` date NOT NULL DEFAULT '0000-00-00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `b` (`b`,`create_date`),
  KEY `cdata` (`create_date`),
  KEY `create_date` (`create_date`)
) ENGINE=InnoDB AUTO_INCREMENT=34081 DEFAULT CHARSET=utf8;

#
# Structure for table "stock_data"
#

DROP TABLE IF EXISTS `stock_data`;
CREATE TABLE `stock_data` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `a` int(11) NOT NULL,
  `b` varchar(20) NOT NULL,
  `c` varchar(20) NOT NULL,
  `d` double NOT NULL,
  `e` double NOT NULL,
  `f` double NOT NULL,
  `g` varchar(20) NOT NULL,
  `h` varchar(20) NOT NULL,
  `i` double NOT NULL,
  `j` double NOT NULL,
  `k` double NOT NULL,
  `l` double NOT NULL,
  `m` varchar(20) NOT NULL,
  `n` double NOT NULL,
  `o` varchar(20) NOT NULL,
  `p` double NOT NULL,
  `q` double NOT NULL,
  `r` double NOT NULL,
  `s` double NOT NULL,
  `t` double NOT NULL,
  `u` double NOT NULL,
  `v` double NOT NULL,
  `w` varchar(20) NOT NULL,
  `x` double NOT NULL,
  `y` varchar(20) NOT NULL,
  `z` varchar(20) NOT NULL,
  `aa` double NOT NULL,
  `ab` varchar(20) NOT NULL,
  `ac` varchar(20) NOT NULL,
  `ad` double NOT NULL,
  `ae` varchar(20) NOT NULL,
  `af` varchar(20) NOT NULL,
  `ag` varchar(20) NOT NULL,
  `ah` varchar(20) NOT NULL,
  `ai` double NOT NULL,
  `aj` double NOT NULL,
  `ak` double NOT NULL,
  `al` double NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `b` (`b`,`create_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "stock_data_day"
#

DROP TABLE IF EXISTS `stock_data_day`;
CREATE TABLE `stock_data_day` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `b` varchar(20) NOT NULL,
  `c` varchar(20) NOT NULL,
  `d` double NOT NULL,
  `e` double NOT NULL,
  `r` double NOT NULL,
  `p` double NOT NULL,
  `q` double NOT NULL,
  `s` double NOT NULL,
  `o` double NOT NULL,
  `m` double NOT NULL,
  `m5` double NOT NULL,
  `m10` double NOT NULL,
  `m20` double NOT NULL,
  `m30` double NOT NULL,
  `m60` double NOT NULL,
  `m120` double NOT NULL,
  `m250` double NOT NULL,
  `create_date` date NOT NULL DEFAULT '0000-00-00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `b` (`b`,`create_date`),
  KEY `cdata` (`create_date`),
  KEY `create_date` (`create_date`)
) ENGINE=InnoDB AUTO_INCREMENT=360179 DEFAULT CHARSET=utf8;

#
# Structure for table "stock_data_query"
#

DROP TABLE IF EXISTS `stock_data_query`;
CREATE TABLE `stock_data_query` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `b` varchar(20) NOT NULL,
  `c` varchar(20) NOT NULL,
  `d` double NOT NULL,
  `e` double NOT NULL,
  `r` double NOT NULL,
  `p` double NOT NULL,
  `q` double NOT NULL,
  `s` double NOT NULL,
  `o` double NOT NULL,
  `m` double NOT NULL,
  `g` varchar(20) DEFAULT NULL,
  `bbi` double DEFAULT NULL,
  `m5` double DEFAULT NULL,
  `m10` double DEFAULT NULL,
  `m20` double DEFAULT NULL,
  `m30` double DEFAULT NULL,
  `m60` double DEFAULT NULL,
  `m120` double DEFAULT NULL,
  `m250` double DEFAULT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `b` (`b`,`create_date`),
  UNIQUE KEY `code` (`b`),
  KEY `index_stockCode` (`b`)
) ENGINE=InnoDB AUTO_INCREMENT=2867 DEFAULT CHARSET=utf8;

#
# Structure for table "gugainian"
#

DROP TABLE IF EXISTS `gugainian`;
CREATE TABLE `gugainian` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(20) NOT NULL,
  `gainian` varchar(200) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  CONSTRAINT `gugainian_ibfk_1` FOREIGN KEY (`code`) REFERENCES `stock_data_query` (`b`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "gainiangu"
#

DROP TABLE IF EXISTS `gainiangu`;
CREATE TABLE `gainiangu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `gnid` bigint(20) DEFAULT NULL,
  `code` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idcode` (`code`,`gnid`),
  KEY `code` (`code`),
  CONSTRAINT `gainiangu_ibfk_1` FOREIGN KEY (`code`) REFERENCES `stock_data_query` (`b`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "stock_guben"
#

DROP TABLE IF EXISTS `stock_guben`;
CREATE TABLE `stock_guben` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `stockCode` varchar(20) NOT NULL,
  `stockholderName` varchar(100) NOT NULL DEFAULT '',
  `stockholderType` varchar(10) NOT NULL,
  `stockholderRatio` float(8,4) NOT NULL,
  `addOrSubtract` varchar(20) NOT NULL DEFAULT '无',
  `createDate` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26732 DEFAULT CHARSET=utf8;

#
# Structure for table "stock_lhb"
#

DROP TABLE IF EXISTS `stock_lhb`;
CREATE TABLE `stock_lhb` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `SalesCode` varchar(20) NOT NULL,
  `SalesName` varchar(60) NOT NULL DEFAULT '',
  `SumActBMoney` varchar(20) NOT NULL,
  `SumActSMoney` varchar(20) NOT NULL,
  `SumActMoney` varchar(20) NOT NULL,
  `BCount` int(11) NOT NULL DEFAULT '0',
  `SCount` int(11) NOT NULL DEFAULT '0',
  `UpCount` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `U_SalesCode` (`SalesCode`)
) ENGINE=InnoDB AUTO_INCREMENT=3800 DEFAULT CHARSET=utf8;

#
# Structure for table "stock_lhb_gegu"
#

DROP TABLE IF EXISTS `stock_lhb_gegu`;
CREATE TABLE `stock_lhb_gegu` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `SalesName` varchar(60) NOT NULL DEFAULT '',
  `SalesCode` varchar(20) NOT NULL,
  `SName` varchar(60) NOT NULL DEFAULT '',
  `SCode` varchar(20) NOT NULL,
  `BMoney` varchar(20) NOT NULL,
  `SMoney` varchar(20) NOT NULL,
  `RChange3M` varchar(20) NOT NULL,
  `RChange3DC` varchar(20) NOT NULL,
  `RChange20DC` varchar(20) NOT NULL,
  `RChange15DC` varchar(20) NOT NULL,
  `RChange10DC` varchar(20) NOT NULL,
  `RChange5DC` varchar(20) NOT NULL,
  `RChange20DO` varchar(20) NOT NULL,
  `RChange2DC` varchar(20) NOT NULL,
  `RChange30DC` varchar(20) NOT NULL,
  `RChange15DO` varchar(20) NOT NULL,
  `RChange3DO` varchar(20) NOT NULL,
  `RChange10DO` varchar(20) NOT NULL,
  `RChange5DO` varchar(20) NOT NULL,
  `RChange1DO` varchar(20) NOT NULL,
  `RChange30DO` varchar(20) NOT NULL,
  `RChange1DC` varchar(20) NOT NULL,
  `RChange2DO` varchar(20) NOT NULL,
  `RChange1Y` varchar(20) NOT NULL,
  `RChange1M` varchar(20) NOT NULL,
  `RChange6M` varchar(20) NOT NULL,
  `ActSellNum` varchar(20) NOT NULL,
  `ActBuyNum` varchar(20) NOT NULL,
  `ChgRadio` float NOT NULL,
  `CPrice` float NOT NULL,
  `PBuy` float NOT NULL,
  `TDate` date NOT NULL,
  `CTypeDes` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53758 DEFAULT CHARSET=utf8;

#
# Structure for table "stock_notice"
#

DROP TABLE IF EXISTS `stock_notice`;
CREATE TABLE `stock_notice` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `stockCode` varchar(20) NOT NULL,
  `stockName` varchar(10) NOT NULL,
  `title` varchar(100) NOT NULL DEFAULT '',
  `type` varchar(10) NOT NULL,
  `noticeDate` date NOT NULL DEFAULT '0000-00-00',
  `createDate` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28327 DEFAULT CHARSET=utf8;

#
# Structure for table "stock_ticai_detail"
#

DROP TABLE IF EXISTS `stock_ticai_detail`;
CREATE TABLE `stock_ticai_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(20) NOT NULL,
  `orderBy` varchar(20) NOT NULL,
  `content` varchar(4000) NOT NULL DEFAULT '',
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_ticai_code_orderBy` (`code`,`orderBy`)
) ENGINE=InnoDB AUTO_INCREMENT=44700 DEFAULT CHARSET=utf8;

#
# Structure for table "stock_yyb"
#

DROP TABLE IF EXISTS `stock_yyb`;
CREATE TABLE `stock_yyb` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `Province` varchar(20) NOT NULL,
  `SalesCode` varchar(60) NOT NULL DEFAULT '',
  `SalesName` varchar(60) NOT NULL,
  `SumActBMoney` varchar(20) NOT NULL,
  `SumActSMoney` varchar(20) NOT NULL,
  `SumActMoney` varchar(20) NOT NULL,
  `BCount` int(11) NOT NULL DEFAULT '0',
  `SCount` int(11) NOT NULL DEFAULT '0',
  `UpCount` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `U_SalesCode` (`SalesCode`)
) ENGINE=InnoDB AUTO_INCREMENT=4019 DEFAULT CHARSET=utf8;

#
# Structure for table "stock_zijin"
#

DROP TABLE IF EXISTS `stock_zijin`;
CREATE TABLE `stock_zijin` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `a` int(11) NOT NULL,
  `b` varchar(20) NOT NULL,
  `c` varchar(20) NOT NULL,
  `d` double NOT NULL,
  `e` double NOT NULL,
  `f` varchar(20) NOT NULL,
  `g` int(11) NOT NULL,
  `h` varchar(20) NOT NULL,
  `i` varchar(20) NOT NULL,
  `j` varchar(20) NOT NULL,
  `k` double NOT NULL,
  `l` varchar(20) NOT NULL,
  `m` varchar(20) NOT NULL,
  `n` varchar(20) NOT NULL,
  `o` double NOT NULL,
  `p` varchar(20) NOT NULL,
  `q` varchar(20) NOT NULL,
  `r` varchar(20) NOT NULL,
  `s` double NOT NULL,
  `t` varchar(20) NOT NULL,
  `u` varchar(20) NOT NULL,
  `v` varchar(20) NOT NULL,
  `w` double NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `b` (`b`,`create_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "system_param"
#

DROP TABLE IF EXISTS `system_param`;
CREATE TABLE `system_param` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `s_key` varchar(50) NOT NULL,
  `s_name` varchar(50) NOT NULL,
  `s_value` varchar(2000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `loginName` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

#
# Function "isPianLi"
#

DROP FUNCTION IF EXISTS `isPianLi`;
CREATE FUNCTION `isPianLi`(d float,m float) RETURNS tinyint(1)
begin
return 1;
end;
