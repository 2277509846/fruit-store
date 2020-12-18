/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : fruit_store

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 14/05/2020 11:37:03
*/
CREATE DATABASE fruit_store;
USE fruit_store;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `username` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('admin', 'admin');

-- ----------------------------
-- Table structure for fruit
-- ----------------------------
DROP TABLE IF EXISTS `fruit`;
CREATE TABLE `fruit`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `price` double NULL DEFAULT NULL,
  `unit` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sortId` int(11) NULL DEFAULT NULL,
  `count` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE,
  INDEX `sortId`(`sortId`) USING BTREE,
  CONSTRAINT `fruit_ibfk_1` FOREIGN KEY (`sortId`) REFERENCES `sort` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 78 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of fruit
-- ----------------------------
INSERT INTO `fruit` VALUES (33, '西瓜', 5, 'kg', 2, 15);
INSERT INTO `fruit` VALUES (34, '苹果', 15, 'kg', 2, 100);
INSERT INTO `fruit` VALUES (37, '葡萄', 10, 'kg', 8, 11);
INSERT INTO `fruit` VALUES (38, '哈密瓜', 10, 'kg', 5, 10);
INSERT INTO `fruit` VALUES (39, '杨梅', 5, 'kg', 3, 100);
INSERT INTO `fruit` VALUES (41, '火龙果', 10, 'kg', 3, 10);
INSERT INTO `fruit` VALUES (42, '猕猴桃', 9.5, 'kg', 3, 5);
INSERT INTO `fruit` VALUES (43, '草莓', 10, 'kg', 3, 10);
INSERT INTO `fruit` VALUES (44, '梨', 10, 'kg', 2, 10);
INSERT INTO `fruit` VALUES (45, '水蜜桃', 10, 'kg', 2, 10);
INSERT INTO `fruit` VALUES (46, '香蕉', 10, 'kg', 6, 10);
INSERT INTO `fruit` VALUES (47, '菠萝', 10, 'kg', 2, 10);
INSERT INTO `fruit` VALUES (48, '枇杷', 10, 'kg', 3, 10);
INSERT INTO `fruit` VALUES (50, '荔枝', 10, 'kg', 3, 10);
INSERT INTO `fruit` VALUES (53, '橙子', 10, 'kg', 4, 10);
INSERT INTO `fruit` VALUES (54, '橘子', 10, 'kg', 4, 10);
INSERT INTO `fruit` VALUES (55, '芒果', 15, 'kg', 3, 10);
INSERT INTO `fruit` VALUES (56, '樱桃', 5, 'kg', 3, 10);
INSERT INTO `fruit` VALUES (57, '凤梨', 15, 'kg', 3, 10);
INSERT INTO `fruit` VALUES (58, '车厘子', 15, 'kg', 3, 50);
INSERT INTO `fruit` VALUES (59, '红枣', 15, 'kg', 7, 50);
INSERT INTO `fruit` VALUES (60, '石榴', 15, 'kg', 3, 50);
INSERT INTO `fruit` VALUES (63, '柠檬', 15, 'kg', 4, 50);
INSERT INTO `fruit` VALUES (64, '山楂', 15, 'kg', 2, 50);
INSERT INTO `fruit` VALUES (66, '黑莓', 15, 'kg', 2, 50);
INSERT INTO `fruit` VALUES (67, '柚子', 15, 'kg', 4, 50);
INSERT INTO `fruit` VALUES (70, '香瓜', 10, 'kg', 5, 10);
INSERT INTO `fruit` VALUES (71, '沙果', 10, 'kg', 2, 10);
INSERT INTO `fruit` VALUES (72, '牛油果', 10, 'kg', 10, 10);
INSERT INTO `fruit` VALUES (73, '黑枣', 10, 'kg', 7, 10);
INSERT INTO `fruit` VALUES (79, '龙眼', 10, 'kg', 2, 10);

-- ----------------------------
-- Table structure for sort
-- ----------------------------
DROP TABLE IF EXISTS `sort`;
CREATE TABLE `sort`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sort
-- ----------------------------
INSERT INTO `sort` VALUES (2, '蔷薇科', '薇');
INSERT INTO `sort` VALUES (3, '核果科', '瓜与果。亦泛指果品。');
INSERT INTO `sort` VALUES (4, '芸香科', '芸');
INSERT INTO `sort` VALUES (5, '葫芦科', '芦');
INSERT INTO `sort` VALUES (6, '芭蕉科', '蕉');
INSERT INTO `sort` VALUES (7, '鼠李科', '李');
INSERT INTO `sort` VALUES (8, '葡萄科', '葡');
INSERT INTO `sort` VALUES (10, '樟科', '樟');
INSERT INTO `sort` VALUES (14, '浆果', '浆浆浆');

SET FOREIGN_KEY_CHECKS = 1;
