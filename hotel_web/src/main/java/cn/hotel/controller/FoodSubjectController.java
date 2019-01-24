package cn.hotel.controller;

import cn.hotel.entity.FoodSubjectDto;
import cn.hotel.entity.model.PagerModel;
import cn.hotel.service.FoodSubjectService;
import cn.hotel.service.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.*;

@Controller
public class FoodSubjectController {

    @Autowired
    private FoodSubjectService foodSubjectService;

    //跳转到食物科目页面
    @RequestMapping(value = "/htm/food/hoteFoodlSubject.action")
    public String hotelFoodManage(HttpServletRequest request){
        return "hotelSubjectManage";
    }

    //食物科目管理列表
    @RequestMapping("/hotel/food/foodSubjectList.action")
    @ResponseBody
    public PagerModel<List<FoodSubjectDto>> foodClassList(HttpServletRequest request){
        PagerModel <List<FoodSubjectDto>> pager = new PagerModel <>();
        List <FoodSubjectDto> list = new ArrayList<>();
        Long count = 0L;
        pager.setTotal(count);
        pager.setPageData(list);
        Map<String, Object> param = getSearchParam(request);
        List <FoodSubjectDto> foodSubjectDtos = foodSubjectService.selectFoodSubjectRecord(param);
        if(foodSubjectDtos !=null && foodSubjectDtos.size()>0){
            pager.setTotal(Long.valueOf(foodSubjectDtos.size()));
            pager.setPageData(foodSubjectDtos);
            return pager;
        }
        return pager;
    }

    private Map<String, Object> getSearchParam(HttpServletRequest request) {
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
        String searchFoodSubjectId = request.getParameter("searchFoodSubjectId");
        if(searchFoodSubjectId !=null && StringUtils.isNotBlank(searchFoodSubjectId)){
            param.put("foodSubjectId",searchFoodSubjectId);
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
