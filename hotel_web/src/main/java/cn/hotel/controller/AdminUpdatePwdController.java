package cn.hotel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminUpdatePwdController {

    private static final Logger logger = LoggerFactory.getLogger(AdminUpdatePwdController.class);

    //修改密码
    @RequestMapping(value = "/htm/base/updatePwd.action")
    public String hotelRoomInfo(HttpServletRequest request){
        return  "hotelDishManagent";
    }


}
