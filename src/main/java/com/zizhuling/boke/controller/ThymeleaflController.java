package com.zizhuling.boke.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hebiao on 2018/2/27.
 */
@Controller
@RequestMapping("/index")
public class ThymeleaflController {

    /**增加日志*/
    private final Logger log= LoggerFactory.getLogger("ThymeleaflController.class");
    @RequestMapping("/index")
    public String index() {
        return "blog/index";
    }
}