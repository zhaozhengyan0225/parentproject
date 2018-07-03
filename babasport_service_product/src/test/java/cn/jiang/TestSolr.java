package cn.jiang;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class TestSolr {
	@Autowired
	private SolrServer solrServerSpring;
	
	@Test
	public void testSolrServerSpring() throws Exception {
		SolrInputDocument doc = new SolrInputDocument();
		doc.setField("id", 16);
		doc.setField("name", "世界别");
		
		solrServerSpring.add(doc);
		solrServerSpring.commit();
	}
	
	@Test
	public void testSolrJ() throws Exception {
		String baseURL = "http://123.207.148.28:8080/solr";
		SolrServer solrServer = new HttpSolrServer(baseURL);
		
		SolrInputDocument doc = new SolrInputDocument();
		doc.setField("id", 15);
		doc.setField("name", "花花世界就好看不是打飞机客户说");
		doc.setField("brandId", "19");

		solrServer.add(doc);
		solrServer.commit();
	}
}
