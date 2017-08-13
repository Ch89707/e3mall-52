package cn.e3mall.content.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.mapper.TbContentCategoryMapper;
import cn.e3mall.pojo.TbContentCategory;
import cn.e3mall.pojo.TbContentCategoryExample;
import cn.e3mall.pojo.TbContentCategoryExample.Criteria;
import cn.e3mall.pojo.TbContentExample;
import cn.e3mall.content.service.TbContentCategoryService;

@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService {

	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;

	//查询
	public List<TbContentCategory> getContentCategory(long parentId) {

		TbContentCategoryExample example = new TbContentCategoryExample();
		 Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		criteria.andStatusEqualTo(1);
		List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
		return list;
	}

	//新增
	public E3Result createContentCategory(long parentId,String name) {
		
		TbContentCategory tbContentCategory = new TbContentCategory();
		TbContentCategory parentContent = tbContentCategoryMapper.selectByPrimaryKey(parentId);
		tbContentCategory.setParentId(parentId);
		tbContentCategory.setName(name);
		tbContentCategory.setCreated(new Date());
		tbContentCategory.setUpdated(new Date());
		tbContentCategory.setSortOrder(1);
		tbContentCategory.setStatus(1);
		tbContentCategory.setIsParent(false);
		tbContentCategoryMapper.insert(tbContentCategory);
		if(!parentContent.getIsParent()){
		parentContent.setIsParent(true);
		tbContentCategoryMapper.updateByPrimaryKey(parentContent);
		}		
	return E3Result.ok(tbContentCategory);	
	}

	public void updateContentCategory(long id, String name) {
			
		TbContentCategory contentCategory = tbContentCategoryMapper.selectByPrimaryKey(id);
		contentCategory.setName(name);
		tbContentCategoryMapper.updateByPrimaryKey(contentCategory);
	}

	@Override
	public boolean deleteContentCategory(long id) {

		TbContentCategory contentCategory = tbContentCategoryMapper.selectByPrimaryKey(id);
		Boolean isParent = contentCategory.getIsParent();
		if(!isParent){
			contentCategory.setStatus(2);
			tbContentCategoryMapper.updateByPrimaryKey(contentCategory);
			return true;
		}
		return false;
	}

}
