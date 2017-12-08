package cn.jiang.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jiang.core.bean.TestPro;
import cn.jiang.core.dao.TestProDao;

/**
 * 测试
 * @author Administrator
 *
 */
@Service("testProService")
@Transactional
public class TestProServiceImpl implements TestProService {
	@Autowired
	private TestProDao testProDao;
	
	@Override
	public void insertTestPro(TestPro testPro){
		testProDao.insertTb(testPro);
		
		//throw new RuntimeException();//抛出异常，因为是事务，就回滚，无法插入
	}
}
