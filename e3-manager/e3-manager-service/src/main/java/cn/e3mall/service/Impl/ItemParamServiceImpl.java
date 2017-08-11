package cn.e3mall.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.common.pojo.DataGridResult;
import cn.e3mall.mapper.TbItemParamMapper;
import cn.e3mall.pojo.TbItemParam;
import cn.e3mall.pojo.TbItemParamExample;
import cn.e3mall.pojo.TbItemParamExample.Criteria;
import cn.e3mall.service.ItemParamService;

@Service
public class ItemParamServiceImpl implements ItemParamService {

	
	@Autowired
	private TbItemParamMapper tbItemParamMapper;
	public DataGridResult getItemParamService(int page,int rows) {
		PageHelper.startPage(page, rows);
		TbItemParamExample example = new TbItemParamExample();
		List<TbItemParam> list = tbItemParamMapper.selectByExample(example);
		PageInfo<TbItemParam> info = new PageInfo<>(list);
		long total = info.getTotal();
		DataGridResult result = new DataGridResult();
		result.setRows(list);
		result.setTotal(total);
		return result;
	}

}
