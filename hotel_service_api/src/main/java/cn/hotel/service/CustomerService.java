package cn.hotel.service;

import cn.hotel.entity.CustomerDto;
import cn.hotel.entity.model.CustomerInfoRequest;
import cn.hotel.entity.model.CustomerInfoResponse;
import cn.hotel.service.utils.RestModel;

import java.util.Map;

public interface CustomerService {

    //查询全部的信息
    RestModel findAllCustomer(Map<String,Object> map);

    //查询总的数据
    public RestModel countCustomerInfoRecord(Map<String,Object> param);


    //根据id查询用户详情
    public RestModel selectCustomerByPrimkey(CustomerDto customerDto);

}
