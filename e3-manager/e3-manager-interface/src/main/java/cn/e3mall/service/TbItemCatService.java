package cn.e3mall.service;

import java.util.List;

import cn.e3mall.pojo.TbItemCat;

public interface TbItemCatService {

	List<TbItemCat> getTbItemCat(long parentId);
}
