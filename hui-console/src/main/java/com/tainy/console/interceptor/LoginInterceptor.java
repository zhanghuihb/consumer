package com.tainy.console.interceptor;

import com.tainy.common.base.BaseRequest;
import com.tainy.common.exception.TokenLossEfficacyException;
import com.tainy.common.filter.RequestWrapper;
import com.tainy.common.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Tainy
 * @date 2018/5/16 13:57
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private static final ThreadLocal<Long> threadLocals = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        threadLocals.set(System.currentTimeMillis());
        // 请求参数
        String body = new RequestWrapper(request).getBody();
        LOGGER.info("{} 接口请求数据：{}", request.getRequestURI(), body);
        // json 格式
        BaseRequest<?> baseRequest = JsonUtil.fromJson(body, BaseRequest.class);

        if(baseRequest != null){
            /*if(true){
                LOGGER.warn("token={}失效", baseRequest.getToken());
                throw new TokenLossEfficacyException("token失效，请重新登录!");
            }*/
        }

        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOGGER.info("{} 请求处理耗时：{}",request.getRequestURI(),System.currentTimeMillis() - threadLocals.get());
        super.afterCompletion(request, response, handler, ex);
    }
}
