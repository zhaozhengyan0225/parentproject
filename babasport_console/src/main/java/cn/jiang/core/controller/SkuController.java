package cn.jiang.core.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.jiang.core.bean.product.Sku;
import cn.jiang.core.service.product.SkuService;

/**
 * 库存管理
 * 去库存页面
 * js 修改
 * 保存
 * @author Administrator
 *
 */
@Controller
public class SkuController {
	@Autowired
	private SkuService skuService;
	
	//去库存页面
	@RequestMapping(value = "/sku/list.do")
	public String list(Long productId,Model model){
		List<Sku> skus = skuService.selectSkuListByProductId(productId);
		model.addAttribute("skus", skus);
		return "sku/list";
	}
	//修改
	@RequestMapping(value = "/sku/addSku.do")
	public void addSku(Sku sku,HttpServletResponse response) throws IOException{
		response.setContentType("application/json;charset=UTF-8");
		
		skuService.updateSkuById(sku);
		
		JSONObject js = new JSONObject();
		js.put("message", "保存成功!");
		
		response.getWriter().write(js.toString());
	}
	
}
