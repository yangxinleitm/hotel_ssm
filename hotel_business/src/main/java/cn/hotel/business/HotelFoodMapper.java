package cn.hotel.business;


import cn.hotel.entity.HotelFoodDto;

import java.util.List;
import java.util.Map;

public interface HotelFoodMapper {

    //对条件查询全部数据
    List<HotelFoodDto> selectHotelFoodRecord(Map<String,Object> param);

    //统计客房的所有数据
    int countHotelFoodRecord(Map<String,Object> param);

    //添加客房信息
//    public int addHotelFoodRecrd(HotelFoodDto hotelFoodRequest);

    //修改客房信息
//    public int updateHotelFoodRecord(HotelFoodDto hotelFoodRequest);

    //根据id查询客房信息
//    public HotelFoodDto selectHotelFoodById(HotelFoodDto hotelFoodRequest);
}