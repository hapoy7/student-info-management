package com.ldr.manage.base.studentcourse;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ldr.common.aop.AutoLog;
import com.ldr.common.base.Page;
import com.ldr.common.config.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

/**
 * @类说明 【学生课程关系】控制器
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
//@Slf4j
@RestController
@RequestMapping("studentcourse")
@Tag(name = "【学生课程关系】API", description = "【学生课程关系】接口")
public class StudentCourseController {

	@Autowired
	private StudentCourseService studentCourseService; // 注入【学生课程关系】业务逻辑层

	/**
	 * @方法说明 【学生课程关系】新增
	 */
	@PostMapping("save")
	@Operation(summary = "【学生课程关系】新增", description = "【学生课程关系】新增")
	@AutoLog("【学生课程关系】新增")
	public Result<Long> save(@RequestBody @Validated StudentCourse studentCourse) {
		//if (studentCourseService.count(StudentCourseCond.builder().nameEq(studentCourse.getName()).build()) > 0)
			//return Result.error(1, "【学生课程关系名称】不能重复！");
		return Result.success(studentCourseService.save(studentCourse));
	}

	/**
	 * @方法说明 【学生课程关系】删除
	 */
	@PostMapping("delete")
	@Operation(summary = "【学生课程关系】删除", description = "参数格式:[1,2,3,4...]")
	@AutoLog("【学生课程关系】删除")
	public Result<Integer> delete(@RequestBody Long[] ids) {
		return Result.success(studentCourseService.delete(ids));
	}

	/**
	 * @方法说明 【学生课程关系】修改
	 */
	@PostMapping("update")
	@Operation(summary = "【学生课程关系】修改", description = "【学生课程关系】修改")
	@AutoLog("【学生课程关系】修改")
	public Result<Integer> update(@RequestBody @Validated StudentCourse studentCourse) {
		//if (studentCourseService.count(StudentCourseCond.builder().nameEq(studentCourse.getName()).idNe(studentCourse.getId()).build()) > 0)
			//return Result.error(1, "【学生课程关系名称】不能重复！");
		return Result.success(studentCourseService.update(studentCourse));
	}

	/**
	 * @方法说明 【学生课程关系】分页列表
	 */
	@PostMapping("page")
	@Operation(summary = "【学生课程关系】分页列表", description = "【学生课程关系】分页列表")
	@AutoLog("【学生课程关系】分页列表")
	public Result<Page<StudentCourse>> page(@RequestBody StudentCourseCond cond) {
		return Result.success(studentCourseService.page(cond));
	}

	/**
	 * @方法说明 【学生课程关系】列表
	 */
	@PostMapping("list")
	@Operation(summary = "【学生课程关系】列表", description = "【学生课程关系】列表")
	@AutoLog("【学生课程关系】列表")
	public Result<List<StudentCourse>> list(@RequestBody StudentCourseCond cond) {
		return Result.success(studentCourseService.list(cond));
	}

	/**
	 * @方法说明 【学生课程关系】按主键查
	 */
	@PostMapping("findById")
	@Operation(summary = "【学生课程关系】按主键查", description = "【学生课程关系】按主键查")
	@AutoLog("【学生课程关系】按主键查")
	public Result<StudentCourse> findById(@RequestParam("id") Long id) {
		return Result.success(studentCourseService.findById(id));
	}

	/**
	 * @方法说明 【学生课程关系】个数
	 */
	@PostMapping("count")
	@Operation(summary = "【学生课程关系】个数", description = "【学生课程关系】个数")
	@AutoLog("【学生课程关系】按主键查")
	public Result<Integer> count(@RequestBody StudentCourseCond cond) {
		return Result.success(studentCourseService.count(cond));
	}
}
