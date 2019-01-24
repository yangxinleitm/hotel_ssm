package cn.hotel.controller;
import cn.hotel.entity.HotelFoodDto;
import cn.hotel.entity.model.PagerModel;
import cn.hotel.service.HotelFoodService;
import cn.hotel.service.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class HotelFoodManagerController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(HotelFoodManagerController.class);

    @Autowired
    private HotelFoodService foodService;

    //跳转到食品管理页面
    @RequestMapping(value = "/htm/food/hotelFoodManage.action")
    public String hotelFoodManage(HttpServletRequest request){
        return "hotelFoodManage";
    }

    @RequestMapping(value = "/hotel/food/findHotelFood.action")
    @ResponseBody
    public PagerModel<List<HotelFoodDto>> getAllPageModel(HttpServletRequest request){
        PagerModel <List<HotelFoodDto>> pager = new PagerModel <>();
        List <HotelFoodDto> list = new ArrayList <>();
        Long count = 0L;
        pager.setTotal(count);
        pager.setPageData(list);
        Map <String, Object> param = getSearchParam(request);
        List <HotelFoodDto> hotelFoodDtos = foodService.selectHotelFoodRecord(param);
        if(hotelFoodDtos !=null && hotelFoodDtos.size()>0){
            pager.setTotal(Long.valueOf(hotelFoodDtos.size()));
            pager.setPageData(hotelFoodDtos);
            return pager;
        }
        return pager;
    }

    private Map<String, Object> getSearchParam(HttpServletRequest request) {
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
        String searchFoodClass = request.getParameter("searchFoodClass");
        if(searchFoodClass !=null && StringUtils.isNotBlank(searchFoodClass)){
            param.put("foodClassId",searchFoodClass);
        }
        String searchFoodId = request.getParameter("searchFoodId");
        if(searchFoodId !=null && StringUtils.isNotBlank(searchFoodId)){
            param.put("foodId",searchFoodId);
        }
        String searchFoodSubject = request.getParameter("searchFoodSubject");
        if(searchFoodSubject !=null && StringUtils.isNotBlank(searchFoodSubject)){
            param.put("foodSubjectId",searchFoodSubject);
        }

        String createTime = request.getParameter("createTimeStart");
        if(StringUtils.isNotBlank(createTime)){
            param.put("createTime",DateUtils.getLongByDateString(createTime));
        }
        String createTimeEnd = request.getParameter("crateTimeEnd");
        if(StringUtils.isNotBlank(createTimeEnd)){
            param.put("createTimeEnd",DateUtils.getLongByString(createTimeEnd+" 23:59:59"));
        }
        return param;
    }

}
