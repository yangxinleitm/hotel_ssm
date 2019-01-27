package cn.hotel.business;

import cn.hotel.entity.CustomerOrder;
import cn.hotel.entity.CustomerOrderExample;
import cn.hotel.entity.model.CustomerOrderRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerOrderMapper {
    int countByExample(CustomerOrderExample example);

    int deleteByExample(CustomerOrderExample example);

    int deleteByPrimaryKey(Long orderId);

    int insert(CustomerOrder record);

    int insertSelective(CustomerOrder record);

    List<CustomerOrder> selectAllCustomerOrder(CustomerOrderRequest customerOrder);

    List<CustomerOrder> selectByExample(CustomerOrderExample example);

    CustomerOrder selectByPrimaryKey(Long orderId);

    int updateByExampleSelective(@Param("record") CustomerOrder record, @Param("example") CustomerOrderExample example);

    int updateByExample(@Param("record") CustomerOrder record, @Param("example") CustomerOrderExample example);

    int updateByPrimaryKeySelective(CustomerOrder record);

    int updateByPrimaryKey(CustomerOrder record);
}