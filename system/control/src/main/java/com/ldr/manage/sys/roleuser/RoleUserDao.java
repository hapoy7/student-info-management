package com.ldr.manage.sys.roleuser;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ldr.common.base.BaseDao;

/**
 * @类说明 【角色用户关联】数据访问层
 * @author 高振中
 * @date 2021-02-20 11:34:58
 **/
@Repository
public class RoleUserDao extends BaseDao<RoleUser> {
	/**
	 * @方法说明 【用户角色类型】列表
	 */
	public List<Byte> roleType(Long userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT r.type");
		sql.append(" FROM sys_role_user t JOIN sys_role r ON t.role_id = r.role_id");
		sql.append(" WHERE t.user_id=?");
		return listSingle(sql.toString(), Byte.class, userId);
	}
}
