package cn.e3mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.DataGridResult;
import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.pojo.TbItemParam;
import cn.e3mall.service.TbItemDescService;
import cn.e3mall.service.TbItemParamService;
import cn.e3mall.service.TbItemService;
//要开linux，再开zookeeper,和nginx,还要看linux是否关闭了防火墙，
//linux中出了jdk是永久开启的，其它都是关闭即销毁的。

@Controller
public class TbItemController {

	@Autowired
	private TbItemService tbItemService;
	
	@Autowired
	private TbItemDescService tbItemDescService;
	
	@Autowired
	private TbItemParamService tbItemParamService;
	
	
	/*用于测试的
	 * @RequestMapping("/tbItem/{id}")
	@ResponseBody
	public TbItem getTbItemById( @PathVariable long id){
		
		TbItem tbItem = tbItemService.selectByPrimaryKey(id);
		System.out.println(tbItem.toString()+"------------------");
		return tbItem;
	}*/
	@RequestMapping("/item/list")
	@ResponseBody
	public DataGridResult getItemList(Integer page,Integer rows){
	DataGridResult dataGridResult=tbItemService.getItemList(page, rows);
		return dataGridResult;
	}
	//添加商品
	@RequestMapping(value="/item/save",method=RequestMethod.POST)
	@ResponseBody
	public E3Result saveItem(TbItem tbItem,String desc){
	
		E3Result e3Result = tbItemService.saveItem(tbItem, desc);
		//e3Result.setStatus(200);
		return e3Result;
	}
	
	
	@RequestMapping("/rest/page/item-edit")
	public String toEditDesc(){
		return "item-edit";
	}
	@RequestMapping("/rest/item/query/item/desc/{id}")
	@ResponseBody
	public E3Result toEditquery(@PathVariable long id){
		
		TbItemDesc itemDesc = tbItemDescService.selectItemDescById(id);
		
		return E3Result.ok(itemDesc);
	}
	
	@RequestMapping("/rest/item/param/item/query/{id}")
	@ResponseBody
	public E3Result toEditDesc(@PathVariable long id){
		
		TbItem tbItem = tbItemService.selectByPrimaryKey(id);
		Long cid = tbItem.getCid();
		System.out.println(cid+"ddddddddddddddddddddddd");
		 List<TbItemParam> list = tbItemParamService.selectItemParamById(cid);
		 TbItemParam tbItemParam = list.get(0);
		 System.out.println(tbItemParam.getParamData());
		return E3Result.ok(tbItemParam);
	}

	

	//下架商品
	@RequestMapping("/rest/item/instock")
	@ResponseBody
	public E3Result instockItem(String ids){
	String[] id = ids.split(",");
	E3Result e3Result=null;
	for (String i : id) {
		long l = Long.parseLong(i);
		e3Result = tbItemService.instockItem(l);
	}
		return e3Result;
	}
	//上架商品
		@RequestMapping("/rest/item/reshelf")
		@ResponseBody
		public E3Result reshelfItem(String ids){
		String[] id = ids.split(",");
		E3Result e3Result=null;
		for (String i : id) {
			long l = Long.parseLong(i);
			e3Result = tbItemService.reshelfItem(l);
		}
			return e3Result;
		}
	
	//删除商品
	@RequestMapping("/rest/item/delete")
	@ResponseBody
	public E3Result deleteItemById(String ids){
	String[] id = ids.split(",");
	E3Result e3Result=null;
	for (String i : id) {
		long l = Long.parseLong(i);
		 e3Result = tbItemService.deleteItemById(l);
	}
		return e3Result ;
	}
	

	
}
