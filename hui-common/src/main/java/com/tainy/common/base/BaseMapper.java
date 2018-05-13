package com.tainy.common.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Tainy
 * @date 2017/10/7
 */
public interface BaseMapper<T> {

    int deleteByPrimaryKey(Integer id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    List<T> queryByObject(@Param("record") T record);
}
