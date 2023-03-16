package com.jljcxy.manage.base.student;

import com.jljcxy.common.aop.AutoLog;
import com.jljcxy.common.base.Page;
import com.jljcxy.common.config.Result;
import com.jljcxy.manage.sys.user.User;
import com.jljcxy.manage.sys.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @类说明 【学生】控制器
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
//@Slf4j
@RestController
@RequestMapping("student")
@Tag(name = "【学生】API", description = "【学生】接口")
public class StudentController {

	@Autowired
	private StudentService studentService; // 注入【学生】业务逻辑层
	@Autowired
	private UserService userService; // 注入【用户】业务逻辑层

	/**
	 * @方法说明 【学生】新增
	 */
	@PostMapping("save")
	@Operation(summary = "【学生】新增", description = "【学生】新增")
	@AutoLog("【学生】新增")
	public Result<Long> save(@RequestBody @Validated Student student) {
		Long userId = userService.save(User.builder().name(student.getName()).avatar(student.getPhotoUrl())
				.loginName(student.getLoginName()).password(student.getPassword()).phone(student.getPhone()).gender(student.getSex().byteValue()).type((byte) 3).build());
		student.setUserId(userId);
		return Result.success(studentService.save(student));
	}

	/**
	 * @方法说明 【学生】删除
	 */
	@PostMapping("delete")
	@Operation(summary = "【学生】删除", description = "参数格式:[1,2,3,4...]")
	@AutoLog("【学生】删除")
	public Result<Integer> delete(@RequestBody Long[] ids) {
		return Result.success(studentService.delete(ids));
	}

	/**
	 * @方法说明 【学生】修改
	 */
	@PostMapping("update")
	@Operation(summary = "【学生】修改", description = "【学生】修改")
	@AutoLog("【学生】修改")
	public Result<Integer> update(@RequestBody @Validated Student student) {
		userService.save(User.builder().name(student.getName()).avatar(student.getPhotoUrl())
				.loginName(student.getLoginName()).password(student.getPassword()).phone(student.getPhone()).gender(student.getSex().byteValue()).type((byte) 3).build());
		return Result.success(studentService.update(student));
	}

	/**
	 * @方法说明 【学生】分页列表
	 */
	@PostMapping("page")
	@Operation(summary = "【学生】分页列表", description = "【学生】分页列表")
	@AutoLog("【学生】分页列表")
	public Result<Page<Student>> page(@RequestBody StudentCond cond) {
		return Result.success(studentService.page(cond));
	}

	/**
	 * @方法说明 【学生】列表
	 */
	@PostMapping("list")
	@Operation(summary = "【学生】列表", description = "【学生】列表")
	@AutoLog("【学生】列表")
	public Result<List<Student>> list(@RequestBody StudentCond cond) {
		return Result.success(studentService.list(cond));
	}

	/**
	 * @方法说明 【学生】按主键查
	 */
	@PostMapping("findById")
	@Operation(summary = "【学生】按主键查", description = "【学生】按主键查")
	@AutoLog("【学生】按主键查")
	public Result<Student> findById(@RequestParam("id") Long id) {
		return Result.success(studentService.findById(id));
	}

	/**
	 * @方法说明 【学生】个数
	 */
	@PostMapping("count")
	@Operation(summary = "【学生】个数", description = "【学生】个数")
	@AutoLog("【学生】按主键查")
	public Result<Integer> count(@RequestBody StudentCond cond) {
		return Result.success(studentService.count(cond));
	}
}
