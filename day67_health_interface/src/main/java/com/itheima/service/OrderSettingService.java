package com.itheima.service;

import com.itheima.pojo.OrderSetting;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface OrderSettingService {
    public void add(List<OrderSetting> list);
    public List<Map<String,Object>> getOrderSettingByMonth(String month) throws ParseException;
    public void editNumberByOrderDate(OrderSetting orderSetting);
}
