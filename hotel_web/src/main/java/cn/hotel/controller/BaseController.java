package cn.hotel.controller;

import cn.hotel.service.AdminInfoService;
import cn.hotel.service.CustomerService;
import cn.hotel.service.HotelRoomService;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {

    @Autowired
    protected CustomerService customerService;

    @Autowired
    protected HotelRoomService hotelRoomService;

    @Autowired
    protected AdminInfoService adminInfoService;


}
