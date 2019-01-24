package cn.hotel.business;

import cn.hotel.entity.Room;


import java.util.List;
import java.util.Map;

public interface HotelRoomMapper {

    //查询全部数据
    List<Room> selectRoomInfoRecord (Map<String,Object> map);

    //统计客房的所有数据
    Long countHotelRoomInfoRecord(Map<String,Object> map);

    //添加客房信息
    void addHotelRoomInfoRecrd(Map<String,Object> map);

    //修改客房信息
    void updateRoomInfoRecord(Room room);

    //根据id查询客房信息
    Room selectRoomInfoById(Room room);

}
