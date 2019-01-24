package cn.hotel.service.impl;

import cn.hotel.business.FoodClassMapper;
import cn.hotel.entity.FoodClassDto;
import cn.hotel.service.FoodClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FoodClassServiceImpl implements FoodClassService {

    @Autowired
    private FoodClassMapper foodClassMapper;

    @Override
    public List<FoodClassDto> selectAllFoodClass(Map<String, Object> map) {
        List <FoodClassDto> foodClassDtos = foodClassMapper.selectAllFoodClass(map);
        return foodClassDtos;
    }

    @Override
    public int countFoodClass(Map <String, Object> map) {
        int count = foodClassMapper.countFoodClass(map);
        return count;
    }

    @Override
    public int updateFoodClass(FoodClassDto foodClassDto) {
        int count = foodClassMapper.updateFoodClass(foodClassDto);
        return count;
    }

    @Override
    public int foodClasAdd(FoodClassDto foodClassDto) {
        return foodClassMapper.foodClasAdd(foodClassDto);
    }
}
