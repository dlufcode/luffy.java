package com.luffy.view.dao;

import com.luffy.view.domain.TestDeliveryOrder;
import com.luffy.view.domain.TestDeliveryOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TestDeliveryOrderDao {
    long countByExample(TestDeliveryOrderExample example);

    int deleteByExample(TestDeliveryOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TestDeliveryOrder record);

    int insertSelective(TestDeliveryOrder record);

    List<TestDeliveryOrder> selectByExample(TestDeliveryOrderExample example);

    TestDeliveryOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TestDeliveryOrder record, @Param("example") TestDeliveryOrderExample example);

    int updateByExample(@Param("record") TestDeliveryOrder record, @Param("example") TestDeliveryOrderExample example);

    int updateByPrimaryKeySelective(TestDeliveryOrder record);

    int updateByPrimaryKey(TestDeliveryOrder record);
}