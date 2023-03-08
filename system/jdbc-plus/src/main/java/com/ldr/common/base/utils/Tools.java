package com.ldr.common.base.utils;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @summary 系统工具
 * @summary
 * @author 高振中
 * @date 2022-06-20 23:23:23
 **/
public class Tools {
	/**
	 * 驼峰转下划线
	 */
	public static String toLine(String param) {
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append(Const.UNDERLINE);
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 下划线转大写驼峰
	 */
	public static String toUpperCamel(String param) {
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (c == Const.UNDERLINE) {
				if (++i < len) {
					sb.append(Character.toUpperCase(param.charAt(i)));
				}
			} else {
				sb.append(c);
			}
		}
		char[] chars = sb.toString().toCharArray();
		chars[0] -= 32;
		return new String(chars);
	}

	/**
	 * 下划线转小写驼峰
	 */
	public static String toLowerCamel(String param) {
		StringBuilder sb = new StringBuilder(param);
		Matcher mc = Pattern.compile(Const.UNDER_LINE).matcher(param);
		int i = 0;
		while (mc.find()) {
			int position = mc.end() - (i++);
//		 String.valueOf(Character.toUpperCase(sb.charAt(position)));
			sb.replace(position - 1, position + 1, sb.substring(position, position + 1).toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 产生一个32位的UUID
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll(Const.MLINE, Const.EMPTY);
	}
}
