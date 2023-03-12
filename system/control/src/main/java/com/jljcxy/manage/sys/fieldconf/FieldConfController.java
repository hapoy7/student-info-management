package com.jljcxy.manage.sys.fieldconf;

import com.jljcxy.common.base.Page;
import com.jljcxy.common.config.Result;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @类说明 【字段配置】控制器
 * @author 高振中
 * @date 2023-02-02 15:51:45
 **/
@RestController
@RequestMapping("fieldconf")
public class FieldConfController {

	@Autowired
	private FieldConfService fieldConfService; // 注入【字段配置】业务逻辑层
	@Autowired
	private HttpSession session;

	/**
	 * @方法说明 【字段配置】新增
	 */
	@PostMapping("save")
	public Result<Long> save(@RequestBody FieldConfVo vo) {
		Long userId = (Long) session.getAttribute("userId");
		vo.setUserId(userId);
		return Result.success(fieldConfService.save(vo));
	}
	/**
	 * @方法说明 【字段配置】修改
	 */
	@PostMapping("update")
	public Result<Integer> update(@RequestBody @Validated FieldConf fieldConf) {
		//if (fieldConfService.count(FieldConfCond.builder().nameEq(fieldConf.getName()).idNe(fieldConf.getId()).build()) > 0)
			//return Result.error(1, "【字段配置名称】不能重复！");
		return Result.success(fieldConfService.update(fieldConf));
	}
	/**
	 * @方法说明 【字段配置】分页列表
	 */
	@PostMapping("page")
	public Result<Page<FieldConf>> page(@RequestBody FieldConfCond cond) {
		return Result.success(fieldConfService.page(cond));
	}

	/**
	 * @方法说明 【字段配置】列表
	 */
	@PostMapping("list")
	public Result<FieldConfVo> list(@RequestBody FieldConfCond cond) {
		Long userId = (Long) session.getAttribute("userId");
		cond.setUserId(userId);
		return Result.success(fieldConfService.list(cond));
	}

}
