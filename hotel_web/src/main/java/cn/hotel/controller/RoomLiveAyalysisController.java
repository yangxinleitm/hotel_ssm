package cn.hotel.controller;

import cn.hotel.entity.model.PagerModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.*;
import java.util.List;

@Controller
public class RoomLiveAyalysisController {

    @RequestMapping(value = "/htm/hotel/roomLiveAyalysis.action")
    public String roomLiveAyalysis(HttpServletRequest request){
        return  "roomLiveAyalysis";
    }

    @RequestMapping(value = "/htm/hotel/roomLiveAyalysisDisplay.action",method = RequestMethod.GET)
    @ResponseBody
    public PagerModel<List<Map<String,Object>>> getLiveAyalysisDisplay(HttpServletRequest request){
        PagerModel <List<Map<String,Object>>> pagerModel = new PagerModel <>();
        List <Map<String,Object>> list = new ArrayList <>();

        String  str = "[\"周一\",\"周二\",\"周三\",\"周四\",\"周五\",\"周六\"]";
        String  data ="[5, 20, 36, 10, 10, 20]";
        Map <String, Object> info = new HashMap <>();
        info.put("str",str);
        info.put("data1",data);
        list.add(info);
        pagerModel.setPageData(list);
        return pagerModel;
    }
}
