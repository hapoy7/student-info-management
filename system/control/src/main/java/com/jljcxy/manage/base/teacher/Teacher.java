package com.jljcxy.manage.base.teacher;

import com.jljcxy.common.base.annotation.Exclude;
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
 * @类说明 【老师】实体
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
@Setter
@Getter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@Schema(name = "base_teacher", description = "【老师】实体")
@Table("base_teacher")
public class Teacher {
	// 字段↓
	@Id(IdType.SNOW)
	@Schema(name = "id", description = "主键id")
	private Long id; /* 主键id */
	@Schema(name = "user_id", description = "用户id")
	private Long userId; /* 用户id */
	@Schema(name = "name", description = "老师姓名")
	private String name; /* 老师姓名 */
	@Schema(name = "sex", description = "性别")
	private Integer sex; /* 性别 */
	@Schema(name = "age", description = "年龄")
	private Integer age; /* 年龄 */
	@Schema(name = "title", description = "头衔")
	private String title; /* 头衔 */
	@Schema(name = "email", description = "邮箱")
	private String email; /* 邮箱 */
	@Schema(name = "photo_url", description = "头像地址")
	private String photoUrl; /* 头像地址 */
	@Schema(name = "phone", description = "手机")
	private String phone; /* 手机 */
	/* 扩展(显示)属性↓@Exclude */
	@Exclude
	private String loginName;
	@Exclude
	private String password;
}
