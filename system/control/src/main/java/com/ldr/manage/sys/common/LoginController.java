package com.ldr.manage.sys.common;

import com.google.common.cache.Cache;
import com.ldr.common.aop.AutoLog;
import com.ldr.common.config.Result;
import com.ldr.common.filter.JwtTools;
import com.ldr.manage.sys.dictitem.DictItemService;
import com.ldr.manage.sys.func.FuncService;
import com.ldr.manage.sys.roledept.RoleDeptService;
import com.ldr.manage.sys.rolefunc.RoleFuncService;
import com.ldr.manage.sys.roleuser.RoleUserService;
import com.ldr.manage.sys.user.User;
import com.ldr.manage.sys.user.UserCond;
import com.ldr.manage.sys.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @类说明 【用户登录】控制器
 * @author 高振中
 * @date 2020-11-17 01:42:03
 **/
@RestController
@RequestMapping("sys")
public class LoginController {

	@Autowired
	private UserService userService; // 注入【用户】业务逻辑层
	@Autowired
	private DictItemService dictItemService; // 注入【字典项】业务逻辑层
	@Autowired
	private Cache<Long, LoginVO> userCache;// 注入用户登录信息缓存
	@Autowired
	private RoleFuncService roleFuncService; // 注入【角色功能关联】业务逻辑层
	@Autowired
	private FuncService funcService; // 注入【功能菜单】业务逻辑层
	@Autowired
	private RoleDeptService roleDeptService; // 注入【角色部门关联】业务逻辑层
	@Autowired
	private RoleUserService roleUserService; // 注入【角色用户关联】业务逻辑层

	@AutoLog("【登录】")
	@PostMapping("login")
	public Result<LoginVO> login(@RequestBody LoginParam param) {
		List<User> users = userService.list(UserCond.builder().loginName(param.getName()).build());
		if (CollectionUtils.isEmpty(users))
			return Result.error(1, "用户不存在");
		User user = users.get(0);
		if (!user.getPassword().equals(DigestUtils.md5DigestAsHex(param.getPassword().getBytes())))
			return Result.error(2, "密码不正确");
		Long userId = user.getUserId();
		List<Byte> types = roleUserService.roleType(userId);
		if (CollectionUtils.isEmpty(types))
			return Result.error(4, "用户没有对应角色");
		if (types.size() > 1)
			return Result.error(5, "用户关联的多个角色,请调整");
		user.setRoleType(types.get(0));
		Object[] funcIds = userId.equals(10000L) ? null : roleFuncService.funcIds(userId).toArray();
//		List<Long> deptIds = roleDeptService.authDeptIds(userId);
		if (funcIds != null && funcIds.length == 0)
			return Result.error(3, "当前用户没有菜单授权");
		String token = JwtTools.createToken(user.getUserId() + "");// 生成token
		LoginVO vo = LoginVO.builder().user(user).token(token).dicts(dictItemService.dicts()).menus(funcService.tree(funcIds)).build();
		userCache.put(user.getUserId(), vo);// 放入缓存
		return Result.success(vo);
	}
}
