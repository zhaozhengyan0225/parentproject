package cn.jiang.core.dao.product;

import java.util.List;

import cn.jiang.core.bean.product.Brand;
import cn.jiang.core.bean.product.BrandQuery;

/**
 * 查询
 * @author Administrator
 *
 */
public interface BrandDao {
	//查询结果集
	public List<Brand> selectBrandListByQuery(BrandQuery brandQuery);
	//查询总条数
	public Integer selectCount(BrandQuery brandQuery);
	//通过id查询品牌
	public Brand selectBrandById(Long id);
	//修改
	public void updateBrandById(Brand brand);
	//批量删除
	public void deletes(Long[] ids);
}
