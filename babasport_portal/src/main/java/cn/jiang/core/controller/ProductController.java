package cn.jiang.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.common.page.Pagination;
import cn.jiang.core.bean.product.Product;
import cn.jiang.core.service.SearchService;

/**
 * 前台的商品页面
 * @author Administrator
 *
 */
@Controller
public class ProductController {
	@Autowired
	private SearchService searchService;
	
	//去首页入口
	@RequestMapping(value = "/")
	public String index(){
		return "index";
	}
	
	//搜索
	@RequestMapping(value = "/search")
	public String search(Model model,String keyword,Integer pageNo) throws Exception{
		Pagination pagination = searchService.selectPaginationByQuery(keyword, pageNo);
		model.addAttribute("pagination", pagination);
		return "search";
	}
}
