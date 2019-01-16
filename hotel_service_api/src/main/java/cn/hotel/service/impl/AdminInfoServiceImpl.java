package cn.hotel.service.impl;

import cn.hotel.business.AdminInfoMapper;
import cn.hotel.entity.AdminDto;
import cn.hotel.entity.Enum.SysResponse;
import cn.hotel.entity.model.AdminInfoRequest;
import cn.hotel.entity.model.AdminInfoResponse;
import cn.hotel.service.AdminInfoService;
import cn.hotel.service.utils.RestModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminInfoServiceImpl implements AdminInfoService {

    @Autowired
    private AdminInfoMapper adminInfoMapper;

    RestModel restModel = new RestModel();


    @Override
    public RestModel findAllAdminInfo(AdminInfoRequest request) {
        if(StringUtils.isBlank(request.getPageNumber().toString()) || request.getPageNumber() ==null){
            restModel.setCode(SysResponse.RECORD_CODE.PARAM_ISIMPTY.get().toString());
            restModel.setMessage("pageNumber"+SysResponse.RECORD_MESSAGE.PARAM_ISIMPTY_MESSAGE.get());
            return restModel;
        }
        if(request.getPageSize() ==null && StringUtils.isBlank(request.getPageSize().toString())){
            restModel.setCode(SysResponse.RECORD_CODE.PARAM_ISIMPTY.get().toString());
            restModel.setMessage("PageSize"+SysResponse.RECORD_MESSAGE.PARAM_ISIMPTY_MESSAGE.get());
            return restModel;
        }

        List <AdminInfoResponse> adminInfoResponses = adminInfoMapper.selectAllAdminInfo(request);
        restModel.setCode(SysResponse.RECORD_CODE.RESPONSE_SUCCESS.get().toString());
        restModel.setMessage(SysResponse.RECORD_MESSAGE.RESPONSE_SUCCESS_MESSAGE.get());
        restModel.setData(adminInfoResponses);
        return restModel;
    }

    @Override
    public RestModel countAdminInfoRecord(AdminInfoRequest request) {
        Long count = adminInfoMapper.countAdminInfo(request);
        restModel.setCode(SysResponse.RECORD_CODE.RESPONSE_SUCCESS.get().toString());
        restModel.setMessage(SysResponse.RECORD_MESSAGE.RESPONSE_SUCCESS_MESSAGE.get());
        restModel.setData(count);
        return restModel;
    }


    //删除
    @Override
    public RestModel deleteAdminInfoRecord(Map<String, Object> map) {
        if(map.get("adminId").toString()==null && !StringUtils.isNotBlank(map.get("adminId").toString())){
            restModel.setCode(SysResponse.RECORD_CODE.PARAM_ISIMPTY.get().toString());
            restModel.setMessage(SysResponse.RECORD_MESSAGE.PARAM_ISIMPTY_MESSAGE.get());
            return restModel;
        }else if(map.get("adminIsPostion") ==null && !StringUtils.isNotBlank(map.get("adminIsPostion").toString())){
            restModel.setCode(SysResponse.RECORD_CODE.PARAM_ISIMPTY.get().toString());
            restModel.setMessage(SysResponse.RECORD_MESSAGE.PARAM_ISIMPTY_MESSAGE.get());
            return restModel;
        }
        Long count = adminInfoMapper.deleteAdminInfoByPrimKey(map);
        restModel.setCode(SysResponse.RECORD_CODE.RESPONSE_SUCCESS.get().toString());
        restModel.setMessage(SysResponse.RECORD_MESSAGE.RESPONSE_SUCCESS_MESSAGE.get());
        restModel.setData(count);
        return restModel;
    }

    @Override
    public RestModel selectAdminInfoRecordById(AdminDto adminDto) {
        RestModel restModel = new RestModel();
        AdminDto adminRequest = new AdminDto();
        if(StringUtils.isNotBlank(adminDto.getAdminId().toString())){
            AdminDto adminDto1 = adminInfoMapper.selectAdminInfoById(adminDto);
            restModel.setCode("200");
            restModel.setMessage("成功");
            restModel.setData(adminDto1);
            return restModel;
        }
        return restModel;
    }

    // 管理员信息添加
    @Override
    public RestModel adminInfoAdd(AdminDto adminDto) {
        AdminDto adminRequest = new AdminDto();
        if(adminDto.getAdminName()!=null && StringUtils.isNotBlank(adminDto.getAdminName())){
            adminRequest.setAdminName(adminDto.getAdminName());
        }
        if(adminDto.getAdminPwd()!=null && StringUtils.isNotBlank(adminDto.getAdminPwd())){
            adminRequest.setAdminPwd(adminDto.getAdminPwd());
        }
        if(adminDto.getAdminRealName()!=null && StringUtils.isNotBlank(adminDto.getAdminRealName())){
            adminRequest.setAdminRealName(adminDto.getAdminRealName());
        }
        if(adminDto.getAdminSex()!=null && StringUtils.isNotBlank(adminDto.getAdminSex())){
            adminRequest.setAdminSex(adminDto.getAdminSex());
        }
        if(adminDto.getAdminBirthday()!=null && StringUtils.isNotBlank(adminDto.getAdminBirthday())){
            adminRequest.setAdminBirthday(adminDto.getAdminBirthday());
        }
        if(adminDto.getAdminNation()!=null && StringUtils.isNotBlank(adminDto.getAdminNation())){
            adminRequest.setAdminNation(adminDto.getAdminNation());
        }
        if(adminDto.getAdminIdCard()!=null && StringUtils.isNotBlank(adminDto.getAdminIdCard().toString())){
            adminRequest.setAdminIdCard(adminDto.getAdminIdCard());
        }
        if(adminDto.getAdminMobile()!=null && StringUtils.isNotBlank(adminDto.getAdminMobile().toString())){
            adminRequest.setAdminMobile(adminDto.getAdminMobile());
        }
        if(adminDto.getAddress()!=null && StringUtils.isNotBlank(adminDto.getAddress())){
            adminRequest.setAddress(adminDto.getAddress());
        }
        if(adminDto.getAdminIdCardType()!=null && StringUtils.isNotBlank(adminDto.getAdminIdCardType())){
            adminRequest.setAdminIdCardType(adminDto.getAdminIdCardType());
        }
        if(adminDto.getAdminIsPostion() !=null && StringUtils.isNotBlank(adminDto.getAdminIsPostion())){
            adminRequest.setAdminIsPostion(adminDto.getAdminIsPostion());
        }
        adminRequest.setCreateTime(System.currentTimeMillis());
        int count = adminInfoMapper.AdminInfoAdd(adminRequest);
        if(count >0){
            restModel.setCode("200");
            restModel.setMessage("成功");
            restModel.setData(count);
        }else{
            restModel.setCode("300");
            restModel.setMessage("传入参数不对，调用失败！");
            restModel.setData("");
        }
        return restModel;
    }


}
