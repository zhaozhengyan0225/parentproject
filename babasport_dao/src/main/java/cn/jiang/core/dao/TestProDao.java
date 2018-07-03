package cn.jiang.core.dao;

import cn.jiang.core.bean.TestPro;

/**
 * 测试
 * @author Administrator
 *
 */
public interface TestProDao {
	//保存
	public void insertTb(TestPro testPro);

	int updateTb(TestPro testPro);
}
