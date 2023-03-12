package com.jljcxy.manage.sys.roleuser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jljcxy.manage.sys.roleuser.vo.Auth;
import com.jljcxy.manage.sys.user.User;
import com.jljcxy.manage.sys.user.UserCond;
import com.jljcxy.manage.sys.user.UserDao;

/**
 * @类说明 【角色用户关联】业务逻辑层
 * @author 高振中
 * @date 2021-02-20 11:34:58
 **/
@Service
public class RoleUserService {

	@Autowired
	private RoleUserDao roleUserDao; // 注入【角色用户关联】数据访问层
	@Autowired
	private UserDao userDao; // 注入【用户】数据访问层

	/**
	 * @方法说明 【用户列表与授权信息】
	 */
	public Auth auth(RoleUserCond cond) {
		List<RoleUser> users = roleUserDao.list(RoleUserCond.builder().roleId(cond.getRoleId()).build());
		List<User> list = userDao.list(UserCond.builder().deptName(cond.getDeptName()).name(cond.getName()).build());
		return Auth.builder().userList(list).ids(users.stream().map(i -> i.getUserId()).toList()).build();
	}

	/**
	 * @方法说明 【角色用户关联】批量插入
	 */
	public void insertBatch(List<Long> userIds, Long roleId) {
		roleUserDao.delete(RoleUserCond.builder().roleId(roleId).build());
		roleUserDao.saveBatch(userIds.stream().map(u -> new RoleUser(u, roleId)).toList());
	}

	/**
	 * @方法说明 【用户角色类型】列表
	 */
	public List<Byte> roleType(Long userId) {
		return roleUserDao.roleType(userId);
	}
}
