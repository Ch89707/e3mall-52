package cn.e3mall.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.common.pojo.DataGridResult;
import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentExample;
import cn.e3mall.pojo.TbContentExample.Criteria;
import cn.e3mall.service.TbContentService;

@Service
public class TbContentServiceImpl implements TbContentService {

@Autowired
private TbContentMapper tbContentMapper;
	
	public DataGridResult getContentList(long categoryId,int rows, int page) {
		PageHelper.startPage(page, rows);
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> list = tbContentMapper.selectByExample(example);
		PageInfo<TbContent>info=new PageInfo<>(list);
		long total = info.getTotal();
		
		DataGridResult dataGridResult = new DataGridResult();
		dataGridResult.setTotal(total);
		dataGridResult.setRows(list);
		return dataGridResult;
	}

	public E3Result saveContent(TbContent tbContent) {

		tbContent.setCreated(new Date());
		tbContent.setUpdated(new Date());
		 tbContentMapper.insert(tbContent);
		return E3Result.ok();
	}

	@Override
	public E3Result updateContent(TbContent tbContent) {

		Long id = tbContent.getId();
		TbContent content = tbContentMapper.selectByPrimaryKey(id);
		content.setTitle(tbContent.getTitle());
		content.setSubTitle(tbContent.getSubTitle());
		content.setTitleDesc(tbContent.getTitleDesc());
		content.setPic(tbContent.getPic());
		content.setPic2(tbContent.getPic2());
		content.setUrl(tbContent.getUrl());
		content.setUpdated(new Date());
		content.setContent(tbContent.getContent());
		tbContentMapper.updateByPrimaryKey(content);
		return E3Result.ok();
	}

	@Override
	public E3Result deleteContent(long l) {

		
		tbContentMapper.deleteByPrimaryKey(l);
		
		return E3Result.ok();
	}

}
