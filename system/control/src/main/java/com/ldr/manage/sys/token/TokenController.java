package com.ldr.manage.sys.token;

import com.google.common.cache.Cache;
import com.ldr.common.config.Result;
import com.ldr.common.filter.JwtTools;
import com.ldr.manage.sys.common.LoginVO;
import com.ldr.manage.sys.user.User;
import com.ldr.manage.sys.user.UserCond;
import com.ldr.manage.sys.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @类说明: 获取token控制层
 * @author: LIFE
 * @date 2022/12/21 9:33
 **/
@RestController
@RequestMapping("sys")
public class TokenController {

    @Autowired
    private UserService userService; // 注入【用户】业务逻辑层
    @Autowired
    private Cache<Long, LoginVO> userCache; // 注入 缓存

    @PostMapping("token")
    public Result<LoginVO> getToken(@RequestBody TokenParam param) {
        // 根据用户的姓名查询用户
        List<User> users = userService.list(UserCond.builder().loginName(param.getName()).build());
        // 判断用户是否存在
        if (CollectionUtils.isEmpty(users))
            return Result.error(1, "用户不存在");
        // 判断密码是否正确
        User user = users.get(0);
        if (!user.getPassword().equals(DigestUtils.md5DigestAsHex(param.getPassword().getBytes())))
            return Result.error(2, "密码不正确");
        String token = JwtTools.createToken(user.getUserId() + "");// 生成token
        LoginVO vo = LoginVO.builder().user(user).token(token).build();
        userCache.put(user.getUserId(), vo);// 放入缓存
        return Result.success(vo);
    }
}
