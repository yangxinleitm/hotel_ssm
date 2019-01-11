package cn.hotel.controller;

import cn.hotel.controller.utils.JsonModel;
import cn.hotel.entity.model.AdminInfoResponse;
import cn.hotel.service.utils.RestUtils;
import cn.hotel.entity.Enum.SysResponse;
import cn.hotel.entity.model.AdminInfoRequest;
import cn.hotel.service.AdminInfoService;
import cn.hotel.service.utils.RestModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController extends BaseController{

    @Autowired
    private AdminInfoService adminInfoService;

    //管理员登录
    @RequestMapping(value = "/htm/loginManageUser.action",method = RequestMethod.POST)
    @ResponseBody
    public JsonModel adminLogin(HttpServletRequest request, HttpSession session){
        List <AdminInfoResponse> list = new ArrayList <>();
        JsonModel jsonModel = new JsonModel();
        AdminInfoRequest adminInfoRequest = getSearchParam(request);
        RestModel restModel = adminInfoService.findAllAdminInfo(adminInfoRequest);
        if(restModel.getCode().toString().equals(SysResponse.RECORD_CODE.RESPONSE_SUCCESS.get().toString())){
            List <AdminInfoResponse> modelList = RestUtils.getModelList(restModel, AdminInfoResponse.class);
            for(AdminInfoResponse model : modelList){
                if(model.getAdminName().equals(adminInfoRequest.getAdminName())
                    && model.getAdminPwd().toString().equals(adminInfoRequest.getAdminPwd())){

                    //登录成功，把结果放在session中
                    session.setAttribute("adminInfo",model);
                    jsonModel.setStatus(true);
                    jsonModel.setMessage("成功");
                    jsonModel.setResult(model);
                }else{
                    jsonModel.setResult(false);
                    jsonModel.setMessage("失败");
                    return jsonModel;
                }
            }
        }
        return jsonModel;
    }

    private AdminInfoRequest getSearchParam(HttpServletRequest request) {
        AdminInfoRequest adminInfoRequest = new AdminInfoRequest();
        String userName = request.getParameter("userName");
        if(StringUtils.isNotBlank(userName)){
            adminInfoRequest.setAdminName(userName);
        }
        String password = request.getParameter("password");
        if(StringUtils.isNotBlank(password)){
            adminInfoRequest.setAdminPwd(password);
        }
        adminInfoRequest.setPageNumber(Long.valueOf(0));
        adminInfoRequest.setPageSize(Long.valueOf(100));
        return adminInfoRequest;
    }
}
