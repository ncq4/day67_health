package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.constant.MessageConstant;
import com.itheima.dao.MemberDao;
import com.itheima.dao.OrderDao;
import com.itheima.dao.OrderSettingDao;
import com.itheima.entity.Result;
import com.itheima.pojo.Member;
import com.itheima.pojo.Order;
import com.itheima.pojo.OrderSetting;
import com.itheima.utils.DateUtils;
import com.sun.xml.internal.xsom.impl.scd.Iterators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderSettingDao orderSettingDao;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private OrderDao orderDao;
    //体检预约
    @Override
    public Result order(Map map) throws Exception {
        /**
         1、检查用户所选择的预约日期是否已经提前进行了预约设置，如果没有设置则无法进行预约
         2、检查用户所选择的预约日期是否已经约满，如果已经约满则无法预约
         3、检查用户是否重复预约（同一个用户在同一天预约了同一个套餐），如果是重复预约则无法完成再次预约
         4、检查当前用户是否为会员，如果是会员则直接完成预约，如果不是会员则自动完成注册并进行预约
         5、预约成功，更新当日的已预约人数
         */
        //获取日期
        String orderDate = (String) map.get("orderDate");
        Date date = DateUtils.parseString2Date(orderDate);
        //通过日期查找预约当天是否开放了预约
        OrderSetting orderSetting = orderSettingDao.findByOrderDate(date);
        if(orderDate == null){
            //所选日期没有提前预约设置,不能完成预约
            return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }
        //如果所选日期内预约人数大于或者等于最大预约人数,则无法预约
        if(orderSetting.getReservations() >= orderSetting.getNumber()){
            return new Result(false,MessageConstant.ORDER_FULL);
        }
        //判断是否在重复预约
        String telephone = (String) map.get("telephone");
        //通过手机号找到该会员
        Member member = memberDao.findByTelephone(telephone);
        if(member != null){
            Integer memberId = member.getId();
            //获取会员的id
            Integer setmealId = Integer.parseInt((String) map.get("setmealId"));
            Order order = new Order(memberId, date, setmealId);
            List<Order> orderList = orderDao.findByCondition(order);
            if(orderList != null&&orderList.size() > 0){
                //用户在重复预约,不能完成预约
                return new Result(false,MessageConstant.HAS_ORDERED);
            }
        }

        if(member == null){
            //当前用户会员,需要自动完成注册
            member = new Member();
            member.setName((String) map.get("name"));
            member.setPhoneNumber(telephone);
            member.setIdCard((String)map.get("sex"));
            member.setRegTime(new Date());
            memberDao.add(member);
        }

        Order order = new Order(member.getId(),
                date,
                (String)map.get("orderType"),
                Order.ORDERSTATUS_NO,
                Integer.parseInt((String) map.get("setmealId"))
                );
        orderDao.add(order);
        orderSetting.setReservations(orderSetting.getReservations()+1);
        orderSettingDao.editReservationsByOrderDate(orderSetting);
        return new Result(true,MessageConstant.ORDER_SUCCESS,order.getId());
    }

    //根据id查询预约详细信息(包括会员姓名,套餐名称,预约基本信息)
    @Override
    public Map findById(Integer id) {
        Map map = orderDao.findById4Detail(id);
        if (map != null){
            Date orderDate = (Date) map.get("orderDate");
            try {
                map.put("orderDate",DateUtils.parseDate2String(orderDate));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
