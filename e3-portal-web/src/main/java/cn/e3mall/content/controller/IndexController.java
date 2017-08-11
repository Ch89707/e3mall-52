package cn.e3mall.content.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.content.service.TbContentIndexService;
import cn.e3mall.pojo.TbContent;

@Controller
public class IndexController {

	
	@Value("${index.ad.cid}")
	private long cid;
	
	@Autowired
	private TbContentIndexService tbContentIndexService;
	
	@RequestMapping("/default")
	public String showIndex(Model model){
		List<TbContent> ad1List = tbContentIndexService.getContentList(cid);
		model.addAttribute("ad1List", ad1List);
		return "index";
	}
	
}
