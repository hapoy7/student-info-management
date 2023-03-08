package com.ldr.common.base.inter;

import java.lang.reflect.Field;
import java.util.List;

import com.ldr.common.base.BaseCondition;

/**
 * @summary 【数据访问】JDBC基类(联表操作)
 * @author 高振中
 * @date 2022-05-22 10:00:00
 */

public interface IBaseJDBC extends IBaseTemplate {
	<C extends BaseCondition> Integer count(String sql, C cond);

	<C extends BaseCondition> Integer count(boolean showSql, String sql, C cond);

	<C extends BaseCondition> Integer count(String sql, Object... obj);

	<C extends BaseCondition> Integer count(boolean showSql, String sql, Object... obj);

	Integer update(String sql, Object... obj);

	<T> long save(boolean showSql, final T t, String sql, List<Field> fields);

	<T> long save(final T t, String sql);

	<T> T saveKey(boolean showSql, final T t, String sql, final String idName, List<Field> fields);

	Integer update(boolean showSql, String sql, Object... obj);

	<T, C extends BaseCondition> List<T> list(String sql, C cond, Class<T> clazz);

	<T, C extends BaseCondition> List<T> list(boolean showSql, String sql, C cond, Class<T> clazz);

	<T> List<T> list(String sql, Class<T> clazz, Object... obj);

	<T> List<T> list(boolean showSql, String sql, Class<T> clazz, Object... obj);

	<T, C extends BaseCondition> List<T> listSingle(String sql, C cond, Class<T> clazz);

	<T, C extends BaseCondition> List<T> listSingle(boolean showSql, String sql, C cond, Class<T> clazz);

	<T> List<T> listSingle(String sql, Class<T> clazz, Object... obj);

	<T> List<T> listSingle(boolean showSql, String sql, Class<T> clazz, Object... obj);

	<T> T findById(boolean showSql, String sql, Class<T> clazz, Object id);
}