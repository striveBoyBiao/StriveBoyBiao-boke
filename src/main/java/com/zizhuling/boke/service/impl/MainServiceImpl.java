package com.zizhuling.boke.service.impl;

import com.zizhuling.boke.dao.ContentDao;
import com.zizhuling.boke.utils.PageInfo;
import com.zizhuling.boke.service.MainService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: hebiao
 * Date:2018/12/28
 * Time: 14:21
 * To change this template use File | Settings | File Templates.
 */
@Service
public class MainServiceImpl implements MainService{
    private static final Logger LOGGER = LoggerFactory.getLogger(MainService.class);

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ContentDao contentDao;

    /**
     * 查询忙里偷闲界面数据
     * @return
     */
    @Override
    public PageInfo findDoing(Map<String,Object> map) {
        PageInfo pageInfo=new PageInfo(map.get("pageNo").toString());
        map.put("pagebegin",pageInfo.getPageBegin());
        map.put("pagesize",pageInfo.getPageSize());
        int rowCount=contentDao.queryCount(map);
        int pageCount;
        //计算总共多少页
        if(rowCount% PageInfo.pageSize==0){
            pageCount=rowCount/PageInfo.pageSize;
        }else{
            pageCount=rowCount/PageInfo.pageSize+1;
        }
        pageInfo.setPageCount(pageCount);
        List<Map<String,Object>> list=contentDao.findDoing(map);
        pageInfo.setPageDate(list);
        return pageInfo;
    }

    /**
     * 查询首页,慢生活,学无止境 界面数据
     * @return
     */
    @Override
    public PageInfo findLife(Map<String, Object> map) {
        PageInfo pageInfo=new PageInfo(map.get("pageNo").toString());
        map.put("pagebegin",pageInfo.getPageBegin());
        map.put("pagesize",pageInfo.getPageSize());
        int rowCount=contentDao.queryLifeCount(map);
        int pageCount;
        //计算总共多少页
        if(rowCount%PageInfo.pageSize==0){
            pageCount=rowCount/PageInfo.pageSize;
        }else{
            pageCount=rowCount/PageInfo.pageSize+1;
        }
        pageInfo.setPageCount(pageCount);
        List<Map<String,Object>> list=contentDao.findLife(map);
        map.put("news",0);
        map.put("newslength",6);
        map.put("rank",0);
        map.put("ranklength",6);
        List<Map<String,Object>> newsDate=contentDao.findLifeNewsDate(map);
        List<Map<String,Object>> rankDate=contentDao.findLifeRankDate(map);
        pageInfo.setPageDate(list);
        pageInfo.setNewsDate(newsDate);
        pageInfo.setRankDate(rankDate);
        return pageInfo;
    }
    /**
     * 查询首页,慢生活,学无止境 详细界面数据
     * @return
     */
    @Override
    public PageInfo findlifeDetails(Map<String, Object> map) {
        PageInfo pageInfo=new PageInfo("1");
        map.put("news",0);
        map.put("newslength",8);
        map.put("rank",0);
        map.put("ranklength",5);
        /*修改阅读量*/
        contentDao.updateClickRate(map);
        List<Map<String,Object>> list=contentDao.findlifeDetails(map);
        List<Map<String,Object>> newsDate=contentDao.findLifeNewsDate(map);
        List<Map<String,Object>> rankDate=contentDao.findLifeRankDate(map);
        map.put("gjzc",list.get(0).get("gjzc"));
        List<Map<String,Object>> relateDate=contentDao.findLifeRelateDate(map);
        Map<String,Object> onDate=contentDao.findLifeOnDate(map);
        Map<String,Object> underDate=contentDao.findLifeUnderDate(map);
        pageInfo.setOnDate(onDate);
        pageInfo.setUnderDate(underDate);
        pageInfo.setRelateDate(relateDate);
        pageInfo.setPageDate(list);
        pageInfo.setNewsDate(newsDate);
        pageInfo.setRankDate(rankDate);
        return pageInfo;
    }


}
