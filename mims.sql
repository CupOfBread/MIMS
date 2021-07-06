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

 Date: 06/07/2021 13:35:17
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
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '快递表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mims_express
-- ----------------------------
INSERT INTO `mims_express` VALUES (1, '圆通速递');
INSERT INTO `mims_express` VALUES (2, '顺丰速运');
INSERT INTO `mims_express` VALUES (3, '中通快递');
INSERT INTO `mims_express` VALUES (4, '申通快递');
INSERT INTO `mims_express` VALUES (5, 'EMS');
INSERT INTO `mims_express` VALUES (6, '京东快递');
INSERT INTO `mims_express` VALUES (7, '韵达快递');
INSERT INTO `mims_express` VALUES (8, '天猫快递');

-- ----------------------------
-- Table structure for mims_finance_bank
-- ----------------------------
DROP TABLE IF EXISTS `mims_finance_bank`;
CREATE TABLE `mims_finance_bank`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `money` double(11, 2) NULL DEFAULT NULL COMMENT '金额',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '银行表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mims_finance_bank
-- ----------------------------
INSERT INTO `mims_finance_bank` VALUES (1, '中国工商银行', 121110.00, '员工工资');
INSERT INTO `mims_finance_bank` VALUES (2, '中国建设银行', 315790.26, '耗材资金');
INSERT INTO `mims_finance_bank` VALUES (3, '招商银行', 56366.00, '待结转');
INSERT INTO `mims_finance_bank` VALUES (4, '中国农业银行', 3412223.75, '备用');

-- ----------------------------
-- Table structure for mims_finance_category
-- ----------------------------
DROP TABLE IF EXISTS `mims_finance_category`;
CREATE TABLE `mims_finance_category`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '财务分类表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mims_finance_category
-- ----------------------------
INSERT INTO `mims_finance_category` VALUES (1, '收入');
INSERT INTO `mims_finance_category` VALUES (2, '支出');

-- ----------------------------
-- Table structure for mims_finance_record
-- ----------------------------
DROP TABLE IF EXISTS `mims_finance_record`;
CREATE TABLE `mims_finance_record`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `u_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '创建用户',
  `bank_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '银行',
  `c_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '分类',
  `status` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '1' COMMENT '状态',
  `type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '收入支出类型',
  `money` double(11, 2) NULL DEFAULT NULL COMMENT '金额',
  `date_time` datetime(0) NULL DEFAULT NULL COMMENT '日期时间',
  `attn_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '经办人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `remark` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `u_id`(`u_id`) USING BTREE,
  INDEX `bank_id`(`bank_id`) USING BTREE,
  INDEX `c_id`(`c_id`) USING BTREE,
  INDEX `attn_id`(`attn_id`) USING BTREE,
  CONSTRAINT `mims_finance_record_ibfk_1` FOREIGN KEY (`c_id`) REFERENCES `mims_finance_category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `mims_finance_record_ibfk_2` FOREIGN KEY (`bank_id`) REFERENCES `mims_finance_bank` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `mims_finance_record_ibfk_3` FOREIGN KEY (`u_id`) REFERENCES `mims_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '财务记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mims_finance_record
-- ----------------------------
INSERT INTO `mims_finance_record` VALUES (1, 1, 4, 1, '1', '经营', 200100.00, '2021-06-02 10:57:59', 1, '2021-06-02 10:58:27', '1');
INSERT INTO `mims_finance_record` VALUES (2, 1, 3, 1, '1', '待结转', 23334.00, '2021-06-07 10:59:21', 2, '2021-06-07 10:59:29', '1');
INSERT INTO `mims_finance_record` VALUES (3, 1, 1, 2, '1', '转入工资', 45000.00, '2021-06-03 11:00:13', 1, '2021-06-04 11:00:23', '2');

-- ----------------------------
-- Table structure for mims_inventory
-- ----------------------------
DROP TABLE IF EXISTS `mims_inventory`;
CREATE TABLE `mims_inventory`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `p_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '产品id',
  `w_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '仓库id',
  `quantity` bigint NULL DEFAULT NULL COMMENT '库存数量',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `p_id`(`p_id`) USING BTREE,
  INDEX `w_id`(`w_id`) USING BTREE,
  CONSTRAINT `mims_inventory_ibfk_1` FOREIGN KEY (`p_id`) REFERENCES `mims_product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `mims_inventory_ibfk_2` FOREIGN KEY (`w_id`) REFERENCES `mims_warehouse` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '库存表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mims_inventory
