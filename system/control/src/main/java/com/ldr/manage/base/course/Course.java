package com.ldr.manage.base.course;

import com.ldr.common.base.annotation.Id;
import com.ldr.common.base.annotation.Table;
import com.ldr.common.base.key.IdType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @类说明 【课程】实体
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
@Setter
@Getter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@Schema(name = "base_course", description = "【课程】实体")
@Table("base_course")
public class Course {
	// 字段↓
	@Id(IdType.SNOW)
	@Schema(name = "id", description = "主键id")
	private Long id; /* 主键id */
	@Schema(name = "name", description = "课程名称")
	private String name; /* 课程名称 */
	@Schema(name = "course_type", description = "课程类型")
	private Integer courseType; /* 课程类型 */
	@Schema(name = "credit", description = "学分")
	private Double credit; /* 学分 */
	@Schema(name = "sign_stime", description = "签到开始时间")
	private LocalDate signStime; /* 签到开始时间 */
	@Schema(name = "sign_etime", description = "签到结束时间")
	private LocalDate signEtime; /* 签到结束时间 */
	@Schema(name = "exam_stime", description = "考试开始时间")
	private LocalDate examStime; /* 考试开始时间 */
	@Schema(name = "exam_etime", description = "考试结束时间")
	private LocalDate examEtime; /* 考试结束时间 */
	/* 扩展(显示)属性↓@Exclude */
}
