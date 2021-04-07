package org.javaboy.helloworld;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author dick <18668485565@163.com>
 * @version V1.0.0
 * @description
 * @date created on 2020/5/13
 */
public class MyController implements Controller {
    /**
     * 这就是一个请求处理接口
     * @param req 这就是前端发送来的请求
     * @param resp 这就是服务端给前端的响应
     * @return 返回值是一个 ModelAndView，Model 相当于是我们的数据模型，View 是我们的视图
     * @throws Exception
     */
    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ModelAndView mv = new ModelAndView("hello");
        mv.addObject("name", "javaboy");
        return mv;
    }
}
