package com.jljcxy.manage.base.examrecord;

import com.jljcxy.common.base.annotation.Exclude;
import com.jljcxy.common.base.annotation.Id;
import com.jljcxy.common.base.annotation.Table;
import com.jljcxy.common.base.key.IdType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @类说明 【考试记录表】实体
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
@Setter
@Getter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@Schema(name = "question_exam_record", description = "【考试记录表】实体")
@Table("question_exam_record")
public class ExamRecord {
	// 字段↓
	@Id(IdType.SNOW)
	@Schema(name = "id", description = "考试记录表的主键")
	private Long id; /* 考试记录表的主键 */
	@Schema(name = "exam_id", description = "考试安排ID")
	private Long examId; /* 考试安排ID */
	@Schema(name = "student_id", description = "学生id")
	private Long studentId; /* 学生id */
	@Schema(name = "exem_date", description = "参加考试的时间")
	private LocalDate exemDate; /* 参加考试的时间 */
	@Schema(name = "exem_time_cost", description = "完成考试所用的时间,单位分钟")
	private Integer exemTimeCost; /* 完成考试所用的时间,单位分钟 */
	@Schema(name = "exem_score", description = "参与考试的实际得分")
	private Integer exemScore; /* 参与考试的实际得分 */
	@Schema(name = "exem_result_level", description = "考试结果的等级")
	private Integer exemResultLevel; /* 考试结果的等级 */
	/* 扩展(显示)属性↓@Exclude */
	@Exclude
	private String studentName; /* 学生姓名 */
}
