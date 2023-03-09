package com.ldr.common.config;

/**
 * @author keke
 * @类说明 人脸识别返回结果的实体类
 * @Date 2022/12/9 12:09
 */
public enum RespCode {

    SUCCESS(200, "比对成功"),
    FACE_NO_REGISTER(7, "您还未人脸注册,请先注册人脸!"),
    FACE_COMPARE_FAIL(8, "人脸比对失败，请重新操作!"),
    IMG_EMPTY(9, "照片不存在或者照片为空"),
    FACE_DETECT(10, "请拍摄清晰的人脸图片"),
    USER_FUN_EMPTY(3, "当前用户没有菜单授权"),
    USER_ROLE_EMPTY(4, "用户没有对应角色"),
    USER_ROLE_MORE(5, "用户关联的多个角色,请调整"),
    ;

    RespCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
