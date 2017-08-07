package cn.e3mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.container.page.PageHandler;

@Controller
public class PageController {
	
	
	@RequestMapping("/")
	public String showIndex(){
		
		
		return "index";
	}
	
	@RequestMapping("/{page}")
	public String showList(@PathVariable String page){
		
		return page;
	}

}
