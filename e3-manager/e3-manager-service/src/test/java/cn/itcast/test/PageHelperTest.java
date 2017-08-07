package cn.itcast.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemExample;

public class PageHelperTest {

	
	@Test
	public void t1(){
		PageHelper.startPage(1, 10);
		//执行查询
		//初始化spring容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		TbItemMapper itemMapper = ac.getBean(TbItemMapper.class);
		TbItemExample tbItemExample = new TbItemExample();
		//从容器中获得Mapper的代理对象
		List<TbItem> list = itemMapper.selectByExample(tbItemExample);
		PageInfo<TbItem>pageInfo=new PageInfo<>(list);
		System.out.println(pageInfo.getTotal());
		System.out.println(pageInfo.getPages());
		System.out.println(pageInfo.getSize());
		
		
		
		
	}
}
