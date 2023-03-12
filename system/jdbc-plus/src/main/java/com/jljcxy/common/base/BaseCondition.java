package com.jljcxy.common.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.util.StringUtils;
import com.jljcxy.common.base.utils.Sql;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @summary 【拼加条件】基类
 * @author 高振中
 * @date 2021-04-24 20:20:20
 */
@Setter
@Getter
public abstract class BaseCondition {
	private List<Object> paramList = new ArrayList<>();// 参数值
	private StringBuilder condition = new StringBuilder();// 条件语句

	@Accessors(chain = true)
	private String orders;// 排序子句

	private Integer size = 10;// 页大小(每页记录个数)

	private Integer page = 0;// 当前页

	/**
	 * 拼加运算符(=,>,<,<>)和参数(用于主表,非主表)
	 */
	protected final BaseCondition add(final String sql, final Object value) {
		if (StringUtils.hasLength(sql) && value != null) {
			condition.append(" ").append(sql);
			paramList.add(value);
		}
		return this;
	}

	/**
	 * 拼加IN子句
	 */
	public final BaseCondition add(final String sql, final Object[] ids) {
		if (StringUtils.hasLength(sql) && ids != null) {
			condition.append(" ").append(sql).append(toIn(ids));
			paramList.addAll(Arrays.asList(ids));
		}
		return this;
	}

	/**
	 * 拼加LIKE条件:site=1->%张三,site=2->张三%,site=3->%张三%(用于主表,非主表)
	 */
	protected final BaseCondition add(final String sql, final String value, final int site) {
		if (StringUtils.hasLength(value)) {
			condition.append(" ").append(sql);
			if (site == 1) {
				paramList.add("%" + value);
			} else if (site == 2) {
				paramList.add(value + "%");
			} else if (site == 3) {
				paramList.add("%" + value + "%");
			}
		}
		return this;
	}

	/**
	 * 不使用占位符直接拼sql(不建议使用)
	 */
	protected final BaseCondition add(final String sql) {
		if (StringUtils.hasLength(sql)) {
			condition.append(" ").append(sql);
		}
		return this;
	}

	/**
	 * 将List转为数组
	 */
	public final Object[] array() {
		return paramList.toArray();
	}

	/**
	 * 把第一个AND换成WHERE
	 */
	public final String where() {
		return and().replaceFirst("(?i)(AND)", "WHERE");
	}

	/**
	 * 取条件字符串
	 */
	public final String and() {
		condition.setLength(0);/* 清除查询条件 */
		paramList.clear();
		addCondition();
		return condition.toString();
	}

	/**
	 * 把组数拼接成(?, ?, ?)的形式条件
	 */
	public static String toIn(final Object... ids) {
		return Sql.toIn(ids);
	}

	/**
	 * 拼加条件(在具体的条件类中实现)
	 */
	protected abstract void addCondition();

	/**
	 * 拼加排序子句
	 */
	protected final String orders() {
		if (!StringUtils.hasLength(orders))
			return "";
		return " ORDER BY " + orders;
	}

	/**
	 * 拼加运算符(=,>,<,<>)和参数(主表)
	 */
	protected final BaseCondition and(final String field, final Object value) {
		return add("AND t." + field + " ?", value);
	}

	/**
	 * 拼加LIKE条件:site=1->%张三,site=2->张三%,site=3->%张三%(主表)
	 */
	protected final BaseCondition and(final String field, final String value, final int site) {
		return add("AND t." + field + " ?", value, site);
	}

	/**
	 * 拼加IN子句(主表)
	 */
	protected final BaseCondition in(final String field, final Object[] ids) {
		return add("AND t." + field + " IN", ids);
	}
}
