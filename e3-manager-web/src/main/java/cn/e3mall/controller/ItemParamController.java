package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.DataGridResult;
import cn.e3mall.service.ItemParamService;

@RequestMapping("/item/param/")
@Controller
public class ItemParamController {

	
	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("list")
	@ResponseBody
	public DataGridResult getItemParamList(int page,int rows){
		DataGridResult result = itemParamService.getItemParamService(page, rows);
		return result;
	}
}
