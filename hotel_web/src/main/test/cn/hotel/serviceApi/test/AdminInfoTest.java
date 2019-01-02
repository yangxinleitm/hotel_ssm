package cn.hotel.serviceApi.test;

import cn.hotel.business.AdminInfoMapper;
import cn.hotel.entity.CustomerDto;
import cn.hotel.entity.Enum.SysResponse;
import cn.hotel.entity.model.AdminInfoRequest;
import cn.hotel.service.AdminInfoService;
import cn.hotel.service.CustomerService;
import cn.hotel.service.utils.RestModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:config/applicationContext.xml")
public class AdminInfoTest {

    @Autowired
    private AdminInfoService adminInfoService;

    //测试admin表
    @Test
    public void getSelectAdmin(){
        AdminInfoRequest adminInfoRequest = new AdminInfoRequest();
        adminInfoRequest.setPageSize(Long.valueOf(49));
        adminInfoRequest.setPageNumber(Long.valueOf(0));
        RestModel restModel = adminInfoService.findAllAdminInfo(adminInfoRequest);
        if(restModel.getCode().toString().equals("200") && StringUtils.isNotBlank(restModel.getData().toString())){
            List<CustomerDto> list= (List)restModel.getData();
            System.out.println(list);
        }
    }

    @Test
    public void deleteAdmin(){
        HashMap <String, Object> map = new HashMap <>();
        map.put("adminId","10102");
        map.put("adminIsPostion","0");
        RestModel restModel = adminInfoService.deleteAdminInfoRecord(map);
        Long count = Long.valueOf(restModel.getData().toString());
        System.out.println(count);
    }

}
