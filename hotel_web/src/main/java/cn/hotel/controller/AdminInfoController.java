package cn.hotel.controller;
import cn.hotel.entity.Enum.SysResponse;
import cn.hotel.entity.model.AdminInfoRequest;
import cn.hotel.entity.model.AdminInfoResponse;
import cn.hotel.entity.model.PagerModel;
import cn.hotel.service.AdminInfoService;
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
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Controller
public class AdminInfoController {
    private static final Logger logger = LoggerFactory.getLogger(AdminInfoController.class);

    @Autowired
    private AdminInfoService adminInfoService;

    //跳转到管理员界面
    @RequestMapping(value = "/htm/user/adminInfo.action")
    public String adminInfo(HttpServletRequest request){
        return  "adminInfo";
    }


    //管理员信息列表展示
    @RequestMapping(value = "/htm/adminInfoList.action",method = RequestMethod.GET)
    @ResponseBody
    public PagerModel<List<AdminInfoResponse>> getCustomer(HttpServletRequest request) {
        PagerModel <List <AdminInfoResponse>> pager = new PagerModel <>();
        List <AdminInfoResponse> list = new ArrayList<>();
        Long count = 0L;
        AdminInfoRequest adminInfoRequest = getSearchParam(request);

        logger.info("用户信息查询参数 param={}", JSON.toJSONString(adminInfoRequest));
        RestModel restModel = adminInfoService.findAllAdminInfo(adminInfoRequest);
        logger.info("用户信息查询返回结果 param={}", JSON.toJSONString(restModel));
        List data = (List) restModel.getData();
        if(restModel.getCode().equals(SysResponse.RECORD_CODE.RESPONSE_SUCCESS.get().toString())){

            logger.info("用户信息查询参数 param={}", JSON.toJSONString(adminInfoRequest));
            restModel = adminInfoService.countAdminInfoRecord(adminInfoRequest);
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


    private AdminInfoRequest getSearchParam(HttpServletRequest request) {

        AdminInfoRequest adminInfoRequest = new AdminInfoRequest();
        String pageSize = request.getParameter("pageSize");
        if (StringUtils.isNotBlank(pageSize)) {
            adminInfoRequest.setPageSize(Long.valueOf(pageSize));
        } else {
            adminInfoRequest.setPageSize(Long.valueOf(50));
        }
        String pageNumber = request.getParameter("pageNumber");
        if (StringUtils.isNotBlank(pageNumber)) {
            if (Integer.valueOf(pageNumber) <= 1) {
                adminInfoRequest.setPageNumber(Long.valueOf(0));
            } else {
                adminInfoRequest.setPageNumber(Long.valueOf(pageNumber));
            }
        } else {
            adminInfoRequest.setPageNumber(Long.valueOf(0));
        }

        String searchAdminId = request.getParameter("searchAdminId");
        if(StringUtils.isNotBlank(searchAdminId)){
            adminInfoRequest.setAdminId(Long.valueOf(searchAdminId));
        }
        String mobile = request.getParameter("searchMobile");
        if(StringUtils.isNotBlank(mobile)){
            adminInfoRequest.setAdminMobile(Long.valueOf(mobile));
        }
        String createTime = request.getParameter("createTimeStart");
        if(StringUtils.isNotBlank(createTime)){
            adminInfoRequest.setCreateTimeStart(DateUtils.getLongByDateString(createTime));
        }
        String createTimeEnd = request.getParameter("crateTimeEnd");
        if(StringUtils.isNotBlank(createTimeEnd)){
            adminInfoRequest.setCreateTimeEnd(DateUtils.getLongByString(createTimeEnd+" 23:59:59"));
        }
        return adminInfoRequest;
    }

}
