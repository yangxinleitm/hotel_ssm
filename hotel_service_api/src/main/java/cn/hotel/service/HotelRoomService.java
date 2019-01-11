package cn.hotel.service;

import cn.hotel.entity.Room;
import cn.hotel.service.utils.RestModel;

import java.util.List;
import java.util.Map;

public interface HotelRoomService {
    //查询全部数据
    public List<Room> selectRoomInfoRecord (Map<String,Object> map);

    //统计客房的所有数据
    public Long countHotelRoomInfoRecord(Map<String,Object> map);

    //添加客房信息
    public RestModel addHotelRoomInfoRecrd(Map<String,Object> map);

}
