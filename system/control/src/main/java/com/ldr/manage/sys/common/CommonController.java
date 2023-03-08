package com.ldr.manage.sys.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ldr.common.config.Result;
import com.ldr.common.tools.FileTools;
import com.ldr.common.tools.FileVo;

/**
 * 【系统共用】控制器
 * @author 高振中
 * @date 2021-02-20 14:02:55
 **/
@RestController
public class CommonController {
	private static final String filePath = "sys/";// 当前功能使用这个目录，其它功能可以换成其它目录
	@Autowired
	private FileTools fileTools;// 文件工具

	/**
	 * 解决swagger请求这个地址时前端报错问题
	 **/
	@GetMapping({ "csrf" })
	public Result<?> list() {
		return Result.success();
	}

	@PostMapping(value = "sys/saveFile", headers = "content-type=multipart/form-data")
	public Result<FileVo> saveFile(@RequestPart("file") MultipartFile file) {
		return Result.success(fileTools.saveFile(file, filePath));
	}

	@PostMapping(value = "sys/deleteFile")
	public Result<Boolean> deleteFile(String path) {
		return Result.success(fileTools.deleteFile(path));
	}

}