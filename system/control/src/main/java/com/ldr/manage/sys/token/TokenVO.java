package com.ldr.manage.sys.token;

import com.ldr.manage.sys.user.User;
import lombok.Builder;
import lombok.Data;

/**
 * @类说明: tokenVO实体类
 * @author: LIFE
 * @date 2022/12/21 9:33
 **/
@Data
@Builder
public class TokenVO {
	private User user;// 用户信息
	private String token;// 令牌
}
