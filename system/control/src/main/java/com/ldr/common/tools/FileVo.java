package com.ldr.common.tools;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 【上传文件】响应信息
 * @author 高振中
 * @date 2021-03-08 20:20:20
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileVo {
	private String fileUrl;// 展示路径
	private String filePath;// 储存路径
	private String fileName;// 原始文件名
	private String fileSize;// 文件大小
}
