package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Illness;
import com.itheima.service.IllnessService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
@RequestMapping("/illness")
public class illnessController {
    @Reference
    private IllnessService illnessService;


    //http://127.0.0.1:8080/checkgroup/add1
    //当处理器添加了@ResponseBody注解后，不走视图解析器，走转换器MessageConverter
    //新增
    @RequestMapping("/add")
    public Result add(@RequestBody Illness illness, Integer[] checkitemIds){
        try{
            illnessService.add(illness,checkitemIds);
            return new Result(true, MessageConstant.ADD_ILLNESS_LIST_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_ILLNESS_LIST_FAIL);
        }
    }
    //查询所有检查组
    @RequestMapping("/findAll")
    public Result findAll(){
        try{
            List<Illness> list = illnessService.findAll();
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,list);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }
    //分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean pageBean){
        return illnessService.findPage(pageBean);
    }

    //根据id查询检查组
    @RequestMapping("/findById")
    public Result findById(Integer id){
        try{
            Illness illness = illnessService.findById(id);
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,illness);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    //编辑
    @RequestMapping("/edit")
    public Result edit(@RequestBody Illness illness,Integer[] checkitemIds){
        try{
            illnessService.edit(illness,checkitemIds);
            return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
    }


}
