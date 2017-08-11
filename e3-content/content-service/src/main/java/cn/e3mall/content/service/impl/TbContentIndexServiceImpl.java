package cn.e3mall.content.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.content.service.TbContentIndexService;
import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentExample;
import cn.e3mall.pojo.TbContentExample.Criteria;

@Service
public class TbContentIndexServiceImpl implements TbContentIndexService {

	@Autowired
	private TbContentMapper tbContentMapper;
	
	public List<TbContent> getContentList(long cid) {

		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(cid);
		List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
		return list;
	}

}
