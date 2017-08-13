package cn.e3mall.content.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.jedis.JedisClientCluster;
import cn.e3mall.common.pojo.DataGridResult;
import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentExample;
import cn.e3mall.pojo.TbContentExample.Criteria;
import redis.clients.jedis.JedisCluster;
import cn.e3mall.content.service.TbContentService;

@Service
public class TbContentServiceImpl implements TbContentService {

private static final String CONTENT="content";

@Autowired
private TbContentMapper tbContentMapper;
	
@Autowired
private JedisClient jedisClient;
	public List<TbContent> getContentList(long categoryId) {
		
		String json = jedisClient.hget(CONTENT, categoryId+"");
		if(!StringUtils.isEmpty(json)){
			List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
			return list;
		}
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> list = tbContentMapper.selectByExample(example);
		jedisClient.hset(CONTENT, categoryId+"", JsonUtils.objectToJson(list));
		return list;
	}

	public E3Result saveContent(TbContent tbContent) {

		tbContent.setCreated(new Date());
		tbContent.setUpdated(new Date());
		 tbContentMapper.insert(tbContent);
		 jedisClient.hdel(CONTENT, tbContent.getCategoryId().toString());
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
