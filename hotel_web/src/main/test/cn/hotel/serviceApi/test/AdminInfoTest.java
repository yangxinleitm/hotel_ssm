package cn.hotel.serviceApi.test;

import cn.hotel.business.CustomerOrderMapper;
import cn.hotel.entity.AdminDto;
import cn.hotel.entity.CustomerDto;
import cn.hotel.entity.CustomerOrder;
import cn.hotel.entity.model.AdminInfoRequest;
import cn.hotel.service.AdminInfoService;
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
public class AdminInfoTest {

    @Autowired
    private AdminInfoService adminInfoService;

    //查询admin列表集合
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


    //删除管理员信息
    @Test
    public void deleteAdmin(){
        HashMap <String, Object> map = new HashMap <>();
        map.put("adminId","10102");
        map.put("adminIsPostion","0");
        RestModel restModel = adminInfoService.deleteAdminInfoRecord(map);
        Long count = Long.valueOf(restModel.getData().toString());
        System.out.println(count);
    }


    //根据adminId来查询管理员信息
    @Test
    public void selectAdminById(){
        AdminDto adminDto = new AdminDto();
        adminDto.setAdminId(Long.valueOf(10101));
        RestModel restModel = adminInfoService.selectAdminInfoRecordById(adminDto);
        System.out.println(restModel);
    }

    @Autowired
    private CustomerOrderMapper customerOrderMapper;

    @Test
    public void test01(){
        CustomerOrder customerOrder = customerOrderMapper.selectByPrimaryKey(1);
        System.out.println(JSON.toJSONString(customerOrder));
    }



}
