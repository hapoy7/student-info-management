package com.ldr.manage.sys.tableref;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ldr.common.base.annotation.Id;
import com.ldr.common.base.annotation.Table;
import com.ldr.common.base.key.IdType;

import lombok.Getter;
import lombok.Setter;

/**
 * @类说明 【表引用关系】实体
 * @author 高振中
 * @date 2022-10-12 12:10:59
 **/
@Setter
@Getter
@Table("sys_table_ref")
public class TableRef {
	// 字段↓
	@Id(IdType.SNOW)
	private Long id; /* 主键 */
	private String mainTable; /* 主表表名 */
	private String mainInfo; /* 主表描述 */
	private String subTable; /* 子表表名 */
	private String subInfo; /* 子表描述 */
	private String refId; /* 引表外键名称 */
	private String delFlag; /* 删除标记字段名 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime createTime; /* 创建时间 */
	private Long createBy; /* 创建人id */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime updateTime; /* 修改时间 */
	private Long updateBy; /* 修改人id */
	private Byte dr; /* 删除标记 */
	/* 扩展(显示)属性↓@Exclude */
}