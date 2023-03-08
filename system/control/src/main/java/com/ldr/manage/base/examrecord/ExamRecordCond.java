package com.jljcxy.manage.base.examrecord;

import java.time.LocalDate;
import com.ldr.common.base.BaseCondition;
import lombok.Builder;
import lombok.Getter;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;
/**
 * @类说明 【考试记录表】查询条件
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
@Setter
@Getter
@Builder
//@Accessors(chain = true)
//@AllArgsConstructor
//@NoArgsConstructor
@Schema(description = "【考试记录表】查询条件")
public class ExamRecordCond extends BaseCondition {

	/**
	 * @方法说明: 拼加条件
	 **/
	@Override
	protected void addCondition() {
		and("id =", id);
		and("exam_id =", examId);
		and("student_id =", studentId);
		and("exem_date =", exemDate);
		and("exem_time_cost =", exemTimeCost);
		and("exem_score =", exemScore);
		and("exem_result_level =", exemResultLevel);
		in("id", ids);
	}
	/* 默认条件↓ */
	@Schema(description = "考试记录表的主键")
	private Long id; /* 考试记录表的主键 */
	@Schema(description = "考试安排ID")
	private Long examId; /* 考试安排ID */
	@Schema(description = "学生id")
	private Long studentId; /* 学生id */
	@Schema(description = "参加考试的时间")
	private LocalDate exemDate; /* 参加考试的时间 */
	@Schema(description = "完成考试所用的时间,单位分钟")
	private Integer exemTimeCost; /* 完成考试所用的时间,单位分钟 */
	@Schema(description = "参与考试的实际得分")
	private Integer exemScore; /* 参与考试的实际得分 */
	@Schema(description = "考试结果的等级")
	private Integer exemResultLevel; /* 考试结果的等级 */
	@Schema(description = "主键数组")
	private Object[] ids; /* 主键列表 */
	/* 自定义条件↓ */
}