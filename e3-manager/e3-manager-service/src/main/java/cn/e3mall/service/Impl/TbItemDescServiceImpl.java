package cn.e3mall.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.service.TbItemDescService;

@Service
public class TbItemDescServiceImpl implements TbItemDescService{

	@Autowired
	private TbItemDescMapper tbItemDescMapper;

	
	public TbItemDesc selectItemDescById(long id) {
	
		TbItemDesc itemDesc = tbItemDescMapper.selectByPrimaryKey(id);
		return itemDesc;
	}

	
}
