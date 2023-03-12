package com.jljcxy.manage.base.courseteacher;

import lombok.Getter;
import lombok.Setter;
//import lombok.Builder;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import com.ldr.common.base.annotation.Exclude;
import com.jljcxy.common.base.annotation.Id;
import com.jljcxy.common.base.annotation.Table;
import com.jljcxy.common.base.key.IdType;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @类说明 【老师课程关系】实体
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
@Setter
@Getter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@Schema(name = "base_course_teacher", description = "【老师课程关系】实体")
@Table("base_course_teacher")
public class CourseTeacher {
	// 字段↓
	@Id(IdType.SNOW)
	@Schema(name = "id", description = "主键id")
	private Long id; /* 主键id */
	@Schema(name = "course_id", description = "课程id")
	private Long courseId; /* 课程id */
	@Schema(name = "teacher_id", description = "老师id")
	private Long teacherId; /* 老师id */
	/* 扩展(显示)属性↓@Exclude */
}
