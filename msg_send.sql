/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.200.188
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : 192.168.200.188:3306
 Source Schema         : push_msg

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 02/04/2020 12:03:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_mail_msg
-- ----------------------------
DROP TABLE IF EXISTS `t_mail_msg`;
CREATE TABLE `t_mail_msg`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键ID',
  `app_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用名称',
  `msg_type` tinyint(4) NULL DEFAULT NULL COMMENT '消息类型',
  `msg_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息接收者',
  `template` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板名称',
  `subject` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮件标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '邮件内容',
  `success` tinyint(4) NULL DEFAULT NULL COMMENT '发送状态',
  `result_msg` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发送结果',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '邮件消息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_sms_msg
-- ----------------------------
DROP TABLE IF EXISTS `t_sms_msg`;
CREATE TABLE `t_sms_msg`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键ID',
  `app_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用名称',
  `msg_type` tinyint(4) NULL DEFAULT NULL COMMENT '消息类型',
  `msg_phone` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '消息接收电话',
  `template_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息模板ID',
  `content` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息内容',
  `success` tinyint(4) NULL DEFAULT NULL COMMENT '发送状态',
  `result_msg` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发送结果',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '短信消息' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
