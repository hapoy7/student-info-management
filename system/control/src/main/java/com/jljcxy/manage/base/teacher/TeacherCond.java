package com.jljcxy.manage.base.teacher;

import com.jljcxy.common.base.BaseCondition;
import lombok.Builder;
import lombok.Getter;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;
/**
 * @类说明 【老师】查询条件
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
@Setter
@Getter
@Builder
//@Accessors(chain = true)
//@AllArgsConstructor
//@NoArgsConstructor
@Schema(description = "【老师】查询条件")
public class TeacherCond extends BaseCondition {

	/**
	 * @方法说明: 拼加条件
	 **/
	@Override
	protected void addCondition() {
		and("id =", id);
		and("user_id =", userId);
		and("name LIKE", name, 3);
		and("sex =", sex);
		and("age =", age);
		and("title LIKE", title, 3);
		and("email LIKE", email, 3);
		and("photo_url LIKE", photoUrl, 3);
		and("phone LIKE", phone, 3);
		in("id", ids);
	}
	/* 默认条件↓ */
	@Schema(description = "主键id")
	private Long id; /* 主键id */
	@Schema(description = "用户id")
	private Long userId; /* 用户id */
	@Schema(description = "老师姓名")
	private String name; /* 老师姓名 */
	@Schema(description = "性别")
	private Integer sex; /* 性别 */
	@Schema(description = "年龄")
	private Integer age; /* 年龄 */
	@Schema(description = "头衔")
	private String title; /* 头衔 */
	@Schema(description = "邮箱")
	private String email; /* 邮箱 */
	@Schema(description = "头像地址")
	private String photoUrl; /* 头像地址 */
	@Schema(description = "手机")
	private String phone; /* 手机 */
	@Schema(description = "主键数组")
	private Object[] ids; /* 主键列表 */
	/* 自定义条件↓ */
}
