/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : platform

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-01-22 13:46:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for platform_base_info
-- ----------------------------
DROP TABLE IF EXISTS `platform_base_info`;
CREATE TABLE `platform_base_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '变量配置表id',
  `base_code` varchar(255) DEFAULT NULL COMMENT '变量代码',
  `base_name` varchar(255) DEFAULT NULL COMMENT '变量名称',
  `base_type` varchar(255) DEFAULT NULL COMMENT '变量类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='变量配置表';

-- ----------------------------
-- Records of platform_base_info
-- ----------------------------
INSERT INTO `platform_base_info` VALUES ('1', '20191231', '1', 'OrderNoCreateVar');
INSERT INTO `platform_base_info` VALUES ('2', '0', '待审核', 'OrderStatus');
INSERT INTO `platform_base_info` VALUES ('3', '1', '待签收', 'OrderStatus');
INSERT INTO `platform_base_info` VALUES ('4', '2', '处理中', 'OrderStatus');
INSERT INTO `platform_base_info` VALUES ('5', '3', '部门拒绝', 'OrderStatus');
INSERT INTO `platform_base_info` VALUES ('6', '4', '已结案', 'OrderStatus');
INSERT INTO `platform_base_info` VALUES ('7', '5', '退回修改', 'OrderStatus');
INSERT INTO `platform_base_info` VALUES ('8', '6', '部门答复', 'OrderStatus');
INSERT INTO `platform_base_info` VALUES ('10', '7', '送结案', 'OrderStatus');
INSERT INTO `platform_base_info` VALUES ('11', '0', '建议', 'AppealType');
INSERT INTO `platform_base_info` VALUES ('12', '1', '求助', 'AppealType');
INSERT INTO `platform_base_info` VALUES ('13', '2', '表扬', 'AppealType');
INSERT INTO `platform_base_info` VALUES ('14', '3', '举报', 'AppealType');
INSERT INTO `platform_base_info` VALUES ('16', '0', '女', 'Sex');
INSERT INTO `platform_base_info` VALUES ('17', '1', '男', 'Sex');

-- ----------------------------
-- Table structure for platform_organ
-- ----------------------------
DROP TABLE IF EXISTS `platform_organ`;
CREATE TABLE `platform_organ` (
  `id` int(11) NOT NULL COMMENT '处办部门id',
  `organ_name` varchar(255) DEFAULT NULL COMMENT '处办单位姓名',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级处办单位id',
  `reserved1` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `reserved2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `reserved3` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `reserved4` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `reserved5` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='处办部门表';

-- ----------------------------
-- Records of platform_organ
-- ----------------------------
INSERT INTO `platform_organ` VALUES ('1', '工业园区', '0', null, null, null, null, null);
INSERT INTO `platform_organ` VALUES ('10', '街道', '1', null, null, null, null, null);
INSERT INTO `platform_organ` VALUES ('20', '社工委', '1', null, null, null, null, null);
INSERT INTO `platform_organ` VALUES ('101', '斜塘街道', '10', null, null, null, null, null);
INSERT INTO `platform_organ` VALUES ('102', '娄葑街道', '10', null, null, null, null, null);
INSERT INTO `platform_organ` VALUES ('103', '唯亭街道', '10', null, null, null, null, null);
INSERT INTO `platform_organ` VALUES ('201', '湖西社工委', '20', null, null, null, null, null);
INSERT INTO `platform_organ` VALUES ('202', '湖东社工委', '20', null, null, null, null, null);
INSERT INTO `platform_organ` VALUES ('10101', '莲花社区', '101', null, null, null, null, null);

