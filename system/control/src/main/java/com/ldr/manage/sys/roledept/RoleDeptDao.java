package com.ldr.manage.sys.roledept;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ldr.common.base.BaseDao;

/**
 * @类说明 【角色部门关联】数据访问层
 * @author 高振中
 * @date 2021-02-20 11:34:58
 **/
@Repository
public class RoleDeptDao extends BaseDao<RoleDept> {
 
	/**
	 * @方法说明 查询用户权限中的部门ID集合
	 */
	public List<Long> authDeptIds(Long userId) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT rd.dept_id FROM sys_role_user ru ");
		sb.append("JOIN sys_role_dept rd ON ru.role_id = rd.role_id ");
		sb.append("WHERE ru.user_id = ? ");
		return listSingle(sb.toString(), Long.class, userId);
	}
}