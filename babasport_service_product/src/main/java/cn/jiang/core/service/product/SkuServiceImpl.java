package cn.jiang.core.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jiang.core.bean.product.Sku;
import cn.jiang.core.bean.product.SkuQuery;
import cn.jiang.core.dao.product.ColorDao;
import cn.jiang.core.dao.product.SkuDao;
/**
 * 库存管理
 * @author Administrator
 *
 */
@Service("skuService")
@Transactional
public class SkuServiceImpl implements SkuService{

	@Autowired
	private SkuDao skuDao;
	@Autowired
	private ColorDao colorDao;
	
	
	@Override
	public List<Sku> selectSkuListByProductId(Long productId) {
		SkuQuery skuQuery = new SkuQuery();
		skuQuery.createCriteria().andProductIdEqualTo(productId);
		
		List<Sku> skus = skuDao.selectByExample(skuQuery);
		for (Sku sku : skus) {
			sku.setColor(colorDao.selectByPrimaryKey(sku.getColorId()));
		}
		return skus;
	}


	//修改
	@Override
	public void updateSkuById(Sku sku) {
		skuDao.updateByPrimaryKeySelective(sku);
	}

}
