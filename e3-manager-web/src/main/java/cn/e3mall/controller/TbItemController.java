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
//要开linux，再开zookeeper,和nginx,还要看linux是否关闭了防火墙，
//linux中出了jdk是永久开启的，其它都是关闭即销毁的。

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
	@RequestMapping("/rest/item/delete")
	@ResponseBody
	public String deleteItemById(String ids){
	String[] id = ids.split(",");
	for (String i : id) {
		long l = Long.parseLong(i);
		tbItemService.deleteItemById(l);
		System.out.println(l+"--------------------------------");
	}
		return null;
	}
	

	
}
