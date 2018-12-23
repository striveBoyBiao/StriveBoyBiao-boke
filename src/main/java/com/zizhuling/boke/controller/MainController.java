package com.zizhuling.boke.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 11697 on 2018/12/23.
 */
@Controller
public class MainController {

    /**增加日志*/
    private final Logger log= LoggerFactory.getLogger("MainController.class");

    /**
     * 跳到首页
     * @return
     */
    @RequestMapping("/index")
    public String index() {
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


}
