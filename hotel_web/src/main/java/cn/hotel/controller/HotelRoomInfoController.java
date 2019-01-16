package cn.hotel.controller;

import cn.hotel.controller.utils.JsonModel;
import cn.hotel.entity.Room;
import cn.hotel.entity.model.PagerModel;
import cn.hotel.service.HotelRoomService;
import cn.hotel.service.utils.DateUtils;
import cn.hotel.service.utils.RestModel;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
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

    //酒店客房添加记录
    @RequestMapping(value = "/htm/hotelRoomInfoAdd.action",method = RequestMethod.POST)
    @ResponseBody
    public JsonModel hotelRoomInfoAdd(HttpServletRequest request,
                                      @RequestParam(value = "roomNo",required = true) String roomNo,
                                      @RequestParam(value = "roomType",required = true) String roomType,
                                      @RequestParam(value = "isClean",required = true) String isClean,
                                      @RequestParam(value = "isLive",required = true) String isLive,
                                      @RequestParam(value = "roomArea",required = true) String roomArea,
                                      @RequestParam(value = "isVip",required = true) String isVip){
        JsonModel jsonModel = new JsonModel();
        if(StringUtils.isEmpty(roomType)
            || StringUtils.isEmpty(roomNo)
            || StringUtils.isEmpty(isClean)
            || StringUtils.isEmpty(isLive)
            || StringUtils.isEmpty(roomArea)
            || StringUtils.isEmpty(isVip)){
            jsonModel.setStatus(false);
            jsonModel.setMessage("所传参数不能为空!");
            return  jsonModel;
        }
        //数据封装
        Map <String, Object> parms = converParms(roomNo, roomType, isClean, isLive, roomArea, isVip);
        RestModel restModel = hotelRoomService.addHotelRoomInfoRecrd(parms);
        if(restModel.getCode().toString().equals("200")){
            jsonModel.setStatus(true);
            jsonModel.setMessage("添加成功!");
            return jsonModel;
        }
        jsonModel.setStatus(false);
        jsonModel.setMessage("添加失败!");
        return jsonModel;
    }


    //酒店客房修改记录
    @RequestMapping(value = "/htm/hotelRoomInfoModify.action",method = RequestMethod.POST)
    @ResponseBody
    public JsonModel hotelRoomInfoModify(HttpServletRequest request,
                                      @RequestParam(value = "modifyRoomId",required = true) String modifyRoomId,
                                      @RequestParam(value = "modifyRoomNo",required = true) String modifyRoomNo,
                                      @RequestParam(value = "modifyRoomType",required = true) String modifyRoomType,
                                      @RequestParam(value = "modifyIsClean",required = true) String modifyIsClean,
                                      @RequestParam(value = "modifyRoomArea",required = true) String modifyRoomArea,
                                      @RequestParam(value = "modifyIsLive",required = true) String modifyIsLive,
                                      @RequestParam(value = "modifyIsVip",required = true) String modifyIsVip,
                                      @RequestParam(value = "modifyVipPrice",required = true) String modifyVipPrice){
        JsonModel jsonModel = new JsonModel();
        if(StringUtils.isEmpty(modifyRoomId)
                || StringUtils.isEmpty(modifyRoomNo)
                || StringUtils.isEmpty(modifyRoomType)
                || StringUtils.isEmpty(modifyIsClean)
                || StringUtils.isEmpty(modifyRoomArea)
                || StringUtils.isEmpty(modifyIsLive)
                || StringUtils.isEmpty(modifyIsVip)
                || StringUtils.isEmpty(modifyVipPrice)){
            jsonModel.setStatus(false);
            jsonModel.setMessage("所传参数不能为空!");
            return  jsonModel;
        }
        //数据封装
        Room room = getRoom(modifyRoomId, modifyRoomNo, modifyRoomType, modifyIsClean, modifyRoomArea, modifyIsLive, modifyIsVip, modifyVipPrice);
        logger.info("修改客房信息的传入的参数",JSON.toJSONString(room));
        RestModel restModel = hotelRoomService.updateRoomInfoRecord(room);
        logger.info("修改客房信息的返回的参数",JSON.toJSONString(restModel));
        if(restModel.getCode().equals("200")){
            jsonModel.setStatus(false);
            jsonModel.setMessage("修改信息成功!");
            return jsonModel;
        }
        jsonModel.setStatus(false);
        jsonModel.setMessage("修改信息失败!");
        return jsonModel;
    }

    private Room getRoom(String modifyRoomId, String modifyRoomNo, String modifyRoomType, String modifyIsClean, String modifyRoomArea,
                         String modifyIsLive, String modifyIsVip, String modifyVipPrice) {
        Room room = new Room();
        room.setRoomId(Integer.valueOf(modifyRoomId));
        room.setRoomNo(Long.valueOf(modifyRoomNo));
        room.setRoomType(modifyRoomType);
        room.setIsClean(modifyIsClean);
        room.setRoomArea(modifyRoomArea);
        room.setIsLive(modifyIsLive);
        room.setIsVip(modifyIsVip);
        room.setVipPrice(new BigDecimal(modifyVipPrice));
        return room;
    }



    //数据封装
    private Map<String,Object> converParms(String roomNo, String roomType, String isClean, String isLive, String roomArea, String isVip) {
        HashMap <String, Object> param = new HashMap <>();
        param.put("roomNo",roomNo);
        param.put("roomType",roomType);
        param.put("isClean",isClean);
        param.put("isLive",isLive);
        param.put("roomDetail","json串");
        param.put("roomArea",roomArea);
        param.put("isVip",isVip);
        param.put("hotelId",1);
        param.put("vipPrice",0);
        param.put("createTime",System.currentTimeMillis());
        param.put("modify",System.currentTimeMillis());
        return param;
    }


    private Map<String,Object> getSearchParam(HttpServletRequest request) {
        Map <String, Object> param = new HashMap <>();

        String pageSize = request.getParameter("pageSize");
        if (StringUtils.isNotBlank(pageSize)) {
            param.put("pageSize",Integer.valueOf(pageSize));
        } else {
            param.put("pageSize",20);
        }
        String pageNumber = request.getParameter("pageNumber");
        if (StringUtils.isNotBlank(pageNumber)) {
            if (Integer.valueOf(pageNumber) <= 1) {
                param.put("pageNumber",0);
            } else {
                param.put("pageNumber",(Long.valueOf(pageNumber) - 1) * Long.valueOf(pageSize));
            }
        } else {
            param.put("pageNumber",0);
        }
        String roomId = request.getParameter("roomId");
        if(StringUtils.isNotBlank(roomId)){
            param.put("roomId",roomId);
        }

        String searchIsVip = request.getParameter("searchIsVip");
        if(StringUtils.isNotBlank(searchIsVip)) {
            param.put("isVip", searchIsVip);
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
