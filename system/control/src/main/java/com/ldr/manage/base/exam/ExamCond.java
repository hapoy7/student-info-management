package com.ldr.manage.base.exam;

import com.ldr.common.base.BaseCondition;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
/**
 * @类说明 【考试安排】查询条件
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
@Setter
@Getter
@Builder
//@Accessors(chain = true)
//@AllArgsConstructor
//@NoArgsConstructor
@Schema(description = "【考试安排】查询条件")
public class ExamCond extends BaseCondition {

	/**
	 * @方法说明: 拼加条件
	 **/
	@Override
	protected void addCondition() {
		and("id =", id);
		and("exam_desc LIKE", examDesc, 3);
		and("course_id =", courseId);
		and("exam_course LIKE", examCourse, 3);
		and("exam_date =", examDate);
		and("total_time =", totalTime);
		and("major LIKE", major, 3);
		and("institute LIKE", institute, 3);
		and("total_score =", totalScore);
		and("exam_type =", examType);
		in("id", ids);
	}
	/* 默认条件↓ */
	@Schema(description = "id")
	private Long id; /* id */
	@Schema(description = "考试介绍")
	private String examDesc; /* 考试介绍 */
	@Schema(description = "课程id")
	private Long courseId; /* 课程id */
	@Schema(description = "课程名称")
	private String examCourse; /* 课程名称 */
	@Schema(description = "考试日期")
	private LocalDate examDate; /* 考试日期 */
	@Schema(description = "考试时长")
	private Integer totalTime; /* 考试时长 */
	@Schema(description = "专业")
	private String major; /* 专业 */
	@Schema(description = "学院")
	private String institute; /* 学院 */
	@Schema(description = "总分")
	private Integer totalScore; /* 总分 */
	@Schema(description = "考试类型")
	private Integer examType; /* 考试类型 */
	@Schema(description = "主键数组")
	private Object[] ids; /* 主键列表 */
	/* 自定义条件↓ */
}
