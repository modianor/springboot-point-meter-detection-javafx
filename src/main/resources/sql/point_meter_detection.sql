/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : point_meter_detection

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2020-06-09 13:49:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for items
-- ----------------------------
DROP TABLE IF EXISTS `items`;
CREATE TABLE `items` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `img_id` varchar(255) DEFAULT NULL,
  `angle` double(25,0) DEFAULT NULL,
  `value` double(25,0) DEFAULT NULL,
  `date` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of items
-- ----------------------------
INSERT INTO `items` VALUES ('59', 'test.png', '61', '20', '2020-06-03 21:13:36');
INSERT INTO `items` VALUES ('60', 'test.png', '61', '20', '2020-06-03 21:13:43');
INSERT INTO `items` VALUES ('61', 'test.png', '61', '20', '2020-06-03 21:13:44');
INSERT INTO `items` VALUES ('62', 'test.png', '61', '20', '2020-06-03 21:13:44');
INSERT INTO `items` VALUES ('63', 'test.png', '61', '20', '2020-06-03 21:13:45');
INSERT INTO `items` VALUES ('64', 'test.png', '61', '20', '2020-06-03 21:13:45');
INSERT INTO `items` VALUES ('65', 'test.png', '61', '20', '2020-06-03 21:13:45');
INSERT INTO `items` VALUES ('66', 'test.png', '61', '20', '2020-06-03 21:13:46');
INSERT INTO `items` VALUES ('67', 'test.png', '61', '20', '2020-06-03 21:13:48');
INSERT INTO `items` VALUES ('68', 'test.png', '61', '20', '2020-06-03 21:13:49');
INSERT INTO `items` VALUES ('69', 'test.png', '61', '20', '2020-06-03 21:13:49');
INSERT INTO `items` VALUES ('70', 'test.png', '61', '20', '2020-06-03 21:13:49');
INSERT INTO `items` VALUES ('71', 'test.png', '61', '20', '2020-06-03 21:13:50');
INSERT INTO `items` VALUES ('72', 'test.png', '61', '20', '2020-06-03 21:13:50');
INSERT INTO `items` VALUES ('73', 'test.png', '61', '20', '2020-06-03 21:13:50');
INSERT INTO `items` VALUES ('74', 'test.png', '61', '20', '2020-06-03 21:13:50');
INSERT INTO `items` VALUES ('75', 'test.png', '61', '20', '2020-06-03 21:13:51');
INSERT INTO `items` VALUES ('76', 'test.png', '61', '20', '2020-06-03 21:13:51');
INSERT INTO `items` VALUES ('77', 'test.png', '61', '20', '2020-06-03 21:13:51');
INSERT INTO `items` VALUES ('78', 'test.png', '61', '20', '2020-06-03 21:13:52');
INSERT INTO `items` VALUES ('79', 'test.png', '61', '20', '2020-06-03 21:13:52');
INSERT INTO `items` VALUES ('80', 'test.png', '61', '20', '2020-06-03 21:13:53');
INSERT INTO `items` VALUES ('81', 'test.png', '61', '20', '2020-06-03 21:13:53');
INSERT INTO `items` VALUES ('82', 'test.png', '61', '20', '2020-06-03 21:13:53');
INSERT INTO `items` VALUES ('83', 'test.png', '61', '20', '2020-06-03 21:13:53');
INSERT INTO `items` VALUES ('84', 'test.png', '61', '20', '2020-06-03 21:13:54');
INSERT INTO `items` VALUES ('85', 'test.png', '61', '20', '2020-06-03 21:13:55');
INSERT INTO `items` VALUES ('86', 'test.png', '61', '20', '2020-06-03 21:13:55');
INSERT INTO `items` VALUES ('87', 'test.png', '61', '20', '2020-06-03 21:13:55');
INSERT INTO `items` VALUES ('88', 'test.png', '61', '20', '2020-06-03 21:13:56');
INSERT INTO `items` VALUES ('89', 'test.png', '61', '20', '2020-06-03 21:13:56');
INSERT INTO `items` VALUES ('90', 'test.png', '61', '20', '2020-06-03 21:13:56');
INSERT INTO `items` VALUES ('91', 'test.png', '61', '20', '2020-06-03 21:13:57');
INSERT INTO `items` VALUES ('92', 'test.png', '61', '20', '2020-06-03 21:13:57');
INSERT INTO `items` VALUES ('93', 'test.png', '61', '20', '2020-06-03 21:13:57');
INSERT INTO `items` VALUES ('94', 'test.png', '61', '20', '2020-06-03 21:13:57');
INSERT INTO `items` VALUES ('95', 'test.png', '61', '20', '2020-06-03 21:13:58');
INSERT INTO `items` VALUES ('96', 'test.png', '61', '20', '2020-06-03 21:13:58');
INSERT INTO `items` VALUES ('97', 'test.png', '61', '20', '2020-06-03 21:13:58');
INSERT INTO `items` VALUES ('98', 'test.png', '61', '20', '2020-06-03 21:13:58');
INSERT INTO `items` VALUES ('99', 'test.png', '61', '20', '2020-06-03 21:13:59');
INSERT INTO `items` VALUES ('100', 'test.png', '61', '20', '2020-06-03 21:30:14');
INSERT INTO `items` VALUES ('101', 'test.png', '61', '20', '2020-06-05 00:57:32');
INSERT INTO `items` VALUES ('102', 'test.png', '61', '20', '2020-06-05 00:59:41');
INSERT INTO `items` VALUES ('103', 'test.png', '61', '20', '2020-06-05 01:00:02');
INSERT INTO `items` VALUES ('104', 'test.png', '62', '19', '2020-06-05 19:49:30');
INSERT INTO `items` VALUES ('105', 'test4.png', '30', '10', '2020-06-08 18:24:08.429');
INSERT INTO `items` VALUES ('106', 'test.png', '62', '19', '2020-06-08 18:41:51.687');
INSERT INTO `items` VALUES ('107', 'test.png', '62', '19', '2020-06-08 20:06:05.121');
INSERT INTO `items` VALUES ('108', 'test.png', '62', '19', '2020-06-08 20:12:54.043');
INSERT INTO `items` VALUES ('109', 'test.png', '62', '19', '2020-06-08 20:18:12.341');
INSERT INTO `items` VALUES ('110', 'test.png', '62', '19', '2020-06-09 00:11:48.044');
INSERT INTO `items` VALUES ('111', 'test.png', '62', '19', '2020-06-09 00:20:51.345');
INSERT INTO `items` VALUES ('112', 'test.png', '62', '19', '2020-06-09 11:55:19.998');
INSERT INTO `items` VALUES ('113', 'test.png', '62', '19', '2020-06-09 11:56:12.105');
INSERT INTO `items` VALUES ('114', 'test.png', '62', '19', '2020-06-09 12:13:48.82');
INSERT INTO `items` VALUES ('115', 'test.png', '62', '19', '2020-06-09 12:17:30.684');
INSERT INTO `items` VALUES ('116', 'test.png', '62', '19', '2020-06-09 12:17:44.072');
INSERT INTO `items` VALUES ('117', 'test.png', '62', '19', '2020-06-09 12:56:13.769');
INSERT INTO `items` VALUES ('118', 'test.png', '62', '19', '2020-06-09 12:59:14.908');
INSERT INTO `items` VALUES ('119', 'test.png', '62', '19', '2020-06-09 12:59:47.759');
INSERT INTO `items` VALUES ('120', 'test.png', '62', '19', '2020-06-09 13:00:29.173');
INSERT INTO `items` VALUES ('121', 'test.png', '62', '19', '2020-06-09 13:01:09.536');
INSERT INTO `items` VALUES ('122', 'test.png', '62', '19', '2020-06-09 13:01:40.044');
INSERT INTO `items` VALUES ('123', 'test.png', '62', '19', '2020-06-09 13:02:46.206');
INSERT INTO `items` VALUES ('124', 'test.png', '62', '19', '2020-06-09 13:02:51.857');
INSERT INTO `items` VALUES ('125', 'test.png', '62', '19', '2020-06-09 13:06:12.027');
INSERT INTO `items` VALUES ('126', 'test.png', '62', '19', '2020-06-09 13:10:35.823');
