package com.hyc.oa.common.entity;

import java.util.List;

import net.sf.jsqlparser.schema.Column;

public class TableInfo {
	
	/** 数据库名称 */
	private String tableSchema;
	/** 表名 */
	private String tableName;
	/** 表中文名 */
	private String tableComment;
	
	private String packageName;
	private String javaFilename;
	private String name;
	private List<Column> columns;
	public String getTableSchema() {
		return tableSchema;
	}
	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableComment() {
		return tableComment;
	}
	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getJavaFilename() {
		return javaFilename;
	}
	public void setJavaFilename(String javaFilename) {
		this.javaFilename = javaFilename;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Column> getColumns() {
		return columns;
	}
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	
}
