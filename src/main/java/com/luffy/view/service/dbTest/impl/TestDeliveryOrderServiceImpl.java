package com.luffy.view.service.dbTest.impl;

import com.luffy.view.dao.TestDeliveryOrderDao;
import com.luffy.view.domain.TestDeliveryOrder;
import com.luffy.view.domain.TestDeliveryOrderExample;
import com.luffy.view.service.dbTest.TestDeliveryOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestDeliveryOrderServiceImpl implements TestDeliveryOrderService {

    @Autowired
    private TestDeliveryOrderDao testDeliveryOrderDao;

    @Override
    public List<TestDeliveryOrder> getList() {
        TestDeliveryOrderExample example = new TestDeliveryOrderExample();
        TestDeliveryOrderExample.Criteria criteria = example.createCriteria();
        List<TestDeliveryOrder> orderList = testDeliveryOrderDao.selectByExample(example);
        return orderList;
    }
}
