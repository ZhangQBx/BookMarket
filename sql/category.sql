/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : bookstore

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 28/07/2020 21:54:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `categoryId` varchar(6) NOT NULL,
  `title` varchar(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
BEGIN;
INSERT INTO `category` VALUES (1, '242', '中国文学');
INSERT INTO `category` VALUES (2, '252', '人物传记');
INSERT INTO `category` VALUES (3, '244', '儿童文学');
INSERT INTO `category` VALUES (4, '248', '历史');
INSERT INTO `category` VALUES (5, '257', '哲学');
INSERT INTO `category` VALUES (6, '243', '外国文学');
INSERT INTO `category` VALUES (7, '247', '小说');
INSERT INTO `category` VALUES (8, '251', '心灵鸡汤');
INSERT INTO `category` VALUES (9, '253', '心理学');
INSERT INTO `category` VALUES (10, '250', '成功励志');
INSERT INTO `category` VALUES (11, '249', '教育');
INSERT INTO `category` VALUES (12, '245', '散文');
INSERT INTO `category` VALUES (13, '256', '理财');
INSERT INTO `category` VALUES (14, '254', '管理');
INSERT INTO `category` VALUES (15, '246', '经典名著');
INSERT INTO `category` VALUES (16, '255', '经济');
INSERT INTO `category` VALUES (17, '258', '计算机');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
