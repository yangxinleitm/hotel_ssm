package cn.hotel.service.impl;

import cn.hotel.business.CustomerOrderMapper;
import cn.hotel.entity.CustomerOrder;
import cn.hotel.entity.CustomerOrderExample;
import cn.hotel.entity.model.CustomerOrderRequest;
import cn.hotel.service.CustomerOrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerOrderImpl implements CustomerOrderService {

    @Autowired
    private CustomerOrderMapper customerOrderMapper;

    @Override
    public int countByExample(CustomerOrderExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(CustomerOrderExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer orderId) {
        return 0;
    }

    @Override
    public int insert(CustomerOrder record) {
        return 0;
    }

    @Override
    public int insertSelective(CustomerOrder record) {
        return 0;
    }


    //多条件查询
    @Override
    public List <CustomerOrder> selectAllCustomerOrder(CustomerOrderRequest customerOrderRequest) {
        List <CustomerOrder> customerOrders = customerOrderMapper.selectAllCustomerOrder(customerOrderRequest);
        return customerOrders;
    }

    @Override
    public List <CustomerOrder> selectByExample(CustomerOrderRequest customerOrder) {
        CustomerOrderExample customerOrderExample = new CustomerOrderExample();
        CustomerOrderExample.Criteria criteria = customerOrderExample.createCriteria();

        if(customerOrder !=null &&StringUtils.isNoneBlank(customerOrder.getRoomId().toString())){
            criteria.andRoomIdEqualTo(customerOrder.getRoomId());
        }
        List <CustomerOrder> customerOrders = customerOrderMapper.selectByExample(customerOrderExample);
        return customerOrders;
    }

    @Override
    public CustomerOrder selectByPrimaryKey(Integer orderId) {
        return null;
    }

    @Override
    public int updateByExampleSelective(CustomerOrder record, CustomerOrderExample example) {
        return 0;
    }

    @Override
    public int updateByExample(CustomerOrder record, CustomerOrderExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(CustomerOrder record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(CustomerOrder record) {
        return 0;
    }
}
