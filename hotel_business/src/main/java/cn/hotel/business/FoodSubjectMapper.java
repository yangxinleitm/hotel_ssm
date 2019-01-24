package cn.hotel.business;

import cn.hotel.entity.FoodSubjectDto;

import java.util.List;
import java.util.Map;

public interface FoodSubjectMapper {

    //对条件查询全部数据
     List<FoodSubjectDto> selectFoodSubjectRecord(Map<String,Object> param);

    //统计客房的所有数据
     int countFoodSubjectRecord(Map<String,Object> param);
}