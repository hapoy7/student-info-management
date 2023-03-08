package com.ldr.common.base.utils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import com.ldr.common.base.BaseCondition;
import com.ldr.common.tools.DateUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @summary 【SQL处理工具】
 * @author 高振中
 * @date 2022-05-22 10:00:00
 **/
@Slf4j
public class Sql {

	public static Sql.Builder builder() { return new Sql.Builder(); }
	public static class Builder {
		private final StringBuilder SQL = new StringBuilder();
		public String sql() { return SQL.toString(); }
		public Builder select() { SQL.append(Const.SQL.SELECTB); return this; }
		public Builder count() { SQL.append(Const.SQL.COUNT1); return this; }
		public Builder as() { SQL.append(Const.SQL.BAS); return this; }
		public Builder delete() { SQL.append(Const.SQL.DELETEB); return this; }
		public Builder insert() { SQL.append(Const.SQL.INSERTB); return this; }
		public Builder into() { SQL.append(Const.SQL.INTOB); return this; }
		public Builder update() { SQL.append(Const.SQL.UPDATEB); return this; }
		public Builder from() { SQL.append(Const.SQL.BFROMB); return this; }
		public Builder id(String idName) { SQL.append(idName); SQL.append(Const.SQL.EQQ); return this; }
		public Builder idIn(String idName, Object... ids) { SQL.append(idName); SQL.append(Const.SQL.BINB); SQL.append(toIn(ids)); return this; }
		public Builder where() { SQL.append(Const.SQL.BWHEREB); return this; }
		public <C extends BaseCondition> Builder where(final C c) { SQL.append(c.where()); return this; }
		public Builder whereT() { SQL.append(Const.SQL.WHERET); return this; }
		public Builder set(String fields) { SQL.append(Const.SQL.TSET); SQL.append(fields); return this; }
		public Builder values(String sql) { SQL.append(sql); return this; }
		public Builder table(String table) { SQL.append(table); return this; }
		public Builder fields(List<Field> fields) { 
			SQL.append(fields.stream().map(i -> Const.SQL.T + Tools.toLine(i.getName())).collect(Collectors.joining(Const.SQL.COMMA))); 
			return this; 
		}
	}
	/**
	 * 把组数拼接成(?,?,?)的形式条件
	 */
	public static String toIn(final Object... ids) { return Const.SQL.RIGHTQUESTION + Const.SQL.COMMAQUESTION.repeat(ids.length - 1) + Const.SQL.RIGHTBRACKET; }
	/**
	 * 解析带参数的SQL语句
	 */
	public static String parse(String sql, final Object... object) {
		for (Object obj : object) { sql = sql.replaceFirst(Const.SQL.LEFTQUESTIONRIGHT, parseParam(obj)); }
		return sql;
	}
	/**
	 * 填充参数到SQL语句
	 */
	public static <T> String parse(String sql, final T t,List<Field> fields) {
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				Object value = field.get(t);
				sql = sql.replaceFirst(Const.SQL.COLON + field.getName(), parseParam(value));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				log.error(Const.EXCEPTION_SQL, e);
				throw new RuntimeException(Const.EXCEPTION_SQL);
			}
		}
		return sql;
	}

	/**
	 * 按类型解析参数
	 */
	public static String parseParam(Object obj) {
		if (obj == null) return Const.SQL.NULL;
		if (obj instanceof LocalDateTime) return Const.SQL.QUOT + DateUtils.formatAll((LocalDateTime) obj) + Const.SQL.QUOT;
		if (obj instanceof LocalDate) return Const.SQL.QUOT + DateUtils.formatYmd((LocalDate) obj) + Const.SQL.QUOT;
		if (obj instanceof LocalTime) return Const.SQL.QUOT + DateUtils.formatHms((LocalTime) obj) + Const.SQL.QUOT;
		if (obj instanceof String) return Const.SQL.QUOT + obj + Const.SQL.QUOT;
		return obj.toString();
	}

	/**
	 * 解析带参数的SQL语句
	 */
	public static <T> String sqlBatch(String sql, List<T> list,List<Field> fields) {
		StringBuilder sb = new StringBuilder(Const.SQL.RN);
		for (T t : list) {
			String row = sql;
			for (Field field : fields) {
				try {
					field.setAccessible(true);
					Object value = field.get(t);
					row = row.replaceFirst(Const.SQL.COLON + field.getName(), parseParam(value));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					log.error(Const.EXCEPTION_PARAM, e);
					throw new RuntimeException(Const.EXCEPTION_PARAM);
				}
			}
			sb.append(row).append(Const.SQL.SEMICOLONRN);
		}
		return sb.toString();
	}
}
