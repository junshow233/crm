package com.bjpowernode.crm.web.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author jun
 * @date 2020/11/28 - 9:58
 */
public class EncodingFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        System.out.println("进入到过滤字符编码过滤器");

        req.setCharacterEncoding("UTF-8");

        resp.setContentType("text/html;charset=utf-8");

        chain.doFilter(req,resp);
    }
}
