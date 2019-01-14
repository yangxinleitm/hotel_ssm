package cn.hotel.business;

import cn.hotel.entity.CustomerDto;
import cn.hotel.entity.Room;
import cn.hotel.entity.model.CustomerInfoResponse;

import java.util.List;
import java.util.Map;

public interface HotelRoomMapper {

    //查询全部数据
    public List<Room> selectRoomInfoRecord (Map<String,Object> map);

    //统计客房的所有数据
    public Long countHotelRoomInfoRecord(Map<String,Object> map);

    //添加客房信息
    public void addHotelRoomInfoRecrd(Map<String,Object> map);

    //修改客房信息
    public void updateRoomInfoRecord(Room room);

}
