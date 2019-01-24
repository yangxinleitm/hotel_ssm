package cn.hotel.service;

import cn.hotel.entity.FoodClassDto;

import java.util.List;
import java.util.Map;

public interface FoodClassService {

    List<FoodClassDto> selectAllFoodClass(Map<String,Object> map);

    int countFoodClass(Map<String,Object> map);


    int updateFoodClass(FoodClassDto foodClassDto);

    int foodClasAdd(FoodClassDto foodClassDto);

}
