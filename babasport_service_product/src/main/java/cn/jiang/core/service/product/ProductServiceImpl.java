package cn.jiang.core.service.product;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.common.page.Pagination;
import cn.jiang.core.bean.product.Color;
import cn.jiang.core.bean.product.ColorQuery;
import cn.jiang.core.bean.product.Product;
import cn.jiang.core.bean.product.ProductQuery;
import cn.jiang.core.bean.product.ProductQuery.Criteria;
import cn.jiang.core.bean.product.Sku;
import cn.jiang.core.dao.product.ColorDao;
import cn.jiang.core.dao.product.ProductDao;
import cn.jiang.core.dao.product.SkuDao;
import redis.clients.jedis.Jedis;

/**
 * 商品
 * @author Administrator
 *
 */
@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDao productDao;
	@Autowired
	private ColorDao colorDao;
	@Autowired
	private SkuDao skuDao;
	@Autowired
	private Jedis jedis;
	
	//分页对象
	@Override
	public Pagination selectPaginationByQuery(Integer pageNo, String name, Long brandId, Boolean isShow) {
		ProductQuery productQuery = new ProductQuery();
		productQuery.setPageNo(Pagination.cpn(pageNo));
		
		StringBuilder params = new StringBuilder();
		//排序
		productQuery.setOrderByClause("id desc");
		Criteria createCriteria = productQuery.createCriteria();
		if (null != name) {
			createCriteria.andNameLike("%" + name + "%");
			params.append("name=").append(name);
		}
		if (null != brandId) {
			createCriteria.andBrandIdEqualTo(brandId);
			params.append("&brandId=").append(brandId);
		}
		if (null != isShow) {
			createCriteria.andIsShowEqualTo(isShow);
			params.append("&isShow=").append(isShow);
		}else{
			createCriteria.andIsShowEqualTo(false);
			params.append("&isShow=").append(false);
		}
		
		Pagination pagination = new Pagination(
				productQuery.getPageNo(),
				productQuery.getPageSize(),
				productDao.countByExample(productQuery),
				productDao.selectByExample(productQuery)
				);
		String url = "/product/list.do";
		pagination.pageView(url, params.toString());
		return pagination;
	}

	//加载颜色
	//查询颜色结果集
	@Override
	public List<Color> selectColorList() {
		ColorQuery colorQuery = new ColorQuery();
		colorQuery.createCriteria().andParentIdNotEqualTo(0L);
		return colorDao.selectByExample(colorQuery);
	}
	
	//商品保存
	@Override
	public void insertProduct(Product product) {
		//设置商品ID
		Long id = jedis.incr("pno");
		product.setId(id);
		//下架状态
		product.setIsShow(false);
		//是否删除
		product.setIsDel(true);
		//创建时间
		product.setCreateTime(new Date());
		//保存商品
		productDao.insertSelective(product);
		//返回ID
		//保存SKU
		String[] colors = product.getColors().split(",");
		String[] sizes = product.getSizes().split(",");
		for (String color : colors) {
			for (String size : sizes) {
				//保存SKU
				Sku sku = new Sku();
				//商品id
				sku.setProductId(product.getId());
				//颜色
				sku.setColorId(Long.parseLong(color));
				//尺码
				sku.setSize(size);
				//市场价
				sku.setMarketPrice(999f);
				//售价
				sku.setPrice(666f);
				//运费
				sku.setDeliveFee(8f);
				//库存
				sku.setStock(0);
				//限制
				sku.setUpperLimit(200);
				//时间
				sku.setCreateTime(new Date());
				
				skuDao.insertSelective(sku);
			}
		}
		
	}

	//上架
	@Override
	public void isShow(Long[] ids) {
		Product product = new Product();
		//上架
		product.setIsShow(true);
		
		for (Long id : ids) {
			product.setId(id);
			//更该商品状态
			productDao.updateByPrimaryKeySelective(product);
			//TODO 保存商品信息到SOLr服务器
			
			//TODO 静态化
		}
	}

}
