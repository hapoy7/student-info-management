package com.ldr.manage.sys.request;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ldr.common.aop.AutoLog;
import com.ldr.common.config.Result;

/**
 * @类说明 【请求时长统计】控制器
 * @author 高振中
 * @date 2021-02-20 14:02:55
 **/
@RestController
@RequestMapping("request")
public class RequestController {

	@Autowired
	private RequestService requestService; // 注入【请求时长统计】业务逻辑层

	/**
	 * @方法说明 【请求时长统计】删除
	 */
	@AutoLog("【请求时长统计】删除")
	@PostMapping("delete")
	public Result<Integer> delete(@RequestBody String[] ids) {
		int count = requestService.delete(ids);
		return Result.success(count, "删除成功");
	}

	/**
	 * @方法说明 【请求时长统计】列表
	 */
	@AutoLog("【请求时长统计】列表")
	@PostMapping("list")
	public Result<List<Request>> list(@RequestBody RequestCond cond) {
		return Result.success(requestService.list(cond));
	}

}