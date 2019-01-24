package cn.hotel.business;

import cn.hotel.entity.HotelMaterialDto;

import java.util.List;
import java.util.Map;

public interface HotelMaterialMapper {

    //查询全部数据
    List<HotelMaterialDto> selecHotelMaterialList (Map<String,Object> map);

    //统计客房的所有数据
    Long countHotelMaterialRecord(Map<String,Object> map);
}
