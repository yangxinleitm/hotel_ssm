package cn.hotel.controller;

import cn.hotel.controller.utils.JsonModel;
import cn.hotel.entity.FoodClassDto;
import cn.hotel.entity.model.PagerModel;
import cn.hotel.service.FoodClassService;
import cn.hotel.service.utils.DateUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class FoodClassController extends BaseController{

    @Autowired
    private FoodClassService foodClassService;

    //跳转到食品类别页面
    @RequestMapping(value = "/htm/food/hoteFoodlClass.action")
    public String hotelFoodManage(HttpServletRequest request){
        return "hotelFoodClassManage";
    }

    //食物类别列表
    @RequestMapping("/hotel/food/foodClassList.action")
    @ResponseBody
    public PagerModel<List<FoodClassDto>>  foodClassList(HttpServletRequest request){
        PagerModel <List<FoodClassDto>> pager = new PagerModel <>();
        List <FoodClassDto> list = new ArrayList<>();
        Long count = 0L;
        pager.setTotal(count);
        pager.setPageData(list);
        Map<String, Object> param = getSearchParam(request);
        List <FoodClassDto> foodClassDtos = foodClassService.selectAllFoodClass(param);
        if(foodClassDtos !=null && foodClassDtos.size()>0){
            pager.setTotal(Long.valueOf(foodClassDtos.size()));
            pager.setPageData(foodClassDtos);
            return pager;
        }
        return pager;
    }


    //设置状态
    @RequestMapping(value = "/htm/food/updateFoodClassStatus.action",method = RequestMethod.POST)
    @ResponseBody
    public JsonModel updateFoodClassStatus(HttpServletRequest request,
                                           @RequestParam(value = "foodClassId",required = true) String foodClassId,
                                           @RequestParam(value = "status",required = true) String status){
        JsonModel jsonModel = new JsonModel();
        if(StringUtils.isEmpty(foodClassId)
            || StringUtils.isEmpty(status)){
            jsonModel.setStatus(false);
            jsonModel.setMessage("传入的参数不能为空！");
            return  jsonModel;
        }
        FoodClassDto foodClassDto = new FoodClassDto();
        foodClassDto.setFoodClassId(Long.valueOf(foodClassId));
        foodClassDto.setIsDelete(1);
        foodClassDto.setModifyTime(System.currentTimeMillis());
        int count = foodClassService.updateFoodClass(foodClassDto);
        if(count >0){
            jsonModel.setStatus(true);
            jsonModel.setMessage("修改成功");
            return jsonModel;
        }
        return jsonModel;
    }

    //类别添加
    @RequestMapping(value = "/htm/food/foodClassAdd.action",method = RequestMethod.POST)
    @ResponseBody
    public JsonModel foodClassAdd(HttpServletRequest request,HttpSession session,
                                    @RequestParam(value = "foodClassId",required = true) String foodClassId,
                                    @RequestParam(value = "foodClassNo",required = true) String foodClassNo,
                                    @RequestParam(value = "foorClassName",required = true) String foorClassName,
                                    @RequestParam(value = "foodSubjectId",required = true) String foodSubjectId,
                                    @RequestParam(value = "isDelete",required = true) String isDelete){
        JsonModel jsonModel = new JsonModel();
        if(StringUtils.isEmpty(foodClassId)
            || StringUtils.isEmpty(foodClassNo)
            || StringUtils.isEmpty(foorClassName)
            || StringUtils.isEmpty(foodSubjectId)
            || StringUtils.isEmpty(isDelete)){
            jsonModel.setStatus(false);
            jsonModel.setMessage("所有的参数不能为空！");
            return jsonModel;
        }
        FoodClassDto foodClassDto = new FoodClassDto();
        foodClassDto.setFoodClassId(Long.valueOf(foodClassId));
        foodClassDto.setFoodClassNo(foodClassNo);
        foodClassDto.setFoodClassName(foorClassName);
        foodClassDto.setFoodSubjectId(foodSubjectId);
        foodClassDto.setIsDelete(0);
        foodClassDto.setApplyUserId((Long) session.getAttribute("adminId"));
        foodClassDto.setAuthUserId(Long.valueOf(0));
        foodClassDto.setCreateTime(System.currentTimeMillis());
        foodClassDto.setModifyTime(Long.valueOf(0));
        int i = foodClassService.foodClasAdd(foodClassDto);
        if(i>0){
            jsonModel.setStatus(true);
            jsonModel.setMessage("添加成功");
            return jsonModel;
        }else{
            jsonModel.setStatus(false);
            jsonModel.setMessage("添加失败");
            return jsonModel;
        }
    }



    //产生一个随机数，作为类编号
    @RequestMapping(value = "/htm/food/getFoodClassNoByUUID.action",method = RequestMethod.POST)
    @ResponseBody
    public List<JSONObject> getClassNoUUID(HttpSession session){
        List <JSONObject> list = new ArrayList <>();
        JSONObject jsonObject = new JSONObject();
        UUID uuid = UUID.randomUUID();
        jsonObject.put("uuid",uuid);
        list.add(jsonObject);
        return list;
    }


    //获取参数
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
