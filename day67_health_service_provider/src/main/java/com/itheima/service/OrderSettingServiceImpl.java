package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.OrderSettingDao;
import com.itheima.pojo.OrderSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 预约设置服务
 */

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    private OrderSettingDao orderSettingDao;

    //批量导入预约设置信息
    public void add(List<OrderSetting> list) {
        if (list != null && list.size() > 0) {
            for (OrderSetting orderSetting : list) {
                //判断当前日期是否已经进行了设置
                long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
                if (count > 0) {
                    //如果已经设置，执行更新操作
                    orderSettingDao.editNumberByOrderDate(orderSetting);
                } else {
                    //如果没有设置，执行插入操作
                    orderSettingDao.add(orderSetting);
                }
            }
        }
    }

    public List<Map<String, Object>> getOrderSettingByMonth(String month) {//2019-7
        String begin = month + "-1";//2019-7-1

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
        Calendar calendar = Calendar.getInstance();//日历类，可以处理日期类型
        try {
            calendar.setTime(sdf.parse(begin));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String end = month + "-" + calendar.getActualMaximum(Calendar.DAY_OF_MONTH);//2019-7-31

        Map<String, String> map = new HashMap<>();
        map.put("begin", begin);
        map.put("end", end);
        List<OrderSetting> list = orderSettingDao.getOrderSettingByMonth(map);
        List<Map<String, Object>> data = new ArrayList<>();
        for (OrderSetting orderSetting : list) {
            Map<String, Object> orderSettingData = new HashMap<>();
            orderSettingData.put("date", orderSetting.getOrderDate().getDate());
            orderSettingData.put("number", orderSetting.getNumber());
            orderSettingData.put("reservations", orderSetting.getReservations());
            data.add(orderSettingData);
        }
        return data;
    }

    public void editNumberByOrderDate(OrderSetting orderSetting) {
        long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
        if (count > 0) {
            //如果已经设置，执行更新操作
            orderSettingDao.editNumberByOrderDate(orderSetting);
        } else {
            //如果没有设置，执行插入操作
            orderSettingDao.add(orderSetting);
        }
        //orderSettingDao.editNumberByOrderDate(orderSetting);
    }
}
