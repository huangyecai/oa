package com.hyc.oa.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyc.oa.common.entity.ColumnInfo;
import com.hyc.oa.common.entity.TableInfo;

@Mapper
public interface DataBaseDao {

	List<TableInfo> tableList(TableInfo tableInfo );
	List<ColumnInfo> columnList(String tableSchema ,String tableName);
}
