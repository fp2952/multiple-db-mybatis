package com.peng.mybatis.multiple.db.example.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.peng.mybatis.multiple.db.config.DS;

import com.peng.mybatis.multiple.db.example.mapper.model.BaseRole;
import com.peng.mybatis.multiple.db.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fp295 on 2018/10/22.
 */
@Service
public class BaseRoleService extends BaseService<BaseRole> {


    public List<BaseRole> get(){
        return mapper.selectAll();
    }

    @DS()
    public List<BaseRole> get1(){
        return this.selectAll();
    }

    @DS("db2")
    public List<BaseRole> get2(){
        return this.selectAll();
    }

    @DS("db3")
    public List<BaseRole> get3(){
        return this.selectAll();
    }

    @DS("db4")
    public List<BaseRole> get4(){
        return this.selectAll();
    }

    public List page() {
        PageHelper.offsetPage(1, 1);
        return new PageInfo(this.selectAll()).getList();
    }
}
