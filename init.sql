/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : localhost:3306
 Source Schema         : matter

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 26/10/2020 15:48:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for equip_info
-- ----------------------------
DROP TABLE IF EXISTS `equip_info`;
CREATE TABLE `equip_info` (
  `equip_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '装备Id',
  `equip_name` varchar(255) DEFAULT NULL COMMENT '装备名称',
  `part_name` varchar(255) DEFAULT NULL COMMENT '配件名称',
  `part_type` tinyint(2) DEFAULT NULL COMMENT '配件类型（0：装备 ， 1：配件）',
  `state` varchar(255) DEFAULT NULL COMMENT '维修标准',
  `worker_type` int(255) DEFAULT NULL COMMENT '工种',
  PRIMARY KEY (`equip_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of equip_info
-- ----------------------------
BEGIN;
INSERT INTO `equip_info` VALUES (1, '钢结构', '', 0, NULL, NULL);
INSERT INTO `equip_info` VALUES (2, NULL, '平台', 1, NULL, NULL);
INSERT INTO `equip_info` VALUES (3, NULL, '行走结构', 1, NULL, NULL);
INSERT INTO `equip_info` VALUES (4, NULL, '洒水器', 1, NULL, NULL);
INSERT INTO `equip_info` VALUES (5, '刀盘/刀头', NULL, 0, NULL, NULL);
INSERT INTO `equip_info` VALUES (6, '钢结构', NULL, 0, '影响施工安全', NULL);
COMMIT;

-- ----------------------------
-- Table structure for equip_mapping
-- ----------------------------
DROP TABLE IF EXISTS `equip_mapping`;
CREATE TABLE `equip_mapping` (
  `equip_mapping_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '装映射备Id',
  `equip_id` int(11) DEFAULT NULL COMMENT '装备Id',
  `part_id` int(11) DEFAULT NULL COMMENT '配件Id',
  PRIMARY KEY (`equip_mapping_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of equip_mapping
-- ----------------------------
BEGIN;
INSERT INTO `equip_mapping` VALUES (1, 1, 2);
INSERT INTO `equip_mapping` VALUES (2, 1, 3);
INSERT INTO `equip_mapping` VALUES (3, 5, 4);
COMMIT;

-- ----------------------------
-- Table structure for equip_work_info
-- ----------------------------
DROP TABLE IF EXISTS `equip_work_info`;
CREATE TABLE `equip_work_info` (
  `equip_work_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '本身Id',
  `equip_start_time` timestamp NULL DEFAULT NULL COMMENT '装备启动时间',
  `equip_last_shutdown_time` timestamp NULL DEFAULT NULL COMMENT '上次关闭时间',
  `equip_work_hours` int(255) DEFAULT NULL COMMENT '装备运行总时长',
  PRIMARY KEY (`equip_work_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of equip_work_info
-- ----------------------------
BEGIN;
INSERT INTO `equip_work_info` VALUES (1, '2020-09-23 14:56:05', '2020-10-13 19:24:08', 1);
COMMIT;

-- ----------------------------
-- Table structure for matter
-- ----------------------------
DROP TABLE IF EXISTS `matter`;
CREATE TABLE `matter` (
  `matter_id` int(32) NOT NULL AUTO_INCREMENT COMMENT '事件Id',
  `equip_id` int(32) NOT NULL COMMENT '装备Id',
  `part_id` varchar(255) NOT NULL COMMENT '配件Id',
  `pre_op` varchar(255) NOT NULL COMMENT '前导动作',
  `execu_standard` varchar(255) DEFAULT NULL COMMENT '标准/阈值',
  `maint_op` varchar(255) DEFAULT NULL COMMENT '保养动作',
  `matter_trigger_id` int(32) NOT NULL COMMENT '工作时间',
  `worker_type` int(255) DEFAULT NULL COMMENT '工种',
  PRIMARY KEY (`matter_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of matter
-- ----------------------------
BEGIN;
INSERT INTO `matter` VALUES (1, 1, '2', '检查', '破损1', NULL, 1, 0);
INSERT INTO `matter` VALUES (2, 6, '4', '检查', '破损2', NULL, 2, 0);
INSERT INTO `matter` VALUES (3, 1, '3', '检查', '破损3', NULL, 13, 0);
INSERT INTO `matter` VALUES (4, 5, '2', '检查', '破损4', NULL, 13, 1);
COMMIT;

-- ----------------------------
-- Table structure for matter_trigger
-- ----------------------------
DROP TABLE IF EXISTS `matter_trigger`;
CREATE TABLE `matter_trigger` (
  `trigger_id` int(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL COMMENT '描述',
  `type` int(255) DEFAULT NULL COMMENT '标识触发类型 0：时间间隔，1：时间点',
  `time_span` int(11) DEFAULT NULL COMMENT '时间间隔',
  `time_point` int(11) DEFAULT NULL COMMENT '时间点(8点，9点)',
  PRIMARY KEY (`trigger_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of matter_trigger
-- ----------------------------
BEGIN;
INSERT INTO `matter_trigger` VALUES (1, '开机前', 1, NULL, NULL);
INSERT INTO `matter_trigger` VALUES (2, '每班', 0, 8, NULL);
INSERT INTO `matter_trigger` VALUES (3, '每天', 0, 24, NULL);
INSERT INTO `matter_trigger` VALUES (4, '每周', 0, 168, NULL);
INSERT INTO `matter_trigger` VALUES (5, '每月', 0, 720, NULL);
INSERT INTO `matter_trigger` VALUES (6, '每季度', 0, 2160, NULL);
INSERT INTO `matter_trigger` VALUES (7, '每半年', 0, 4320, NULL);
INSERT INTO `matter_trigger` VALUES (8, '每年', 0, 8640, NULL);
INSERT INTO `matter_trigger` VALUES (9, '每300小时', 0, 300, NULL);
INSERT INTO `matter_trigger` VALUES (10, '带压进舱前', 1, NULL, NULL);
INSERT INTO `matter_trigger` VALUES (11, '停机后', 1, NULL, NULL);
INSERT INTO `matter_trigger` VALUES (12, '新项目开始前', 1, NULL, NULL);
INSERT INTO `matter_trigger` VALUES (13, '每小时', 0, 1, NULL);
COMMIT;

-- ----------------------------
-- Table structure for task_list_info
-- ----------------------------
DROP TABLE IF EXISTS `task_list_info`;
CREATE TABLE `task_list_info` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '子任务id',
  `work_id` int(11) NOT NULL COMMENT '工单Id',
  `task_status` int(11) NOT NULL COMMENT '任务状态（0：未完成，1：跳过，2 已完成）',
  `matter_id` int(11) NOT NULL COMMENT '维修事件（matter）id',
  `worker_info_id` int(11) DEFAULT NULL COMMENT '任务执行人',
  PRIMARY KEY (`task_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of task_list_info
-- ----------------------------
BEGIN;
INSERT INTO `task_list_info` VALUES (33, 34, 0, 3, NULL);
INSERT INTO `task_list_info` VALUES (34, 35, 0, 4, NULL);
COMMIT;

-- ----------------------------
-- Table structure for work_list_info
-- ----------------------------
DROP TABLE IF EXISTS `work_list_info`;
CREATE TABLE `work_list_info` (
  `work_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '任务Id',
  `work_order_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '任务下达时间',
  `work_start_time` timestamp NULL DEFAULT NULL COMMENT '任务开始时间',
  `worker` int(255) DEFAULT NULL COMMENT '任务执行人',
  `work_status` int(255) NOT NULL COMMENT '任务状态（0：初始状态，1：工人确认，2：已完成，3：存在遗留项）',
  `work_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '任务更新时间',
  `work_hours` int(255) DEFAULT NULL COMMENT '任务持续时长',
  `work_quality` int(255) DEFAULT NULL COMMENT '任务评分',
  `worker_type` int(255) DEFAULT NULL COMMENT '执行工种',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`work_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of work_list_info
-- ----------------------------
BEGIN;
INSERT INTO `work_list_info` VALUES (34, '2020-10-26 07:43:00', NULL, NULL, 0, '2020-10-26 07:43:00', NULL, NULL, 0, NULL);
INSERT INTO `work_list_info` VALUES (35, '2020-10-26 07:43:00', NULL, NULL, 0, '2020-10-26 07:43:00', NULL, NULL, 1, NULL);
COMMIT;

-- ----------------------------
-- Table structure for worker_info
-- ----------------------------
DROP TABLE IF EXISTS `worker_info`;
CREATE TABLE `worker_info` (
  `worker_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '工人Id',
  `worker_name` varchar(255) DEFAULT NULL COMMENT '工人名字',
  `worker_type` int(255) DEFAULT NULL COMMENT '工种类型（1：维修工，2：电工）',
  `hours` int(255) DEFAULT NULL COMMENT '工作时长（小时）',
  `worker_state` int(255) DEFAULT NULL COMMENT '工作状态（0：待工，1：在工，2：请假 ）',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`worker_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of worker_info
-- ----------------------------
BEGIN;
INSERT INTO `worker_info` VALUES (1000, '维修工-张三', 11, NULL, 1, '普通维修工');
INSERT INTO `worker_info` VALUES (1001, '管理员-王五', 1, 12, 1, NULL);
INSERT INTO `worker_info` VALUES (1002, '电工-李四', 12, NULL, 1, '电工');
INSERT INTO `worker_info` VALUES (1003, '信息员-赵六', 2, NULL, NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
