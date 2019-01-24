package cn.hotel.business;

import cn.hotel.entity.FoodClassDto;
import java.util.*;
import java.util.List;

public interface FoodClassMapper {

    List<FoodClassDto> selectAllFoodClass(Map<String,Object> map);

    int countFoodClass(Map<String,Object> map);

    int updateFoodClass(FoodClassDto foodClassDto);

    int foodClasAdd(FoodClassDto foodClassDto);
}
