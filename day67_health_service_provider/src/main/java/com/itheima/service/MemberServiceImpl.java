package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.MemberDao;
import com.itheima.pojo.Member;
import com.itheima.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDao memberDao;
    @Override
    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }

    @Override
    public void add(Member member) {
        String password = member.getPassword();
        if (password != null){
            password = MD5Utils.md5(password);
            member.setPassword(password);
        }
        memberDao.add(member);
    }

    @Override
    public List<Integer> findMemberCountByMonths(List<String> months) {
        List<Integer> memberCounts = new ArrayList<>();
        if(months != null && months.size() > 0){
            for (String month : months) {//2019.01

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM");
                Calendar calendar = Calendar.getInstance();//日历类，可以处理日期类型
                try {
                    calendar.setTime(sdf.parse(month));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String endTime = month + "-" + calendar.getActualMaximum(Calendar.DAY_OF_MONTH);//2019-7-31
                Integer memberCount = memberDao.findMemberCountBeforeDate(endTime);
                memberCounts.add(memberCount);
            }
        }
        return memberCounts;
    }
}
