package com.peng.mybatis.multiple.db.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public class BaseService<T> {

    @Autowired
    protected Mapper<T> mapper;


    /**
     * 查询所有
     */
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    /**
     * 根据主键查询
     */
    public T selectByPrimaryKey(T key){
        return mapper.selectByPrimaryKey(key);
    }

    /**
     * 根据属性查询
     */
    public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    /**
     * 根据属性查询一个对象
     */
    public T selectOne(T record) {
        return mapper.selectOne(record);
    }

    /**
     * 新增
     */
    public int insert(T record) {
        return mapper.insert(record);
    }

    /**
     * 选择新增
     */
    public int insertSelective(T record) {
        return mapper.insertSelective(record);
    }


    /**
     * 根据主键新增
     */
    public int updateByPrimaryKey(T record) {
        return mapper.updateByPrimaryKey(record);
    }

    /**
     * 根据主键选择新增
     */
    public int updateByPrimaryKeySelective(T record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据属性更新
     */
    public int updateByExample(T record, Object example) {
        return mapper.updateByExample(record, example);
    }

    /**
     * 根据属性选择更新
     */
    public int updateByExampleSelective(T record, Object example) {
        return mapper.updateByExampleSelective(record, example);
    }

    /**
     * 删除
     */
    public int delete(T record) {
        return mapper.delete(record);
    }

    /**
     * 根据主键删除
     */
    public int deleteByPrimaryKey(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    /**
     * 根据属性删除
     */
    public int deleteByExample(Object example) {
        return mapper.deleteByExample(example);
    }

    /**
     * 根据属性分页查询
     */
    public PageInfo<T> selectByList(T record, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<T>(mapper.select(record));
    }

    /**
     * 根据example分页查询
     */
    public PageInfo<T> selectByExampleList(Object example, int pageNum, int pageSize) {
        PageHelper.offsetPage(pageNum, pageSize);
        return new PageInfo<T>(mapper.selectByExample(example));
    }

}
