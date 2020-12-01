/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : ssm-orders

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 24/09/2019 09:39:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_item
-- ----------------------------
DROP TABLE IF EXISTS `tbl_item`;
CREATE TABLE `tbl_item`  (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`item_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_item
-- ----------------------------
INSERT INTO `tbl_item` VALUES (1, ' 水费');
INSERT INTO `tbl_item` VALUES (2, '电费');
INSERT INTO `tbl_item` VALUES (3, '燃气费');

-- ----------------------------
-- Table structure for tbl_order
-- ----------------------------
DROP TABLE IF EXISTS `tbl_order`;
CREATE TABLE `tbl_order`  (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_time` datetime(0) NULL DEFAULT NULL,
  `u_id` int(11) NULL DEFAULT NULL,
  `order_price` double(10, 2) NULL DEFAULT NULL,
  `i_id` int(11) NULL DEFAULT NULL,
  `is_paid` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_order
-- ----------------------------
INSERT INTO `tbl_order` VALUES (11, '2019-09-23 15:07:20', 1, 9.99, 1, 'N');
INSERT INTO `tbl_order` VALUES (12, '2019-09-23 15:07:28', 1, 6.39, 2, 'N');
INSERT INTO `tbl_order` VALUES (13, '2019-09-23 15:07:42', 1, 9.30, 3, 'N');
INSERT INTO `tbl_order` VALUES (16, '2019-09-23 15:14:05', 1, 10.12, 1, 'N');
INSERT INTO `tbl_order` VALUES (17, '2019-09-24 09:36:54', 1, 66.00, 3, 'N');
INSERT INTO `tbl_order` VALUES (18, '2019-09-23 15:14:05', 1, 12.12, 1, 'N');
INSERT INTO `tbl_order` VALUES (19, '2019-09-23 15:14:05', 1, 13.12, 1, 'N');
INSERT INTO `tbl_order` VALUES (20, '2019-09-23 15:14:05', 1, 14.12, 1, 'N');
INSERT INTO `tbl_order` VALUES (21, '2019-09-23 15:14:05', 1, 15.12, 1, 'N');
INSERT INTO `tbl_order` VALUES (22, '2019-09-23 15:14:05', 1, 16.12, 1, 'N');
INSERT INTO `tbl_order` VALUES (23, '2019-09-23 15:14:05', 1, 17.12, 1, 'N');
INSERT INTO `tbl_order` VALUES (24, '2019-09-23 15:14:05', 1, 18.12, 1, 'N');
INSERT INTO `tbl_order` VALUES (25, '2019-09-23 15:14:05', 1, 19.12, 1, 'N');
INSERT INTO `tbl_order` VALUES (26, '2019-09-23 15:14:05', 1, 20.12, 1, 'N');
INSERT INTO `tbl_order` VALUES (27, '2019-09-23 15:14:05', 1, 21.12, 1, 'N');
INSERT INTO `tbl_order` VALUES (28, '2019-09-23 15:14:05', 1, 22.12, 1, 'N');
INSERT INTO `tbl_order` VALUES (29, '2019-09-24 09:31:21', 1, 2.33, 1, 'N');

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO `tbl_user` VALUES (1, 'tom', '123');
INSERT INTO `tbl_user` VALUES (2, 'mary', '321');
INSERT INTO `tbl_user` VALUES (3, 'jack', '111');
INSERT INTO `tbl_user` VALUES (4, 'tom12', 'jack');
INSERT INTO `tbl_user` VALUES (5, 'mary7', 'yyy');
INSERT INTO `tbl_user` VALUES (6, 'mary1', '33');
INSERT INTO `tbl_user` VALUES (7, 'mary9', '77');

SET FOREIGN_KEY_CHECKS = 1;
