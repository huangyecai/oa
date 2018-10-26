DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` varchar(64) NOT NULL COMMENT '主键id（uuid）',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `description` varchar(255) DEFAULT NULL COMMENT '说明',
  `code` varchar(64) DEFAULT NULL COMMENT '编码',
  `parent_id` varchar(64) DEFAULT NULL COMMENT '父id',
  `parent_ids` varchar(255) DEFAULT NULL COMMENT '父部门ids（逗号隔开）',
  `status` int(11) DEFAULT NULL COMMENT '状态（1有效、3无效）',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) NOT NULL COMMENT '更新者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;