-- ----------------------------
INSERT INTO `mims_inventory` VALUES (1, 1, 1, 200);
INSERT INTO `mims_inventory` VALUES (2, 1, 5, 175);
INSERT INTO `mims_inventory` VALUES (3, 2, 2, 301);
INSERT INTO `mims_inventory` VALUES (4, 2, 3, 30);
INSERT INTO `mims_inventory` VALUES (5, 3, 1, 196);
INSERT INTO `mims_inventory` VALUES (6, 3, 5, 200);
INSERT INTO `mims_inventory` VALUES (7, 3, 4, 102);
INSERT INTO `mims_inventory` VALUES (8, 4, 3, 187);
INSERT INTO `mims_inventory` VALUES (9, 5, 1, 68);
INSERT INTO `mims_inventory` VALUES (10, 5, 2, 73);
INSERT INTO `mims_inventory` VALUES (11, 5, 3, 93);
INSERT INTO `mims_inventory` VALUES (12, 5, 4, 124);
INSERT INTO `mims_inventory` VALUES (13, 6, 1, 50);
INSERT INTO `mims_inventory` VALUES (14, 6, 2, 55);
INSERT INTO `mims_inventory` VALUES (15, 6, 3, 124);
INSERT INTO `mims_inventory` VALUES (16, 6, 5, 101);
INSERT INTO `mims_inventory` VALUES (17, 7, 1, 67);
INSERT INTO `mims_inventory` VALUES (18, 7, 5, 98);
INSERT INTO `mims_inventory` VALUES (19, 8, 5, 133);
INSERT INTO `mims_inventory` VALUES (20, 9, 2, 205);
INSERT INTO `mims_inventory` VALUES (21, 9, 3, 130);
INSERT INTO `mims_inventory` VALUES (22, 10, 4, 255);

