package com.reminder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wills on 7/14/14.
 */
@Controller
@RequestMapping("/")
public class OtherController {

    @RequestMapping("/**")
    @ResponseBody
    public int defaultHandler(){
        return 0;
    }
}
