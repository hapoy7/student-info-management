package com.jljcxy.manage.sys.rolefunc;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jljcxy.common.aop.AutoLog;
import com.jljcxy.common.config.Result;
import com.jljcxy.manage.sys.func.FuncService;

/**
 * @类说明 【角色功能关联】控制器
 * @author 高振中
 * @date 2021-02-20 11:34:58
 **/
@RestController
@RequestMapping("roleFunc")
public class RoleFuncController {
	@Autowired
	private RoleFuncService roleFuncService; // 注入【角色功能关联】业务逻辑层
	@Autowired
	private FuncService funcService; // 注入【功能菜单】业务逻辑层

	@AutoLog("【角色功能关联】-查询【功能菜单】树及选中节点")
	@PostMapping("tree")
	public Result<RoleFuncVO> tree(Long roleId) {
		List<Long> ids = roleFuncService.list(roleId).stream().map(i -> i.getFuncId()).toList();
		return Result.success(RoleFuncVO.builder().ids(ids).funcs(funcService.tree(null)).build());
	}

	@AutoLog("【角色功能关联】-保存")
	@PostMapping("save")
	public Result<Void> insertBatch(@RequestBody List<RoleFunc> list, Long roleId) {
		roleFuncService.insertBatch(list, roleId);
		return Result.success();
	}
}
