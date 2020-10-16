package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.IllnessDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Illness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service(interfaceClass = IllnessService.class)
@Transactional
public class IllnessServiceImpl implements IllnessService {
    @Autowired
    private IllnessDao illnessDao;
    //分页查询
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();//ThreadLocal
        PageHelper.startPage(currentPage,pageSize);//分页插件，会在执行sql之前将分页关键字追加到SQL后面
        Page<Illness> page = illnessDao.findByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }
    //查找全部
    @Override
    public List<Illness> findAll() {
        return illnessDao.findAll();
    }
    //根据id查询
    @Override
    public Illness findById(Integer id) {
        return illnessDao.findById(id);
    }
    //修改
    @Override
    public void edit(Illness illness, Integer[] checkitemIds) {
        //基本信息修改
        illnessDao.edit(illness);
    }

    @Override
    public void add(Illness illness, Integer[] checkitemIds) {
        //新增检查组
        illnessDao.add(illness);
    }
}
