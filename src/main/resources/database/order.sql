/*
Navicat MySQL Data Transfer

Source Server         : Jeery
Source Server Version : 50613
Source Host           : localhost:3306
Source Database       : order

Target Server Type    : MYSQL
Target Server Version : 50613
File Encoding         : 65001

Date: 2020-11-12 11:15:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for apply_info
-- ----------------------------
DROP TABLE IF EXISTS `apply_info`;
CREATE TABLE `apply_info` (
  `id` varchar(64) NOT NULL COMMENT '应用号',
  `apply_name` varchar(128) NOT NULL COMMENT '应用名称',
  `apply_key` varchar(128) NOT NULL COMMENT '应用密钥',
  `create_user` varchar(128) NOT NULL COMMENT '创建用户',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应用信息表 ';

-- ----------------------------
-- Table structure for logic
-- ----------------------------
DROP TABLE IF EXISTS `logic`;
CREATE TABLE `logic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '逻辑id',
  `apply_id` varchar(64) NOT NULL COMMENT '应用号',
  `data` varchar(1024) NOT NULL COMMENT '逻辑数据',
  `create_user` varchar(128) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `name` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='工单逻辑表 ';

-- ----------------------------
-- Table structure for model
-- ----------------------------
DROP TABLE IF EXISTS `model`;
CREATE TABLE `model` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '模板id',
  `apply_id` varchar(64) NOT NULL COMMENT '应用id',
  `data` varchar(1024) NOT NULL COMMENT '模板数据',
  `create_user` varchar(128) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `name` varchar(64) NOT NULL COMMENT '模板名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='工单模板表 ';

-- ----------------------------
-- Table structure for order_img
-- ----------------------------
DROP TABLE IF EXISTS `order_img`;
CREATE TABLE `order_img` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `work_id` bigint(20) NOT NULL COMMENT '流程id',
  `img` varchar(128) NOT NULL COMMENT '图片地址',
  `thumbnail` varchar(128) NOT NULL COMMENT '缩略图地址',
  `upload_time` datetime NOT NULL COMMENT '上传时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='订单图片表 ';

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `id` varchar(64) NOT NULL COMMENT '工单号',
  `apply_id` varchar(64) NOT NULL COMMENT '应用号',
  `order_style` varchar(32) NOT NULL COMMENT '工单种类',
  `order_type` varchar(32) DEFAULT NULL COMMENT '工单类型',
  `task_name` varchar(64) NOT NULL COMMENT '工单模板id',
  `order_content` varchar(4000) NOT NULL COMMENT '工单内容 保存json字符串',
  `order_state` int(11) NOT NULL COMMENT '工单状态 0:初始状态；1:已经指 派（处理中）；2:已处理',
  `deal_time` datetime NOT NULL COMMENT '预处理日期 规定工单结束时间',
  `order_logic` varchar(4000) DEFAULT NULL COMMENT '工单逻辑 保存逻辑',
  `now_deal_user` varchar(32) DEFAULT NULL COMMENT '当前处理人',
  `end_time` datetime DEFAULT NULL COMMENT '工单结束时间',
  `create_user` varchar(32) NOT NULL COMMENT '创建工单用户 创建工单用户（可为空， 如烟感报警）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工单信息表 保存工单的信息';

-- ----------------------------
-- Table structure for order_task
-- ----------------------------
DROP TABLE IF EXISTS `order_task`;
CREATE TABLE `order_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '配置 ID(自增)',
  `apply_id` varchar(64) NOT NULL COMMENT '应用号',
  `name` varchar(64) NOT NULL COMMENT '流程名 应用号和工单种类相同的情况下流程名不能重复',
  `order_style` varchar(32) NOT NULL COMMENT '工单种类',
  `order_type` varchar(32) DEFAULT NULL COMMENT '工单类型 0:自动派发；1:人工指派；2:自动抢单',
  `logic_id` bigint(20) NOT NULL COMMENT '工单逻辑id 工单逻辑(人工指派逻辑指定工单派发，自动指派指定所有逻辑)',
  `model_id` bigint(20) NOT NULL COMMENT '工单模板id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='工单配置表 ';

-- ----------------------------
-- Table structure for order_user
-- ----------------------------
DROP TABLE IF EXISTS `order_user`;
CREATE TABLE `order_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `account` varchar(32) NOT NULL COMMENT '用户名',
  `pwd` varchar(32) NOT NULL COMMENT '密码',
  `type` int(11) NOT NULL COMMENT '用户权限。0:超级管理员；1:公司账号',
  `phone` varchar(32) NOT NULL COMMENT '联系电话',
  `company` varchar(128) DEFAULT NULL COMMENT '公司 公司名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='工单用户表 ';

-- ----------------------------
-- Table structure for order_work
-- ----------------------------
DROP TABLE IF EXISTS `order_work`;
CREATE TABLE `order_work` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_id` varchar(64) NOT NULL COMMENT '工单号',
  `node_id` int(11) NOT NULL COMMENT '节点顺序',
  `node_type` varchar(255) NOT NULL,
  `work_data` varchar(4000) DEFAULT NULL COMMENT '流程内容',
  `node_state` int(11) NOT NULL COMMENT '节点状态 0:待完成；1:已完成;2:回退',
  `deal_user` varchar(32) NOT NULL COMMENT '流程处理人',
  `order_logic` varchar(4000) DEFAULT NULL COMMENT '工单逻辑',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8 COMMENT='工单流程表 ';

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apply_id` varchar(64) NOT NULL COMMENT '应用号',
  `account` varchar(64) NOT NULL COMMENT '与接入系统相匹配的账号',
  `phone` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `department` varchar(32) DEFAULT NULL COMMENT '部门',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
