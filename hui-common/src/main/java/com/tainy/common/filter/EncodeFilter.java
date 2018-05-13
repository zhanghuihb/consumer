package com.tainy.common.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Map;

/**
 * @author Tainy
 * @date 2017/12/27
 */
public class EncodeFilter implements Filter {
    private FilterConfig config = null;
    private ServletContext context = null;
    private String encode = null;

    //过滤器销毁接口
    public void destroy() {

    }

    //过滤器处理接口
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        //解决response中的乱码问题
        //设置服务器端的编码格式
        response.setCharacterEncoding(encode);
        //设置浏览器端的解码格式（同时也会设置服务器端编码采取相同格式，故上面一行代码设置可以省略）
        response.setContentType("text/html;charset="+encode);
        /* 放行，调用当前过滤器链上的下一个过滤器的doFilter()接口，
         * 传入用于解决request请求参数乱码问题的装饰类对象 */
        chain.doFilter(new MyHttpServletRequest((HttpServletRequest)request), response);
    }

    //过滤器初始化接口
    public void init(FilterConfig filterConfig) throws ServletException {
        /* 当web应用被加载到内存中时，过滤器对象也被加载，此时将调用init方法对其初始化
         * 在初始化接口中，获取过滤器配置对象 */
        this.config = filterConfig;
        //获取代表当前web应用的ServletContext对象
        this.context = config.getServletContext();
        //获取当前web应用范围内的初始化参数，即全站编码参数
        this.encode = context.getInitParameter("encode");

    }

    /* 在全站乱码过滤器内部设计一个HttpServletRequest的装饰内部类，
     * 使用装饰设计模式重写request对象中获取请求参数相关的接口，从而解决乱码问题 */
    private class MyHttpServletRequest extends HttpServletRequestWrapper{
        private HttpServletRequest request = null;
        //手动编解码操作控制符
        private boolean isNotEncode = true;
        //在构造函数中保存被装饰的request对象
        public MyHttpServletRequest(HttpServletRequest request) {
            super(request);
            this.request = request;
        }
        /* 重写以下三个被装饰对象request中与请求参数获取相关的接口 */
        @Override
        public Map<String,String[]> getParameterMap() {
            try{
                /* 若request请求方式为POST（参数保存在请求消息的body实体内容部分），
                 * 则只需设置字符编码格式，即可解决解析乱码问题 */
                if(request.getMethod().equalsIgnoreCase("POST")){
                    request.setCharacterEncoding(encode);
                    return request.getParameterMap();
                }
                /* 若request请求方式为GET（参数将作为请求url的一部分拼接在url的最后），
                 * 这种发送请求参数的方式并不能通过设置字符编码格式的方式解决解析乱码问题，
                 * 需要通过手动编解码才能解决此类乱码问题 */
                else if(request.getMethod().equalsIgnoreCase("GET")){
                    Map<String,String[]> map = request.getParameterMap();
                    /* 当该方法首次被调用获取请求参数时，request对象会将请求中的所有参数提取出来
                     * 封装成一个map后返回，此时存在乱码问题，需要通过手动编解码方式才能解决，
                     * 当再次调用该方法时，由于首次调用时返回的map会被缓存在内存当中，之后的所有
                     * 调用获取到的都将是同一个内存中的map，而首次调用时已经解决了乱码问题，所以
                     * 之后的调用中直接返回map即可，不能再做重复的乱码处理，否则又将出现乱码。*/
                    if(isNotEncode){
                        //依次处理每组请求参数中的乱码
                        for(Map.Entry<String, String[]> entry : map.entrySet()) {
                            String[] values = entry.getValue();
                            for(int i=0; i<values.length; i++){
                                //先将乱码数据重新编码成字节流（服务器端默认采用iso8859-1解码请求参数）
                                byte[] bytes = values[i].getBytes("iso8859-1");
                                //再对字节流重新解码
                                String encodeStr = new String(bytes,encode);
                                //将解决乱码后的数据写回map中
                                values[i] = encodeStr;
                            }
                        }
                        //手动编解码操作只能在方法首次调用时执行一次，以后再调用便已不存在乱码
                        isNotEncode = false;
                    }
                    return map;
                }
                /* 一般情况下，浏览器端发送请求的方式即为POST和GET两种，
                 * 除此以外通过其他方式发送的请求中可能存在的乱码问题暂不做处理 */
                else{
                    return request.getParameterMap();
                }
            }catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        @Override
        public String[] getParameterValues(String name) {
            return getParameterMap().get(name);
        }

        @Override
        public String getParameter(String name) {
            String[] values = getParameterValues(name);
            //若name所对应的请求参数不存在，则返回null；存在则返回数组中的第一个参数值
            return values ==null  ? null : values[0];
        }
    }
}
