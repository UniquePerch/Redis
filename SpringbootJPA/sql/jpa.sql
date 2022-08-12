/*
 Navicat MySQL Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : jpa

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 12/08/2022 16:24:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `detail_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK2evm4a5md7gcyy7tg2fsb59o6`(`detail_id` ASC) USING BTREE,
  CONSTRAINT `FK2evm4a5md7gcyy7tg2fsb59o6` FOREIGN KEY (`detail_id`) REFERENCES `account_detail` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1, '654321', 'test', 1);
INSERT INTO `account` VALUES (5, '654321', 'Nike', 3);

-- ----------------------------
-- Table structure for account_detail
-- ----------------------------
DROP TABLE IF EXISTS `account_detail`;
CREATE TABLE `account_detail`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account_detail
-- ----------------------------
INSERT INTO `account_detail` VALUES (1, '杭州', '11@qq.com', '114514', 'lbw');
INSERT INTO `account_detail` VALUES (3, '重庆市渝中区解放碑', '73281937@qq.com', '1234567890', '张三');

-- ----------------------------
-- Table structure for account_score
-- ----------------------------
DROP TABLE IF EXISTS `account_score`;
CREATE TABLE `account_score`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `socre` double NULL DEFAULT NULL,
  `uid` int NULL DEFAULT NULL,
  `cid` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK7q5vn60aeeawsqh806seow2yg`(`cid` ASC) USING BTREE,
  INDEX `FKn7s2bwqeymfuh5jefw3yo9nua`(`uid` ASC) USING BTREE,
  CONSTRAINT `FK7q5vn60aeeawsqh806seow2yg` FOREIGN KEY (`cid`) REFERENCES `subjects` (`cid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKn7s2bwqeymfuh5jefw3yo9nua` FOREIGN KEY (`uid`) REFERENCES `account` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account_score
-- ----------------------------
INSERT INTO `account_score` VALUES (1, 95, 1, 1);
INSERT INTO `account_score` VALUES (2, 85, 1, 2);
INSERT INTO `account_score` VALUES (3, 100, 5, 4);
INSERT INTO `account_score` VALUES (4, 59, 5, 3);

-- ----------------------------
-- Table structure for subjects
-- ----------------------------
DROP TABLE IF EXISTS `subjects`;
CREATE TABLE `subjects`  (
  `cid` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `time` int NULL DEFAULT NULL,
  `tid` int NULL DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE,
  INDEX `FK6t1elu9u7mtgtdyi6o6t6c6xa`(`tid` ASC) USING BTREE,
  CONSTRAINT `FK6t1elu9u7mtgtdyi6o6t6c6xa` FOREIGN KEY (`tid`) REFERENCES `teachers` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of subjects
-- ----------------------------
INSERT INTO `subjects` VALUES (1, '数据结构', 64, 1);
INSERT INTO `subjects` VALUES (2, '计算机网络', 64, 1);
INSERT INTO `subjects` VALUES (3, '操作系统', 64, 2);
INSERT INTO `subjects` VALUES (4, '计算机组成原理', 64, 2);

-- ----------------------------
-- Table structure for teach_relation
-- ----------------------------
DROP TABLE IF EXISTS `teach_relation`;
CREATE TABLE `teach_relation`  (
  `cid` int NOT NULL,
  `tid` int NOT NULL,
  INDEX `FKe21rufpdxqb5n5xj1sertf9ow`(`tid` ASC) USING BTREE,
  INDEX `FK991s8e7lrvlbyx2xnm88m85mm`(`cid` ASC) USING BTREE,
  CONSTRAINT `FK991s8e7lrvlbyx2xnm88m85mm` FOREIGN KEY (`cid`) REFERENCES `subjects` (`cid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKe21rufpdxqb5n5xj1sertf9ow` FOREIGN KEY (`tid`) REFERENCES `teachers` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teach_relation
-- ----------------------------
INSERT INTO `teach_relation` VALUES (1, 1);
INSERT INTO `teach_relation` VALUES (1, 2);
INSERT INTO `teach_relation` VALUES (2, 2);
INSERT INTO `teach_relation` VALUES (3, 1);
INSERT INTO `teach_relation` VALUES (3, 2);
INSERT INTO `teach_relation` VALUES (4, 1);

-- ----------------------------
-- Table structure for teachers
-- ----------------------------
DROP TABLE IF EXISTS `teachers`;
CREATE TABLE `teachers`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teachers
-- ----------------------------
INSERT INTO `teachers` VALUES (1, '张三', '男');
INSERT INTO `teachers` VALUES (2, '李四', '女');

SET FOREIGN_KEY_CHECKS = 1;
