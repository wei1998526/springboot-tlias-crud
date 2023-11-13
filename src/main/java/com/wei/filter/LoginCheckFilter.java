package com.wei.filter;

import com.alibaba.fastjson.JSONObject;
import com.wei.pojo.Result;
import com.wei.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*") //拦截路径
public class LoginCheckFilter implements Filter {
    //初始化方法，启动时执行，只执行一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
        System.out.println("初始化方法执行了----------------------");
    }

    //拦截器，每次请求都执行，执行多次
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse reqs = (HttpServletResponse) servletResponse;
        System.out.println("拦截到了请求----------------------");
        //获取请求路径
        String url = req.getRequestURI();
        log.info("拦截到请求：{}", url);
        //请求包含login->放行
        if (url.contains("login")) {
            log.info("登录操作，放行------");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //不是登录操作，获取token
        String jwt = req.getHeader("token");
        log.info("获取到jwt令牌：{}", jwt);
        //判断token是否为空
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求头token为空，返回未登录信息-----");
            Result error = Result.error("NOT_LOGIN");
            //对象转JSON
            String notLogin = JSONObject.toJSONString(error);
            //响应给浏览器
            reqs.getWriter().write(notLogin);
            return;
        }

        //token不为空，解析token，解析失败返回未登录
        try {
            JwtUtils.parserJwt(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析token失败，返回登录-----");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            reqs.getWriter().write(notLogin);
            return;
        }

        //解析成功，放行请求
        log.info("令牌验证通过，放行------");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    //销毁方法，停止时执行，只执行一次
    @Override
    public void destroy() {
//        Filter.super.destroy();
        System.out.println("结束方法执行了----------------------");
    }
}
