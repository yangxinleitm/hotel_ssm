package cn.hotel.service.impl;

import cn.hotel.business.CustomerMapper;
import cn.hotel.entity.CustomerDto;
import cn.hotel.entity.Enum.SysResponse;
import cn.hotel.entity.model.CustomerInfoRequest;
import cn.hotel.entity.model.CustomerInfoResponse;
import cn.hotel.service.CustomerService;
import cn.hotel.service.utils.RestModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceimpl implements CustomerService {

    RestModel restModel = new RestModel();

    @Resource
    private CustomerMapper customerMapper;

    @Cacheable(value = "customer",condition = "#result.userId>0")
    @Override
    public RestModel findAllCustomer(Map<String,Object> map) {
        List <CustomerInfoResponse> customerInfoResponses = customerMapper.selectAllCustomer(map);
        restModel.setCode(SysResponse.RECORD_CODE.RESPONSE_SUCCESS.get().toString());
        restModel.setMessage(SysResponse.RECORD_MESSAGE.RESPONSE_SUCCESS_MESSAGE.get());
        restModel.setData(customerInfoResponses);
        return restModel;
    }


    @Override
    public RestModel countCustomerInfoRecord(Map<String, Object> param) {

        Long count = customerMapper.countCustomerInfoRecord(param);
        restModel.setCode(SysResponse.RECORD_CODE.RESPONSE_SUCCESS.get().toString());
        restModel.setMessage(SysResponse.RECORD_MESSAGE.RESPONSE_SUCCESS_MESSAGE.get());
        restModel.setData(count);
        return restModel;
    }


    @Override
    public RestModel selectCustomerByPrimkey(CustomerDto customerDto) {
        if(customerDto.getUserId()==null || StringUtils.isEmpty(customerDto.getUserId().toString())){
            restModel.setCode(SysResponse.RECORD_CODE.PARAM_ISIMPTY.get().toString());
            restModel.setMessage(SysResponse.RECORD_MESSAGE.PARAM_ISIMPTY_MESSAGE.get());
            restModel.setData(customerDto);
        }
        restModel.setCode("200");
        restModel.setMessage("成功");
        restModel.setData(customerMapper.selectCustomerByPrimkey(customerDto));
        return restModel;
    }

}
