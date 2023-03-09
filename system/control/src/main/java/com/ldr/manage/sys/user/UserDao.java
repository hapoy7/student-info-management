package com.ldr.manage.sys.user;

import com.ldr.common.base.BaseDao;
import com.ldr.common.base.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @类说明 【用户】数据访问层
 * @author 高振中
 * @date 2020-11-17 01:42:03
 **/
@Repository
public class UserDao extends BaseDao<User> {

	/**
	 * @方法说明 【用户】分页列表
	 */
	public Page<User> page(UserCond cond) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT t.user_id,t.dept_id,t.name,t.password,t.login_name,t.avatar,t.phone,t.gender,t.status,t.code,");
		sql.append("t.number,t.card_no,t.group_name,t.fingerprint_count,t.face_info,t.type,t.start_time,t.end_time,");
		sql.append("t.create_time,t.update_time,t.remark,t.dr ");
		sql.append("FROM sys_user t ");
		sql.append("WHERE t.dr=0");
		sql.append(cond.and());
		return page(sql.toString(), cond, User.class);
	}

	/**
	 * @方法说明 【用户】列表
	 */
	public List<User> list(UserCond cond) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT t.user_id,t.dept_id,t.name,t.password,t.login_name,t.avatar,t.phone,t.gender,t.status,t.code,");
		sql.append("t.create_time,t.update_time,t.remark,t.dr ");
		sql.append("FROM sys_user t  ");
		sql.append("WHERE t.dr=0");
		sql.append(cond.and());
		return list(sql.toString(), cond, User.class);
	}

	/**
	 * @方法说明 【用户】查询最大编码
	 */
	public Integer maxCode() {
		String sql = "SELECT max(t.code) FROM sys_user t";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	/**
	 * @方法说明 【用户】更新最大编码
	 */
	public Integer updateCode(Long userId, int code) {
		String sql = "UPDATE sys_user SET code=? WHERE user_id=?";
		return jdbcTemplate.update(sql, code, userId);
	}

	/**
	 * @方法说明 【用户】批量插入
	 */
	public int[] insertBatch(List<User> list) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO sys_user (user_id,dept_id,name,password,login_name,avatar,phone,gender,status,number,");
		sql.append("card_no,group_name,fingerprint_count,face_info,type,start_time,end_time,remark,create_time,create_by,");
		sql.append("update_time,update_by,dr,code)");
		sql.append(" VALUES (:userId,:deptId,:name,:password,:loginName,:avatar,:phone,:gender,:status,:number,");
		sql.append(":cardNo,:groupName,:fingerprintCount,:faceInfo,:type,:startTime,:endTime,:remark,:createTime,:createBy,");
		sql.append(":updateTime,:updateBy,:dr,:code)");
		return batchOperate(list, sql.toString());
	}
}
