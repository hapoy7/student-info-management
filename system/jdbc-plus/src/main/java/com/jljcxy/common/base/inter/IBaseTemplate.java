package com.jljcxy.common.base.inter;

import java.util.List;

import com.jljcxy.common.base.BaseCondition;
import com.jljcxy.common.base.Page;

/**
 * @summary 【数据访问】JDBC模板类
 * @author 高振中
 * @date 2022-05-22 10:00:00
 */

public interface IBaseTemplate {

	<T, C extends BaseCondition> Page<T> page(final String sql, C cond, final Class<T> clazz);

	<T, C extends BaseCondition> Page<T> page(final Boolean showSql, final String sql, C cond, final Class<T> clazz);

	<T, C extends BaseCondition> Page<T> page(final String sql, C cond, final Class<T> clazz, final Boolean nestCount);

	/**
	 * 分页查询
	 */
	<T, C extends BaseCondition> Page<T> page(boolean showSql, final String sql, C cond, final Class<T> clazz, final Boolean nestCount);

	/**
	 * 批操作
	 */
	<T> int[] batchOperate(final List<T> list, final String sql);

	/**
	 * 保存数据返回主键
	 */
	<T> long saveKey(final T t, String sql, final String idName);

}
