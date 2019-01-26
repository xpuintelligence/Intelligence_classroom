/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50703
 Source Host           : localhost:3306
 Source Schema         : fc_server

 Target Server Type    : MySQL
 Target Server Version : 50703
 File Encoding         : 65001

 Date: 23/01/2019 23:04:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for duty_count
-- ----------------------------
DROP TABLE IF EXISTS `duty_count`;
CREATE TABLE `duty_count`  (
  `class_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级的ID(例如：九年级1班）',
  `count` int(11) NOT NULL DEFAULT 0 COMMENT '班级总人数',
  `come_count` int(11) NOT NULL DEFAULT 0 COMMENT '已经到了的人数',
  PRIMARY KEY (`class_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '此表统计每个班的出勤情况！' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for students_info
-- ----------------------------
DROP TABLE IF EXISTS `students_info`;
CREATE TABLE `students_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '学号',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `class_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '此表是学生基本信息统计表！' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for teacher_info
-- ----------------------------
DROP TABLE IF EXISTS `teacher_info`;
CREATE TABLE `teacher_info`  (
  `id` bigint(20) NOT NULL COMMENT '老师的ID',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '老师姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for teaching_info
-- ----------------------------
DROP TABLE IF EXISTS `teaching_info`;
CREATE TABLE `teaching_info`  (
  `teaching_id` bigint(20) NOT NULL COMMENT '每一节课程的id',
  `teacher_id` bigint(20) DEFAULT NULL COMMENT '老师的ID',
  `class_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '班级的ID',
  PRIMARY KEY (`teaching_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
