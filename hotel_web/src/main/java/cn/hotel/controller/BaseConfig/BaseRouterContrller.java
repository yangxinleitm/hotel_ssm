package cn.hotel.controller.BaseConfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseRouterContrller {

    //配置主页面和登录页面
    @RequestMapping(value = "/htm/login.action")
    public String login(){
        return "platformLogin";
    }
    //main主界面
    @RequestMapping(value = "/htm/main.action")
    public String main(){
        return "main";
    }
    //top界面
    @RequestMapping(value = "/htm/top.action")
    public String top(){
        return "top";
    }
    //左侧界面
    @RequestMapping(value = "/htm/left.action")
    public String left(){
        return "left";
    }


    //配置前台客户端首页
    @RequestMapping(value = "/htm/clientShowPage.action")
    public String clientShowPage(){
        return "client/index";
    }


}
