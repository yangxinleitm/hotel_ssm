package cn.hotel.controller;

import cn.hotel.controller.utils.JsonModel;
import cn.hotel.entity.model.AdminInfoResponse;
import cn.hotel.service.utils.RestUtils;
import cn.hotel.entity.Enum.SysResponse;
import cn.hotel.entity.model.AdminInfoRequest;
import cn.hotel.service.AdminInfoService;
import cn.hotel.service.utils.RestModel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.List;

/*
   管理员登录信息查询
   逻辑：
   <--------------------
    管理员进行登录，查询管理员表，如果表中存在，则登录成功，进入后台主页面，同时把信息放入到session中
    否则，则弹出提示框提示
   --------------------->
 */

@Controller
public class LoginController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

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
                    session.setAttribute("admin",model);
                    session.setAttribute("adminId",model.getAdminId());
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


    @RequestMapping("/admin/adminUser/logout.action")
    public ModelAndView logoutHandler(HttpSession session, HttpServletResponse response) throws UnsupportedEncodingException {
        ModelAndView modelAndView = new ModelAndView();
        session.removeAttribute("admin");
        Cookie login_cookie = new Cookie("userName", URLEncoder.encode("2", "utf-8") );
        Cookie password_cookie = new Cookie("password",URLEncoder.encode("", "utf-8") );
        response.addCookie(login_cookie);
        response.addCookie(password_cookie);
        modelAndView.setViewName("forward:index.jsp");
        return modelAndView;
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
