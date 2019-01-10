package cn.hotel.controller;

import cn.hotel.entity.Room;
import cn.hotel.entity.model.PagerModel;
import cn.hotel.service.HotelRoomService;
import cn.hotel.service.utils.DateUtils;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**

* @Description:    酒店事务管理 -- 酒店客房信息
* @Author:         yangxinlei
* @CreateDate:     2019/1/10 13:52
*/

@Controller
public class HotelRoomInfoController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(HotelRoomInfoController.class);


    //跳转到酒店事务管理 -- 酒店客房信息
    @RequestMapping(value = "/htm/hotel/hotelRoomInfo.action")
    public String hotelRoomInfo(HttpServletRequest request){
        return  "hotelRoomInfo";
    }

    @Autowired
    private HotelRoomService hotelRoomService;


    //酒店客房信息列表展示
    @RequestMapping(value = "/htm/hotelRoomInfoList.action",method = RequestMethod.GET)
    @ResponseBody
    public PagerModel<List<Room>> getCustomer(HttpServletRequest request) {
        PagerModel <List <Room>> pager = new PagerModel <>();
        List <Room> list = new ArrayList<>();
        Long count = 0L;
        Map <String, Object> param = getSearchParam(request);
        logger.info("用户信息查询参数 param={}", JSON.toJSONString(param));
        List <Room> rooms = hotelRoomService.selectRoomInfoRecord(param);
        logger.info("用户信息查询返回结果 param={}", JSON.toJSONString(rooms));
        if(rooms!=null && rooms.size()>0){
            count = hotelRoomService.countHotelRoomInfoRecord(param);
        }
        pager.setPageData(rooms);
        pager.setTotal(count);
        return pager;
    }

    private Map<String,Object> getSearchParam(HttpServletRequest request) {
        Map <String, Object> param = new HashMap <>();

        String pageSize = request.getParameter("pageSize");
        if (StringUtils.isNotBlank(pageSize)) {
            param.put("pageSize",Integer.valueOf(pageSize));
        } else {
            param.put("pageSize",50);
        }
        String pageNumber = request.getParameter("pageNumber");
        if (StringUtils.isNotBlank(pageNumber)) {
            if (Integer.valueOf(pageNumber) <= 1) {
                param.put("pageNumber",0);
            } else {
                param.put("pageNumber",Integer.valueOf(pageNumber));
            }
        } else {
            param.put("pageNumber",0);
        }
        String roomId = request.getParameter("roomId");
        if(StringUtils.isNotBlank(roomId)){
            param.put("roomId",roomId);
        }

        String isVip = request.getParameter("isVip");
        if(StringUtils.isNotBlank(isVip)) {
            param.put("isVip", isVip);
        }
        String createTime = request.getParameter("createTimeStart");
        if(StringUtils.isNotBlank(createTime)){
            param.put("createTimeStart", DateUtils.getLongByDateString(createTime));
        }
        String createTimeEnd = request.getParameter("crateTimeEnd");
        if(StringUtils.isNotBlank(createTimeEnd)){
            param.put("createTimeEnd", DateUtils.getLongByString(createTimeEnd+" 23:59:59"));
        }
        return param;
    }


}
