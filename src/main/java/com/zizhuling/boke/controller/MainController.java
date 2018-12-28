package com.zizhuling.boke.controller;

import com.zizhuling.boke.utils.PageInfo;
import com.zizhuling.boke.service.MainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 11697 on 2018/12/23.
 */
@Controller
public class MainController {

    /**增加日志*/
    private final Logger log= LoggerFactory.getLogger("MainController.class");
    @Autowired
    private MainService mainService;

    /**
     * 跳到首页
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "blog/index";
    }
    /**
     * 跳到首页
     * @return
     */
    @RequestMapping("/index")
    public String main() {
        return "blog/index";
    }

    /**
     * 跳到关于我
     * @return
     */
    @RequestMapping("/about")
    public String about() {
        return "blog/about";
    }

    /**
     * 跳到慢生活
     * @return
     */
    @RequestMapping("/life")
    public String life() {
        return "blog/life";
    }

    /**
     * 跳到忙里偷闲
     * @return
     */
    @RequestMapping("/doing")
    public String doing() {
        return "blog/doing";
    }

    /**
     * 跳到学无止境
     * @return
     */
    @RequestMapping("/learn")
    public String learn() {
        return "blog/learn";
    }


    /**
     * 跳到相册
     * @return
     */
    @RequestMapping("/photo")
    public String photo() {
        return "blog/photo";
    }

    /**
     * 跳到留言板
     * @return
     */
    @RequestMapping("/saying")
    public String saying() {
        return "blog/saying";
    }
    /**
     * 跳到标签云
     * @return
     */
    @RequestMapping("/tags")
    public String tags() {
        return "blog/tags";
    }




    /**
     * 分页查询首页数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/main/findIndex")
    public PageInfo findIndex(HttpServletRequest request){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("type","1");
        map.put("pageNo",request.getParameter("pageNo"));
        PageInfo list=mainService.findLife(map);
        return list;
    }

    /**
     * 查询首页详细界面数据
     * @return
     */
    @RequestMapping("/main/findIndexDetails")
    public String findIndexDetails(HttpServletRequest request,Model model){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("cid",request.getParameter("cid"));
        map.put("type","1");
        PageInfo pageInfo=mainService.findlifeDetails(map);
        model.addAttribute("list",pageInfo.getPageData().get(0));
        model.addAttribute("newsdate",pageInfo.getNewsData());
        model.addAttribute("rankdate",pageInfo.getRankData());
        model.addAttribute("relatedate",pageInfo.getRelateData());
        model.addAttribute("ondate",pageInfo.getOnData());
        model.addAttribute("underDate",pageInfo.getUnderData());
        return "blog/article";
    }




    /**
     * 分页查询慢生活界面数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/main/findLife")
    public PageInfo findLife(HttpServletRequest request){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("type","3");
        map.put("pageNo",request.getParameter("pageNo"));
        PageInfo list=mainService.findLife(map);
        return list;
    }
    /**
     * 查询慢生活详细界面数据
     * @return
     */
    @RequestMapping("/main/findlifeDetails")
    public String findlifeDetails(HttpServletRequest request,Model model){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("cid",request.getParameter("cid"));
        map.put("type","3");
        PageInfo pageInfo=mainService.findlifeDetails(map);
        model.addAttribute("list",pageInfo.getPageData().get(0));
        model.addAttribute("newsdate",pageInfo.getNewsData());
        model.addAttribute("rankdate",pageInfo.getRankData());
        model.addAttribute("relatedate",pageInfo.getRelateData());
        model.addAttribute("ondate",pageInfo.getOnData());
        model.addAttribute("underDate",pageInfo.getUnderData());
        return "blog/article";
    }

    /**
     * 分页查询忙里偷闲界面数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/main/findDoing")
    public PageInfo findDoing(HttpServletRequest request){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("pageNo",request.getParameter("pageNo"));
        PageInfo list=mainService.findDoing(map);
        return list;
    }





    /**
     * 分页查询学无止境界面数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/main/findLearn")
    public PageInfo findLearn(HttpServletRequest request){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("type","5");
        map.put("pageNo",request.getParameter("pageNo"));
        map.put("search",request.getParameter("search"));
        PageInfo list=mainService.findLife(map);
        return list;
    }
    /**
     * 查询学无止境详细界面数据
     * @return
     */
    @RequestMapping("/main/findLearnDetails")
    public String findLearnDetails(HttpServletRequest request,Model model){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("cid",request.getParameter("cid"));
        map.put("type","5");
        PageInfo pageInfo=mainService.findlifeDetails(map);
        model.addAttribute("list",pageInfo.getPageData().get(0));
        model.addAttribute("newsdate",pageInfo.getNewsData());
        model.addAttribute("rankdate",pageInfo.getRankData());
        model.addAttribute("relatedate",pageInfo.getRelateData());
        model.addAttribute("ondate",pageInfo.getOnData());
        model.addAttribute("underDate",pageInfo.getUnderData());
        return "blog/article";
    }


}
