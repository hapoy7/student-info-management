package com.jljcxy.manage.base.student;

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
 * @类说明 【学生】实体
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
@Setter
@Getter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@Schema(name = "base_student", description = "【学生】实体")
@Table("base_student")
public class Student {
	// 字段↓
	@Id(IdType.SNOW)
	@Schema(name = "id", description = "主键id")
	private Long id; /* 主键id */
	@Schema(name = "user_id", description = "用户id")
	private Long userId; /* 用户id */
	@Schema(name = "student_number", description = "学号")
	private String studentNumber; /* 学号 */
	@Schema(name = "clazz_id", description = "班级id")
	private Long clazzId; /* 班级id */
	@Schema(name = "name", description = "学生姓名")
	private String name; /* 学生姓名 */
	@Schema(name = "sex", description = "性别")
	private Integer sex; /* 性别 */
	@Schema(name = "age", description = "年龄")
	private Integer age; /* 年龄 */
	@Schema(name = "phone", description = "联系电话")
	private String phone; /* 联系电话 */
	@Schema(name = "email", description = "邮箱")
	private String email; /* 邮箱 */
	@Schema(name = "photo_url", description = "头像地址")
	private String photoUrl; /* 头像地址 */
	/* 扩展(显示)属性↓@Exclude */
}