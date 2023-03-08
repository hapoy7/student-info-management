package com.jljcxy.manage.base.clazz;

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
 * @类说明 【班级】控制器
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
//@Slf4j
@RestController
@RequestMapping("clazz")
@Tag(name = "【班级】API", description = "【班级】接口")
public class ClazzController {

	@Autowired
	private ClazzService clazzService; // 注入【班级】业务逻辑层

	/**
	 * @方法说明 【班级】新增
	 */
	@PostMapping("save")
	@Operation(summary = "【班级】新增", description = "【班级】新增")
	@AutoLog("【班级】新增")
	public Result<Long> save(@RequestBody @Validated Clazz clazz) {
		//if (clazzService.count(ClazzCond.builder().nameEq(clazz.getName()).build()) > 0)
			//return Result.error(1, "【班级名称】不能重复！");
		return Result.success(clazzService.save(clazz));
	}

	/**
	 * @方法说明 【班级】删除
	 */
	@PostMapping("delete")
	@Operation(summary = "【班级】删除", description = "参数格式:[1,2,3,4...]")
	@AutoLog("【班级】删除")
	public Result<Integer> delete(@RequestBody Long[] ids) {
		return Result.success(clazzService.delete(ids));
	}

	/**
	 * @方法说明 【班级】修改
	 */
	@PostMapping("update")
	@Operation(summary = "【班级】修改", description = "【班级】修改")
	@AutoLog("【班级】修改")
	public Result<Integer> update(@RequestBody @Validated Clazz clazz) {
		//if (clazzService.count(ClazzCond.builder().nameEq(clazz.getName()).idNe(clazz.getId()).build()) > 0)
			//return Result.error(1, "【班级名称】不能重复！");
		return Result.success(clazzService.update(clazz));
	}

	/**
	 * @方法说明 【班级】分页列表
	 */
	@PostMapping("page")
	@Operation(summary = "【班级】分页列表", description = "【班级】分页列表")
	@AutoLog("【班级】分页列表")
	public Result<Page<Clazz>> page(@RequestBody ClazzCond cond) {
		return Result.success(clazzService.page(cond));
	}

	/**
	 * @方法说明 【班级】列表
	 */
	@PostMapping("list")
	@Operation(summary = "【班级】列表", description = "【班级】列表")
	@AutoLog("【班级】列表")
	public Result<List<Clazz>> list(@RequestBody ClazzCond cond) {
		return Result.success(clazzService.list(cond));
	}

	/**
	 * @方法说明 【班级】按主键查
	 */
	@PostMapping("findById")
	@Operation(summary = "【班级】按主键查", description = "【班级】按主键查")
	@AutoLog("【班级】按主键查")
	public Result<Clazz> findById(@RequestParam("id") Long id) {
		return Result.success(clazzService.findById(id));
	}

	/**
	 * @方法说明 【班级】个数
	 */
	@PostMapping("count")
	@Operation(summary = "【班级】个数", description = "【班级】个数")
	@AutoLog("【班级】按主键查")
	public Result<Integer> count(@RequestBody ClazzCond cond) {
		return Result.success(clazzService.count(cond));
	}
}