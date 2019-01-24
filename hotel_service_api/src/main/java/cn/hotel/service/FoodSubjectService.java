package cn.hotel.service;

import cn.hotel.entity.FoodSubjectDto;

import java.util.List;
import java.util.Map;

public interface FoodSubjectService {

    //对条件查询全部数据
    public List<FoodSubjectDto> selectFoodSubjectRecord(Map<String,Object> param);

    //统计所有数据
    public int countFoodSubjectRecord(Map<String,Object> param);
}
