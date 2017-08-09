package cn.e3mall.service;

import cn.e3mall.common.pojo.DataGridResult;
import cn.e3mall.pojo.TbItem;

public interface TbItemService {

	TbItem selectByPrimaryKey(Long id);
	DataGridResult getItemList(int page,int rows);
	void deleteItemById(long id);
}
