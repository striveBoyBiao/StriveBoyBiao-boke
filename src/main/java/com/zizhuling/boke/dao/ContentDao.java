package com.zizhuling.boke.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


/**
 * 城市 DAO 接口类
 *
 * Created by bysocket on 07/02/2017.
 */
@Mapper
public interface ContentDao {
    /**查询忙里偷闲*/
    List<Map<String,Object>> findDoing(Map<String,Object> map);
    int queryCount(Map<String,Object> map);

    /**查询慢生活数据*/
    List<Map<String,Object>> findLife(Map<String,Object> map);
    List<Map<String,Object>> findlifeDetails(Map<String,Object> map);


    /**查询慢生活数据数量*/
    int queryLifeCount(Map<String,Object> map);
    /**查询慢生活最新文章*/
    List<Map<String,Object>> findLifeNewsDate(Map<String,Object> map);
    /**查询慢生活最新排行*/
    List<Map<String,Object>> findLifeRankDate(Map<String,Object> map);
    /**查询慢生活相关文章*/
    List<Map<String,Object>> findLifeRelateDate(Map<String,Object> map);


    /**查询上一篇文章*/
    Map<String,Object> findLifeOnDate(Map<String,Object> map);
    /**查询下一篇文章*/
    Map<String,Object> findLifeUnderDate(Map<String,Object> map);
    /**修改阅读量*/
    void updateClickRate(Map<String,Object> map);
}
