package com.jljcxy.manage.base.course;

import com.jljcxy.common.base.BaseCondition;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
/**
 * @类说明 【课程】查询条件
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
@Setter
@Getter
@Builder
//@Accessors(chain = true)
//@AllArgsConstructor
//@NoArgsConstructor
@Schema(description = "【课程】查询条件")
public class CourseCond extends BaseCondition {

	/**
	 * @方法说明: 拼加条件
	 **/
	@Override
	protected void addCondition() {
		and("id =", id);
		and("name LIKE", name, 3);
		and("course_type =", courseType);
		and("credit =", credit);
		and("sign_stime >=", signStime);
		and("sign_etime <=", signEtime);
		and("exam_stime >=", examStime);
		and("exam_etime <=", examEtime);
		in("id", ids);
	}
	/* 默认条件↓ */
	@Schema(description = "主键id")
	private Long id; /* 主键id */
	@Schema(description = "课程名称")
	private String name; /* 课程名称 */
	@Schema(description = "课程类型")
	private Integer courseType; /* 课程类型 */
	@Schema(description = "学分")
	private Double credit; /* 学分 */
	@Schema(description = "签到开始时间")
	private LocalDate signStime; /* 签到开始时间 */
	@Schema(description = "签到结束时间")
	private LocalDate signEtime; /* 签到结束时间 */
	@Schema(description = "考试开始时间")
	private LocalDate examStime; /* 考试开始时间 */
	@Schema(description = "考试结束时间")
	private LocalDate examEtime; /* 考试结束时间 */
	@Schema(description = "主键数组")
	private Object[] ids; /* 主键列表 */
	/* 自定义条件↓ */
}
