package cn.hotel.serviceApi.test;
import cn.hotel.entity.CustomerDto;
import cn.hotel.entity.Enum.SysResponse;
import cn.hotel.entity.model.CustomerInfoResponse;
import cn.hotel.service.CustomerService;
import cn.hotel.service.utils.RestModel;
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



}
