package com.jljcxy.common.base;

import java.lang.reflect.Field;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

import com.jljcxy.common.base.utils.ReflectUtil;
import com.jljcxy.common.base.utils.Sql;

import lombok.extern.slf4j.Slf4j;

/**
 * @summary 【数据访问】JDBC基类(联表操作)
 * @author 高振中
 * @date 2022-05-22 10:00:00
 */
@Slf4j
public class BaseJDBC extends BaseTemplate {
	final protected <C extends BaseCondition> Integer count(String sql, C cond) { return count(false, sql, cond); }
	final protected <C extends BaseCondition> Integer count(boolean showSql, String sql, C cond) {
		if (showSql)
			log.info(Sql.parse(sql, cond.array()));
		return jdbcTemplate.queryForObject(sql, Integer.class, cond.array());
	}
	final protected <C extends BaseCondition> Integer count(String sql, Object... obj) {return count(false,sql,obj);}
	final protected <C extends BaseCondition> Integer count(boolean showSql, String sql, Object... obj) {
		if (showSql)
			log.info(Sql.parse(sql, obj));
		return jdbcTemplate.queryForObject(sql, Integer.class, obj);
	}
	final protected Integer update(String sql, Object... obj) { return update(false, sql, obj); }
	final protected <T> long save(boolean showSql, final T t, String sql, List<Field> fields) {
		if (showSql)
			log.info(Sql.parse(sql, t, fields));
		return save(t, sql);
	}
	final protected <T> long save(final T t, String sql) { return nameJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(t)); }
	final protected <T> T saveKey(boolean showSql, final T t, String sql, final String idName, List<Field> fields) {
		if (showSql)
			log.info(Sql.parse(sql, t));
		Long key = saveKey(t, sql, idName);
		ReflectUtil.setValue(fields, t, idName, key);
		return t;
	}
	final protected Integer update(boolean showSql, String sql, Object... obj) {
		if (showSql)
			log.info(Sql.parse(sql, obj));
		return jdbcTemplate.update(sql, obj);
	}
	final protected <T, C extends BaseCondition> List<T> list(String sql, C cond, Class<T> clazz) { return list(false, sql, cond, clazz); }
	final protected <T, C extends BaseCondition> List<T> list(boolean showSql, String sql, C cond, Class<T> clazz) {
		sql = sql + cond.orders();
		if (showSql)
			log.info(Sql.parse(sql, cond.array()));
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(clazz), cond.array());
	}
	final protected <T> List<T> list(String sql, Class<T> clazz, Object... obj) { return list(false, sql, clazz, obj); }
	final protected <T> List<T> list(boolean showSql, String sql, Class<T> clazz, Object... obj) {
		if (showSql)
			log.info(Sql.parse(sql, obj));
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(clazz), obj);
	}
	final protected <T, C extends BaseCondition> List<T> listSingle(String sql, C cond, Class<T> clazz) {
		return listSingle(false, sql, cond, clazz);
	}
	final protected <T, C extends BaseCondition> List<T> listSingle(boolean showSql, String sql, C cond, Class<T> clazz) {
		if (showSql)
			log.info(Sql.parse(sql, cond.array()));
		return jdbcTemplate.query(sql + cond.orders(), new SingleColumnRowMapper<>(clazz), cond.array());
	}
	final protected <T> List<T> listSingle(String sql, Class<T> clazz, Object... obj) {
		return listSingle(false, sql, clazz, obj);
	}
	final protected <T> List<T> listSingle(boolean showSql, String sql, Class<T> clazz, Object... obj) {
		if (showSql)
			log.info(Sql.parse(sql, obj));
		return jdbcTemplate.query(sql, new SingleColumnRowMapper<>(clazz), obj);
	}
	final protected <T> T findById(boolean showSql, String sql, Class<T> clazz, Object id) {
		if (showSql)
			log.info(Sql.parse(sql, id));
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(clazz), id);
	}
}
