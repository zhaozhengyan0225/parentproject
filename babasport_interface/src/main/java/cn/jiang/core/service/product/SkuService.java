package cn.jiang.core.service.product;

import java.util.List;

import cn.jiang.core.bean.product.Sku;
/**
 * 库存管理
 * @author Administrator
 *
 */
public interface SkuService {
	//查询库存结果集
	public List<Sku> selectSkuListByProductId(Long productId);
	//修改
	public void updateSkuById(Sku sku);
}
