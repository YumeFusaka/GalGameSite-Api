package yumefusaka.galgamesite.interceptor;

// interceptor/LoginCheckInterceptor.java

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import yumefusaka.galgamesite.common.context.BaseContext;
import yumefusaka.galgamesite.common.properties.JwtProperties;
import yumefusaka.galgamesite.common.result.Result;
import yumefusaka.galgamesite.utils.JwtUtils;

//自定义拦截器
@Component //当前拦截器对象由Spring创建和管理
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    //前置方式
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle .... ");
        //1.获取请求url
        //2.判断请求url中是否包含login，如果包含，说明是登录操作，放行
        //3.获取请求头中的令牌（token）
        String token = request.getHeader("Authorization");
        log.info("从请求头中获取的令牌：{}", token);
        //4.判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if (!StringUtils.hasLength(token)) {
            log.info("Token不存在");

            //创建响应结果对象
            Result responseResult = Result.noToken("请先登录喵~");
            //把Result对象转换为JSON格式字符串 (fastjson是阿里巴巴提供的用于实现对象和json的转换工具类)
            String json = JSONObject.toJSONString(responseResult);
            //设置状态码
            response.setStatus(401);
            //设置响应头（告知浏览器：响应的数据类型为json、响应的数据编码表为utf-8）
            response.setContentType("application/json;charset=utf-8");
            //响应
            response.getWriter().write(json);
            return false;//不放行
        }

        token = token.substring(7);

        //5.解析token，如果解析失败，返回错误结果（未登录）
        try {
            Claims claims = JwtUtils.parseToken(jwtProperties.getSecretKey(), token);
            BaseContext.setCurrentId((String) claims.get("qq"));
        } catch (Exception e) {
            log.info("令牌解析失败!");

            //创建响应结果对象
            Result responseResult = Result.noToken("NOT_LOGIN");
            //把Result对象转换为JSON格式字符串 (fastjson是阿里巴巴提供的用于实现对象和json的转换工具类)
            String json = JSONObject.toJSONString(responseResult);
            //设置响应头
            response.setContentType("application/json;charset=utf-8");
            //设置状态码
            response.setStatus(401);
            //响应
            response.getWriter().write(json);
            return false;
        }

        //6.放行
        return true;
    }
}