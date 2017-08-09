package cn.e3mall.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.common.pojo.DataGridResult;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.service.TbItemService;

@Service
public class TbItemServiceImpl implements TbItemService {


	@Autowired
	private TbItemMapper tbItemMapper;

	public TbItem selectByPrimaryKey(Long id) {
		TbItem tbItem = tbItemMapper.selectByPrimaryKey(id);
		return tbItem;
	}

	public DataGridResult getItemList(int page, int rows) {
		
			PageHelper.startPage(page, rows);
			TbItemExample itemExample = new TbItemExample();
			List<TbItem> list = tbItemMapper.selectByExample(itemExample);
			PageInfo<TbItem> pageInfo = new PageInfo<>(list);
			long total = pageInfo.getTotal();
			
			DataGridResult dataGridResult = new DataGridResult();
			dataGridResult.setTotal(total);
			dataGridResult.setRows(list);
			return dataGridResult;
	}

	@Override
	public void deleteItemById(long id) {
		 tbItemMapper.deleteByPrimaryKey(id);
		
	}

}
