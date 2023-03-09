package com.ldr.manage.base.major;

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
 * @类说明 【专业】控制器
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
//@Slf4j
@RestController
@RequestMapping("major")
@Tag(name = "【专业】API", description = "【专业】接口")
public class MajorController {

	@Autowired
	private MajorService majorService; // 注入【专业】业务逻辑层

	/**
	 * @方法说明 【专业】新增
	 */
	@PostMapping("save")
	@Operation(summary = "【专业】新增", description = "【专业】新增")
	@AutoLog("【专业】新增")
	public Result<Long> save(@RequestBody @Validated Major major) {
		//if (majorService.count(MajorCond.builder().nameEq(major.getName()).build()) > 0)
			//return Result.error(1, "【专业名称】不能重复！");
		return Result.success(majorService.save(major));
	}

	/**
	 * @方法说明 【专业】删除
	 */
	@PostMapping("delete")
	@Operation(summary = "【专业】删除", description = "参数格式:[1,2,3,4...]")
	@AutoLog("【专业】删除")
	public Result<Integer> delete(@RequestBody Long[] ids) {
		return Result.success(majorService.delete(ids));
	}

	/**
	 * @方法说明 【专业】修改
	 */
	@PostMapping("update")
	@Operation(summary = "【专业】修改", description = "【专业】修改")
	@AutoLog("【专业】修改")
	public Result<Integer> update(@RequestBody @Validated Major major) {
		//if (majorService.count(MajorCond.builder().nameEq(major.getName()).idNe(major.getId()).build()) > 0)
			//return Result.error(1, "【专业名称】不能重复！");
		return Result.success(majorService.update(major));
	}

	/**
	 * @方法说明 【专业】分页列表
	 */
	@PostMapping("page")
	@Operation(summary = "【专业】分页列表", description = "【专业】分页列表")
	@AutoLog("【专业】分页列表")
	public Result<Page<Major>> page(@RequestBody MajorCond cond) {
		return Result.success(majorService.page(cond));
	}

	/**
	 * @方法说明 【专业】列表
	 */
	@PostMapping("list")
	@Operation(summary = "【专业】列表", description = "【专业】列表")
	@AutoLog("【专业】列表")
	public Result<List<Major>> list(@RequestBody MajorCond cond) {
		return Result.success(majorService.list(cond));
	}

	/**
	 * @方法说明 【专业】按主键查
	 */
	@PostMapping("findById")
	@Operation(summary = "【专业】按主键查", description = "【专业】按主键查")
	@AutoLog("【专业】按主键查")
	public Result<Major> findById(@RequestParam("id") Long id) {
		return Result.success(majorService.findById(id));
	}

	/**
	 * @方法说明 【专业】个数
	 */
	@PostMapping("count")
	@Operation(summary = "【专业】个数", description = "【专业】个数")
	@AutoLog("【专业】按主键查")
	public Result<Integer> count(@RequestBody MajorCond cond) {
		return Result.success(majorService.count(cond));
	}
}
