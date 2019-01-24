package cn.hotel.service;

import cn.hotel.entity.HotelFoodDto;
import java.util.*;

public interface HotelFoodService {

    //多条件查询菜品信息
    public List<HotelFoodDto> selectHotelFoodRecord (Map<String,Object> param);

    //统计菜品的数据
    public int countHotelFoodRecord(Map<String,Object> param);
}
