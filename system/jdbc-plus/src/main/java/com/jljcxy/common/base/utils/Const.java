package com.jljcxy.common.base.utils;

import java.time.format.DateTimeFormatter;
import java.util.Set;

/**
 * @summary 常量接口
 * @author gzz_gzz@163.com
 * @date 2022-06-20 23:23:23
 */
public interface Const {
	interface SQL {
		String BAS = " t";
		String SELECTB = "SELECT ";
		String DELETEB = "DELETE ";
		String INSERTB = "INSERT ";
		String INTOB = "INTO ";
		String UPDATEB = "UPDATE ";
		String REPLACEINTO = "REPLACE INTO ";
		String INSERTINTO = "INSERT INTO ";
		String BFROMB = " FROM ";
		String EQQ = "=?";
		String EQQCOMMA = "=?,";
		String BINB = " IN ";
		String BWHEREB = " WHERE ";
		String WHERET = " WHERE t.";
		String TSET = " t SET ";
		String T = "t.";
		String COMMA = ",";
		String COLON = ":";
		String NULL = "NULL";
		String QUOT = "'";
		String LEFTBRACKET = "(";
		String BLEFTBRACKET = " (";
		String RIGHTBRACKET = ")";
		String VALUES = " VALUES (";
		String RVALUES = ") VALUES (";
		String COMMAQUESTION = ",?";
		String RIGHTQUESTION = "(?";
		String LEFTQUESTIONRIGHT = "[?]";
		String RN = "\r\n";
		String SEMICOLONRN = ";\r\n";
		String COUNT1 = "COUNT(1)";
		String DREQ = "dr=1";
		boolean AUTOTIME = true;// 启用[创建人,创建时间,修改人,修改时间]自动记录
//		Long USER_ID = -1L;
		String CREATE_TIME = "CREATETIME";
		String CREATE_BY = "CREATEBY";
		String UPDATE_TIME = "UPDATETIME";
		String UPDATE_BY = "UPDATEBY";
		String DR = "dr";
		Set<String> NO_UPDATE = Set.of(CREATE_TIME, CREATE_BY);
		Set<String> NO_INSERT = Set.of(UPDATE_TIME, UPDATE_BY);
	}

	DateTimeFormatter FORMAT_ALL = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	DateTimeFormatter FORMAT_HMS = DateTimeFormatter.ofPattern("HH:mm:ss");
	DateTimeFormatter FORMAT_YMD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	char UNDERLINE = '_';
	String UNDER_LINE = "_";
	String BLANK = " ";
	String EMPTY = "";
	String MLINE = "-";
	int COUNT = 10;
	String EXCEPTION_SAVE = "保存对像时出现异常!";
	String EXCEPTION_SQL = "生成SQL时发生异常!";
	String EXCEPTION_PARAM = "解析SQL时发生异常!";

	interface Result {
		int OK = 200;
		int ERROR = 500;
		String SUCCESS = "SUCCESS";
		String FAILURE = "FAILURE";
	}
}
