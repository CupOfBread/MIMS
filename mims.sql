/*
 Navicat Premium Data Transfer

 Source Server         : LOCALHOST
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : mims

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 06/06/2021 00:01:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mims_express
-- ----------------------------
DROP TABLE IF EXISTS `mims_express`;
CREATE TABLE `mims_express`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '快递名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '快递表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mims_express
-- ----------------------------

-- ----------------------------
-- Table structure for mims_finance_bank
-- ----------------------------
DROP TABLE IF EXISTS `mims_finance_bank`;
CREATE TABLE `mims_finance_bank`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `money` double(11, 2) NULL DEFAULT NULL COMMENT '金额',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '银行表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mims_finance_bank
-- ----------------------------
INSERT INTO `mims_finance_bank` VALUES (1, '1111', 12111.00, '11111');

-- ----------------------------
-- Table structure for mims_finance_category
-- ----------------------------
DROP TABLE IF EXISTS `mims_finance_category`;
CREATE TABLE `mims_finance_category`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '财务分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mims_finance_category
-- ----------------------------
INSERT INTO `mims_finance_category` VALUES (1, '收入');
INSERT INTO `mims_finance_category` VALUES (2, '支出');
INSERT INTO `mims_finance_category` VALUES (3, 'xxxx');

-- ----------------------------
-- Table structure for mims_finance_record
-- ----------------------------
DROP TABLE IF EXISTS `mims_finance_record`;
CREATE TABLE `mims_finance_record`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `u_id` bigint NULL DEFAULT NULL COMMENT '创建用户',
  `bank_id` bigint NULL DEFAULT NULL COMMENT '银行',
  `c_id` bigint NULL DEFAULT NULL COMMENT '分类',
  `status` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '1' COMMENT '状态',
  `type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '收入支出类型',
  `money` double(11, 2) NULL DEFAULT NULL COMMENT '金额',
  `date_time` datetime(0) NULL DEFAULT NULL COMMENT '日期时间',
  `attn_id` bigint NULL DEFAULT NULL COMMENT '经办人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `remark` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `u_id`(`u_id`) USING BTREE,
  INDEX `bank_id`(`bank_id`) USING BTREE,
  INDEX `c_id`(`c_id`) USING BTREE,
  INDEX `attn_id`(`attn_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '财务记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mims_finance_record
-- ----------------------------

-- ----------------------------
-- Table structure for mims_inventory
-- ----------------------------
DROP TABLE IF EXISTS `mims_inventory`;
CREATE TABLE `mims_inventory`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `p_id` bigint NULL DEFAULT NULL COMMENT '产品id',
  `w_id` bigint NULL DEFAULT NULL COMMENT '仓库id',
  `quantity` bigint NULL DEFAULT NULL COMMENT '库存数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '库存表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mims_inventory
-- ----------------------------
INSERT INTO `mims_inventory` VALUES (1, 1, 1, 20);

-- ----------------------------
-- Table structure for mims_process_record
-- ----------------------------
DROP TABLE IF EXISTS `mims_process_record`;
CREATE TABLE `mims_process_record`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `serial_number` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '编号',
  `p_id` bigint NULL DEFAULT NULL COMMENT '产品id',
  `quantity` bigint NULL DEFAULT NULL COMMENT '数量',
  `produce_time` datetime(0) NULL DEFAULT NULL COMMENT '加工日期',
  `w_id` bigint NULL DEFAULT NULL COMMENT '仓库id',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `u_id` bigint NULL DEFAULT NULL COMMENT '创建用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mims_process_record
-- ----------------------------

-- ----------------------------
-- Table structure for mims_product
-- ----------------------------
DROP TABLE IF EXISTS `mims_product`;
CREATE TABLE `mims_product`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `img` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '产品图片',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '产品名称',
  `item_number` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '产品货号',
  `specifications` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '产品规格',
  `bar_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '条形码',
  `unit` bigint UNSIGNED NULL DEFAULT NULL COMMENT '计量单位',
  `sales` decimal(10, 0) NULL DEFAULT NULL COMMENT '销售价',
  `purchase` decimal(10, 0) NULL DEFAULT NULL COMMENT '进货价',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `cid` bigint UNSIGNED NULL DEFAULT NULL COMMENT '产品分类id',
  `uid` bigint UNSIGNED NULL DEFAULT NULL COMMENT '创建员工',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '产品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mims_product
-- ----------------------------
INSERT INTO `mims_product` VALUES (1, NULL, '贴心小夜灯', NULL, NULL, 'A90150001', NULL, NULL, NULL, NULL, 2, NULL);
INSERT INTO `mims_product` VALUES (2, NULL, '座圈感应', NULL, NULL, 'A90150002', NULL, NULL, NULL, NULL, 3, NULL);
INSERT INTO `mims_product` VALUES (3, NULL, '安全插头', NULL, NULL, 'A90150003', NULL, NULL, NULL, NULL, 2, NULL);
INSERT INTO `mims_product` VALUES (4, NULL, '盖板', NULL, NULL, 'A90150004', NULL, NULL, NULL, NULL, 3, NULL);
INSERT INTO `mims_product` VALUES (5, NULL, '热模块', NULL, NULL, 'A90150005', NULL, NULL, NULL, NULL, 2, NULL);
INSERT INTO `mims_product` VALUES (6, NULL, '分水阀', NULL, NULL, 'A90150006', NULL, NULL, NULL, NULL, 1, NULL);
INSERT INTO `mims_product` VALUES (7, NULL, '减压进水阀', NULL, NULL, 'A90150007', NULL, NULL, NULL, NULL, 1, NULL);
INSERT INTO `mims_product` VALUES (8, NULL, '移动洗净组件', NULL, NULL, 'A90150008', NULL, NULL, NULL, NULL, 1, NULL);
INSERT INTO `mims_product` VALUES (9, NULL, '安防监控电机', NULL, NULL, 'A90150009', NULL, NULL, NULL, NULL, 2, NULL);
INSERT INTO `mims_product` VALUES (10, NULL, '水流分配器', NULL, NULL, 'A90150010', NULL, NULL, NULL, NULL, 1, NULL);

-- ----------------------------
-- Table structure for mims_product_category
-- ----------------------------
DROP TABLE IF EXISTS `mims_product_category`;
CREATE TABLE `mims_product_category`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分类名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '产品分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mims_product_category
-- ----------------------------
INSERT INTO `mims_product_category` VALUES (1, '水箱装置');
INSERT INTO `mims_product_category` VALUES (2, '安全装置');
INSERT INTO `mims_product_category` VALUES (3, '外部配件装置');

-- ----------------------------
-- Table structure for mims_product_in_record
-- ----------------------------
DROP TABLE IF EXISTS `mims_product_in_record`;
CREATE TABLE `mims_product_in_record`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `p_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '产品',
  `s_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '供应商',
  `u_id` bigint NULL DEFAULT NULL COMMENT '创建用户',
  `w_id` bigint NULL DEFAULT NULL COMMENT '仓库id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `serial_number` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '序列号',
  `product_data` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '产品数据',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `amount` double(22, 0) NULL DEFAULT NULL COMMENT '金额',
  `quantity` bigint UNSIGNED NULL DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id`(`id`) USING BTREE,
  INDEX `p_id`(`p_id`) USING BTREE,
  CONSTRAINT `mims_product_in_record_ibfk_1` FOREIGN KEY (`p_id`) REFERENCES `mims_product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '入库记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mims_product_in_record
-- ----------------------------
INSERT INTO `mims_product_in_record` VALUES (2, 1, 1, 1, 1, '2021-02-01 12:19:50', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for mims_product_out_record
-- ----------------------------
DROP TABLE IF EXISTS `mims_product_out_record`;
CREATE TABLE `mims_product_out_record`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `serial_number` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '序列号',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态',
  `p_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '产品id',
  `u_id` bigint NULL DEFAULT NULL COMMENT '创建用户',
  `w_id` bigint NULL DEFAULT NULL COMMENT '仓库id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `ship_time` datetime(0) NULL DEFAULT NULL COMMENT '发货日期',
  `discounts` decimal(4, 2) NULL DEFAULT NULL COMMENT '折扣',
  `quantity` bigint UNSIGNED NULL DEFAULT NULL COMMENT '数量',
  `amount` double(22, 2) NULL DEFAULT NULL COMMENT '金额',
  `cost` double(22, 2) NULL DEFAULT NULL COMMENT '销售成本',
  `product_data` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '产品数据',
  `express_id` bigint NULL DEFAULT NULL COMMENT '快递',
  `express_num` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '快递单号',
  `express_addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `p_id`(`p_id`) USING BTREE,
  CONSTRAINT `mims_product_out_record_ibfk_1` FOREIGN KEY (`p_id`) REFERENCES `mims_product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '出库记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mims_product_out_record
-- ----------------------------

-- ----------------------------
-- Table structure for mims_product_scrapped
-- ----------------------------
DROP TABLE IF EXISTS `mims_product_scrapped`;
CREATE TABLE `mims_product_scrapped`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `u_id` bigint NOT NULL COMMENT '操作用户',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `p_id` bigint NULL DEFAULT NULL COMMENT '产品',
  `w_id` bigint NULL DEFAULT NULL COMMENT '仓库',
  `quantity` bigint NULL DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '产品报废表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mims_product_scrapped
-- ----------------------------

-- ----------------------------
-- Table structure for mims_supplier
-- ----------------------------
DROP TABLE IF EXISTS `mims_supplier`;
CREATE TABLE `mims_supplier`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `linkman` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系人',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机',
  `pc` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮编',
  `fax` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '传真',
  `tel` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'EMAIL',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `website` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '网址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `u_id` bigint NULL DEFAULT NULL COMMENT '更新人id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '供应商表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mims_supplier
-- ----------------------------
INSERT INTO `mims_supplier` VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2021-06-24 14:40:27', '2021-06-30 14:40:31', NULL);

-- ----------------------------
-- Table structure for mims_unit
-- ----------------------------
DROP TABLE IF EXISTS `mims_unit`;
CREATE TABLE `mims_unit`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '计量单位名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '计量单位表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mims_unit
-- ----------------------------
INSERT INTO `mims_unit` VALUES (1, '个');
INSERT INTO `mims_unit` VALUES (2, '瓶');
INSERT INTO `mims_unit` VALUES (3, '箱');
INSERT INTO `mims_unit` VALUES (4, '片');
INSERT INTO `mims_unit` VALUES (5, '盒');
INSERT INTO `mims_unit` VALUES (6, '张');
INSERT INTO `mims_unit` VALUES (7, '斤');
INSERT INTO `mims_unit` VALUES (8, '台');

-- ----------------------------
-- Table structure for mims_user
-- ----------------------------
DROP TABLE IF EXISTS `mims_user`;
CREATE TABLE `mims_user`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_group` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `name` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `phone` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mims_user
-- ----------------------------
INSERT INTO `mims_user` VALUES (1, NULL, '123', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for mims_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `mims_warehouse`;
CREATE TABLE `mims_warehouse`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '仓库名称',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `person_in_charge` bigint UNSIGNED NULL DEFAULT NULL COMMENT '负责人',
  `stock` bigint(20) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '库存',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '仓库表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mims_warehouse
-- ----------------------------
INSERT INTO `mims_warehouse` VALUES (1, '莫高仓库', '敦煌市', NULL, 00000000000000000000);
INSERT INTO `mims_warehouse` VALUES (2, '云冈仓库', '大同市', NULL, 00000000000000000000);
INSERT INTO `mims_warehouse` VALUES (3, '龙门仓库', '洛阳市', NULL, 00000000000000000000);
INSERT INTO `mims_warehouse` VALUES (4, '麦积仓库', '天水市', NULL, 00000000000000000000);
INSERT INTO `mims_warehouse` VALUES (5, '欧亚达建材市场', '武汉市', NULL, 00000000000000000000);

-- ----------------------------
-- Table structure for mims_warehouse_transfer
-- ----------------------------
DROP TABLE IF EXISTS `mims_warehouse_transfer`;
CREATE TABLE `mims_warehouse_transfer`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `u_id` bigint NULL DEFAULT NULL COMMENT '操作用户',
  `in_id` bigint NULL DEFAULT NULL COMMENT '拔入仓库',
  `out_id` bigint NULL DEFAULT NULL COMMENT '排出仓库',
  `p_id` bigint NULL DEFAULT NULL COMMENT '产品',
  `quantity` bigint NULL DEFAULT NULL COMMENT '数量',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '货物调度表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mims_warehouse_transfer
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
