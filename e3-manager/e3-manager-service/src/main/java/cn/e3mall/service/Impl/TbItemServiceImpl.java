package cn.e3mall.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.common.pojo.DataGridResult;
import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.utils.IDUtils;
import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.service.TbItemService;

@Service
public class TbItemServiceImpl implements TbItemService {


	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
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

	public E3Result saveItem(TbItem tbItem, String desc) {
		
		long id = IDUtils.genItemId();
		tbItem.setId(id);
		tbItem.setStatus((byte)1);
		tbItem.setCreated(new Date());
		tbItem.setUpdated(new Date());
		tbItemMapper.insert(tbItem);
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(id);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
	//	E3Result e3Result = new E3Result();
		tbItemDescMapper.insert(itemDesc);
		return E3Result.ok();
	}
	@Override
	public E3Result deleteItemById(long id) {
		updateItemStatus(id, (byte)0);
		return E3Result.ok();
	}

	@Override
	public E3Result instockItem(long id) {
		updateItemStatus(id, (byte)2);
		return E3Result.ok();
	}

	@Override
	public E3Result reshelfItem(long id) {
		updateItemStatus(id, (byte)1);
		return E3Result.ok();
	}
	
	public void  updateItemStatus(long id,byte i){
		TbItem item = tbItemMapper.selectByPrimaryKey(id);
		item.setStatus(i);
		tbItemMapper.updateByPrimaryKey(item);
	}

}
