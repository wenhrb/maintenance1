
-- ----------------------------
-- Table structure for equip_info
-- ----------------------------
DROP TABLE IF EXISTS `equip_info`;
CREATE TABLE `equip_info`  (
  `equip_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '装备Id',
  `equip_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '装备名称',
  `part_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配件名称',
  `part_type` tinyint(2) NULL DEFAULT NULL COMMENT '配件类型（0：装备 ， 1：配件）',
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维修标准',
  PRIMARY KEY (`equip_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of equip_info
-- ----------------------------
INSERT INTO `equip_info` VALUES (1, '钢结构', '', 0, NULL);
INSERT INTO `equip_info` VALUES (2, NULL, '平台', 1, NULL);
INSERT INTO `equip_info` VALUES (3, NULL, '行走结构', 1, NULL);
INSERT INTO `equip_info` VALUES (4, NULL, '洒水器', 1, NULL);
INSERT INTO `equip_info` VALUES (5, '刀盘/刀头', NULL, 0, NULL);
INSERT INTO `equip_info` VALUES (6, '钢结构', NULL, 0, '影响施工安全');
INSERT INTO `equip_info` VALUES (7, NULL, NULL, NULL, NULL);
INSERT INTO `equip_info` VALUES (8, '钢结构', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for equip_mapping
-- ----------------------------
DROP TABLE IF EXISTS `equip_mapping`;
CREATE TABLE `equip_mapping`  (
  `equip_mapping_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '装映射备Id',
  `equip_id` int(11) NULL DEFAULT NULL COMMENT '装备Id',
  `part_id` int(11) NULL DEFAULT NULL COMMENT '配件Id',
  PRIMARY KEY (`equip_mapping_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of equip_mapping
-- ----------------------------
INSERT INTO `equip_mapping` VALUES (1, 1, 2);
INSERT INTO `equip_mapping` VALUES (2, 1, 3);
INSERT INTO `equip_mapping` VALUES (3, 5, 4);

-- ----------------------------
-- Table structure for equip_work_info
-- ----------------------------
DROP TABLE IF EXISTS `equip_work_info`;
CREATE TABLE `equip_work_info`  (
  `equip_work_id` int(11) NOT NULL COMMENT '本身Id',
  `equip_start_time` timestamp(0) NULL DEFAULT NULL COMMENT '装备启动时间',
  `equip_last_shutdown_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上次关闭时间',
  `equip_work_hours` int(255) NULL DEFAULT NULL COMMENT '装备运行总时长',
  PRIMARY KEY (`equip_work_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for maintenance
-- ----------------------------
DROP TABLE IF EXISTS `matter`;
CREATE TABLE `matter`  (
  `matter_id` int(32) NOT NULL AUTO_INCREMENT COMMENT '事件Id',
  `equip_id` int(32) NOT NULL COMMENT '装备Id',
  `part_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配件Id',
  `pre_op` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '前导动作',
  `execu_standard` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标准/阈值',
  `maint_op` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '保养动作',
  `matter_trigger_id` int(32) NOT NULL COMMENT '工作时间',
  PRIMARY KEY (`matter_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for task_list_info
-- ----------------------------
DROP TABLE IF EXISTS `task_list_info`;
CREATE TABLE `task_list_info`  (
  `task_id` int(11) NOT NULL COMMENT '子任务id',
  `work_id` int(11) NOT NULL COMMENT '工单Id',
  `task_status` int(11) NOT NULL COMMENT '任务状态（0：未完成，1：跳过，2 已完成）',
  `matter_id` int(11) NOT NULL COMMENT '维修事件（maintenance）id',
  `worker_info_id` int(11) NOT NULL COMMENT '任务执行人',
  PRIMARY KEY (`task_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for trigger
-- ----------------------------
DROP TABLE IF EXISTS `trigger`;
CREATE TABLE `trigger`  (
  `trigger_id` int(20) NOT NULL AUTO_INCREMENT,
  `condition` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`trigger_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of trigger
-- ----------------------------
INSERT INTO `trigger` VALUES (1, '开机前');
INSERT INTO `trigger` VALUES (2, '每班');
INSERT INTO `trigger` VALUES (3, '每天');
INSERT INTO `trigger` VALUES (4, '每周');
INSERT INTO `trigger` VALUES (5, '每月');
INSERT INTO `trigger` VALUES (6, '每季度');
INSERT INTO `trigger` VALUES (7, '每半年');
INSERT INTO `trigger` VALUES (8, '每年');
INSERT INTO `trigger` VALUES (9, '每300小时');
INSERT INTO `trigger` VALUES (10, '带压进舱前');
INSERT INTO `trigger` VALUES (11, '停机后');
INSERT INTO `trigger` VALUES (12, '新项目开始前');

-- ----------------------------
-- Table structure for work_list_info
-- ----------------------------
DROP TABLE IF EXISTS `work_list_info`;
CREATE TABLE `work_list_info`  (
  `work_id` int(11) NOT NULL COMMENT '任务Id',
  `work_order_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '任务下达时间',
  `work_start_time` timestamp(0) NULL DEFAULT NULL COMMENT '任务开始时间',
  `worker` int(255) NOT NULL COMMENT '任务执行人',
  `work_status` int(255) NOT NULL COMMENT '任务状态（0：初始状态，1：工人确认，2：已完成，3：存在遗留项）',
  `work_update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '任务更新时间',
  `work_hours` int(255) NULL DEFAULT NULL COMMENT '任务持续时长',
  `work_quality` int(255) NULL DEFAULT NULL COMMENT '任务评分',
  PRIMARY KEY (`work_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for worker_info
-- ----------------------------
DROP TABLE IF EXISTS `worker_info`;
CREATE TABLE `worker_info`  (
  `worker_id` int(11) NOT NULL COMMENT '工人Id',
  `worker_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工人名字',
  `worker_type` int(255) NULL DEFAULT NULL COMMENT '工种类型（1：维修工，2：电工）',
  `hours` int(255) NULL DEFAULT NULL COMMENT '工作时长（小时）',
  `worker_state` int(255) NULL DEFAULT NULL COMMENT '工作状态（0：待工，1：在工，2：请假 ）',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`worker_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
