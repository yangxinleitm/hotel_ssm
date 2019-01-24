package cn.hotel.controller;

import cn.hotel.controller.utils.JsonModel;
import cn.hotel.entity.Enum.SysResponse;
import cn.hotel.entity.model.AdminInfoRequest;
import cn.hotel.entity.model.AdminInfoResponse;
import cn.hotel.service.utils.RestModel;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**

* @Description:    修改密码
* @Author:         田梦
* @CreateDate:     2019/1/22 19:10


*/
@Controller
public class AdminUpdatePwdController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(AdminUpdatePwdController.class);

    //修改密码
    @RequestMapping(value = "/htm/base/updatePwd.action")
    public String hotelRoomInfo(HttpServletRequest request){
        return  "adminUpdatePwd";
    }

    //修改密码操作---初始化数据
    @RequestMapping(value = "/htm/base/initAdminData.action",method = RequestMethod.POST)
    @ResponseBody
    public JsonModel initAdminData(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        JsonModel jsonModel = new JsonModel();
        String adminName = request.getParameter("adminName");
        if(adminName !=null && StringUtils.isNotBlank(adminName)){
            //用户adminName进行查询
            AdminInfoRequest adminiRequest = new AdminInfoRequest();
            adminiRequest.setAdminName(adminName);
            adminiRequest.setPageSize(Long.valueOf(999));
            adminiRequest.setPageNumber(Long.valueOf(0));
            logger.info("修改密码操作传入参数为  param={}", JSON.toJSONString(adminiRequest));
            RestModel restModel = adminInfoService.findAllAdminInfo(adminiRequest);
            logger.info("修改密码操作返回数据结果为 restModel={}", JSON.toJSONString(restModel));
            if(SysResponse.RECORD_CODE.RESPONSE_SUCCESS.get().equals(restModel.getCode())
                && restModel.getData()!=null){
                AdminInfoResponse adminInfoResponse = (AdminInfoResponse)restModel.getData();
                session.setAttribute("adminId",adminInfoResponse.getAdminId());
                jsonModel.setStatus(true);
                jsonModel.setMessage("成功");
                jsonModel.setResult(session);
            }
        }else{
            jsonModel.setStatus(false);
            jsonModel.setMessage("失效的页面操作，失败");
            jsonModel.setResult(adminName);
        }
        return jsonModel;
    }

    //修改密码
    @RequestMapping(value = "/htm/base/adminUpdatePwd.action",method = RequestMethod.POST)
    @ResponseBody
    public JsonModel adminUpdatePwd(HttpServletRequest request, HttpSession session,
                                    @RequestParam(value = "adminId" ,required = true) String adminId,
                                    @RequestParam(value = "adminRealName" ,required = true) String adminRealName,
                                    @RequestParam(value = "originalAdminPwd" ,required = true) String originalAdminPwd,
                                    @RequestParam(value = "newAdminPwd" ,required = true) String newAdminPwd){
        JsonModel jsonModel = new JsonModel();
        if(StringUtils.isEmpty(adminId)
            || StringUtils.isEmpty(adminRealName)
            || StringUtils.isEmpty(originalAdminPwd)
            || StringUtils.isEmpty(newAdminPwd)){
            jsonModel.setStatus(true);
            jsonModel.setMessage("所有的参数不能为空！");
            return jsonModel;
        }
        //查询数据库中密码和现在密码不一致
        AdminInfoRequest adminiRequest = new AdminInfoRequest();
        adminiRequest.setAdminName(adminRealName);
        adminiRequest.setAdminId(Long.valueOf(adminId));
        adminiRequest.setPageSize(Long.valueOf(999));
        adminiRequest.setPageNumber(Long.valueOf(0));
        logger.info("修改密码操作传入参数为  param={}", JSON.toJSONString(adminiRequest));
        RestModel restModel = adminInfoService.findAllAdminInfo(adminiRequest);
        logger.info("修改密码操作返回数据结果为 restModel={}", JSON.toJSONString(restModel));
        if(SysResponse.RECORD_CODE.RESPONSE_SUCCESS.get().toString().equals(restModel.getCode()) && restModel.getData()!=null){
            List<AdminInfoResponse> datas = (List) restModel.getData();
            for(int i=0;i<datas.size();i++){


                if(!(datas.get(0).getAdminPwd().equals(originalAdminPwd))){
                    jsonModel.setStatus(false);
                    jsonModel.setMessage("原始密码错误，请重新填写!");
                    return jsonModel;
                }
            }
        }
        //数据封装
        converAdminInfo(adminId,adminRealName,originalAdminPwd,newAdminPwd);

        return jsonModel;
    }

    private AdminInfoRequest converAdminInfo(String adminId, String adminRealName, String originalAdminPwd, String newAdminPwd) {
        AdminInfoRequest param = new AdminInfoRequest();
        if(adminId !=null && StringUtils.isNotBlank(adminId)){
            param.setAdminId(Long.valueOf(adminId));
        }
        if(adminRealName !=null && StringUtils.isNotBlank(adminRealName)){
            param.setAdminRealName(adminRealName);
        }
        param.setPageNumber(Long.valueOf(0));
        param.setPageSize(Long.valueOf(999));
        //原始密码
        if(originalAdminPwd !=null && StringUtils.isNotBlank(originalAdminPwd)){
            logger.info("修改密码操作传入参数为  param={}", JSON.toJSONString(param));
            RestModel restModel = adminInfoService.findAllAdminInfo(param);
            logger.info("修改密码操作返回数据结果为 restModel={}", JSON.toJSONString(restModel));
            if(SysResponse.RECORD_CODE.RESPONSE_SUCCESS.get().equals(restModel.getCode())
                    && restModel.getData()!=null){
                AdminInfoResponse adminInfoResponse = (AdminInfoResponse)restModel.getData();
                if(adminInfoResponse.getAdminRealName().equals(originalAdminPwd)){
                    param.setAdminPwd(originalAdminPwd);
                }else{
                    param.setAdminPwd("");
                }
            }
        }


        return param;
    }


}