-- ----------------------------
-- Table structure for mims_process_record
-- ----------------------------
DROP TABLE IF EXISTS `mims_process_record`;
CREATE TABLE `mims_process_record`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `serial_number` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '编号',
  `p_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '产品id',
  `quantity` bigint NULL DEFAULT NULL COMMENT '数量',
  `produce_time` datetime(0) NULL DEFAULT NULL COMMENT '加工日期',
  `w_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '仓库id',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `u_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '创建用户',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `p_id`(`p_id`) USING BTREE,
  INDEX `w_id`(`w_id`) USING BTREE,
  INDEX `u_id`(`u_id`) USING BTREE,
  CONSTRAINT `mims_process_record_ibfk_1` FOREIGN KEY (`p_id`) REFERENCES `mims_product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `mims_process_record_ibfk_2` FOREIGN KEY (`w_id`) REFERENCES `mims_warehouse` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `mims_process_record_ibfk_3` FOREIGN KEY (`u_id`) REFERENCES `mims_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '生产记录表' ROW_FORMAT = DYNAMIC;

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
  `c_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '产品分类id',
  `u_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '创建员工',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `c_id`(`c_id`) USING BTREE,
  INDEX `u_id`(`u_id`) USING BTREE,
  INDEX `unit`(`unit`) USING BTREE,
  CONSTRAINT `mims_product_ibfk_1` FOREIGN KEY (`c_id`) REFERENCES `mims_product_category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `mims_product_ibfk_2` FOREIGN KEY (`u_id`) REFERENCES `mims_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `mims_product_ibfk_3` FOREIGN KEY (`unit`) REFERENCES `mims_unit` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '产品表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mims_product
-- ----------------------------
INSERT INTO `mims_product` VALUES (1, NULL, '贴心小夜灯', '1204560468', 'LED', 'A90150001', 1, 99, 50, '1', 2, 1);
INSERT INTO `mims_product` VALUES (2, NULL, '座圈感应', '408450405', '一般', 'A90150002', 1, 34, 20, '1', 3, 1);
INSERT INTO `mims_product` VALUES (3, NULL, '安全插头', '405450558', '国标', 'A90150003', 1, 69, 40, '1', 2, 1);
INSERT INTO `mims_product` VALUES (4, NULL, '盖板', '405686860', '塑料', 'A90150004', 4, 20, 20, '1', 3, 1);
INSERT INTO `mims_product` VALUES (5, NULL, '热模块', '238580808641', '一般', 'A90150005', 1, 63, 40, '1', 2, 1);
INSERT INTO `mims_product` VALUES (6, NULL, '分水阀', '68477531018', '合金', 'A90150006', 1, 37, 25, '1', 1, 1);
INSERT INTO `mims_product` VALUES (7, NULL, '减压进水阀', '761242511', '合金', 'A90150007', 1, 43, 50, '1', 1, 1);
INSERT INTO `mims_product` VALUES (8, NULL, '移动洗净组件', '705205343', '一般', 'A90150008', 1, 29, 25, '1', 1, 1);
INSERT INTO `mims_product` VALUES (9, NULL, '安防监控电机', '69120646346', '无刷', 'A90150009', 8, 183, 150, '1', 2, 1);
INSERT INTO `mims_product` VALUES (10, NULL, '水流分配器', '248786081400', '一般', 'A90150010', 1, 7, 10, '1', 1, NULL);

-- ----------------------------
-- Table structure for mims_product_category
-- ----------------------------
DROP TABLE IF EXISTS `mims_product_category`;
CREATE TABLE `mims_product_category`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分类名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '产品分类表' ROW_FORMAT = DYNAMIC;

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
  `u_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '创建用户',
  `w_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '仓库id',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `serial_number` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '序列号',
  `product_data` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '产品数据',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `amount` double(22, 0) NULL DEFAULT NULL COMMENT '金额',
  `quantity` bigint UNSIGNED NULL DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id`(`id`) USING BTREE,
  INDEX `p_id`(`p_id`) USING BTREE,
  INDEX `s_id`(`s_id`) USING BTREE,
  INDEX `u_id`(`u_id`) USING BTREE,
  INDEX `w_id`(`w_id`) USING BTREE,
  CONSTRAINT `mims_product_in_record_ibfk_1` FOREIGN KEY (`p_id`) REFERENCES `mims_product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `mims_product_in_record_ibfk_2` FOREIGN KEY (`s_id`) REFERENCES `mims_supplier` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `mims_product_in_record_ibfk_3` FOREIGN KEY (`u_id`) REFERENCES `mims_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `mims_product_in_record_ibfk_4` FOREIGN KEY (`w_id`) REFERENCES `mims_warehouse` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '入库记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mims_product_in_record
-- ----------------------------
INSERT INTO `mims_product_in_record` VALUES (2, 9, 1, 1, 2, '2021-06-07 19:59:01', '1', '12', '1', 90, 15);
INSERT INTO `mims_product_in_record` VALUES (9, 1, 3, 2, 1, '2021-06-07 00:05:19', '123', '3123', '123', 120, 50);
INSERT INTO `mims_product_in_record` VALUES (10, 6, 2, 3, 1, '2021-06-07 20:00:07', '1', '1', '1', 20, 20);
INSERT INTO `mims_product_in_record` VALUES (11, 7, 2, 1, 4, '2021-06-07 20:00:49', '1', '1', '1', 25, 10);

