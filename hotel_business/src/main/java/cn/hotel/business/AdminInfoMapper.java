package cn.hotel.business;

import cn.hotel.entity.model.AdminInfoRequest;
import cn.hotel.entity.model.AdminInfoResponse;

import java.util.List;


public interface AdminInfoMapper {


    //查询全部数据
    public List<AdminInfoResponse> selectAllAdminInfo(AdminInfoRequest request);

    //查询总数据
    public Long countAdminInfo(AdminInfoRequest adminInfoRequest);

}
