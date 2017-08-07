package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.DataGridResult;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.service.TbItemService;


@Controller
public class TbItemController {

	@Autowired
	private TbItemService tbItemService;
	
	@RequestMapping("/tbItem/{id}")
	@ResponseBody
	public TbItem getTbItemById( @PathVariable long id){
		
		TbItem tbItem = tbItemService.selectByPrimaryKey(id);
		System.out.println(tbItem.toString()+"------------------");
		return tbItem;
	}
	@RequestMapping("/item/list")
	@ResponseBody
	public DataGridResult getItemList(Integer page,Integer rows){
	DataGridResult dataGridResult=tbItemService.getItemList(page, rows);
		return dataGridResult;
	}

	
}
