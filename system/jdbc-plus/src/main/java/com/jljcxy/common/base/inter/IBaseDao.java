package com.jljcxy.common.base.inter;

import java.util.List;

import com.jljcxy.common.base.BaseCondition;
import com.jljcxy.common.base.Page;
import com.jljcxy.manage.sys.tableref.TableRef;
import com.jljcxy.manage.sys.tableref.TableRefParam;

/**
 * @summary 【数据访问】DAO基类(单表操作)
 * @author 高振中
 * @date 2022-05-22 10:00:00
 */

public interface IBaseDao<T> extends IBaseJDBC {

	/**
	 * 按条件修改记录
	 *
	 * @param t 记录对象
	 * @param c 查询条件
	 */
	<C extends BaseCondition> int update(final T t, final C c);

	/**
	 * 按条件修改记录
	 *
	 * @param showSql 显示SQL语句
	 * @param t       记录对象
	 * @param c       查询条件
	 */
	<C extends BaseCondition> int update(final Boolean showSql, final T t, final C c);

	/**
	 * 修改记录
	 *
	 * @param t 记录对象
	 */
	int update(final T t);

	/**
	 * 修改记录
	 *
	 * @param showSql 显示SQL语句
	 * @param t       记录对象
	 */
	int update(final Boolean showSql, final T t);

	/**
	 * 保存记录
	 *
	 * @param t 记录对象
	 */
	T save(final T t);

	/**
	 * 保存记录
	 *
	 * @param showSql 显示SQL语句
	 * @param t       记录对象
	 */
	T save(final Boolean showSql, final T t);

	/**
	 * 查询记录个数
	 *
	 * @param c 查询条件
	 */
	<C extends BaseCondition> int count(final C c);

	/**
	 * 查询分页列表
	 *
	 * @param showSql 显示SQL语句
	 * @param c       查询条件
	 */
	<C extends BaseCondition> int count(final Boolean showSql, final C c);

	/**
	 * 查询分页列表
	 *
	 * @param c 查询条件
	 */
	<C extends BaseCondition> Page<T> page(final C c);

	/**
	 * 查询分页列表
	 *
	 * @param showSql 显示SQL语句
	 * @param c       查询条件
	 */
	<C extends BaseCondition> Page<T> page(final Boolean showSql, final C c);

	/**
	 * 分页列表
	 *
	 * @param c         查询条件
	 * @param nestCount 使用嵌套方式查询记录个数
	 */
	<C extends BaseCondition> Page<T> page(final C c, final Boolean nestCount);

	/**
	 * 分页列表
	 *
	 * @param showSql   显示SQL语句
	 * @param c         查询条件
	 * @param nestCount 使用嵌套方式查询记录个数
	 */
	<C extends BaseCondition> Page<T> page(final Boolean showSql, final C c, final Boolean nestCount);

	/**
	 * 按主键查询单个记录
	 */
	T findById(final Object obj);

	/**
	 * 按主键查询单个记录
	 *
	 * @param showSql 显示SQL语句
	 * @param id      主键
	 */
	T findById(final Boolean showSql, final Object id);

	/**
	 * 按条件查询列表
	 *
	 * @param c 条件
	 */
	<C extends BaseCondition> List<T> list(final C c);

	/**
	 * 按条件查询列表
	 *
	 * @param showSql 显示SQL语句
	 * @param c       条件
	 */
	<C extends BaseCondition> List<T> list(Boolean showSql, final C c);

	/**
	 * 批量保存记录
	 *
	 * @param list 记录列表
	 */
	List<T> replaceBatch(final List<T> list);

	List<T> replaceBatch(final Boolean showSql, final List<T> list);

	List<T> saveBatch(final List<T> list);

	List<T> saveBatch(final Boolean showSql, final List<T> list);

	/**
	 * 批量保存记录
	 *
	 * @param showSql 显示SQL语句
	 * @param list    记录列表
	 */
	List<T> saveBatch(final Boolean showSql, final List<T> list, String type);

	/**
	 * 按主键数组删除记录
	 *
	 * @param ids 主键数组
	 */
	int delete(final Object... ids);

	/**
	 * 按主键数组删除记录
	 *
	 * @param showSql 显示SQL语句
	 * @param ids     主键数组
	 */
	int delete(final Boolean showSql, final Object... ids);

	/**
	 * 按条件删除记录
	 *
	 * @param c 查询条件
	 */
	<C extends BaseCondition> int delete(final C c);

	/**
	 * 按条件删除记录
	 *
	 * @param showSql 显示SQL语句
	 * @param c       查询条件
	 */
	<C extends BaseCondition> int delete(final Boolean showSql, final C c);

	/**
	 * @方法说明 【表引用关系】列表
	 */
	void checkReference(TableRefParam param, final Boolean showSql);

	int count(final Boolean showSql, TableRef ref, Object[] ids);

	List<Long> dataAuth(String filedName);
}
