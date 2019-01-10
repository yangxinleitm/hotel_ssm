package cn.hotel.service;

import cn.hotel.entity.HotelMaterialDto;
import cn.hotel.entity.Room;

import java.util.List;
import java.util.Map;

public interface HotelMaterialService {
    //查询全部数据
    public List<HotelMaterialDto> selecHotelMaterialList (Map<String,Object> map);

    //统计客房的所有数据
    public Long countHotelMaterialRecord(Map<String,Object> map);
}
