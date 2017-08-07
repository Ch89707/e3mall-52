package cn.e3mall.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.TreeNode;
import cn.e3mall.pojo.TbItemCat;
import cn.e3mall.service.TbItemCatService;

@Controller
public class TbItemCatController {

	@Autowired
	private TbItemCatService tbItemCatService;
	
	@RequestMapping("/item/cat/list")
	@ResponseBody
	public List<TreeNode> getTreeNodeList(@RequestParam(value="id",defaultValue="0")long parentId){
		
		List<TbItemCat> list = tbItemCatService.getTbItemCat(parentId);
		List<TreeNode> treeNodeList=new ArrayList<>();
		for (TbItemCat tbItemCat : list) {
			TreeNode node = new TreeNode();
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
			node.setState(tbItemCat.getIsParent()?"closed":"open");
			treeNodeList.add(node);
		} 
		return treeNodeList;
	}
	
	
}
