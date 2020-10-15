package com.itheima.dao;

import com.itheima.pojo.Order;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    public void add(Order order);
    public List<Order> findByCondition(Order order);
    public Map findById4Detail(Integer id);

    Integer findOrderCountByDate(String today);

    Integer findVisitsCountByDate(String today);

    Integer findOrderCountAfterDate(String thisWeekMonday);

    Integer findVisitsCountAfterDate(String thisWeekMonday);

    List<Map> findHosSetmeal();
}
