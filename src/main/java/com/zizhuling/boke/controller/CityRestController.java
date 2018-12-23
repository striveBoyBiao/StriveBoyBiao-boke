package com.zizhuling.boke.controller;


import com.zizhuling.boke.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by bysocket on 07/02/2017.
 */
@RestController
public class CityRestController {

    @Autowired
    private CityService cityService;


    @RequestMapping(value = "/api/city/{id}", method = RequestMethod.GET)
    public void findOneCity(@PathVariable("id") Long id) {
    }

    @RequestMapping(value = "/api/city", method = RequestMethod.POST)
    public void createCity() {
    }

}
