package cn.hotel.controller;

import cn.hotel.entity.CustomerOrder;
import cn.hotel.entity.model.CustomerOrderRequest;
import cn.hotel.entity.model.PagerModel;
import cn.hotel.service.CustomerOrderService;
import cn.hotel.service.utils.DateUtils;
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
import java.util.ArrayList;
import java.util.List;

/**

* @Description:    订单明细表
* @Author:         田梦
* @CreateDate:     2019/1/25 11:24


*/

@Controller
public class CustomerOrderDetailController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(CustomerOrderDetailController.class);

    @Autowired
    private CustomerOrderService customerOrderService;

    //跳转到用户订单详情
    @RequestMapping(value = "/htm/fee/customerOrderDetail.action")
    public String customerRoomFee(HttpServletRequest request){
        return  "customerOrderDetail";
    }


    //多条件查询用户订单详情列表
    @RequestMapping(value = "/htm/fee/customerOrderDetailList.action")
    @ResponseBody
    public PagerModel<List<CustomerOrder>> selectAllCustomer(HttpServletRequest request){
        PagerModel <List<CustomerOrder>> pager = new PagerModel <>();
        List <CustomerOrder> list = new ArrayList <>();
        Long count = 0L;
        pager.setTotal(count);
        pager.setPageData(list);
        CustomerOrderRequest searchParam = getSearchParam(request);
        logger.info("多条件查询订单详情列表传入的参数 param={}", JSON.toJSONString(searchParam));
        List <CustomerOrder> customerOrders = customerOrderService.selectAllCustomerOrder(searchParam);
        logger.info("多条件查询订单详情列表传入的参数 param={}", JSON.toJSONString(customerOrders));
        if(customerOrders !=null && customerOrders.size()>0){
            pager.setTotal(Long.valueOf(customerOrders.size()));
            pager.setPageData(customerOrders);
            return pager;
        }
        return pager;
    }

    private CustomerOrderRequest getSearchParam(HttpServletRequest request) {
        CustomerOrderRequest customerOrderRequest = new CustomerOrderRequest();
        String pageSize = request.getParameter("pageSize");
        if (StringUtils.isNotBlank(pageSize)) {
            customerOrderRequest.setPageSize(Long.valueOf(pageSize));
        } else {
            customerOrderRequest.setPageSize(Long.valueOf(50));
        }
        String pageNumber = request.getParameter("pageNumber");
        if (StringUtils.isNotBlank(pageNumber)) {
            if (Integer.valueOf(pageNumber) <= 1) {
                customerOrderRequest.setPageNumber(Long.valueOf(0));
            } else {
                customerOrderRequest.setPageNumber(Long.valueOf(pageNumber));
            }
        } else {
            customerOrderRequest.setPageNumber(Long.valueOf(0));
        }
        String searchOrderId = request.getParameter("searchOrderId");
        if(searchOrderId !=null && StringUtils.isNotBlank(searchOrderId)){
            customerOrderRequest.setOrderId(Long.valueOf(searchOrderId));
        }
        String searchUserId = request.getParameter("searchUserId");
        if(searchUserId !=null && StringUtils.isNotBlank(searchOrderId)){
            customerOrderRequest.setUserId(Long.valueOf(searchUserId));
        }
        String searchJournalId = request.getParameter("searchJournalId");
        if(searchJournalId !=null && StringUtils.isNotBlank(searchJournalId)){
            customerOrderRequest.setJournalId(Long.valueOf(searchJournalId));
        }

        String orderTime = request.getParameter("orderTime");
        if(StringUtils.isNotBlank(orderTime)){
            customerOrderRequest.setOrderTime(DateUtils.getLongByDateString(orderTime));
        }
        return customerOrderRequest;
    }



}
