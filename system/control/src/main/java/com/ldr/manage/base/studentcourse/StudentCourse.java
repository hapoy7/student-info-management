package com.jljcxy.manage.base.studentcourse;

import lombok.Getter;
import lombok.Setter;
//import lombok.Builder;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import com.ldr.common.base.annotation.Exclude;
import com.ldr.common.base.annotation.Id;
import com.ldr.common.base.annotation.Table;
import com.ldr.common.base.key.IdType;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @类说明 【学生课程关系】实体
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
@Setter
@Getter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@Schema(name = "base_student_course", description = "【学生课程关系】实体")
@Table("base_student_course")
public class StudentCourse {
	// 字段↓
	@Id(IdType.SNOW)
	@Schema(name = "id", description = "主键id")
	private Long id; /* 主键id */
	@Schema(name = "student_id", description = "学生id")
	private Long studentId; /* 学生id */
	@Schema(name = "course_id", description = "课程id")
	private Long courseId; /* 课程id */
	/* 扩展(显示)属性↓@Exclude */
}