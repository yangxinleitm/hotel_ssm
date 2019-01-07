package cn.hotel.controller;
import cn.hotel.controller.utils.JsonModel;
import cn.hotel.entity.AdminDto;
import cn.hotel.entity.Enum.SysResponse;
import cn.hotel.entity.model.AdminInfoRequest;
import cn.hotel.entity.model.AdminInfoResponse;
import cn.hotel.entity.model.PagerModel;
import cn.hotel.service.AdminInfoService;
import cn.hotel.service.utils.DateUtils;
import cn.hotel.service.utils.RestModel;
import com.alibaba.fastjson.JSON;
import com.google.common.io.Files;
import org.apache.commons.codec.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
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



    //添加记录
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public JsonModel adminUserAdd(HttpServletRequest request,@RequestParam(value = "adminId",required = true) String adminId,
                                                             @RequestParam(value = "adminName",required = true) String adminName,
                                                             @RequestParam(value = "adminPwd",required = true) String adminPwd,
                                                             @RequestParam(value = "adminRealName",required = true) String adminRealName,
                                                             @RequestParam(value = "adminIdCard",required = true) String adminIdCard,
                                                             @RequestParam(value = "adminIdType",required = true) String adminIdType,
                                                             @RequestParam(value = "adminNation",required = true) String adminNation,
                                                             @RequestParam(value = "createTime",required = true) String createTime,
                                                             @RequestParam(value = "adminAdress",required = true) String adminAdress){
        JsonModel jsonModel = new JsonModel();
        if(StringUtils.isBlank(adminId)
            || StringUtils.isBlank(adminName)
            || StringUtils.isBlank(adminPwd)
            || StringUtils.isBlank(adminNation)
            || StringUtils.isBlank(adminRealName)
            || StringUtils.isBlank(adminIdCard)
            || StringUtils.isBlank(adminIdType)
            || StringUtils.isBlank(createTime)
            || StringUtils.isBlank(adminAdress)){
        }
        jsonModel.setStatus(false);
        jsonModel.setMessage("所传参数不能为空！");
        return jsonModel;

//        Map <String, Object> map = AddAdminUserInfo(adminId, adminName, adminPwd, adminNation,
//                adminRealName, adminIdCard, adminIdType, createTime, adminAdress);
//        logger.info("添加操作记录的时间传入的参数 param={}",JSON.toJSONString(map));
//        RestModel restModel = adminInfoService.addAdminUserInfo(map);


    }

    private Map<String,Object> AddAdminUserInfo(String adminId, String adminName, String adminPwd, String adminNation, String adminRealName, String adminIdCard,
                                  String adminIdType, String createTime, String adminAdress) {
        Map <String, Object> map = new HashMap <String, Object>();
        map.put("adminId",adminId);
        map.put("adminName",adminName);
        map.put("adminPwd",adminPwd);
        map.put("adminNation",adminNation);
        map.put("adminRealName",adminRealName);
        map.put("adminIdCard",adminIdCard);
        map.put("adminIdType",adminIdType);
        map.put("createTime",createTime);
        map.put("adminAdress",adminAdress);
        return map;
    }



    //导出数据文件
    @RequestMapping(value = "/htm/exportAdminUserInfoFile.action",method = RequestMethod.POST)
    @ResponseBody
    public JsonModel exportExcel(HttpServletRequest request){
        JsonModel jsonModel = new JsonModel();
        Long count = 0L;
        AdminInfoRequest adminInfoRequest = getSearchParam(request);
        logger.info("用户信息查询参数 param={}", JSON.toJSONString(adminInfoRequest));
        RestModel restModel = adminInfoService.findAllAdminInfo(adminInfoRequest);
        logger.info("用户信息查询返回结果 param={}", JSON.toJSONString(restModel));
        List<AdminDto> data = (List) restModel.getData();
        if(restModel.getCode().equals(SysResponse.RECORD_CODE.RESPONSE_SUCCESS.get().toString())){
            try{
                //应当前的时间戳定义导出文件的名称
                String fileName = String.valueOf(System.currentTimeMillis());
                //写文件头
                File exportFile = new File("F://" + fileName + ".csv");
                //Files是gua的架包
                Files.append(new String(new byte[]{(byte)0xEF, (byte)0xBB, (byte)0xBF}, "UTF-8"), exportFile, Charsets.UTF_8);//对文件添加BOM头后，用excel打开即可无乱码
                Files.append("管理员ID,管理员呢程,管理员密码,管理员真实姓名,民族,身份证,手机号,身份证类型,是否在岗,创建时间,修改时间\r\n", exportFile, Charsets.UTF_8);
                StringBuilder sb = new StringBuilder();
                for(AdminDto model : data){
                    sb.append(model.getAdminId()==null? "":model.getAdminId()).append(",");
                    sb.append(model.getAdminName()==null? "":model.getAdminName()).append(",");
                    sb.append(model.getAdminPwd()==null? "":model.getAdminPwd()).append(",");
                    sb.append(model.getAdminRealName()==null? "":model.getAdminRealName()).append(",");
                    sb.append(model.getAdminNation()==null?"":model.getAdminNation()).append(",");
                    sb.append(model.getAdminIdCard()==null? "":model.getAdminIdCard()).append(",");
                    sb.append(model.getAdminMobile()==null ? "":model.getAdminMobile()).append(",");
                    sb.append(model.getAdminIdCardType()==null? "":model.getAdminIdCardType()).append(",");
                    sb.append(model.getAdminIsPostion()==null?"":model.getAdminIsPostion()).append(",");
                    sb.append(model.getCreateTime()==null? "":model.getCreateTime()).append(",");
                }
                Files.append(sb.toString(),exportFile, Charsets.UTF_8);
                InputStream input = new FileInputStream(exportFile);
              //  exportFile.delete();
                jsonModel.setMessage(input.toString());
                jsonModel.setStatus(true);
            }catch (Exception ex){
                logger.info("导出异常");
                jsonModel.setStatus(false);
                jsonModel.setMessage("导出异常");
            }
        }
        return jsonModel;
    }


    //删除记录
    @RequestMapping(value = "/htm/adminInfoDelete.action",method = RequestMethod.POST)
    @ResponseBody
    public JsonModel adminInfoDelete(HttpServletRequest request, @RequestParam(value = "adminId",required = true)String adminId,
                                                                 @RequestParam(value = "adminIsPostion",required = true) String adminIsPostion) {
        JsonModel jsonModel = new JsonModel();
        if(StringUtils.isBlank(adminId)
            ||StringUtils.isBlank(adminIsPostion)){
             jsonModel.setStatus(false);
             jsonModel.setMessage("所传参数不能为空！");
             return  jsonModel;
        }
        if(StringUtils.isBlank(adminIsPostion)&& Integer.valueOf(adminIsPostion)>0){
            jsonModel.setStatus(false);
            jsonModel.setMessage("非离职岗位不能删除");
            return jsonModel;
        }
        Map <String, Object> param = new HashMap <>();
        param.put("adminId",adminId);
        param.put("adminIsPostion",adminIsPostion);

        logger.info("删除离职管理员的信息传入参数 param={}", JSON.toJSONString(param));
        RestModel restModel = adminInfoService.deleteAdminInfoRecord(param);
        logger.info("用户信息查询返回结果 param={}", JSON.toJSONString(param));
        if(restModel.getCode().equals("200") && restModel.getData().toString().equals("1")){
            jsonModel.setStatus(true);
            jsonModel.setMessage("删除离职员工成功！");
            return jsonModel;
        }
        jsonModel.setStatus(false);
        jsonModel.setMessage("删除离职员工失败！");
        return jsonModel;
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
