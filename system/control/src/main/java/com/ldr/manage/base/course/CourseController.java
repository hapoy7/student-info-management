package com.jljcxy.manage.base.course;

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
 * @类说明 【课程】控制器
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
//@Slf4j
@RestController
@RequestMapping("course")
@Tag(name = "【课程】API", description = "【课程】接口")
public class CourseController {

	@Autowired
	private CourseService courseService; // 注入【课程】业务逻辑层

	/**
	 * @方法说明 【课程】新增
	 */
	@PostMapping("save")
	@Operation(summary = "【课程】新增", description = "【课程】新增")
	@AutoLog("【课程】新增")
	public Result<Long> save(@RequestBody @Validated Course course) {
		//if (courseService.count(CourseCond.builder().nameEq(course.getName()).build()) > 0)
			//return Result.error(1, "【课程名称】不能重复！");
		return Result.success(courseService.save(course));
	}

	/**
	 * @方法说明 【课程】删除
	 */
	@PostMapping("delete")
	@Operation(summary = "【课程】删除", description = "参数格式:[1,2,3,4...]")
	@AutoLog("【课程】删除")
	public Result<Integer> delete(@RequestBody Long[] ids) {
		return Result.success(courseService.delete(ids));
	}

	/**
	 * @方法说明 【课程】修改
	 */
	@PostMapping("update")
	@Operation(summary = "【课程】修改", description = "【课程】修改")
	@AutoLog("【课程】修改")
	public Result<Integer> update(@RequestBody @Validated Course course) {
		//if (courseService.count(CourseCond.builder().nameEq(course.getName()).idNe(course.getId()).build()) > 0)
			//return Result.error(1, "【课程名称】不能重复！");
		return Result.success(courseService.update(course));
	}

	/**
	 * @方法说明 【课程】分页列表
	 */
	@PostMapping("page")
	@Operation(summary = "【课程】分页列表", description = "【课程】分页列表")
	@AutoLog("【课程】分页列表")
	public Result<Page<Course>> page(@RequestBody CourseCond cond) {
		return Result.success(courseService.page(cond));
	}

	/**
	 * @方法说明 【课程】列表
	 */
	@PostMapping("list")
	@Operation(summary = "【课程】列表", description = "【课程】列表")
	@AutoLog("【课程】列表")
	public Result<List<Course>> list(@RequestBody CourseCond cond) {
		return Result.success(courseService.list(cond));
	}

	/**
	 * @方法说明 【课程】按主键查
	 */
	@PostMapping("findById")
	@Operation(summary = "【课程】按主键查", description = "【课程】按主键查")
	@AutoLog("【课程】按主键查")
	public Result<Course> findById(@RequestParam("id") Long id) {
		return Result.success(courseService.findById(id));
	}

	/**
	 * @方法说明 【课程】个数
	 */
	@PostMapping("count")
	@Operation(summary = "【课程】个数", description = "【课程】个数")
	@AutoLog("【课程】按主键查")
	public Result<Integer> count(@RequestBody CourseCond cond) {
		return Result.success(courseService.count(cond));
	}
}