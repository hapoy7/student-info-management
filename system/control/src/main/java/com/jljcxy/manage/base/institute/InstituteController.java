package com.jljcxy.manage.base.institute;

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
 * @类说明 【学院】控制器
 * @author 张可可
 * @date 2023-03-08 23:42:07
 **/
//@Slf4j
@RestController
@RequestMapping("institute")
@Tag(name = "【学院】API", description = "【学院】接口")
public class InstituteController {

	@Autowired
	private InstituteService instituteService; // 注入【学院】业务逻辑层

	/**
	 * @方法说明 【学院】新增
	 */
	@PostMapping("save")
	@Operation(summary = "【学院】新增", description = "【学院】新增")
	@AutoLog("【学院】新增")
	public Result<Long> save(@RequestBody @Validated Institute institute) {
		//if (instituteService.count(InstituteCond.builder().nameEq(institute.getName()).build()) > 0)
			//return Result.error(1, "【学院名称】不能重复！");
		return Result.success(instituteService.save(institute));
	}

	/**
	 * @方法说明 【学院】删除
	 */
	@PostMapping("delete")
	@Operation(summary = "【学院】删除", description = "参数格式:[1,2,3,4...]")
	@AutoLog("【学院】删除")
	public Result<Integer> delete(@RequestBody Long[] ids) {
		return Result.success(instituteService.delete(ids));
	}

	/**
	 * @方法说明 【学院】修改
	 */
	@PostMapping("update")
	@Operation(summary = "【学院】修改", description = "【学院】修改")
	@AutoLog("【学院】修改")
	public Result<Integer> update(@RequestBody @Validated Institute institute) {
		//if (instituteService.count(InstituteCond.builder().nameEq(institute.getName()).idNe(institute.getId()).build()) > 0)
			//return Result.error(1, "【学院名称】不能重复！");
		return Result.success(instituteService.update(institute));
	}

	/**
	 * @方法说明 【学院】分页列表
	 */
	@PostMapping("page")
	@Operation(summary = "【学院】分页列表", description = "【学院】分页列表")
	@AutoLog("【学院】分页列表")
	public Result<Page<Institute>> page(@RequestBody InstituteCond cond) {
		return Result.success(instituteService.page(cond));
	}

	/**
	 * @方法说明 【学院】列表
	 */
	@PostMapping("list")
	@Operation(summary = "【学院】列表", description = "【学院】列表")
	@AutoLog("【学院】列表")
	public Result<List<Institute>> list(@RequestBody InstituteCond cond) {
		return Result.success(instituteService.list(cond));
	}

	/**
	 * @方法说明 【学院】按主键查
	 */
	@PostMapping("findById")
	@Operation(summary = "【学院】按主键查", description = "【学院】按主键查")
	@AutoLog("【学院】按主键查")
	public Result<Institute> findById(@RequestParam("id") Long id) {
		return Result.success(instituteService.findById(id));
	}

	/**
	 * @方法说明 【学院】个数
	 */
	@PostMapping("count")
	@Operation(summary = "【学院】个数", description = "【学院】个数")
	@AutoLog("【学院】按主键查")
	public Result<Integer> count(@RequestBody InstituteCond cond) {
		return Result.success(instituteService.count(cond));
	}
}
