package com.jljcxy.manage.base.courseteacher;

import com.ldr.common.base.BaseCondition;
import lombok.Builder;
import lombok.Getter;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;
/**
 * @类说明 【老师课程关系】查询条件
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
@Setter
@Getter
@Builder
//@Accessors(chain = true)
//@AllArgsConstructor
//@NoArgsConstructor
@Schema(description = "【老师课程关系】查询条件")
public class CourseTeacherCond extends BaseCondition {

	/**
	 * @方法说明: 拼加条件
	 **/
	@Override
	protected void addCondition() {
		and("id =", id);
		and("course_id =", courseId);
		and("teacher_id =", teacherId);
		in("id", ids);
	}
	/* 默认条件↓ */
	@Schema(description = "主键id")
	private Long id; /* 主键id */
	@Schema(description = "课程id")
	private Long courseId; /* 课程id */
	@Schema(description = "老师id")
	private Long teacherId; /* 老师id */
	@Schema(description = "主键数组")
	private Object[] ids; /* 主键列表 */
	/* 自定义条件↓ */
}