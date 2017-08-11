package cn.e3mall.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.mapper.TbItemParamMapper;
import cn.e3mall.pojo.TbItemParam;
import cn.e3mall.pojo.TbItemParamExample;
import cn.e3mall.pojo.TbItemParamExample.Criteria;
import cn.e3mall.service.TbItemParamService;

@Service
public class TbItemParamServiceImpl implements TbItemParamService {


	@Autowired
	private TbItemParamMapper tbItemParamMapper;
	public List<TbItemParam> selectItemParamById(long item_cat_id) {

		TbItemParamExample example = new TbItemParamExample();
		
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(item_cat_id);
		 List<TbItemParam> list = tbItemParamMapper.selectByExample(example);
		
		return list;
	}

}
