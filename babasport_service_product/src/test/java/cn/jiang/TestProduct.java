package cn.jiang;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.jiang.core.bean.product.Product;
import cn.jiang.core.bean.product.ProductQuery;
import cn.jiang.core.dao.product.ProductDao;

/**
 * 测试
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class TestProduct {
	@Autowired
	private ProductDao productDao;
	
	@Test
	public void testAdd() throws Exception {
		//通过id查询
//		Product product = productDao.selectByPrimaryKey(441L);
//		System.out.println(product);
		
		//通过条件查询  分页，指定字段，查询，排序
		ProductQuery productQuery = new ProductQuery();
		//productQuery.createCriteria().andBrandIdEqualTo(4L).andNameLike("%好莱坞%");
		
		//分页
		productQuery.setPageNo(3);
		productQuery.setPageSize(10);
		//排序
		productQuery.setOrderByClause("id desc");
		//指定字段
		productQuery.setFields("id,brand_id");
		
		List<Product> productList = productDao.selectByExample(productQuery);
		for (Product product : productList) {
			System.out.println(product);
		}
		
		int countByExample = productDao.countByExample(productQuery);
	}
	
}
