package cn.song.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 控制器类
@Controller
@RequestMapping(value="/test")//次注解的作用就是建立URL和方法之间的映射关系
public class HelloController {

    /**
     * 入门案例
     * @return
     */
    @RequestMapping(value="/hello")
    public String sayHello(){
        System.out.println("Hello StringMVC");
        return "success";
    }

    /**
     * RequestMapping注解
     * value/path 标记着请求路径，
     * method 指定请求方式，只允许此方式进行访问
     * params 用于指定参数进行限定，要求必须穿的参数
     * headers 发送请求，必须含有此请求头
     * @return
     */
    @RequestMapping(value="/testRequestMapping")
    public String testRequestMapping(){

        System.out.println("测试RequestMapping注解...");

        return "success";

    }

}
