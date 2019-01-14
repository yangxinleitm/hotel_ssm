package cn.hotel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class hotelDishManagentController {

    private static final Logger logger = LoggerFactory.getLogger(hotelDishManagentController.class);


    //跳转到酒店事务管理 -- 酒店客房信息
    @RequestMapping(value = "/htm/hotel/hotelDishManagent.action")
    public String hotelRoomInfo(HttpServletRequest request){
        return  "hotelDishManagent";
    }
}
