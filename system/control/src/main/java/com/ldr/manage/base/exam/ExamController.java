package com.ldr.manage.base.exam;

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
 * @类说明 【考试安排】控制器
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
//@Slf4j
@RestController
@RequestMapping("exam")
@Tag(name = "【考试安排】API", description = "【考试安排】接口")
public class ExamController {

	@Autowired
	private ExamService examService; // 注入【考试安排】业务逻辑层

	/**
	 * @方法说明 【考试安排】新增
	 */
	@PostMapping("save")
	@Operation(summary = "【考试安排】新增", description = "【考试安排】新增")
	@AutoLog("【考试安排】新增")
	public Result<Long> save(@RequestBody @Validated Exam exam) {
		//if (examService.count(ExamCond.builder().nameEq(exam.getName()).build()) > 0)
			//return Result.error(1, "【考试安排名称】不能重复！");
		return Result.success(examService.save(exam));
	}

	/**
	 * @方法说明 【考试安排】删除
	 */
	@PostMapping("delete")
	@Operation(summary = "【考试安排】删除", description = "参数格式:[1,2,3,4...]")
	@AutoLog("【考试安排】删除")
	public Result<Integer> delete(@RequestBody Long[] ids) {
		return Result.success(examService.delete(ids));
	}

	/**
	 * @方法说明 【考试安排】修改
	 */
	@PostMapping("update")
	@Operation(summary = "【考试安排】修改", description = "【考试安排】修改")
	@AutoLog("【考试安排】修改")
	public Result<Integer> update(@RequestBody @Validated Exam exam) {
		//if (examService.count(ExamCond.builder().nameEq(exam.getName()).idNe(exam.getId()).build()) > 0)
			//return Result.error(1, "【考试安排名称】不能重复！");
		return Result.success(examService.update(exam));
	}

	/**
	 * @方法说明 【考试安排】分页列表
	 */
	@PostMapping("page")
	@Operation(summary = "【考试安排】分页列表", description = "【考试安排】分页列表")
	@AutoLog("【考试安排】分页列表")
	public Result<Page<Exam>> page(@RequestBody ExamCond cond) {
		return Result.success(examService.page(cond));
	}

	/**
	 * @方法说明 【考试安排】列表
	 */
	@PostMapping("list")
	@Operation(summary = "【考试安排】列表", description = "【考试安排】列表")
	@AutoLog("【考试安排】列表")
	public Result<List<Exam>> list(@RequestBody ExamCond cond) {
		return Result.success(examService.list(cond));
	}

	/**
	 * @方法说明 【考试安排】按主键查
	 */
	@PostMapping("findById")
	@Operation(summary = "【考试安排】按主键查", description = "【考试安排】按主键查")
	@AutoLog("【考试安排】按主键查")
	public Result<Exam> findById(@RequestParam("id") Long id) {
		return Result.success(examService.findById(id));
	}

	/**
	 * @方法说明 【考试安排】个数
	 */
	@PostMapping("count")
	@Operation(summary = "【考试安排】个数", description = "【考试安排】个数")
	@AutoLog("【考试安排】按主键查")
	public Result<Integer> count(@RequestBody ExamCond cond) {
		return Result.success(examService.count(cond));
	}
}
