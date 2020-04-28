//package com.example.platform.common;
//
//import com.example.platform.util.DateUtil;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class CorsFilter implements Filter {
//
//    final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CorsFilter.class);
//
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        DateUtil date = new DateUtil();
//        HttpServletResponse response = (HttpServletResponse) res;
//        HttpServletRequest request = (HttpServletRequest) req;
////        response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
////        response.setHeader("Access-Control-Allow-Origin","*");
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
//        response.setHeader("Access-Control-Expose-Headers", "*");
//        System.out.println("*********************************过滤器被使用**************************");
//        System.out.println("time:" + date.getNowDate() + "     status:" + response.getStatus());
//        System.out.println("time:" + date.getNowDate() + "     url:" + request.getRequestURI());
//        chain.doFilter(req, res);
//    }
//
//    public void init(FilterConfig filterConfig) {
//    }
//
//    public void destroy() {
//    }
//}
