package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.DataGridResult;
import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.service.TbContentCategoryService;
import cn.e3mall.service.TbContentService;

@RequestMapping("/content")
@Controller
public class ContentContorller {

	@Autowired
	private TbContentService tbContentService;
	
	@RequestMapping("/query/list")
	@ResponseBody
	public DataGridResult getContentList(long categoryId,int rows,int page){
		DataGridResult dataGridResult = tbContentService.getContentList(categoryId,rows,page);
		return dataGridResult;
	}

	
	@RequestMapping("/save")
	@ResponseBody
	public E3Result saveContent(TbContent tbContent){
		
		E3Result e3Result = tbContentService.saveContent(tbContent);
		
		return e3Result;
	}
	@RequestMapping("/edit")
	@ResponseBody
	public E3Result editContent(TbContent tbContent){
		E3Result e3Result = tbContentService.updateContent(tbContent);
		return e3Result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public E3Result deleteContent(String ids){
		
String[] id = ids.split(",");
E3Result e3Result=null;
for (String l : id) {
	long i = Long.parseLong(l);
	e3Result = tbContentService.deleteContent(i);
	
}
		return e3Result ;
	}
}