-- ----------------------------
-- Table structure for mims_product_out_record
-- ----------------------------
DROP TABLE IF EXISTS `mims_product_out_record`;
CREATE TABLE `mims_product_out_record`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `serial_number` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '序列号',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态',
  `p_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '产品id',
  `u_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '创建用户',
  `w_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '仓库id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `ship_time` datetime(0) NULL DEFAULT NULL COMMENT '发货日期',
  `discounts` decimal(4, 2) NULL DEFAULT NULL COMMENT '折扣',
  `quantity` bigint UNSIGNED NULL DEFAULT NULL COMMENT '数量',
  `amount` double(22, 2) NULL DEFAULT NULL COMMENT '金额',
  `cost` double(22, 2) NULL DEFAULT NULL COMMENT '销售成本',
  `product_data` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '产品数据',
  `express_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '快递',
  `express_num` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '快递单号',
  `express_addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `p_id`(`p_id`) USING BTREE,
  INDEX `express_id`(`express_id`) USING BTREE,
  INDEX `u_id`(`u_id`) USING BTREE,
  INDEX `w_id`(`w_id`) USING BTREE,
  CONSTRAINT `mims_product_out_record_ibfk_1` FOREIGN KEY (`p_id`) REFERENCES `mims_product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `mims_product_out_record_ibfk_2` FOREIGN KEY (`express_id`) REFERENCES `mims_express` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `mims_product_out_record_ibfk_3` FOREIGN KEY (`u_id`) REFERENCES `mims_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `mims_product_out_record_ibfk_4` FOREIGN KEY (`w_id`) REFERENCES `mims_warehouse` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '出库记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mims_product_out_record
-- ----------------------------
INSERT INTO `mims_product_out_record` VALUES (1, '123', 1, 1, 1, 2, '2021-06-07 20:10:20', '2021-06-08 20:10:23', 0.00, 30, 1.00, 0.00, '0', 1, '022122420055', '敦煌市', NULL);
INSERT INTO `mims_product_out_record` VALUES (2, '1', 1, 2, 2, 3, '2021-06-07 20:11:37', '2021-06-08 20:11:39', 0.00, 10, 1.00, 0.00, '0', 5, '010224552452', '武汉市', NULL);
INSERT INTO `mims_product_out_record` VALUES (3, '2', 1, 5, 3, 2, '2021-06-07 20:12:47', '2021-06-08 20:12:51', 0.00, 20, 1.00, 0.00, '0', 3, '452200173210', '武汉市', NULL);
INSERT INTO `mims_product_out_record` VALUES (4, '12', 1, 8, 1, 1, '2021-06-07 20:13:41', '2021-06-08 20:13:43', 0.00, 10, 1.00, 0.00, '0', 2, '710866103431', '天水市', NULL);

-- ----------------------------
-- Table structure for mims_product_scrapped
-- ----------------------------
DROP TABLE IF EXISTS `mims_product_scrapped`;
CREATE TABLE `mims_product_scrapped`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `u_id` bigint UNSIGNED NOT NULL COMMENT '操作用户',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `p_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '产品',
  `w_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '仓库',
  `quantity` bigint NULL DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `u_id`(`u_id`) USING BTREE,
  INDEX `p_id`(`p_id`) USING BTREE,
  INDEX `w_id`(`w_id`) USING BTREE,
  CONSTRAINT `mims_product_scrapped_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `mims_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `mims_product_scrapped_ibfk_2` FOREIGN KEY (`p_id`) REFERENCES `mims_product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `mims_product_scrapped_ibfk_3` FOREIGN KEY (`w_id`) REFERENCES `mims_warehouse` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '产品报废表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mims_product_scrapped
-- ----------------------------
INSERT INTO `mims_product_scrapped` VALUES (1, 1, '2021-06-07 20:07:37', 'x', 1, 1, 5);
INSERT INTO `mims_product_scrapped` VALUES (2, 1, '2021-06-07 20:07:58', 'x', 2, 3, 6);
INSERT INTO `mims_product_scrapped` VALUES (3, 1, '2021-06-17 20:08:12', 'x', 8, 4, 2);
INSERT INTO `mims_product_scrapped` VALUES (4, 1, '2021-06-07 20:08:30', 'x', 4, 2, 5);
INSERT INTO `mims_product_scrapped` VALUES (5, 1, '2021-06-07 20:08:48', 'x', 7, 5, 3);

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '供应商表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mims_supplier
-- ----------------------------
INSERT INTO `mims_supplier` VALUES (1, '德昌电机集团', '南京分销部', '+86 25 5742 2017', '1', '+852 2897 2054', '+852 2663 6688', 'johnsone@jse.cn', '香港新界沙田香港科技园科技大道东12号6楼', 'www.johnsonelectric.com', '2021-06-24 14:40:27', '2021-06-30 14:40:31', 1);
INSERT INTO `mims_supplier` VALUES (2, '沧州宏丰精密铸造有限公司\n', '刘经理', '15128783303', '1', '0', '0', 'hongfeng@valvecn.net', '河北省沧州娘娘庙工业园', 'www.valvecn.net', '2021-06-07 11:27:40', '2021-06-07 11:27:45', 1);
INSERT INTO `mims_supplier` VALUES (3, '广东兴凯照明有限公司', '廖小姐', '13802663722', '1', '0', '400-1003-400', '2880527960@qq.com', '中山市横栏镇茂辉工业区庆安路10号', 'www.thinkerlamp.com', '2021-06-02 19:58:45', '2021-06-07 19:58:48', 1);
INSERT INTO `mims_supplier` VALUES (4, '广西佳德科技有限公司\r\n', '户文亭', '18376904286', '1', '0', '0', '1067968318@qq.com', '隆安县隆安华侨管理区富侨大道旁壹处', '0', '2021-06-07 20:04:32', '2021-06-07 20:04:36', 1);

