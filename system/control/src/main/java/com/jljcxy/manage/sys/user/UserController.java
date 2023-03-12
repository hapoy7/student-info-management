package com.jljcxy.manage.sys.user;

import com.jljcxy.common.aop.AutoLog;
import com.jljcxy.common.base.Page;
import com.jljcxy.common.config.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 高振中
 * @类说明 【用户】控制器
 * @date 2020-11-17 01:42:03
 **/
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService; // 注入【用户】业务逻辑层

    /**
     * @方法说明 【用户】新增
     */

    @PostMapping("save")
    @AutoLog("【用户】新增")
    public Result<Long> save(@RequestBody @Validated User user) {
        if (userService.count(UserCond.builder().loginName(user.getLoginName()).build()) > 0)
            return Result.error(1, "【登录名】不能重复！");
        return Result.success(userService.save(user));
    }

    /**
     * @方法说明 【用户】删除
     */
    @AutoLog("【用户】删除")
    @PostMapping("delete")
    public Result<Integer> delete(@RequestBody Long[] ids) {
        return Result.success(userService.delete(ids));
    }

    /**
     * @方法说明 【用户】修改
     */
    @AutoLog("【用户】修改")
    @PostMapping("update")
    public Result<Integer> update(@RequestBody @Validated User user) {
        if (userService.count(UserCond.builder().loginName(user.getLoginName()).userIdNeq(user.getUserId()).build()) > 0)
            return Result.error(1, "【登录名】不能重复！");
        return Result.success(userService.update(user));
    }

    /**
     * @方法说明 【用户】分页列表
     */
    @AutoLog("【用户】分页列表")
    @PostMapping("page")
    public Result<Page<User>> page(@RequestBody UserCond cond) {
        return Result.success(userService.page(cond));
    }

    /**
     * @方法说明 【用户】列表
     */
    @AutoLog("【用户】列表")
    @PostMapping("list")
    public Result<List<User>> list(@RequestBody UserCond cond) {
        List<User> list = userService.list(cond);
        return Result.success(list);
    }

    /**
     * @方法说明 【用户】批量新增
     */
    @PostMapping("addBatch")
    @AutoLog("【用户】批量新增")
    public Result<Void> addBatch(@RequestBody @Validated List<User> userList) {
		/*for (User user : userList){
			if (userService.count(UserCond.builder().loginName(user.getLoginName()).build()) > 0)
				return Result.error(1, "【登录名】不能重复！");
		}*/
        try {
            userService.saveBatch(userList);
            return Result.success();
        } catch (Exception e) {
            return Result.error(500, e.toString());
        }
    }

    /**
     * @方法说明 【用户】批量更新
     */
    @PostMapping("updateBatch")
    @AutoLog("【用户】批量更新")
    public Result<Void> updateBatch(@RequestBody @Validated List<User> userList) {
        for (User user : userList) {
            userService.update(user);
        }
        return Result.success();
    }

    /**
     * @方法说明 【用户】批量删除
     */
    @AutoLog("【用户】批量删除")
    @PostMapping("deleteBatch")
    public Result<Integer> deleteBatch(@RequestBody @Validated List<User> userList) {
        Long[] ids = new Long[userList.size()];
        for (int i = 0; i < userList.size(); i++) {
            ids[i] = userList.get(i).getUserId();
        }
        try {
            userService.delete(ids);
            return Result.success();
        } catch (Exception e) {
            return Result.error(500, e.toString());
        }
    }

}
