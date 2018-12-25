package cn.hotel.service.impl;

import cn.hotel.business.AdminInfoMapper;
import cn.hotel.entity.Enum.SysResponse;
import cn.hotel.entity.model.AdminInfoRequest;
import cn.hotel.entity.model.AdminInfoResponse;
import cn.hotel.service.AdminInfoService;
import cn.hotel.service.utils.RestModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
