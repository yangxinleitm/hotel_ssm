package cn.hotel.controller;

import cn.hotel.entity.HotelMaterialDto;
import cn.hotel.entity.model.PagerModel;
import cn.hotel.service.HotelMaterialService;
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
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HotelMaterialManagerController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(HotelMaterialManagerController.class);

    @Autowired
    private HotelMaterialService hotelMaterialService;

    //跳转到酒店事务管理 -- 酒店客房信息
    @RequestMapping(value = "/htm/hotel/hotelMaterial.action")
    public String hotelMaterial(HttpServletRequest request){
        return  "hotelMaterialManager";
    }


    //酒店客房信息列表展示
    @RequestMapping(value = "/htm/hotelMaterialList.action",method = RequestMethod.GET)
    @ResponseBody
    public PagerModel<List<HotelMaterialDto>> getCustomer(HttpServletRequest request) {
        PagerModel <List <HotelMaterialDto>> pager = new PagerModel <>();
        List <HotelMaterialDto> list = new ArrayList<>();
        Long count = 0L;
        Map<String, Object> param = getSearchParam(request);
        logger.info("用户信息查询参数 param={}", JSON.toJSONString(param));
        List <HotelMaterialDto> hotelMaterialDtos = hotelMaterialService.selecHotelMaterialList(param);
        logger.info("用户信息查询返回结果 param={}", JSON.toJSONString(hotelMaterialDtos));
        if(hotelMaterialDtos!=null && hotelMaterialDtos.size()>0){
            count = hotelMaterialService.countHotelMaterialRecord(param);
        }
        pager.setPageData(hotelMaterialDtos);
        pager.setTotal(count);
        return pager;
    }

    private Map<String,Object> getSearchParam(HttpServletRequest request) {
        Map <String, Object> param = new HashMap<>();

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
