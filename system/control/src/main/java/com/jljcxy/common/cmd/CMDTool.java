package com.jljcxy.common.cmd;

import lombok.extern.slf4j.Slf4j;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.springframework.util.StringUtils;

/**
 * @类说明 通讯指令工具类
 * @author 高振中
 * @date 2022-11-10 18:44:38
 **/
@Slf4j
public class CMDTool {

	public static void main(String[] args) {
		String msg = "01 F0 84 03 22 EA ";
		msg.replace(" ", "");
//		validateCRC(toByteArray(msg));
//		System.out.println(Integer.toHexString(10).toUpperCase());

	}

	/**
	 * 16进制表示的字符串转换为字节数组
	 */
	public static byte[] toByteArray(String hexString) {
		hexString = hexString.replaceAll(" ", "");
		int len = hexString.length();
		byte[] bytes = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			// 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个字节
			bytes[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character.digit(hexString.charAt(i + 1), 16));
		}
		return bytes;
	}

	/**
	 * 判断响应信息是否包含错误
	 */
	public static void existError(byte[] bytes) {
		// 第三个字节是8X并具大于80
		if (((bytes[2] & 0XF0) >>> 7 == 1) && (bytes[2] & 0XFF) > 0X80) {
			String msg = CMDConst.BOX_MESSAGE.get(bytes[3] & 0XFF);
			msg = StringUtils.hasText(msg) ? msg : "未知错误!!!";
			log.error("网口提示错误信息:{}", msg);
		}
	}

	/**
	 * byte[]数组转换为16进制的字符串
	 */
	public static String toHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format(CMDConst.HEX2, b));
		}
		return sb.toString();
	}

	/**
	 * 将2个字节[转换]为整型数值
	 */
	public static int convertInt(byte high, byte low) {
		return (0xff00 & (high << 8)) | (0xff & low);
	}

	/**
	 * CRC验证
	 */
	public static boolean validateCRC(byte[] bytes) {
		int len = bytes.length;
		byte[] crcByte = Arrays.copyOfRange(bytes, len - 2, len);
		byte[] dataByte = Arrays.copyOfRange(bytes, 0, len - 2);
		String srcCRC = CMDTool.toHexString(crcByte).replaceAll(" ", "");
		String desCRC = String.format(CMDConst.HEX2_NB, createCRC(dataByte));
		boolean pass = srcCRC.equalsIgnoreCase(desCRC);
		if (!pass)
			log.error("CRC验证未通过,{}", CMDTool.toHexString(bytes));
		return pass;
	}

	/**
	 * 计算CRC16校验码,参数:命令字符串
	 */
	public static int createCRC(String command) {
		return createCRC(toByteArray(command));
	}

	/**
	 * 计算CRC16校验码,参数:字节数组
	 */
	private static int createCRC(byte[] bytes) {
		int CRC = 0X0000FFFF;
		int POLYNOMIAL = 0X0000A001;
		for (int i = 0; i < bytes.length; i++) {
			CRC ^= ((int) bytes[i] & 0x000000ff);
			for (int j = 0; j < 8; j++) {
				if ((CRC & 0x00000001) != 0) {
					CRC >>= 1;
					CRC ^= POLYNOMIAL;
				} else {
					CRC >>= 1;
				}
			}
		}
		return (CRC >> 8) | (CRC << 8 & 0x0000ffff);
	}

	public static void wait(int second) {
		try {
			TimeUnit.MILLISECONDS.sleep(second);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
