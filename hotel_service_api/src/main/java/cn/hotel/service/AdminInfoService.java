package cn.hotel.service;

import cn.hotel.entity.AdminDto;
import cn.hotel.entity.model.AdminInfoRequest;
import cn.hotel.service.utils.RestModel;

import java.util.Map;

public interface AdminInfoService {
    //查询全部的信息
    RestModel findAllAdminInfo(AdminInfoRequest request);

    //查询总的数据
    public RestModel countAdminInfoRecord(AdminInfoRequest request);

    //删除离职员工的数据
    public RestModel deleteAdminInfoRecord(Map<String,Object> map);

    //按照id查询管理员信息
    public RestModel selectAdminInfoRecordById(AdminDto adminDto);

    public RestModel adminInfoAdd(AdminDto adminDto);

}

