package com.jljcxy.manage.sys.common;

import java.util.List;
import java.util.Map;

import com.jljcxy.manage.sys.dictitem.DictVO;
import com.jljcxy.manage.sys.func.Func;
import com.jljcxy.manage.sys.user.User;

import lombok.Builder;
import lombok.Data;

/**
 * @类说明 登录信息
 * @author 高振中
 * @date 2022-11-09 18:44:38
 **/
@Data
@Builder
public class LoginVO {
	private User user;// 用户信息
	private String token;// 令牌
	private Map<String, List<DictVO>> dicts;// 字典数据
	private List<Func> menus;// 功能权限(菜单)
	private List<Long> deptIds;// 数据权限(部门)
}
