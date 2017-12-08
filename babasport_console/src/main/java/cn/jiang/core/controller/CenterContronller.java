package cn.jiang.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台管理
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/control")
public class CenterContronller {

	// 入口  http://localhost:8080/control/index.do
	@RequestMapping(value = "/index.do")
	public String index(Model model) {

		return "index";
	}

	// 头
	@RequestMapping(value = "/top.do")
	public String top(Model model) {

		return "top";
	}

	// 身体
	@RequestMapping(value = "/main.do")
	public String main(Model model) {

		return "main";
	}

	// 身体左
	@RequestMapping(value = "/left.do")
	public String left(Model model) {

		return "left";
	}

	// 身体右
	@RequestMapping(value = "/right.do")
	public String right(Model model) {

		return "right";
	}
	// 商品身体
	@RequestMapping(value = "/frame/product_main.do")
	public String product_main(Model model) {
		
		return "frame/product_main";
	}
	// 商品身体-左
	@RequestMapping(value = "/frame/product_left.do")
	public String product_left(Model model) {
		
		return "frame/product_left";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
