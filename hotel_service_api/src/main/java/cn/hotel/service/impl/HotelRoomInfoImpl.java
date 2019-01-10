package cn.hotel.service.impl;

import cn.hotel.business.HotelRoomMapper;
import cn.hotel.entity.Room;
import cn.hotel.service.HotelRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HotelRoomInfoImpl implements HotelRoomService {


    @Autowired
    private HotelRoomMapper hotelRoomMapper;

    @Override
    public List<Room> selectRoomInfoRecord(Map<String, Object> map) {
        List <Room> rooms = hotelRoomMapper.selectRoomInfoRecord(map);
        return rooms;
    }

    @Override
    public Long countHotelRoomInfoRecord(Map <String, Object> map) {
        Long count = hotelRoomMapper.countHotelRoomInfoRecord(map);
        return count;
    }


}
