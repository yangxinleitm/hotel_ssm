package cn.hotel.service.impl;

import cn.hotel.business.HotelRoomMapper;
import cn.hotel.entity.Room;
import cn.hotel.service.HotelRoomService;
import cn.hotel.service.utils.RestModel;
import com.alibaba.fastjson.JSON;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HotelRoomInfoImpl implements HotelRoomService {

    private static final Logger logger = LoggerFactory.getLogger(HotelRoomInfoImpl.class);


    @Autowired
    private HotelRoomMapper hotelRoomMapper;

    @Override
    public List<Room> selectRoomInfoRecord(Map<String, Object> map) {
        logger.info("多条件查询酒店客房信息参数==>param={}",JSON.toJSONString(map));
        List <Room> rooms = hotelRoomMapper.selectRoomInfoRecord(map);
        logger.debug("多条件查询酒店客房信息返回结果==> restModel = {}","【"+ JSON.toJSONString(rooms)+"】");
        return rooms;
    }

    @Override
    public Long countHotelRoomInfoRecord(Map <String, Object> map) {
        Long count = hotelRoomMapper.countHotelRoomInfoRecord(map);
        return count;
    }

    @Override
    public RestModel addHotelRoomInfoRecrd(Map <String, Object> map) {
        RestModel restModel = new RestModel();
        restModel.setCode("200");
        restModel.setMessage("成功");
        hotelRoomMapper.addHotelRoomInfoRecrd(map);
        restModel.setData("");
        return restModel;
    }

    //修改客房信息
    @Override
    public RestModel updateRoomInfoRecord(Room room) {
        RestModel restModel = new RestModel();
        Room requst = new Room();
        if(room !=null && StringUtils.isNotBlank(room.getRoomId().toString())){
           if(room.getRoomId()!=null && StringUtils.isNotBlank(room.getRoomId().toString())){
               requst.setRoomId(room.getRoomId());
           }
        }
        if(room.getRoomNo()!=null && StringUtils.isNotBlank(room.getRoomNo().toString())){
            requst.setRoomId(room.getRoomId());
        }
        if(room.getRoomType() !=null && StringUtils.isNotBlank(room.getRoomType())){
            requst.setRoomType(room.getRoomType());
        }
        if(room.getIsClean() !=null && StringUtils.isNotBlank(room.getIsClean())){
            requst.setIsClean(room.getIsClean());
        }
        if(room.getIsLive() !=null && StringUtils.isNotBlank(room.getIsLive())){
            requst.setIsLive(room.getIsLive());
        }
        if(room.getRoomArea() !=null && StringUtils.isNotBlank(room.getRoomArea())){
            requst.setRoomArea(room.getRoomArea());
        }
        if(room.getIsVip() !=null && StringUtils.isNotBlank(room.getIsVip())){
            requst.setIsVip(room.getIsVip());
        }
        if(room.getVipPrice() !=null && StringUtils.isNotBlank(room.getVipPrice().toString())){
            requst.setVipPrice(room.getVipPrice());
        }

        requst.setRemark("");
        requst.setModifyTime(System.currentTimeMillis());
        requst.setRoomNo(room.getRoomNo());

        restModel.setCode("200");
        restModel.setMessage("成功");
        hotelRoomMapper.updateRoomInfoRecord(requst);
        restModel.setData("");
        return restModel;
    }


    //根据客房id进行查询
    @Override
    public RestModel selectRoomInfoById(Room room) {
        RestModel restModel = new RestModel();
        Room roomRquest = new Room();
        if(StringUtils.isNotBlank(room.getRoomId().toString())){
            roomRquest.setRoomId(room.getRoomId());
        }
        RestModel restModel1 = new RestModel(new RestModel().getCode(), new RestModel().getMessage(), hotelRoomMapper.selectRoomInfoById(roomRquest));
        return restModel1;
    }


}
