package cn.e3mall.content.service;

import java.util.List;

import cn.e3mall.pojo.TbContent;

public interface TbContentIndexService {

	List<TbContent> getContentList(long cid);
}
