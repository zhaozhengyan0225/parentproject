package cn.jiang.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.common.page.Pagination;
import cn.jiang.core.bean.product.Product;
import cn.jiang.core.bean.product.ProductQuery;
/**
 * solr全文检索
 * @author Administrator
 *
 */
@Service("searchService")
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SolrServer solrServer;
	
	public Pagination selectPaginationByQuery(String keyword,Integer pageNo) throws Exception {
		//创建包装类
	    ProductQuery productQuery = new ProductQuery();
	    //当前页
	    productQuery.setPageNo(Pagination.cpn(pageNo));
	    //每页显示 12条
	    productQuery.setPageSize(15);
	    
	    //拼接条件
	    StringBuilder params = new StringBuilder();
		
		List<Product> products = new ArrayList<>();
		
		SolrQuery solrQuery = new SolrQuery();
		//关键词
		solrQuery.set("q", "name_ik:"+keyword);
		params.append("keyword=").append(keyword);
		//过滤条件
		//高亮
		solrQuery.setHighlight(true);
		solrQuery.addHighlightField("name_ik");
		// 样式  <span style='color:red'>2016</span>
		solrQuery.setHighlightSimplePre("<span style='color:red'>");
		solrQuery.setHighlightSimplePost("</span>");
		//排序
		solrQuery.addSort("price", ORDER.asc);
		//分页  limit 开始行 ， 每页显示条数
		solrQuery.setStart(productQuery.getStartRow());
		solrQuery.setRows(productQuery.getPageSize());
		//执行查询
		QueryResponse response = solrServer.query(solrQuery);
		
		//取高亮
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		// Map K : V    442 : Map
		// Map K : V    name_ik : List<String>
		// List<String> list   2016年最新款191期卖的瑜伽服最新限量纯手工制作细心打造商户经典买一送一清 list.get(0);
		SolrDocumentList docs = response.getResults();
		//发现的条数 （总条件）构建分页时用到
		long numFound = docs.getNumFound();
		for (SolrDocument doc : docs) {
			//创建商品对象
			Product product = new Product();
			//商品ID 
			String id = (String) doc.get("id");
			product.setId(Long.parseLong(id));
			//商品名称  ik
			Map<String, List<String>> map = highlighting.get(id);
			List<String> list = map.get("name_ik");
			product.setName(list.get(0));
			/*
			String name = (String) doc.get("name_ik");
			product.setName(name);
			*/
			//图片
			String url = (String) doc.get("url");
			product.setImgUrl(url);//img,img2,img3
			//最低商品价格，商品上架时存入
			Float price = (Float) doc.get("price");
			product.setPrice(price);
			//品牌ID Long
			Integer brandId = (Integer) doc.get("brandId");
			product.setBrandId(Long.parseLong(String.valueOf(brandId)));
			
			products.add(product);
		}
		
		//构建分页对象
		Pagination pagination = new Pagination(
				productQuery.getPageNo(),
				productQuery.getPageSize(),
				(int)numFound,
				products
				);
		//页面展示
		String url = "/search";
		pagination.pageView(url, params.toString());
		
		return pagination;
	}

}
