package cn.e3mall.service;

import cn.e3mall.common.pojo.DataGridResult;
import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.pojo.TbContent;

public interface TbContentService {

	DataGridResult getContentList(long categoryId,int rows, int page);

	E3Result saveContent(TbContent tbContent);

	E3Result updateContent(TbContent tbContent);

	E3Result deleteContent(long l);

}
