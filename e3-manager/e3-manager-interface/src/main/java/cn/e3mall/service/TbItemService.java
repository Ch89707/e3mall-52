package cn.e3mall.service;

import cn.e3mall.common.pojo.DataGridResult;
import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.pojo.TbItem;

public interface TbItemService {

	TbItem selectByPrimaryKey(Long id);
	DataGridResult getItemList(int page,int rows);
	E3Result saveItem(TbItem tbItem,String desc);
	E3Result deleteItemById(long id);
	E3Result instockItem(long id);
	//上架
	E3Result reshelfItem(long id);
}
