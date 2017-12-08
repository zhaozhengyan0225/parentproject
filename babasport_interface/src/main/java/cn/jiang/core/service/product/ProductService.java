package cn.jiang.core.service.product;

import java.util.List;

import cn.itcast.common.page.Pagination;
import cn.jiang.core.bean.product.Color;
import cn.jiang.core.bean.product.Product;

public interface ProductService {
	//分页对象
	public Pagination selectPaginationByQuery(Integer pageNo,String name,Long brandId,Boolean isShow);
	//加载颜色
	public List<Color> selectColorList();
	//商品保存
	public void insertProduct(Product product);
	//商品上架
	public void isShow(Long[] ids);
}
