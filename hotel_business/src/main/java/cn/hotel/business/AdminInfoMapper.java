package cn.hotel.business;

import cn.hotel.entity.AdminDto;
import cn.hotel.entity.model.AdminInfoRequest;
import cn.hotel.entity.model.AdminInfoResponse;
import java.util.*;
import java.util.List;


public interface AdminInfoMapper {


    //查询全部数据
    public List<AdminInfoResponse> selectAllAdminInfo(AdminInfoRequest request);

    //查询总数据
    public Long countAdminInfo(AdminInfoRequest adminInfoRequest);

    //删除数据
    public Long deleteAdminInfoByPrimKey(Map<String,Object> map);

    //根据adminId查询管理员
    public AdminDto selectAdminInfoById(AdminDto adminDto);

    //修改管理员信息
    public int updateAdminInfo(AdminDto adminDto);


    //添加管理员
    public int AdminInfoAdd(AdminDto adminDto);



}
