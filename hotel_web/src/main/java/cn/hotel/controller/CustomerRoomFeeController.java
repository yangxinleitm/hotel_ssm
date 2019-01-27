package cn.hotel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
/**

* @Description:    客户客房消费控制器
* @Author:         田梦
* @CreateDate:     2019/1/25 10:10


*/
@Controller
public class CustomerRoomFeeController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(CustomerRoomFeeController.class);

    //跳转到客户客房消费页面
    @RequestMapping(value = "/htm/fee/customerRoomFee.action")
    public String customerRoomFee(HttpServletRequest request){
        return  "customerRoomFee";
    }
}
