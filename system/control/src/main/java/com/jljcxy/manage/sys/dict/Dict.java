package com.jljcxy.manage.sys.dict;

import java.time.LocalDateTime;

import com.jljcxy.common.base.annotation.Id;
import com.jljcxy.common.base.annotation.Table;
import com.jljcxy.common.base.key.IdType;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 【枚举字典】实体
 * @author 高振中
 * @date 2020-12-19 21:18:17
 **/
@Setter
@Getter
@Table("sys_dict")
public class Dict {
	// 字段↓
	@Id(IdType.SNOW)
	private Long id; // 主键
	@NotBlank(message = "[字典名称]不能为空!")
	private String name; // 字典名称
	@NotBlank(message = "[字典编码]不能为空!")
	private String code; // 字典编码
	private String remark; // 备注
	private LocalDateTime createTime; /* 创建时间 */
	private LocalDateTime updateTime; /* 修改时间 */
	private Long createBy; /* 创建时间 */
	private Long updateBy; /* 创建时间 */
	private Byte dr; /* 删除标记 */
	// 扩展(显示)属性↓
}
