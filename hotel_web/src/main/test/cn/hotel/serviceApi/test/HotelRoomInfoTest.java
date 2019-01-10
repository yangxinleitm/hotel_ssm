package cn.hotel.serviceApi.test;

import cn.hotel.business.HotelMaterialMapper;
import cn.hotel.business.HotelRoomMapper;
import cn.hotel.entity.Room;
import cn.hotel.service.HotelRoomService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.MARSHAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.dc.pr.PRError;

import java.util.*;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:config/applicationContext.xml")
public class HotelRoomInfoTest {

    @Autowired
    private HotelRoomService hotelRoomServicel;

    @Autowired
    private HotelRoomMapper mapper;

    @Test
    public void selectRoomInfoList(){
        HashMap <String, Object> map = new HashMap <>();
        map.put("pageSize",50);
        map.put("pageNumber",0);
        List <Room> rooms = hotelRoomServicel.selectRoomInfoRecord(map);
        System.out.println(rooms);
    }

    @Test
    public void testadd(){
        Map <String, Object> map = new HashMap <>();
        map.put("roomId",24);
        map.put("roomNo","154656565622656");
        map.put("roomType",1);
        map.put("isClean",0);
        map.put("roomDetail","json串");
        map.put("isLive",0);
        map.put("roomArea",20);
        map.put("isVip",1);
        map.put("vipPrice",100);
        map.put("createTime",System.currentTimeMillis());
        map.put("modifyTime",System.currentTimeMillis());
        map.put("remark","测试");
        mapper.addHotelRoomInfoRecrd(map);
    }

}
