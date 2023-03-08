package com.ldr.common.base.utils;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.ldr.common.base.BaseCondition;
import com.ldr.common.base.annotation.Id;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @summary 【字段处理工具】
 * @author 高振中
 * @date 2022-05-22 10:00:00
 **/
@Slf4j
public class FieldUtil {
	private static Long userId(HttpServletRequest request) {
		return (Long) request.getAttribute("userId");
	}
	@Setter
	@Getter
	public static class Update {
		private StringBuilder SQL = new StringBuilder();
		private Object[] obj;
		public String getSql() { return SQL.toString(); }
	}

	@Setter
	@Getter
	public static class Insert<T> {
		private StringBuilder SQL = new StringBuilder();
		private T t;
		public String getSql() { return SQL.toString(); }
	}
	public static <T> Insert<T> snowInsert(List<Field> fields, T t, Object id, String idName,HttpServletRequest request) {
		fields = excludeFields(fields, Const.SQL.NO_INSERT);
		Insert<T> insert = new Insert<>();
		insert.SQL.append(Const.SQL.BLEFTBRACKET).append(fields.stream().map(i -> Tools.toLine(i.getName())).collect(Collectors.joining(Const.SQL.COMMA))).append(Const.SQL.RIGHTBRACKET);
		insert.SQL.append(Const.SQL.VALUES).append(fields.stream().map(i -> Const.SQL.COLON + i.getName()).collect(Collectors.joining(Const.SQL.COMMA))).append(Const.SQL.RIGHTBRACKET);
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				if (Const.SQL.AUTOTIME && field.getName().equalsIgnoreCase(Const.SQL.CREATE_TIME)) field.set(t, LocalDateTime.now());
				if (Const.SQL.AUTOTIME && field.getName().equalsIgnoreCase(Const.SQL.CREATE_BY)) field.set(t, userId(request));
				if (Tools.toLine(field.getName()).equals(idName)) field.set(t, id);
				if (field.getName().equalsIgnoreCase(Const.SQL.DR)) field.set(t, (byte)0);
			}
		} catch (Exception e) {
			log.error(Const.EXCEPTION_SAVE, e);
			throw new RuntimeException(Const.EXCEPTION_SAVE);
		}
		insert.t = t;
		return insert;
	}

	public static <T> Insert<T> autoInsert(List<Field> fields, T t,HttpServletRequest request) {
		fields = excludeFields(fields, Const.SQL.NO_INSERT);
		Insert<T> insert = new Insert<>();
		insert.SQL.append(Const.SQL.BLEFTBRACKET).append(fields.stream().map(i -> Tools.toLine(i.getName())).collect(Collectors.joining(Const.SQL.COMMA))).append(Const.SQL.RIGHTBRACKET);
		insert.SQL.append(Const.SQL.VALUES).append(fields.stream().map(i -> Const.SQL.COMMA + i.getName()).collect(Collectors.joining(Const.SQL.COMMA))).append(Const.SQL.RIGHTBRACKET);
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				if (Const.SQL.AUTOTIME && field.getName().equalsIgnoreCase(Const.SQL.CREATE_TIME)) field.set(t, LocalDateTime.now());
				if (Const.SQL.AUTOTIME && field.getName().equalsIgnoreCase(Const.SQL.CREATE_BY)) field.set(t, userId(request));
				if (field.getName().equalsIgnoreCase(Const.SQL.DR)) field.set(t, (byte)0);
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			log.error(Const.EXCEPTION_SAVE, e);
			throw new RuntimeException(Const.EXCEPTION_SAVE);
		}
		insert.t = t;
		return insert;
	}

	public static <T, C extends BaseCondition> Update forUpdate(List<Field> fields, T t, final C c,HttpServletRequest request) {
		fields = excludeFields(fields, Const.SQL.NO_UPDATE);
		Update update = new Update();
		List<Object> param = new ArrayList<>();
		try {
			for (Field field : fields) {
				String fieldName = Tools.toLine(field.getName());
				field.setAccessible(true);
				if (Const.SQL.AUTOTIME && field.getName().equalsIgnoreCase(Const.SQL.UPDATE_TIME)) {
					param.add(LocalDateTime.now());
					update.SQL.append(Const.SQL.T).append(fieldName).append(Const.SQL.EQQCOMMA);
				} else if (Const.SQL.AUTOTIME && field.getName().equalsIgnoreCase(Const.SQL.UPDATE_BY)) {
					param.add(userId(request));
					update.SQL.append(Const.SQL.T).append(fieldName).append(Const.SQL.EQQCOMMA);
				} else if (field.get(t) != null) {
					param.add(field.get(t));
					update.SQL.append(Const.SQL.T).append(fieldName).append(Const.SQL.EQQCOMMA);
				}
			}
			update.SQL.deleteCharAt(update.SQL.length() - 1);
			update.SQL.append(c.where());
			param.addAll(c.getParamList());
			update.obj = param.toArray();
			return update;
		} catch (IllegalArgumentException | IllegalAccessException e) {
			log.error(Const.EXCEPTION_SAVE, e);
			throw new RuntimeException(Const.EXCEPTION_SAVE);
		}
	}

	private static List<Field> excludeFields(List<Field> fields, Set<String> set) {
		return Const.SQL.AUTOTIME ? fields.stream().filter(f -> !set.contains(f.getName().toUpperCase())).toList() : fields;

	}

	public static <T> Update forUpdate(List<Field> fields, T t,HttpServletRequest request) {
		fields = excludeFields(fields, Const.SQL.NO_UPDATE);
		List<Field> idFields = fields.stream().filter(i -> i.getDeclaredAnnotationsByType(Id.class).length > 0).toList();
		List<Field> noIdFields = fields.stream().filter(i -> i.getDeclaredAnnotationsByType(Id.class).length == 0).toList();
		Update update = new Update();
		List<Object> param = new ArrayList<>();
		try {
			for (Field field : noIdFields) {
				field.setAccessible(true);
				String fieldName = Tools.toLine(field.getName());
				if (Const.SQL.AUTOTIME && field.getName().equalsIgnoreCase(Const.SQL.UPDATE_TIME)) {
					param.add(LocalDateTime.now());
					update.SQL.append(Const.SQL.T).append(fieldName).append(Const.SQL.EQQCOMMA);
				} else if (Const.SQL.AUTOTIME && field.getName().equalsIgnoreCase(Const.SQL.UPDATE_BY)) {
					param.add(userId(request));
					update.SQL.append(Const.SQL.T).append(fieldName).append(Const.SQL.EQQCOMMA);
				} else if (field.get(t) != null) {
					param.add(field.get(t));
					update.SQL.append(Const.SQL.T).append(fieldName).append(Const.SQL.EQQCOMMA);
				}
			}
			update.SQL.deleteCharAt(update.SQL.length() - 1);
			update.SQL.append(Const.SQL.BWHEREB);
			Field field = idFields.get(0);
			field.setAccessible(true);
			update.SQL.append(Tools.toLine(field.getName())).append(Const.SQL.EQQ);
			param.add(field.get(t));
			update.obj = param.toArray();
			return update;
		} catch (IllegalArgumentException | IllegalAccessException e) {
			log.error(Const.EXCEPTION_SAVE, e);
			throw new RuntimeException(Const.EXCEPTION_SAVE);
		}
	}

}
