package cn.hotel.service.impl;

import cn.hotel.business.HotelFoodMapper;
import cn.hotel.entity.HotelFoodDto;
import cn.hotel.service.HotelFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.List;

@Service
public class HotelFoodServiceImpl implements HotelFoodService {

    @Autowired
    private HotelFoodMapper hotelFoodMapper;

    @Override
    public List<HotelFoodDto> selectHotelFoodRecord(Map<String,Object> param) {
        List <HotelFoodDto> hotelFoodDtos = hotelFoodMapper.selectHotelFoodRecord(param);
        return hotelFoodDtos;
    }

    @Override
    public int countHotelFoodRecord(Map <String, Object> param) {
        return 0;
    }


}
