DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(64) NOT NULL COMMENT '主键id（uuid）',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `description` varchar(255) DEFAULT NULL COMMENT '说明',
  `code` varchar(64) DEFAULT NULL COMMENT '编码',
  `level` int(11) DEFAULT NULL COMMENT '等级',
  `type` varchar(255) DEFAULT NULL COMMENT '类型',
  `sort_num` int(11) DEFAULT NULL COMMENT '拍讯',
  `status` int(11) DEFAULT NULL COMMENT '状态（1有效、3无效）',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;