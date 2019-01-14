package cn.hotel.service.impl;

import cn.hotel.business.HotelRoomMapper;
import cn.hotel.entity.Room;
import cn.hotel.service.HotelRoomService;
import cn.hotel.service.utils.RestModel;
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

    @Override
    public RestModel addHotelRoomInfoRecrd(Map <String, Object> map) {
        RestModel restModel = new RestModel();
        restModel.setCode("200");
        restModel.setMessage("成功");
        hotelRoomMapper.addHotelRoomInfoRecrd(map);
        restModel.setData("");
        return restModel;
    }

    //修改客房信息
    @Override
    public RestModel updateRoomInfoRecord(Room room) {
        RestModel restModel = new RestModel();
        restModel.setCode("200");
        restModel.setMessage("成功");
        hotelRoomMapper.updateRoomInfoRecord(room);
        restModel.setData("");
        return restModel;
    }


}