-- ----------------------------
-- Table structure for mims_unit
-- ----------------------------
DROP TABLE IF EXISTS `mims_unit`;
CREATE TABLE `mims_unit`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '计量单位名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '计量单位表' ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mims_user
-- ----------------------------
INSERT INTO `mims_user` VALUES (1, '管理', '王思怡', '12345678', 'liang@ff.com', '1', '2021-05-31 19:49:51', '2021-06-06 19:50:03', '146146146');
INSERT INTO `mims_user` VALUES (2, '管理', '叶超炜', '12345678', 'liang@ff.com', '1', '2021-05-31 19:49:51', '2021-05-31 19:49:51', '123123');
INSERT INTO `mims_user` VALUES (3, '管理', '汤臣斌', '12345678', 'liang@ff.com', '1', '2021-05-31 19:49:51', '2021-05-31 19:49:51', '123123');

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
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '仓库表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mims_warehouse
-- ----------------------------
INSERT INTO `mims_warehouse` VALUES (1, '莫高仓库', '敦煌市', 1, 00000000000000000581);
INSERT INTO `mims_warehouse` VALUES (2, '云冈仓库', '大同市', 1, 00000000000000000634);
INSERT INTO `mims_warehouse` VALUES (3, '龙门仓库', '洛阳市', 1, 00000000000000000564);
INSERT INTO `mims_warehouse` VALUES (4, '麦积仓库', '天水市', 1, 00000000000000000481);
INSERT INTO `mims_warehouse` VALUES (5, '欧亚达建材市场', '武汉市', 1, 00000000000000000707);

-- ----------------------------
-- Table structure for mims_warehouse_transfer
-- ----------------------------
DROP TABLE IF EXISTS `mims_warehouse_transfer`;
CREATE TABLE `mims_warehouse_transfer`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `u_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '操作用户',
  `in_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '拔入仓库',
  `out_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '排出仓库',
  `p_id` bigint UNSIGNED NULL DEFAULT NULL COMMENT '产品',
  `quantity` bigint NULL DEFAULT NULL COMMENT '数量',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `u_id`(`u_id`) USING BTREE,
  INDEX `p_id`(`p_id`) USING BTREE,
  INDEX `in_id`(`in_id`) USING BTREE,
  INDEX `out_id`(`out_id`) USING BTREE,
  CONSTRAINT `mims_warehouse_transfer_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `mims_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `mims_warehouse_transfer_ibfk_2` FOREIGN KEY (`p_id`) REFERENCES `mims_product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `mims_warehouse_transfer_ibfk_3` FOREIGN KEY (`in_id`) REFERENCES `mims_warehouse` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `mims_warehouse_transfer_ibfk_4` FOREIGN KEY (`out_id`) REFERENCES `mims_warehouse` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '货物调度表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mims_warehouse_transfer
-- ----------------------------
INSERT INTO `mims_warehouse_transfer` VALUES (1, 1, 1, 2, 1, 30, '2021-06-07 20:06:12', '1');
INSERT INTO `mims_warehouse_transfer` VALUES (2, 2, 5, 3, 2, 10, '2021-06-07 20:06:36', '5');
INSERT INTO `mims_warehouse_transfer` VALUES (3, 3, 5, 2, 5, 20, '2021-06-07 20:06:58', '5');
INSERT INTO `mims_warehouse_transfer` VALUES (4, 1, 4, 1, 8, 10, '2021-06-07 20:07:16', '4');

SET FOREIGN_KEY_CHECKS = 1;
