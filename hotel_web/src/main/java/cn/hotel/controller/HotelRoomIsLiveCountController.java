package cn.hotel.controller;

import cn.hotel.entity.CustomerDto;
import cn.hotel.entity.CustomerOrder;
import cn.hotel.entity.Room;
import cn.hotel.entity.model.PagerModel;
import cn.hotel.service.CustomerOrderService;
import cn.hotel.service.CustomerService;
import cn.hotel.service.HotelRoomService;
import cn.hotel.service.utils.DateUtils;
import cn.hotel.service.utils.RestModel;
import cn.hotel.service.utils.RestUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.StreamSupport;

/**

* @Description:    酒店客房入住统计
* @Author:         yangxinlei
* @CreateDate:     2019/1/17 17:02

  多个客户可以入住一个客房
    n----->1

*/


@Controller
public class HotelRoomIsLiveCountController {

    private static final Logger logger = LoggerFactory.getLogger(HotelRoomIsLiveCountController.class);


    //跳转到酒店事务管理 -- 酒店客房信息
    @RequestMapping(value = "/htm/hotel/hotelIsLive.action")
    public String hotelRoomInfo(HttpServletRequest request){
        return  "hotelRoomIsLiveCount";
    }

    @Autowired
    private HotelRoomService hotelRoomService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerOrderService orderService;


    //客房入住情况统计
    @RequestMapping(value = "/htm/hotelRoomIsLiveCount.action",method = RequestMethod.GET)
    @ResponseBody
    public PagerModel<List<Map<String,Object>>> getCustomer(HttpServletRequest request) {
        PagerModel <List <Map<String,Object>>> pager = new PagerModel <>();
        List <Map<String,Object>> list = new ArrayList<>();
        Long count = 0L;

        List <String> userIdList = new ArrayList <>();

        Map<String, Object> param = getSearchParam(request);
        if(param.get("userId") !=null && param.get("roomId")==null){
            //用roomId去查询
            CustomerDto customerDto = new CustomerDto();
            customerDto.setUserId(Long.valueOf(param.get("userId").toString()));
            logger.info("多条件查询客户信息传入的参数 param={}",JSON.toJSONString(customerDto));
            RestModel restModel = customerService.selectCustomerByPrimkey(customerDto);
            logger.info("多条件查询客户信息返回结果为 restModel = {}",JSON.toJSONString(restModel));
            JSONObject model = RestUtils.getModel(customerService.selectCustomerByPrimkey(customerDto), JSONObject.class);
            if(model!=null){
                Map <String, Object> info = new HashMap <>();
                info.put("userId",model.get("userId"));           //客户ID
                info.put("userName",model.get("userName"));       //客户名
                //获取roomId,用户roomId去查询客房信息
                if(model.get("roomId") !=null){
                    Room room = new Room();
                    room.setRoomId(Integer.valueOf(model.get("roomId").toString()));
                    logger.info("多条件查询客房信息传入的参数 param={}",JSON.toJSONString(room));
                    restModel = hotelRoomService.selectRoomInfoById(room);
                    logger.info("多条件查询客房信息返回结果为 restModel = {}",JSON.toJSONString(restModel));
                    if(restModel.getData() !=null){
                        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(restModel.getData()));
                        info.put("roomId",jsonObject.get("roomId"));
                        info.put("roomNo",jsonObject.get("roomNo"));
                        info.put("roomType",jsonObject.get("roomType"));
                        info.put("isVip",jsonObject.get("isVip"));
                        info.put("createTime",jsonObject.get("createTime"));
                        info.put("modifyTime",jsonObject.get("modifyTime"));
                        //计算入住时间
                        Long liveTime = Long.valueOf(jsonObject.get("modifyTime").toString()) - Long.valueOf(jsonObject.get("createTime").toString());
                        info.put("liveTime",liveTime);
                        info.put("vipPrice",jsonObject.get("vipPrice"));
                    }
                }
                list.add(info);
            }
        }else if(param.get("userId")==null && param.get("roomId")!=null){
         /*   CustomerOrder customerOrder = new CustomerOrder();
            customerOrder.setRoomId(Integer.valueOf(param.get("roomId").toString()));
            List <CustomerOrder> customerOrders = orderService.selectByExample(customerOrder);
            CustomerDto customerDto = new CustomerDto();
            for(CustomerOrder model : customerOrders){
                Map <String, Object> map = new HashMap <>();
                Integer roomId = model.getRoomId();
                if(roomId >0){
                    //用户roomId去查room信息
                    Room room = new Room();
                    room.setRoomId(roomId);
                    RestModel restModel = hotelRoomService.selectRoomInfoById(room);
                    if(restModel.getData()!=null){
                        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(restModel.getData()));
                        map.put("roomId",jsonObject.get("roomId"));
                        map.put("roomNo",jsonObject.get("roomNo"));
                        map.put("roomType",jsonObject.get("roomType"));
                        map.put("isVip",jsonObject.get("isVip"));
                        map.put("createTime",jsonObject.get("createTime"));
                        map.put("modifyTime",jsonObject.get("modifyTime"));
                        //计算入住时间
                        Long liveTime = Long.valueOf(jsonObject.get("modifyTime").toString()) - Long.valueOf(jsonObject.get("createTime").toString());
                        map.put("liveTime",liveTime);map.put("vipPrice",jsonObject.get("vipPrice"));

                        //获取用户的id,
                        Long userId = model.getUserId();
                        userIdList.add(String.valueOf(userId));
                        customerDto.setUserId(Long.valueOf(userId));
                        restModel = customerService.selectCustomerByPrimkey(customerDto);
                        jsonObject = JSON.parseObject(JSON.toJSONString(restModel.getData()));
                        map.put("userId",jsonObject.get("userId"));
                        map.put("userName",jsonObject.get("userName"));
                        list.add(map);
                    }else{
                        pager.setTotal(Long.valueOf(0));
                        pager.setPageData(new ArrayList <>());
                        return pager;
                    }
                }
            }
*/
        }
        pager.setPageData(list);
        pager.setTotal(Long.valueOf(list.size()));
        return pager;
    }

    private Map<String, Object> getSearchParam(HttpServletRequest request) {
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
        String searchUserId = request.getParameter("searchUserId");
        if(StringUtils.isNotBlank(searchUserId)){
            param.put("userId",searchUserId);
        }
        String searchRoomId = request.getParameter("searchRoomId");
        if(param.get("userId") ==null && StringUtils.isNoneBlank(searchRoomId)){
            param.put("roomId",searchRoomId);
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

