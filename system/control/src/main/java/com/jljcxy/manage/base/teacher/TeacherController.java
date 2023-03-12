package com.jljcxy.manage.base.teacher;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jljcxy.common.aop.AutoLog;
import com.jljcxy.common.base.Page;
import com.jljcxy.common.config.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

/**
 * @类说明 【老师】控制器
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
//@Slf4j
@RestController
@RequestMapping("teacher")
@Tag(name = "【老师】API", description = "【老师】接口")
public class TeacherController {

	@Autowired
	private TeacherService teacherService; // 注入【老师】业务逻辑层

	/**
	 * @方法说明 【老师】新增
	 */
	@PostMapping("save")
	@Operation(summary = "【老师】新增", description = "【老师】新增")
	@AutoLog("【老师】新增")
	public Result<Long> save(@RequestBody @Validated Teacher teacher) {
		//if (teacherService.count(TeacherCond.builder().nameEq(teacher.getName()).build()) > 0)
			//return Result.error(1, "【老师名称】不能重复！");
		return Result.success(teacherService.save(teacher));
	}

	/**
	 * @方法说明 【老师】删除
	 */
	@PostMapping("delete")
	@Operation(summary = "【老师】删除", description = "参数格式:[1,2,3,4...]")
	@AutoLog("【老师】删除")
	public Result<Integer> delete(@RequestBody Long[] ids) {
		return Result.success(teacherService.delete(ids));
	}

	/**
	 * @方法说明 【老师】修改
	 */
	@PostMapping("update")
	@Operation(summary = "【老师】修改", description = "【老师】修改")
	@AutoLog("【老师】修改")
	public Result<Integer> update(@RequestBody @Validated Teacher teacher) {
		//if (teacherService.count(TeacherCond.builder().nameEq(teacher.getName()).idNe(teacher.getId()).build()) > 0)
			//return Result.error(1, "【老师名称】不能重复！");
		return Result.success(teacherService.update(teacher));
	}

	/**
	 * @方法说明 【老师】分页列表
	 */
	@PostMapping("page")
	@Operation(summary = "【老师】分页列表", description = "【老师】分页列表")
	@AutoLog("【老师】分页列表")
	public Result<Page<Teacher>> page(@RequestBody TeacherCond cond) {
		return Result.success(teacherService.page(cond));
	}

	/**
	 * @方法说明 【老师】列表
	 */
	@PostMapping("list")
	@Operation(summary = "【老师】列表", description = "【老师】列表")
	@AutoLog("【老师】列表")
	public Result<List<Teacher>> list(@RequestBody TeacherCond cond) {
		return Result.success(teacherService.list(cond));
	}

	/**
	 * @方法说明 【老师】按主键查
	 */
	@PostMapping("findById")
	@Operation(summary = "【老师】按主键查", description = "【老师】按主键查")
	@AutoLog("【老师】按主键查")
	public Result<Teacher> findById(@RequestParam("id") Long id) {
		return Result.success(teacherService.findById(id));
	}

	/**
	 * @方法说明 【老师】个数
	 */
	@PostMapping("count")
	@Operation(summary = "【老师】个数", description = "【老师】个数")
	@AutoLog("【老师】按主键查")
	public Result<Integer> count(@RequestBody TeacherCond cond) {
		return Result.success(teacherService.count(cond));
	}
}
