package com.zizhuling.boke.service;


import com.zizhuling.boke.service.MainService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by hebiao on 2018/2/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InterFaceImplTest {
    @Autowired
    private MainService mainService;
    @Test
    public void testInterFace(){
       System.out.print("=======================");
    }

}