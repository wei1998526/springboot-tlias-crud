package com.wei.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.wei.pojo.Result;
import com.wei.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    //目标资源方法运行前运行，返回true 放行，返回false 不放行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        return HandlerInterceptor.super.preHandle(request, response, handler);
        //获取请求路径
        String url = request.getRequestURI();
        log.info("拦截到请求：{}", url);
        //1.获取请求头token
        String jwt = request.getHeader("token");
        log.info("获取到jwt令牌：{}", jwt);
        //2.校验令牌是否为空
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求头token为空，返回未登录信息-----");
            Result error = Result.error("NOT_LOGIN");
            //对象转JSON
            String notLogin = JSONObject.toJSONString(error);
            //响应给浏览器
            response.getWriter().write(notLogin);
            return false;
        }

        //3.解析令牌校验
        try {
            JwtUtils.parserJwt(jwt);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析token失败，返回登录-----");
            Result error = Result.error("NOT_LOGIN");
            //对象转JSON
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }

    }

    //目标资源方法运行后运行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        System.out.println("postHandle运行了------------------");
    }

    //视图渲染完毕后运行，最后运行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        System.out.println("afterCompletion运行了--------------");
    }
}
