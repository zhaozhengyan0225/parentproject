package cn.jiang;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.jiang.core.bean.TestPro;
import cn.jiang.core.dao.TestProDao;
import cn.jiang.core.service.TestProService;

/**
 * 测试
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class TestProTest {
	@Autowired
	private TestProDao testProDao;
	@Autowired
	private TestProService testProService;
	
	@Test
	public void testAdd() throws Exception {
		TestPro testPro = new TestPro();

		testPro.setName("神经病");
		testPro.setBirthday(null);
		
		testProDao.insertTb(testPro);
	}
	@Test
	public void testUpdat() throws Exception {
		TestPro testPro = new TestPro();

		testPro.setId(12);
		testPro.setName("神经病");
		testPro.setBirthday(new Date());

		testProDao.updateTb(testPro);

		testPro.setBirthday(null);

		testProDao.updateTb(testPro);
	}
	@Test
	public void testAdd1() throws Exception {
		TestPro testPro = new TestPro();
		testPro.setName("刘德华");
		testPro.setBirthday(new Date());
		
		testProService.insertTestPro(testPro);
	}
	@Test
	public void testAdd2() throws Exception {
		TestPro testPro = new TestPro();
		testPro.setName("事务分解机");
		testPro.setBirthday(new Date());
		
		testProService.insertTestPro(testPro);
	}
}
