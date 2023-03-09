package com.ldr.manage.base.student;

import com.ldr.common.base.BaseCondition;
import lombok.Builder;
import lombok.Getter;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;
/**
 * @类说明 【学生】查询条件
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
@Setter
@Getter
@Builder
//@Accessors(chain = true)
//@AllArgsConstructor
//@NoArgsConstructor
@Schema(description = "【学生】查询条件")
public class StudentCond extends BaseCondition {

	/**
	 * @方法说明: 拼加条件
	 **/
	@Override
	protected void addCondition() {
		and("id =", id);
		and("user_id =", userId);
		and("student_number LIKE", studentNumber, 3);
		and("clazz_id =", clazzId);
		and("name LIKE", name, 3);
		and("sex =", sex);
		and("age =", age);
		and("phone LIKE", phone, 3);
		and("email LIKE", email, 3);
		and("photo_url LIKE", photoUrl, 3);
		in("id", ids);
	}
	/* 默认条件↓ */
	@Schema(description = "主键id")
	private Long id; /* 主键id */
	@Schema(description = "用户id")
	private Long userId; /* 用户id */
	@Schema(description = "学号")
	private String studentNumber; /* 学号 */
	@Schema(description = "班级id")
	private Long clazzId; /* 班级id */
	@Schema(description = "学生姓名")
	private String name; /* 学生姓名 */
	@Schema(description = "性别")
	private Integer sex; /* 性别 */
	@Schema(description = "年龄")
	private Integer age; /* 年龄 */
	@Schema(description = "联系电话")
	private String phone; /* 联系电话 */
	@Schema(description = "邮箱")
	private String email; /* 邮箱 */
	@Schema(description = "头像地址")
	private String photoUrl; /* 头像地址 */
	@Schema(description = "主键数组")
	private Object[] ids; /* 主键列表 */
	/* 自定义条件↓ */
}
