package com.ldr.common.base;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.ldr.common.base.utils.Sql;

import lombok.extern.slf4j.Slf4j;

/**
 * @summary 【数据访问】JDBC模板类
 * @author 高振中
 * @date 2022-05-22 10:00:00
 */
@Slf4j
public class BaseTemplate {

	@Autowired
	protected JdbcTemplate jdbcTemplate;// 注入jdbc模板(占位符)
	@Autowired
	protected NamedParameterJdbcTemplate nameJdbcTemplate;// 注入jdbc模板(命名参数)

	final protected <T, C extends BaseCondition> Page<T> page(final String sql, C cond, final Class<T> clazz) { return page(false, sql, cond, clazz, false); }

	final protected <T, C extends BaseCondition> Page<T> page(final Boolean showSql, final String sql, C cond, final Class<T> clazz) { return page(showSql, sql, cond, clazz, false); }

	final protected <T, C extends BaseCondition> Page<T> page(final String sql, C cond, final Class<T> clazz, final Boolean nestCount) { return page(false, sql, cond, clazz, nestCount); }

	/**
	 * 分页查询
	 */
	final protected <T, C extends BaseCondition> Page<T> page(boolean showSql, final String sql, C cond, final Class<T> clazz, final Boolean nestCount) {
		String countSql = nestCount ? "SELECT COUNT(1) FROM (" + sql + ") t" : sql.replaceAll("(?i)(SELECT)(.*)(?i)(FROM)", "$1 COUNT(1) $3");
		if (showSql)
			log.info(Sql.parse(countSql, cond.array()));
		int rowCount = jdbcTemplate.queryForObject(countSql, Integer.class, cond.array());
		int size = cond.getSize();
		int page = cond.getPage();
		StringBuilder listSql = new StringBuilder(sql).append(cond.orders()).append(" LIMIT ").append((page - 1) * size).append(",").append(size);
		if (showSql)
			log.info(Sql.parse(listSql.toString(), cond.array()));
		List<T> dataList = jdbcTemplate.query(listSql.toString(), new BeanPropertyRowMapper<>(clazz), cond.array());
		return new Page<>(dataList,size,rowCount, page);
	}

	/**
	 * 批操作
	 */
	final protected <T> int[] batchOperate(final List<T> list, final String sql) {
		return nameJdbcTemplate.batchUpdate(sql, list.stream().map(BeanPropertySqlParameterSource::new).toList().toArray(new BeanPropertySqlParameterSource[] {}));
	}

	/**
	 * 保存数据返回主键
	 */
	final protected <T> long saveKey(final T t, String sql, final String idName) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		nameJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(t), keyHolder, new String[] { idName });
		return Objects.requireNonNull(keyHolder.getKey()).longValue();
	}

}