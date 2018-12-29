package com.zizhuling.boke.service.impl;

import com.zizhuling.boke.dao.ContentDao;
import com.zizhuling.boke.utils.Constants;
import com.zizhuling.boke.utils.PageInfo;
import com.zizhuling.boke.service.MainService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
        pageInfo.setPageData(list);
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
        List<Map<String,Object>> newsData=contentDao.findLifeNewsDate(map);
        List<Map<String,Object>> rankData=contentDao.findLifeRankDate(map);
        pageInfo.setPageData(list);
        pageInfo.setNewsData(newsData);
        pageInfo.setRankData(rankData);
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
        map.put("newslength",10);
        map.put("rank",0);
        map.put("ranklength",6);
        map.put("relate",0);
        map.put("relatelength",9);
        /*修改阅读量*/
        contentDao.updateClickRate(map);
        List<Map<String,Object>> list=contentDao.findlifeDetails(map);
        List<Map<String,Object>> newsData=contentDao.findLifeNewsDate(map);
        List<Map<String,Object>> rankData=contentDao.findLifeRankDate(map);
        map.put("gjzc",list.get(0).get("gjzc"));
        List<Map<String,Object>> relateData=contentDao.findLifeRelateDate(map);
        Map<String,Object> onData=contentDao.findLifeOnDate(map);
        Map<String,Object> underData=contentDao.findLifeUnderDate(map);
        /*处理thymeleaf空数据报错*/
        if (onData==null){
            onData=new HashMap<String,Object>();
            onData.put("cid","");
            onData.put("title","");
        }
        if (underData==null){
            underData=new HashMap<String,Object>();
            underData.put("cid","");
            underData.put("title","");
        }
        for(Map<String,Object> temp:list){
            if(Integer.valueOf(temp.get("categories").toString())> Constants.INT_FIFTEEN){
                temp.put("module","慢生活");
                temp.put("modulepath",Constants.MODULE_PATH_ONE);
            }else{
                temp.put("module","学无止境");
                temp.put("modulepath",Constants.MODULE_PATH_TWO);
            };
        }
        pageInfo.setOnData(onData);
        pageInfo.setUnderData(underData);
        pageInfo.setRelateData(relateData);
        pageInfo.setPageData(list);
        pageInfo.setNewsData(newsData);
        pageInfo.setRankData(rankData);
        return pageInfo;
    }


}
