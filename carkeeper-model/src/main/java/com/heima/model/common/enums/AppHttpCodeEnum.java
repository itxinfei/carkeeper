package com.heima.model.common.enums;

public enum AppHttpCodeEnum {

    // 成功段0
    SUCCESS(0,"操作成功"),
    // 登录段1~50
    NEED_LOGIN(1,"需要登录后操作"),
    LOGIN_PASSWORD_ERROR(2,"密码错误"),
    // TOKEN50~100
    TOKEN_INVALID(50,"无效的TOKEN"),
    TOKEN_EXPIRE(51,"TOKEN已过期"),
    TOKEN_REQUIRE(52,"TOKEN是必须的"),
    // SIGN验签 100~120
    SIGN_INVALID(100,"无效的SIGN"),
    SIG_TIMEOUT(101,"SIGN已过期"),
    // 参数错误 500~1000
    PARAM_REQUIRE(500,"缺少参数"),
    PARAM_INVALID(501,"无效参数"),
    PARAM_IMAGE_FORMAT_ERROR(502,"图片格式有误"),
    SERVER_ERROR(503,"服务器内部错误"),
    // 数据错误 1000~2000
    DATA_EXIST(1000,"数据已经存在"),
    AP_USER_DATA_NOT_EXIST(1001,"ApUser数据不存在"),
    DATA_NOT_EXIST(1002,"数据不存在"),
    // 数据错误 3000~3500
    NO_OPERATOR_AUTH(3000,"无权限操作"),
    NEED_ADMIND(3001,"需要管理员权限"),
    MANUAL_CHECK(3002,"需要人工审核"),


    //===========================业务码================================


    //配件管理
    UNABLE_TO_DELETE(4001,"无法删除"),

    // admin 4000-4999
    PARENTID_ISEXIST_ISSTATUS_ERROR(4001,"有绑定关系不能删除"),
    CODE_EXIST(4006,"分类编码已存在"),
    NAME_EXIST(4006,"分类名称已存在"),
    ADMIN_USER_NAMEORPWD_ERROR(4002,"用户名或密码错误"),
    WMUSER_ADD_ERROR(4004,"wmUser添加错误"),
    APAUTHOR_ADD_ERROR(4005,"apAuthor添加错误"),
    APUSERREALNAME_DATANOTEXIST_ERROR(4003,"认证用户数据不存在错误"),
    FILE_UPLOAD_ERROR(4006,"文件上传失败"),
    SYSTEM_BUSY(4007,"系统正忙，请稍后重试"),
    DATA_ALREADY_EXIST(4007,"数据已经存在"),

    // wmUser 5000-5999
    FILE_UPLOAD_PARAMREQUIRE_ERROR(5001,"文件上传参数错误"),
    FILE_MATERIAL_ISNULL_ERROR(5004,"文件素材是空错误"),
    WEMEDIA_UPDATE_ERROR(5005,"存在敏感词错误"),
    ARTICLE_SAVE_ERROR(5006,"文章保存错误"),
    FILE_DELETE_ERROR(5002,"文件删除错误");

    int code;
    String errorMessage;

    AppHttpCodeEnum(int code, String errorMessage){
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
