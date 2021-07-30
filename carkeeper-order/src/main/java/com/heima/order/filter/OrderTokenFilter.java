package com.heima.order.filter;


import com.heima.model.user.pojos.AdminUser;
import com.heima.utils.threadlocal.AdminThreadLocalUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@WebFilter(filterName = "wmTokenFilter",urlPatterns = "/")
@Order(1)
public class OrderTokenFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //从请求头中解析header中的数据
        String userId = request.getHeader("userId");
        if (!StringUtils.isBlank(userId)){
            AdminUser user = new AdminUser();
            user.setId(Long.valueOf(userId));
            //设置到当前本地线程中
            AdminThreadLocalUtils.setUser(user);
        }
        filterChain.doFilter(request,response);
    }
}