-- ----------------------------
-- Table structure for platform_role
-- ----------------------------
DROP TABLE IF EXISTS `platform_role`;
CREATE TABLE `platform_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_code` varchar(255) DEFAULT NULL COMMENT '角色代码',
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `reserved1` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `reserved2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `reserved3` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `reserved4` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `reserved5` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of platform_role
-- ----------------------------
INSERT INTO `platform_role` VALUES ('3', 'ejbm', '二级部门', '接收一级部门派送来的工单', null, null, null, null, null);
INSERT INTO `platform_role` VALUES ('4', 'htzz', '后台组长', '退回修改，发送二级部门', null, null, null, null, null);
INSERT INTO `platform_role` VALUES ('5', 'ptyh', '普通用户', '只能创建工单', null, null, null, null, null);

-- ----------------------------
-- Table structure for platform_user
-- ----------------------------
DROP TABLE IF EXISTS `platform_user`;
CREATE TABLE `platform_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_code` varchar(255) DEFAULT NULL COMMENT '用户代码\r\n',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户姓名',
  `password` varchar(255) DEFAULT '' COMMENT '用户密码',
  `email` varchar(255) DEFAULT NULL COMMENT '用户邮件',
  `phone` varchar(255) DEFAULT NULL COMMENT '用户手机',
  `id_number` varchar(255) DEFAULT '' COMMENT '身份证号',
  `sex` int(11) DEFAULT NULL COMMENT '性别 1-男 0-女',
  `role` int(11) DEFAULT NULL COMMENT '用户权限',
  `organ_id` int(11) DEFAULT NULL COMMENT '处办单位id',
  `organ_name` varchar(255) DEFAULT NULL COMMENT '处办单位姓名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `reserved1` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `reserved2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `reserved3` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `reserved4` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `reserved5` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of platform_user
-- ----------------------------
INSERT INTO `platform_user` VALUES ('1', 'hfp', '黄飞鹏', 'D81BC281FBC7FA6AB0600151586D24BD', 'hfp@123.com', '12345', '12345', '1', '1', '101', '斜塘街道', '2019-12-26 14:04:07', '2019-12-26 14:04:07', null, null, null, null, null);
INSERT INTO `platform_user` VALUES ('2', 'ljj', '梁俊杰', '09EEB592558D88F372F4AEC8DA4B4637', 'ljj@123.com', '12345', '12345', '1', '0', null, null, '2019-11-01 22:35:40', '2019-11-01 22:35:40', null, null, null, null, null);
INSERT INTO `platform_user` VALUES ('3', 'wbx', 'baiyao', '09EEB592558D88F372F4AEC8DA4B4637', '1272764421@qq.com', '17605240157', '12345', '1', '3', null, null, '2019-11-01 22:35:42', '2019-11-01 22:35:42', null, null, null, null, null);
INSERT INTO `platform_user` VALUES ('4', 'jitui', 'jitui2333', 'D8F80B67499E434EA61ADAF6E6219BF2', 'jitui@123.com', '13812222222', '123456', '0', '3', null, null, '2019-12-06 14:57:56', '2019-12-06 14:57:56', null, null, null, null, null);
INSERT INTO `platform_user` VALUES ('5', 'bjc', 'bjcbjc', 'D8F80B67499E434EA61ADAF6E6219BF2', 'bjc@123.com', '11122223333', '11122223333', '1', '3', null, null, '2019-12-16 16:32:04', '2019-12-16 16:32:04', null, null, null, null, null);
INSERT INTO `platform_user` VALUES ('6', 'hhh', 'hhhhh', '3273C3AF1F259E5E19464976B3FBEE58', 'hhh@123.com', '12311112222', '213321', '0', '0', null, null, '2019-11-01 22:35:46', '2019-11-01 22:35:46', null, null, null, null, null);
INSERT INTO `platform_user` VALUES ('7', 'qyd', '益达23333', 'D8F80B67499E434EA61ADAF6E6219BF2', 'yida@123.com', '12222333333', '1231231231223', '0', '2', null, null, '2019-12-23 15:33:04', '2019-12-23 15:33:04', null, null, null, null, null);
INSERT INTO `platform_user` VALUES ('8', 'qyd2', '益达2222', 'D8F80B67499E434EA61ADAF6E6219BF2', 'yida@123321.com', '12333212312', '12333212312', '1', '0', null, null, '2019-11-15 22:51:58', '2019-11-15 22:51:58', null, null, null, null, null);
INSERT INTO `platform_user` VALUES ('9', 'qyd3', 'qyd3h', 'D8F80B67499E434EA61ADAF6E6219BF2', 'qyd3@123.com', '12321222132', '12321222132', '1', '0', null, null, '2019-11-15 22:52:38', '2019-11-15 22:52:38', null, null, null, null, null);
INSERT INTO `platform_user` VALUES ('10', 'qyd4', 'qyd4h', 'D8F80B67499E434EA61ADAF6E6219BF2', 'qyd4@123.com', '12334521232', '12334521232', '1', '0', null, null, '2019-12-06 15:10:39', '2019-12-06 15:10:39', null, null, null, null, null);
INSERT INTO `platform_user` VALUES ('11', 'jituijitui', 'jituijitui', '66E01CC791F37FCC4676B29497A6137C', 'jituij@123.com', '12312354567', '12312354567', '1', '0', null, null, '2019-12-17 16:53:41', '2019-12-17 16:53:41', null, null, null, null, null);
INSERT INTO `platform_user` VALUES ('12', 'ljj1', '梁俊杰', '09EEB592558D88F372F4AEC8DA4B4637', null, null, null, '1', '0', null, null, '2019-12-17 16:53:44', '2019-12-17 16:53:44', null, null, null, null, null);

