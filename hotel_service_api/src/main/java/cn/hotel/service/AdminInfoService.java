package cn.hotel.service;

import cn.hotel.entity.model.AdminInfoRequest;
import cn.hotel.service.utils.RestModel;

import java.util.Map;

public interface AdminInfoService {
    //查询全部的信息
    RestModel findAllAdminInfo(AdminInfoRequest request);

    //查询总的数据
    public RestModel countAdminInfoRecord(AdminInfoRequest request);
}

