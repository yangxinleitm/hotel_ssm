package cn.hotel.serviceApi.test;
import cn.hotel.entity.CustomerDto;
import cn.hotel.entity.Enum.SysResponse;
import cn.hotel.entity.model.CustomerInfoRequest;
import cn.hotel.entity.model.CustomerInfoResponse;
import cn.hotel.service.CustomerService;
import cn.hotel.service.utils.RestModel;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:config/applicationContext.xml")
public class CustomerTest {

    @Autowired
    private CustomerService customerService;

    //测试客户列表
    @Test
    public void getSelectAllCustomer(){
        HashMap <String, Object> map = new HashMap <>();
        map.put("pageNumber",0);
        map.put("pageSize",49);
        RestModel restModel = customerService.findAllCustomer(map);
        if(restModel.getCode().toString().equals("200") && StringUtils.isNotBlank(restModel.getData().toString())){
            List<CustomerDto> list= (List)restModel.getData();
            System.out.println(list);
        }
    }

    //客户列表展示
    @Test
    public void customerDetail(){
        CustomerDto customerInfoRequest = new CustomerDto();
        customerInfoRequest.setUserId(Long.valueOf("154203696"));
        RestModel restModel = customerService.selectCustomerByPrimkey(customerInfoRequest);
        System.out.println(JSON.toJSONString(restModel));
    }




}
