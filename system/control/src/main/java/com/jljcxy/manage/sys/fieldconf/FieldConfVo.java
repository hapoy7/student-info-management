package com.jljcxy.manage.sys.fieldconf;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @类说明 【字段配置】值对象
 * @author 高振中
 * @date 2023-02-02 15:59:20
 **/
@Setter
@Getter
@NoArgsConstructor
public class FieldConfVo {
	private String[] conds = new String[0]; /* 自定义条件集 */
	private String[] grids = new String[0]; /* 自定义表格列集 */
	private Long userId; /* 用户ID */
	private String component; /* 组件名 */
	public FieldConfVo(String[] conds, String[] grids) {
		super();
		this.conds = conds;
		this.grids = grids;
	}
}
