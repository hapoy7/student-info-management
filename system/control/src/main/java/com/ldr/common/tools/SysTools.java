package com.ldr.common.tools;

import java.io.ByteArrayOutputStream;
import java.util.regex.Pattern;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import lombok.SneakyThrows;

/**
 * 系统工具
 * 
 * @author 高振中
 * @date 2021-03-10 20:20:20
 */
public class SysTools {
	/**
	 * 从字符中提取数字
	 */
	public static Integer getNum(String string) {
		String str = Pattern.compile("[^0-9]").matcher(string).replaceAll("");// 把0~9以外的字符串全替换掉
		return Integer.valueOf(str);
	}

	@SneakyThrows
	public static ResponseEntity<byte[]> write(HSSFWorkbook workBook) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		workBook.write(bos);
		byte[] bytes = bos.toByteArray();
		bos.close();
		workBook.close();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		header.setContentDispositionFormData("attachment", "filename=workBook.xls");
		return new ResponseEntity<>(bytes, header, HttpStatus.OK);
	}
}