package cn.e3mall.service;

import java.util.List;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.pojo.TbContentCategory;

public interface TbContentCategoryService {

	List<TbContentCategory> getContentCategory(long parentId);
	E3Result createContentCategory(long parentId,String name);
	void updateContentCategory(long id,String name);
	boolean deleteContentCategory(long id);
}
