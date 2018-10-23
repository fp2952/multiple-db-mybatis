package com.peng.mybatis.multiple.db.example.controller;

import com.peng.mybatis.multiple.db.example.service.BaseRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by fp295 on 2018/10/21.
 */
@RestController
@RequestMapping("/test")
public class TestController {


    @Autowired
    private BaseRoleService baseRoleService;

    //测试默认
    @RequestMapping(value = "/get")
    @ResponseBody
    public List getDb1AllUser() {
        return baseRoleService.get();
    }

    //测试分页
    @RequestMapping(value = "/page")
    @ResponseBody
    public List getDb1AllUserPage() {
        return baseRoleService.page();
    }

    //测试db1
    @RequestMapping(value = "/get1")
    @ResponseBody
    public List getDb1AllUser1() {
        return baseRoleService.get1();
    }

    //测试db2
    @RequestMapping(value = "/get2")
    @ResponseBody
    public List getDb1AllUser2() {
        return baseRoleService.get2();
    }

    //测试db3
    @RequestMapping(value = "/get3")
    @ResponseBody
    public List getDb1AllUser3() {
        return baseRoleService.get3();
    }

    //测试db4
    @RequestMapping(value = "/get4")
    @ResponseBody
    public List getDb1AllUser4() {
        return baseRoleService.get4();
    }

}
