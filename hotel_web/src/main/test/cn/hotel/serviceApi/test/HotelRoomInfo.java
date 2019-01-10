package cn.hotel.serviceApi.test;

import cn.hotel.entity.Room;
import cn.hotel.service.HotelRoomService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:config/applicationContext.xml")
public class HotelRoomInfo {

    @Autowired
    private HotelRoomService hotelRoomServicel;

    @Test
    public void selectRoomInfoList(){
        HashMap <String, Object> map = new HashMap <>();
        map.put("pageSize",50);
        map.put("pageNumber",0);
        List <Room> rooms = hotelRoomServicel.selectRoomInfoRecord(map);
        System.out.println(rooms);
    }

}
