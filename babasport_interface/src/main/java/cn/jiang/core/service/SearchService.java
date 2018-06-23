package cn.jiang.core.service;

import cn.itcast.common.page.Pagination;

public interface SearchService {

	public Pagination selectPaginationByQuery(String keyword,Integer pageNo) throws Exception;
}
