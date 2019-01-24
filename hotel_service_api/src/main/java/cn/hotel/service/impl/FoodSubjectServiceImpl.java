package cn.hotel.service.impl;

import cn.hotel.business.FoodSubjectMapper;
import cn.hotel.entity.FoodSubjectDto;
import cn.hotel.service.FoodSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class FoodSubjectServiceImpl implements FoodSubjectService {

    @Autowired
    private FoodSubjectMapper foodSubjectMapper;

    @Override
    public List<FoodSubjectDto> selectFoodSubjectRecord(Map<String, Object> param) {
        List <FoodSubjectDto> foodSubjectDtos = foodSubjectMapper.selectFoodSubjectRecord(param);
        return foodSubjectDtos;
    }

    @Override
    public int countFoodSubjectRecord(Map <String, Object> param) {
        int count = foodSubjectMapper.countFoodSubjectRecord(param);
        return count;
    }
}
