package cn.hotel.service.impl;

import cn.hotel.business.HotelMaterialMapper;
import cn.hotel.entity.HotelMaterialDto;
import cn.hotel.entity.Room;
import cn.hotel.service.HotelMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HotelMaterialServiceImpl implements HotelMaterialService {

    @Autowired
    private HotelMaterialMapper hotelMaterialMapper;

    @Override
    public List<HotelMaterialDto> selecHotelMaterialList(Map<String, Object> map) {
        List <HotelMaterialDto> HotelMaterialDtos = hotelMaterialMapper.selecHotelMaterialList(map);
        return HotelMaterialDtos;
    }

    @Override
    public Long countHotelMaterialRecord(Map <String, Object> map) {
        Long count = hotelMaterialMapper.countHotelMaterialRecord(map);
        return count;
    }
}
