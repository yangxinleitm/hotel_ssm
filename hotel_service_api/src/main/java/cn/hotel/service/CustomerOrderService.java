package cn.hotel.service;

import cn.hotel.entity.CustomerOrder;
import cn.hotel.entity.CustomerOrderExample;
import cn.hotel.entity.model.CustomerOrderRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerOrderService {

    int countByExample(CustomerOrderExample example);

    int deleteByExample(CustomerOrderExample example);

    int deleteByPrimaryKey(Integer orderId);

    int insert(CustomerOrder record);

    int insertSelective(CustomerOrder record);

    List<CustomerOrder> selectAllCustomerOrder(CustomerOrderRequest customerOrderRequest);
    List<CustomerOrder> selectByExample(CustomerOrderRequest customerOrderRequest);

    CustomerOrder selectByPrimaryKey(Integer orderId);

    int updateByExampleSelective(@Param("record") CustomerOrder record, @Param("example") CustomerOrderExample example);

    int updateByExample(@Param("record") CustomerOrder record, @Param("example") CustomerOrderExample example);

    int updateByPrimaryKeySelective(CustomerOrder record);

    int updateByPrimaryKey(CustomerOrder record);
}
