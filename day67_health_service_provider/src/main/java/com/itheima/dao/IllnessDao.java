package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckItem;
import com.itheima.pojo.Illness;

import java.util.List;

public interface IllnessDao {
    Page<Illness> findByCondition(String queryString);

    List<Illness> findAll();

    Illness findById(Integer id);

    void edit(Illness illness);

    void add(Illness illness);
}
