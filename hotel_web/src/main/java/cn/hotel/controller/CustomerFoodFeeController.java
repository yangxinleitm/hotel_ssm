package cn.hotel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomerFoodFeeController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(CustomerFoodFeeController.class);

    //跳转到用户信息页面
    @RequestMapping(value = "/htm/fee/customerFoodFee.action")
    public String customerInfo(HttpServletRequest request){
        return  "customerFoodFee";
    }

}
