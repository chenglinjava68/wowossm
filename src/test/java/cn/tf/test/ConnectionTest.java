package cn.tf.test;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-dao.xml")
public class ConnectionTest {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	
	@Test
	public void testConn() {
		Connection con = null;
		try {
			 con = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		assertNotNull(con);
	}

	
	@Test
	public void testConn02() {
		Connection con = sqlSessionFactory.openSession().getConnection();
		assertNotNull(con);
	}
	
}
