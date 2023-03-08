package com.ldr.manage.sys.token;

import lombok.Data;

/**
 * @类说明: 获取token接口参数实体类
 * @author: LIFE
 * @date 2022/12/21 9:33
 **/
@Data
public class TokenParam {
	private String name; // 帐号
	private String password; // 密码
}
