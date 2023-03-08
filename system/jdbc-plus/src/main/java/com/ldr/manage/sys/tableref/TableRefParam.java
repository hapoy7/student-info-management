package com.ldr.manage.sys.tableref;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @类说明 【表引用关系】实体
 * @author 高振中
 * @date 2021-04-30 23:37:26
 **/
@Setter
@Getter
@AllArgsConstructor
public class TableRefParam {
	private String table; /* 主表表名 */
	private Object[] ids; /* 主键值 */
}