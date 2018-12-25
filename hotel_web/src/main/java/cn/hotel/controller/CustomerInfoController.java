package cn.hotel.controller;
import cn.hotel.entity.Enum.SysResponse;
import cn.hotel.service.utils.DateUtils;
import cn.hotel.service.utils.RestModel;
import cn.hotel.entity.model.CustomerInfoResponse;
import cn.hotel.entity.model.PagerModel;
import cn.hotel.service.CustomerService;
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

@Controller
public class CustomerInfoController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerInfoController.class);

    //跳转到用户信息页面
    @RequestMapping(value = "/htm/user/customerInfo.action")
    public String customerInfo(HttpServletRequest request){
        return  "customerInfo";
    }

    @Autowired
    private CustomerService  customerService;

    //用户信息列表展示
    @RequestMapping(value = "/htm/customerList.action",method = RequestMethod.GET)
    @ResponseBody
    public PagerModel<List<CustomerInfoResponse>> getCustomer(HttpServletRequest request) {
        PagerModel <List <CustomerInfoResponse>> pager = new PagerModel <>();
        List <CustomerInfoResponse> list = new ArrayList <>();
        Long count = 0L;
        Map <String, Object> param = getSearchParam(request);

        logger.info("用户信息查询参数 param={}", JSON.toJSONString(param));
        RestModel restModel = customerService.findAllCustomer(param);
        logger.info("用户信息查询返回结果 param={}", JSON.toJSONString(restModel));
        List data = (List) restModel.getData();
        if(restModel.getCode().equals(SysResponse.RECORD_CODE.RESPONSE_SUCCESS.get().toString())){

            logger.info("用户信息查询参数 param={}", JSON.toJSONString(param));
            restModel = customerService.countCustomerInfoRecord(param);
            logger.info("用户信息查询返回结果 param={}", JSON.toJSONString(restModel));
            count = Long.valueOf(restModel.getData().toString());
            pager.setTotal(count);
            pager.setPageData(data);
            return pager;
        }
        pager.setTotal(count);
        pager.setPageData(data);
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

        String searchUserId = request.getParameter("searchUserId");
        if(StringUtils.isNotBlank(searchUserId)){
            param.put("userId",searchUserId);
        }
        String mobile = request.getParameter("searchMobile");
        if(StringUtils.isNotBlank(mobile)){
            param.put("userMobile",mobile);
        }
        String isVip = request.getParameter("searchIsVip");
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
