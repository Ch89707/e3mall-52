package cn.e3mall.content.service;

import java.util.List;

import cn.e3mall.common.pojo.DataGridResult;
import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.pojo.TbContent;

public interface TbContentService {

	List<TbContent> getContentList(long categoryId);

	E3Result saveContent(TbContent tbContent);

	E3Result updateContent(TbContent tbContent);

	E3Result deleteContent(long l);

}
