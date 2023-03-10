package com.jljcxy.manage.base.clazz;

import com.jljcxy.common.base.BaseCondition;
import lombok.Builder;
import lombok.Getter;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;
/**
 * @类说明 【班级】查询条件
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
@Setter
@Getter
@Builder
//@Accessors(chain = true)
//@AllArgsConstructor
//@NoArgsConstructor
@Schema(description = "【班级】查询条件")
public class ClazzCond extends BaseCondition {

	/**
	 * @方法说明: 拼加条件
	 **/
	@Override
	protected void addCondition() {
		and("id =", id);
		and("name LIKE", name, 3);
		and("major_id =", majorId);
		and("institute_id =", instituteId);
		in("id", ids);
	}
	/* 默认条件↓ */
	@Schema(description = "主键id")
	private Long id; /* 主键id */
	@Schema(description = "班级名称")
	private String name; /* 班级名称 */
	@Schema(description = "专业id")
	private Long majorId; /* 专业id */
	@Schema(description = "学院id")
	private Long instituteId; /* 学院id */
	@Schema(description = "主键数组")
	private Object[] ids; /* 主键列表 */
	/* 自定义条件↓ */
}
