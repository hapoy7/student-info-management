package com.jljcxy.manage.sys.fieldconf;

import com.jljcxy.common.base.annotation.Id;
import com.jljcxy.common.base.annotation.Table;
import com.jljcxy.common.base.key.IdType;
import lombok.*;

/**
 * @类说明 【字段配置】实体
 * @author 高振中
 * @date 2023-02-02 15:59:20
 **/
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("sys_field_conf")
public class FieldConf {
	// 字段↓
	@Id(IdType.SNOW)
	private Long id; /* 主键 */
	private Long userId; /* 用户ID */
	private String component; /* 组件名 */
	private String cond; /* 自定义条件 */
	private String grid; /* 自定义表格 */
	private String remark; /* 备注 */
	/* 扩展(显示)属性↓@Exclude */
}
