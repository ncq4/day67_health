package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Illness;

import java.util.List;

public interface IllnessService {

    PageResult findPage(QueryPageBean pageBean);

    List<Illness> findAll();

    Illness findById(Integer id);

    void edit(Illness illness, Integer[] checkitemIds);

    void add(Illness illness, Integer[] checkitemIds);
}
