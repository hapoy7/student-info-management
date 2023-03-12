package com.jljcxy.manage.base.examrecord;

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
 * @类说明 【考试记录表】控制器
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
//@Slf4j
@RestController
@RequestMapping("examrecord")
@Tag(name = "【考试记录表】API", description = "【考试记录表】接口")
public class ExamRecordController {

	@Autowired
	private ExamRecordService examRecordService; // 注入【考试记录表】业务逻辑层

	/**
	 * @方法说明 【考试记录表】新增
	 */
	@PostMapping("save")
	@Operation(summary = "【考试记录表】新增", description = "【考试记录表】新增")
	@AutoLog("【考试记录表】新增")
	public Result<Long> save(@RequestBody @Validated ExamRecord examRecord) {
		//if (examRecordService.count(ExamRecordCond.builder().nameEq(examRecord.getName()).build()) > 0)
			//return Result.error(1, "【考试记录表名称】不能重复！");
		return Result.success(examRecordService.save(examRecord));
	}

	/**
	 * @方法说明 【考试记录表】删除
	 */
	@PostMapping("delete")
	@Operation(summary = "【考试记录表】删除", description = "参数格式:[1,2,3,4...]")
	@AutoLog("【考试记录表】删除")
	public Result<Integer> delete(@RequestBody Long[] ids) {
		return Result.success(examRecordService.delete(ids));
	}

	/**
	 * @方法说明 【考试记录表】修改
	 */
	@PostMapping("update")
	@Operation(summary = "【考试记录表】修改", description = "【考试记录表】修改")
	@AutoLog("【考试记录表】修改")
	public Result<Integer> update(@RequestBody @Validated ExamRecord examRecord) {
		//if (examRecordService.count(ExamRecordCond.builder().nameEq(examRecord.getName()).idNe(examRecord.getId()).build()) > 0)
			//return Result.error(1, "【考试记录表名称】不能重复！");
		return Result.success(examRecordService.update(examRecord));
	}

	/**
	 * @方法说明 【考试记录表】分页列表
	 */
	@PostMapping("page")
	@Operation(summary = "【考试记录表】分页列表", description = "【考试记录表】分页列表")
	@AutoLog("【考试记录表】分页列表")
	public Result<Page<ExamRecord>> page(@RequestBody ExamRecordCond cond) {
		return Result.success(examRecordService.page(cond));
	}

	/**
	 * @方法说明 【考试记录表】列表
	 */
	@PostMapping("list")
	@Operation(summary = "【考试记录表】列表", description = "【考试记录表】列表")
	@AutoLog("【考试记录表】列表")
	public Result<List<ExamRecord>> list(@RequestBody ExamRecordCond cond) {
		return Result.success(examRecordService.list(cond));
	}

	/**
	 * @方法说明 【考试记录表】按主键查
	 */
	@PostMapping("findById")
	@Operation(summary = "【考试记录表】按主键查", description = "【考试记录表】按主键查")
	@AutoLog("【考试记录表】按主键查")
	public Result<ExamRecord> findById(@RequestParam("id") Long id) {
		return Result.success(examRecordService.findById(id));
	}

	/**
	 * @方法说明 【考试记录表】个数
	 */
	@PostMapping("count")
	@Operation(summary = "【考试记录表】个数", description = "【考试记录表】个数")
	@AutoLog("【考试记录表】按主键查")
	public Result<Integer> count(@RequestBody ExamRecordCond cond) {
		return Result.success(examRecordService.count(cond));
	}
}
