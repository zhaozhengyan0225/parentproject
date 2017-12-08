package cn.jiang.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 前台的商品页面
 * @author Administrator
 *
 */
@Controller
public class ProductController {
	//去首页入口
	@RequestMapping(value = "/")
	public String index(){
		
		return "index";
	}
}
