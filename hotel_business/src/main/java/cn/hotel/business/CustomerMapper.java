package cn.hotel.business;

import cn.hotel.entity.model.CustomerInfoResponse;
import  java.util.*;
import java.util.List;


public interface CustomerMapper{

    //查询全部数据
    public List<CustomerInfoResponse> selectAllCustomer(Map<String,Object> map);

    //查询总数据
    public Long countCustomerInfoRecord(Map<String,Object> param);

}