-- ----------------------------
-- Table structure for platform_user_role
-- ----------------------------
DROP TABLE IF EXISTS `platform_user_role`;
CREATE TABLE `platform_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户角色表id',
  `user_name` int(11) DEFAULT NULL COMMENT '用户名',
  `user_code` varchar(255) DEFAULT NULL COMMENT '用户代码',
  `role_code` int(11) DEFAULT NULL COMMENT '角色code',
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名',
  `reserved1` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `reserved2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `reserved3` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `reserved4` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `reserved5` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of platform_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for platform_workorders
-- ----------------------------
DROP TABLE IF EXISTS `platform_workorders`;
CREATE TABLE `platform_workorders` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '工单id',
  `order_number` varchar(255) DEFAULT NULL COMMENT '工单编号',
  `order_status` varchar(255) DEFAULT NULL COMMENT '工单状态\r\n0-	待审核\r\n1-	待签收\r\n2-	处理中\r\n3-	拒绝\r\n4-	已结案\r\n5-	退回修改\r\n6-   部门答复\r\n7-   送结案',
  `user_code` varchar(255) DEFAULT NULL COMMENT '创建人代码',
  `user_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `id_number` varchar(255) DEFAULT NULL COMMENT '诉求人身份证',
  `phone` varchar(255) DEFAULT NULL COMMENT '创建人电话',
  `sex` varchar(255) DEFAULT NULL COMMENT '创建人性别',
  `email` varchar(255) DEFAULT NULL COMMENT '创建人邮箱',
  `appeal_time` datetime DEFAULT NULL COMMENT '诉求时间',
  `appeal_type` varchar(255) DEFAULT NULL COMMENT '诉求类型',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `appeal_content` varchar(255) DEFAULT NULL COMMENT '诉求内容',
  `organ_id` int(11) DEFAULT NULL COMMENT '处办单位id',
  `organ_name` varchar(255) DEFAULT NULL COMMENT '处办单位名字',
  `result_content` varchar(255) DEFAULT NULL COMMENT '处理意见',
  `result_time` datetime DEFAULT NULL COMMENT '交办时限',
  `refuse_content` varchar(255) DEFAULT NULL COMMENT '拒绝理由',
  `sign_time` datetime DEFAULT NULL COMMENT '签收时限',
  `refuse_time` datetime DEFAULT NULL COMMENT '拒绝时限',
  `last_time` datetime DEFAULT NULL COMMENT '上次操作时间',
  `modify_content` varchar(255) DEFAULT NULL COMMENT '退回修改原因',
  `reserved1` varchar(255) DEFAULT NULL COMMENT '预留字段1',
  `reserved2` varchar(255) DEFAULT NULL COMMENT '预留字段2',
  `reserved3` varchar(255) DEFAULT NULL COMMENT '预留字段3',
  `reserved4` varchar(255) DEFAULT NULL COMMENT '预留字段4',
  `reserved5` varchar(255) DEFAULT NULL COMMENT '预留字段5',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='工单表';

-- ----------------------------
-- Records of platform_workorders
-- ----------------------------
INSERT INTO `platform_workorders` VALUES ('1', 'WZ2220001201912180001', '5', 'hfp', '黄飞鹏', '12345', '12345', '1', 'hfp@123.com', '2019-12-18 16:16:26', '建议', '123213', '123213', '101', '斜塘街道', null, '2019-12-18 16:16:26', null, null, '2019-12-19 16:11:04', '2019-12-26 14:07:20', '123', null, null, null, null, null);
INSERT INTO `platform_workorders` VALUES ('5', 'WZ2220001201912200001', '4', 'hfp', '黄飞鹏', '12345', '12345', '1', 'hfp@123.com', '2019-12-23 10:48:05', '举报', '位啊无多', '哇哇的哇大', '101', '斜塘街道', '撒大声地', '2019-12-23 10:48:05', '123', '2019-12-22 10:54:02', '2019-12-22 10:53:57', '2019-12-23 14:29:44', null, null, null, null, null, null);
INSERT INTO `platform_workorders` VALUES ('6', 'WZ2220001201912200002', '2', 'hfp', '黄飞鹏', '12345', '12345', '1', 'hfp@123.com', '2019-12-20 14:04:50', '表扬', '实打实打算', '萨达萨达萨达萨达是', '101', '斜塘街道', '阿斯顿萨达', '2019-12-20 14:04:50', null, '2019-12-22 13:54:37', '2019-12-22 13:54:29', '2019-12-20 14:04:50', null, null, null, null, null, null);
INSERT INTO `platform_workorders` VALUES ('7', 'WZ2220001201912200003', '3', 'hfp', '黄飞鹏', '12345', '12345', '1', 'hfp@123.com', '2019-12-20 14:05:55', '举报', '21321321', '12321321321', '101', '斜塘街道', '123213', '2019-12-20 14:05:55', '我打算大撒大撒', '2019-12-23 00:00:00', '2019-12-23 00:00:00', '2019-12-20 14:05:55', null, null, null, null, null, null);
INSERT INTO `platform_workorders` VALUES ('8', 'WZ2220001201912200004', '7', 'hfp', '黄飞鹏', '12345', '12345', '1', 'hfp@123.com', '2019-12-23 10:55:05', '求助', '123213', '12321321321', '101', '斜塘街道', '其味无穷二群', '2019-12-23 10:55:05', null, '2019-12-23 00:00:00', '2019-12-23 00:00:00', '2019-12-23 13:55:06', null, null, null, null, null, null);
INSERT INTO `platform_workorders` VALUES ('9', 'WZ2220001201912200005', '6', 'hfp', '黄飞鹏', '12345', '12345', '1', 'hfp@123.com', '2019-12-23 10:54:20', '建议', '213213213', '213213213', '101', '斜塘街道', '745132132', '2019-12-25 00:00:00', null, '2019-12-25 00:00:00', '2019-12-25 00:00:00', '2019-12-23 13:54:53', null, null, null, null, null, null);
INSERT INTO `platform_workorders` VALUES ('10', 'WZ2220001201912230001', '1', 'hfp', '黄飞鹏', '12345', '12345', '1', 'hfp@123.com', '2019-12-23 09:05:40', '建议', '1wq21wqewq1eqweqw31', '万达哇无大所', '102', '娄葑街道', '1321312312', '2019-12-25 00:00:00', null, '2019-12-25 00:00:00', '2019-12-25 00:00:00', '2019-12-23 13:53:17', null, null, null, null, null, null);
INSERT INTO `platform_workorders` VALUES ('11', 'WZ2220001201912230002', '5', 'hfp', '黄飞鹏', '12345', '12345', '1', 'hfp@123.com', '2019-12-23 10:24:57', '建议', '山大飒飒大123', '打算的撒大大撒打算打算的幸存者瞎扯下车123123', '101', '斜塘街道', null, '2019-12-24 10:24:57', null, null, '2019-12-24 10:24:57', '2019-12-26 14:13:43', '12323', null, null, null, null, null);
INSERT INTO `platform_workorders` VALUES ('12', 'WZ2220001201912230003', '1', 'hfp', '黄飞鹏', '12345', '12345', '1', 'hfp@123.com', '2019-12-23 14:03:21', '表扬', '湿哒哒所大所', 'sad撒多撒大所撒大声地', '101', '斜塘街道', '撒大声地撒大', '2019-12-25 00:00:00', null, '2019-12-25 00:00:00', '2019-12-25 00:00:00', '2019-12-23 14:04:02', null, null, null, null, null, null);
INSERT INTO `platform_workorders` VALUES ('13', 'WZ2220001201912230004', '0', 'hfp', '黄飞鹏', '12345', '12345', '1', 'hfp@123.com', '2019-12-23 14:03:35', '举报', '阿斯顿撒多撒d', '阿斯顿撒旦sad阿萨德阿萨德112 3132 sadas', '101', '斜塘街道', null, '2019-12-24 14:03:35', null, null, '2019-12-24 14:03:35', '2019-12-23 14:03:35', null, null, null, null, null, null);
INSERT INTO `platform_workorders` VALUES ('15', 'WZ2220001201912250001', '0', 'hfp', '黄飞鹏', '12345', '12345', '1', 'hfp@123.com', '2019-12-25 09:38:34', '建议', 'asdsad', '12312asdas中小城市达大厦', '101', '斜塘街道', null, '2019-12-26 09:38:34', null, null, '2019-12-26 09:38:34', '2019-12-25 09:38:33', null, null, null, null, null, null);
INSERT INTO `platform_workorders` VALUES ('16', 'WZ2220001201912260001', '0', 'hfp', '黄飞鹏', '12345', '12345', '1', 'hfp@123.com', '2019-12-26 15:44:00', '表扬', '2123123', '123123123', '101', '斜塘街道', null, null, null, null, null, '2019-12-26 15:44:00', null, null, null, null, null, null);
INSERT INTO `platform_workorders` VALUES ('17', 'WZ2220001201912310001', '0', 'hfp', '黄飞鹏', '12345', '12345', '1', 'hfp@123.com', '2019-12-31 10:26:24', '表扬', '富华科技大厦', '测试测试', '101', '斜塘街道', null, null, null, null, null, '2019-12-31 10:26:23', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for platform_workorders_log
-- ----------------------------
DROP TABLE IF EXISTS `platform_workorders_log`;
CREATE TABLE `platform_workorders_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '工单历史id',
  `node` varchar(255) DEFAULT NULL COMMENT '工单节点',
  `organ_name` varchar(255) DEFAULT NULL COMMENT '处办单位名称',
  `deal_way` varchar(255) DEFAULT NULL COMMENT '处理方式',
  `deal_content` varchar(255) DEFAULT NULL COMMENT '处理意见',
  `deal_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '处理时间戳',
  `consume_time` varchar(255) DEFAULT NULL COMMENT '耗费时间',
  `deal_user` varchar(255) DEFAULT NULL COMMENT '处理人账号',
  `workorders_id` int(11) DEFAULT NULL COMMENT '工单id',
  `order_number` varchar(255) DEFAULT NULL COMMENT '工单编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8 COMMENT='工单历史表';

-- ----------------------------
-- Records of platform_workorders_log
-- ----------------------------
INSERT INTO `platform_workorders_log` VALUES ('49', '工单创建', '斜塘街道', '送审核', '', '2019-12-18 16:11:04', '0s', 'hfp', '1', 'WZ2220001201912180001');
INSERT INTO `platform_workorders_log` VALUES ('50', '后台审核', '斜塘街道', '退回修改', '123', '2019-12-18 16:16:27', '0s', 'hfp', '1', 'WZ2220001201912180001');
INSERT INTO `platform_workorders_log` VALUES ('52', '工单创建', '斜塘街道', '送审核', '', '2019-12-19 15:03:19', '0s', 'hfp', '2', 'WZ2220001201912190002');
INSERT INTO `platform_workorders_log` VALUES ('53', '后台审核', '斜塘街道', '交办部门', '123', '2019-12-19 16:53:02', '0s', 'hfp', '2', 'WZ2220001201912190002');
INSERT INTO `platform_workorders_log` VALUES ('54', '后台审核', '斜塘街道', '交办部门', '123213123', '2019-12-19 16:58:09', '5m:7s', 'hfp', '2', 'WZ2220001201912190002');
INSERT INTO `platform_workorders_log` VALUES ('57', '工单创建', '斜塘街道', '送审核', '', '2019-12-20 10:06:59', '0s', 'hfp', '5', 'WZ2220001201912200001');
INSERT INTO `platform_workorders_log` VALUES ('58', '后台审核', '斜塘街道', '交办部门', '1撒大声地', '2019-12-20 10:08:32', '1m:32s', 'hfp', '5', 'WZ2220001201912200001');
INSERT INTO `platform_workorders_log` VALUES ('59', '部门处理', '斜塘街道', '部门签收', ' ', '2019-12-20 10:33:57', '1m:44s', 'hfp', '5', 'WZ2220001201912200001');
INSERT INTO `platform_workorders_log` VALUES ('60', '部门处理', '斜塘街道', '部门拒绝', '123', '2019-12-20 10:46:32', '13m:18s', 'hfp', '5', 'WZ2220001201912200001');
INSERT INTO `platform_workorders_log` VALUES ('61', '后台审核', '斜塘街道', '交办部门', '撒大声地', '2019-12-20 10:54:12', '7m:40s', 'hfp', '5', 'WZ2220001201912200001');
INSERT INTO `platform_workorders_log` VALUES ('62', '部门处理', '斜塘街道', '部门签收', '', '2019-12-20 10:54:20', '8s', 'hfp', '5', 'WZ2220001201912200001');
INSERT INTO `platform_workorders_log` VALUES ('63', '部门处理', '斜塘街道', '部门答复', null, '2019-12-20 11:29:09', '34m:49s', 'hfp', '5', 'WZ2220001201912200001');
INSERT INTO `platform_workorders_log` VALUES ('64', '工单创建', '斜塘街道', '送审核', '', '2019-12-20 13:53:41', '0s', 'hfp', '6', 'WZ2220001201912200002');
INSERT INTO `platform_workorders_log` VALUES ('65', '后台审核', '斜塘街道', '交办部门', '阿斯顿萨达', '2019-12-20 13:54:45', '1m:3s', 'hfp', '6', 'WZ2220001201912200002');
INSERT INTO `platform_workorders_log` VALUES ('66', '工单创建', '斜塘街道', '送审核', '', '2019-12-20 13:56:02', '0s', 'hfp', '7', 'WZ2220001201912200003');
INSERT INTO `platform_workorders_log` VALUES ('67', '工单创建', '斜塘街道', '送审核', '', '2019-12-20 14:03:22', '0s', 'hfp', '8', 'WZ2220001201912200004');
INSERT INTO `platform_workorders_log` VALUES ('68', '后台审核', '斜塘街道', '交办部门', '123213', '2019-12-20 14:04:34', '8m:33s', 'hfp', '7', 'WZ2220001201912200003');
INSERT INTO `platform_workorders_log` VALUES ('69', '部门处理', '斜塘街道', '部门签收', '', '2019-12-20 14:04:50', '10m:5s', 'hfp', '6', 'WZ2220001201912200002');
INSERT INTO `platform_workorders_log` VALUES ('70', '后台审核', '斜塘街道', '交办部门', '阿斯顿萨达萨达萨达萨达在', '2019-12-20 14:05:34', '2m:11s', 'hfp', '8', 'WZ2220001201912200004');
INSERT INTO `platform_workorders_log` VALUES ('71', '部门处理', '斜塘街道', '部门签收', '', '2019-12-20 14:05:50', '1m:15s', 'hfp', '7', 'WZ2220001201912200003');
INSERT INTO `platform_workorders_log` VALUES ('72', '部门处理', '斜塘街道', '部门拒绝', '我打算大撒大撒', '2019-12-20 14:05:55', '6s', 'hfp', '7', 'WZ2220001201912200003');
INSERT INTO `platform_workorders_log` VALUES ('73', '工单创建', '斜塘街道', '送审核', '', '2019-12-20 14:07:57', '0s', 'hfp', '9', 'WZ2220001201912200005');
INSERT INTO `platform_workorders_log` VALUES ('74', '工单创建', '娄葑街道', '送审核', '', '2019-12-23 09:05:40', '0s', 'hfp', '10', 'WZ2220001201912230001');
INSERT INTO `platform_workorders_log` VALUES ('75', '工单创建', '湖东社工委', '送审核', '', '2019-12-23 10:24:57', '0s', 'hfp', '11', 'WZ2220001201912230002');
INSERT INTO `platform_workorders_log` VALUES ('76', '后台审核', '斜塘街道', '送结案', '', '2019-12-23 10:48:05', '71h:18m:56s', 'hfp', '5', 'WZ2220001201912200001');
INSERT INTO `platform_workorders_log` VALUES ('77', '后台审核', '斜塘街道', '交办部门', '123123213', '2019-12-23 10:54:21', '68h:46m:24s', 'hfp', '9', 'WZ2220001201912200005');
INSERT INTO `platform_workorders_log` VALUES ('78', '部门处理', '斜塘街道', '部门签收', '', '2019-12-23 10:54:39', '68h:49m:5s', 'hfp', '8', 'WZ2220001201912200004');
INSERT INTO `platform_workorders_log` VALUES ('79', '部门处理', '斜塘街道', '部门答复', '其味无穷二群', '2019-12-23 10:55:06', '27s', 'hfp', '8', 'WZ2220001201912200004');
INSERT INTO `platform_workorders_log` VALUES ('80', '后台审核', '娄葑街道', '交办部门', '1321312312', '2019-12-23 13:53:17', '4h:47m:37s', 'hfp', '10', 'WZ2220001201912230001');
INSERT INTO `platform_workorders_log` VALUES ('81', '部门处理', '斜塘街道', '部门签收', '', '2019-12-23 13:54:41', '3h:0m:20s', 'hfp', '9', 'WZ2220001201912200005');
INSERT INTO `platform_workorders_log` VALUES ('82', '部门处理', '斜塘街道', '部门答复', '745132132', '2019-12-23 13:54:54', '13s', 'hfp', '9', 'WZ2220001201912200005');
INSERT INTO `platform_workorders_log` VALUES ('83', '后台审核', '斜塘街道', '送结案', '', '2019-12-23 13:55:06', '3h', 'hfp', '8', 'WZ2220001201912200004');
INSERT INTO `platform_workorders_log` VALUES ('84', '工单创建', '斜塘街道', '送审核', '', '2019-12-23 14:03:21', '0s', 'hfp', '12', 'WZ2220001201912230003');
INSERT INTO `platform_workorders_log` VALUES ('85', '工单创建', '斜塘街道', '送审核', '', '2019-12-23 14:03:35', '0s', 'hfp', '13', 'WZ2220001201912230004');
INSERT INTO `platform_workorders_log` VALUES ('86', '后台审核', '斜塘街道', '交办部门', '撒大声地撒大', '2019-12-23 14:04:03', '41s', 'hfp', '12', 'WZ2220001201912230003');
INSERT INTO `platform_workorders_log` VALUES ('87', '诉求人操作', '斜塘街道', '结案', '', '2019-12-23 14:29:45', '3h:41m:39s', 'hfp', '5', 'WZ2220001201912200001');
INSERT INTO `platform_workorders_log` VALUES ('88', '工单创建', '斜塘街道', '送审核', '', '2019-12-23 15:01:37', '0s', 'hfp', '14', 'WZ2220001201912230005');
INSERT INTO `platform_workorders_log` VALUES ('89', '诉求人操作', '斜塘街道', '送审核', '', '2019-12-23 15:02:24', '118h:45m:58s', 'hfp', '1', 'WZ2220001201912180001');
INSERT INTO `platform_workorders_log` VALUES ('90', '诉求人操作', '斜塘街道', '送审核', '', '2019-12-23 15:02:42', '118h:46m:15s', 'hfp', '1', 'WZ2220001201912180001');
INSERT INTO `platform_workorders_log` VALUES ('91', '诉求人操作', '斜塘街道', '送审核', '', '2019-12-23 15:06:17', '118h:49m:51s', 'hfp', '1', 'WZ2220001201912180001');
INSERT INTO `platform_workorders_log` VALUES ('92', '诉求人操作', '斜塘街道', '送审核', '', '2019-12-23 15:08:54', '118h:52m:27s', 'hfp', '1', 'WZ2220001201912180001');
INSERT INTO `platform_workorders_log` VALUES ('93', '后台审核', '斜塘街道', '退回修改', '11231', '2019-12-23 15:11:31', '0s', 'hfp', '1', 'WZ2220001201912180001');
INSERT INTO `platform_workorders_log` VALUES ('94', '工单创建', '斜塘街道', '送审核', '', '2019-12-25 09:38:34', '0s', 'hfp', '15', 'WZ2220001201912250001');
INSERT INTO `platform_workorders_log` VALUES ('95', '诉求人操作', '斜塘街道', '送审核', '', '2019-12-26 14:07:04', '70h:55m:32s', 'hfp', '1', 'WZ2220001201912180001');
INSERT INTO `platform_workorders_log` VALUES ('96', '后台审核', '斜塘街道', '退回修改', '123', '2019-12-26 14:07:20', '0s', 'hfp', '1', 'WZ2220001201912180001');
INSERT INTO `platform_workorders_log` VALUES ('97', '后台审核', '湖东社工委', '退回修改', '123123', '2019-12-26 14:12:13', '0s', 'hfp', '11', 'WZ2220001201912230002');
INSERT INTO `platform_workorders_log` VALUES ('98', '诉求人操作', '湖东社工委', '送审核', '', '2019-12-26 14:13:33', '1m:20s', 'hfp', '11', 'WZ2220001201912230002');
INSERT INTO `platform_workorders_log` VALUES ('99', '后台审核', '斜塘街道', '退回修改', '12323', '2019-12-26 14:13:44', '10s', 'hfp', '11', 'WZ2220001201912230002');
INSERT INTO `platform_workorders_log` VALUES ('100', '工单创建', '斜塘街道', '送审核', '', '2019-12-26 15:44:00', '0s', 'hfp', '16', 'WZ2220001201912260001');
INSERT INTO `platform_workorders_log` VALUES ('101', '工单创建', '斜塘街道', '送审核', '', '2019-12-31 10:26:24', '0s', 'hfp', '17', 'WZ2220001201912310001');
