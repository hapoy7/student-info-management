package com.ldr.manage.base.exam;

import com.ldr.common.base.annotation.Id;
import com.ldr.common.base.annotation.Table;
import com.ldr.common.base.key.IdType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @类说明 【考试安排】实体
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
@Setter
@Getter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@Schema(name = "question_exam", description = "【考试安排】实体")
@Table("question_exam")
public class Exam {
	// 字段↓
	@Id(IdType.SNOW)
	@Schema(name = "id", description = "id")
	private Long id; /* id */
	@Schema(name = "exam_desc", description = "考试介绍")
	private String examDesc; /* 考试介绍 */
	@Schema(name = "course_id", description = "课程id")
	private Long courseId; /* 课程id */
	@Schema(name = "exam_course", description = "课程名称")
	private String examCourse; /* 课程名称 */
	@Schema(name = "exam_date", description = "考试日期")
	private LocalDate examDate; /* 考试日期 */
	@Schema(name = "total_time", description = "考试时长")
	private Integer totalTime; /* 考试时长 */
	@Schema(name = "major", description = "专业")
	private String major; /* 专业 */
	@Schema(name = "institute", description = "学院")
	private String institute; /* 学院 */
	@Schema(name = "total_score", description = "总分")
	private Integer totalScore; /* 总分 */
	@Schema(name = "exam_type", description = "考试类型")
	private Integer examType; /* 考试类型 */
	/* 扩展(显示)属性↓@Exclude */
}
