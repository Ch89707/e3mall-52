package cn.e3mall.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.pojo.TreeNode;
import cn.e3mall.pojo.TbContentCategory;
import cn.e3mall.content.service.TbContentCategoryService;

@Controller
public class ContentCategoryController {

	@Autowired
	private TbContentCategoryService tbContentCategoryService;
	//查询结点
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<TreeNode> getContentList(@RequestParam(value="id",defaultValue="0") long parentId){
		
		List<TbContentCategory> contentList = tbContentCategoryService.getContentCategory(parentId);
		List<TreeNode>list=new ArrayList<>();
		for (TbContentCategory tbContentCategory : contentList) {
				TreeNode treeNode = new TreeNode();
				Long id = tbContentCategory.getId();
				String name = tbContentCategory.getName();
				treeNode.setId(id);
				treeNode.setState(tbContentCategory.getIsParent()?"closed":"open");
				treeNode.setText(name);
				list.add(treeNode);
		}
		return list;
	}
	//新增结点
	@RequestMapping("/content/category/create")
	@ResponseBody
	public E3Result createNode(long parentId,String name){
		//System.out.println(parentId+":::"+name);
		E3Result e3Result = tbContentCategoryService.createContentCategory(parentId, name);
		
		return e3Result;
	}
	//修改结点
	@RequestMapping("/content/category/update")
	@ResponseBody
	public E3Result updateNode(long id,String name){
		
		tbContentCategoryService.updateContentCategory(id, name);
		return E3Result.ok();
	}
	
	@RequestMapping("/content/category/delete")
	@ResponseBody
	public String deleteNode(long id){
		
		boolean b = tbContentCategoryService.deleteContentCategory(id);
		if(b){
			return "ok";
		}else{
			return "no";
		}
		
	}
	
	
}
