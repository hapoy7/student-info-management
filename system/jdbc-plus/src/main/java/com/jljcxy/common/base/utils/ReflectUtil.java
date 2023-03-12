package com.jljcxy.common.base.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import com.jljcxy.common.base.annotation.Exclude;
import com.jljcxy.common.base.annotation.Id;
import com.jljcxy.common.base.annotation.Table;
import lombok.extern.slf4j.Slf4j;

/**
 * @summary 【反射工具】
 * @author 高振中
 * @date 2022-05-22 10:00:00
 **/
@Slf4j
public final class ReflectUtil {
	private ReflectUtil() {}// Cannot be constructed
	public static <T> void setValue(List<Field> fields, T t, String idName, Object idValue) {
		for (Field field : fields) {
			try {
				if (field.getName().equals(idName)) {
					field.setAccessible(true);
					field.set(t, idValue);
					break;
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				log.error(Const.EXCEPTION_SAVE, e);
				throw new RuntimeException(Const.EXCEPTION_SAVE);
			}
		}
	}

	/**
	 * 实体类中包含的字段
	 */
	public static List<Field> fields(final Class<?> clazz) {
		return Arrays.stream(clazz.getDeclaredFields()).filter(i -> i.getDeclaredAnnotationsByType(Exclude.class).length == 0).toList();
	}

	/**
	 * 实体类中表名
	 */
	public static String tableName(final Class<?> clazz) { return clazz.getDeclaredAnnotationsByType(Table.class)[0].value(); }
	/**
	 * 实体类中主键名称
	 */
	public static String idName(final List<Field> fields) {
		String idName = fields.stream().filter(i -> i.getDeclaredAnnotationsByType(Id.class).length > 0).toList().get(0).getName();
		return Tools.toLine(idName);
	}

	/**
	 * 实体类中主键的类型
	 */
	public static String idType(final List<Field> fields) {
		return fields.stream().filter(i -> i.getDeclaredAnnotationsByType(Id.class).length > 0).toList().get(0).getDeclaredAnnotation(Id.class).value();
	}
}
