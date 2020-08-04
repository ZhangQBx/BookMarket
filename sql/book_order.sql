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

 Date: 28/07/2020 21:54:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book_order
-- ----------------------------
DROP TABLE IF EXISTS `book_order`;
CREATE TABLE `book_order` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `phone` varchar(20) NOT NULL,
  `book_ids` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `book_count` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` int(1) NOT NULL,
  `create_time` int(10) NOT NULL DEFAULT '0',
  `is_delete` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book_order
-- ----------------------------
BEGIN;
INSERT INTO `book_order` VALUES (2, '123', '[3,2,1,4,8]', '[5,2,6,5,3]', 0, 0, 0);
INSERT INTO `book_order` VALUES (3, '18617354209', '[150,149,148,2,4,5,8,3]', '[2,5,2,2,3,2,1,1]', 0, 0, 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
