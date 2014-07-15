package com.reminder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class ApiController {

	@RequestMapping("/**")
    @ResponseBody
	public int defaultHandler(){
        return 0;
	}
}
