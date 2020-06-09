package com.luffy.view.service.db.impl;

import com.luffy.view.dao.OrderDao;
import com.luffy.view.domain.Order;
import com.luffy.view.domain.OrderExample;
import com.luffy.view.service.db.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbServiceImpl implements DbService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public void addOrder(Order order) {
        orderDao.insert(order);
    }

    @Override
    public List<Order> getOrderList() {
        OrderExample example = new OrderExample();
        example.setOrderByClause("ctime desc");
        return orderDao.selectByExample(example);
    }
}
