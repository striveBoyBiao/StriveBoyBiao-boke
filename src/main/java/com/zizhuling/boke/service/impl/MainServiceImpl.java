package com.zizhuling.boke.service.impl;

import com.zizhuling.boke.service.MainService;
import com.zizhuling.boke.dao.ContentDao;
import com.zizhuling.boke.utils.Constants;
import com.zizhuling.boke.utils.PageInfo;
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
public class MainServiceImpl implements MainService {
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

    @Override
    public PageInfo findTags(Map<String, Object> map) {
        PageInfo pageInfo=new PageInfo("1");
        map.put("news",0);
        map.put("newslength",10);
        map.put("rank",0);
        map.put("ranklength",6);
        List<Map<String,Object>> newsData=contentDao.findLifeNewsData(map);
        List<Map<String,Object>> rankData=contentDao.findLifeRankData(map);
        List<Map<String,Object>> recommendData=contentDao.findRecommendData(map);
        pageInfo.setNewsData(newsData);
        pageInfo.setRankData(rankData);
        pageInfo.setRecommendData(recommendData);
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
        String search=map.get("search")==null?"":map.get("search").toString();
        if(StringUtils.isNotEmpty(search)){
            map.put("search","%"+search+"%");
        }
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
        List<Map<String,Object>> newsData=contentDao.findLifeNewsData(map);
        List<Map<String,Object>> rankData=contentDao.findLifeRankData(map);
        if(Constants.STRING_ONE.equals(map.get("type"))){
            /*首页查询站长推荐文章*/
            List<Map<String,Object>> recommendData=contentDao.findRecommendData(map);
            /*首页查询图文推荐文章*/
            List<Map<String,Object>> readingData=contentDao.findReadingData(map);
            pageInfo.setReadingData(readingData);
            pageInfo.setRecommendData(recommendData);
        }
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
        List<Map<String,Object>> newsData=contentDao.findLifeNewsData(map);
        List<Map<String,Object>> rankData=contentDao.findLifeRankData(map);
        map.put("gjzc",list.get(0).get("gjzc"));
        List<Map<String,Object>> relateData=contentDao.findLifeRelateData(map);
        Map<String,Object> onData=contentDao.findLifeOnData(map);
        Map<String,Object> underData=contentDao.findLifeUnderData(map);
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

    @Override
    public PageInfo findPhoto(Map<String, Object> map) {
        PageInfo pageInfo=new PageInfo(map.get("pageNo").toString());
        map.put("pagebegin",pageInfo.getPageBegin());
        map.put("pagesize",pageInfo.getPageSize());
        int rowCount=contentDao.queryFilesCount(map);
        int pageCount;
        //计算总共多少页
        if(rowCount%PageInfo.pageSize==0){
            pageCount=rowCount/PageInfo.pageSize;
        }else{
            pageCount=rowCount/PageInfo.pageSize+1;
        }
        pageInfo.setPageCount(pageCount);
        List<Map<String,Object>> list=contentDao.queryFiles(map);
        pageInfo.setPageData(list);
        return pageInfo;
    }
}
