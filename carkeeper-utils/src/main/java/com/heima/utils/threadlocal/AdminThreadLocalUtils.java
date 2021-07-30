package com.heima.utils.threadlocal;


import com.heima.model.user.pojos.AdminUser;

public class AdminThreadLocalUtils {

    private final  static ThreadLocal<AdminUser> adminUserThreadLocal = new ThreadLocal<>();

    /**
     * 设置当前线程中的用户
     * @param adminUser
     */
    public static void setUser(AdminUser adminUser){
        adminUserThreadLocal.set(adminUser);
    }

    /**
     * 获取线程中的用户
     * @return
     */
    public static AdminUser getUser( ){
        return adminUserThreadLocal.get();
    }

}
