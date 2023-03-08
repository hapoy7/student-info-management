package com.ldr.manage.sys.role;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.ldr.common.base.Page;
import com.ldr.manage.sys.roledept.RoleDeptCond;
import com.ldr.manage.sys.roledept.RoleDeptDao;
import com.ldr.manage.sys.rolefunc.RoleFuncCond;
import com.ldr.manage.sys.rolefunc.RoleFuncDao;
import com.ldr.manage.sys.roleuser.RoleUserCond;
import com.ldr.manage.sys.roleuser.RoleUserDao;

/**
 * @类说明 【角色】业务逻辑层
 * @author 高振中
 * @date 2021-02-20 11:34:58
 **/
@Service
public class RoleService {
	@Autowired
	private RoleDao roleDao; // 注入【角色】数据访问层
	@Autowired
	private RoleFuncDao roleFuncDao; // 注入【角色功能关联】数据访问层
	@Autowired
	private RoleUserDao roleUserDao; // 注入【角色用户关联】数据访问层
	@Autowired
	private RoleDeptDao roleDeptDao; // 注入【角色部门关联】数据访问层

	/**
	 * @方法说明 【角色】新增
	 */
	public Long save(Role role) {
		return roleDao.save(role).getRoleId();
	}

	/**
	 * @方法说明 【角色】删除
	 */
	@Transactional
	public int delete(Object[] ids) {
		// 删除授权信息
		roleDeptDao.delete(RoleDeptCond.builder().roleIds(ids).build());
		roleFuncDao.delete(RoleFuncCond.builder().roleIds(ids).build());
		roleUserDao.delete(RoleUserCond.builder().roleIds(ids).build());
		return roleDao.delete(ids);
	}

	/**
	 * @方法说明 【角色】更新
	 */
	public int update(Role role) {
		return roleDao.update(role);
	}

	/**
	 * @方法说明 【角色】分页列表
	 */
	public Page<Role> page(RoleCond cond) {
		return roleDao.page(cond);
	}

	/**
	 * @方法说明 【角色】个数
	 */
	public int count(RoleCond cond) {
		return roleDao.count(cond);
	}

	/**
	 * @方法说明 【角色】导入
	 */
	public void importExcel(MultipartFile file){
		roleDao.saveBatch(RoleExcel.list(file));
	}

	/**
	 * @方法说明 【角色】导出
	 */
	public HSSFWorkbook export(RoleCond cond) {
		return RoleExcel.bookFile(roleDao.list(cond));
	}
}