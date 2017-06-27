package com.inforshuttle.mybatis;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.inforshuttle.mybatis.dto.UserDto;

/**
 * @author gacl 测试二级缓存
 */
public class TwoLevelCacheTest {
	private static final Log logger = LogFactory.getLog(OneLevelCacheTest.class);

	/*
	 * 测试二级缓存 使用两个不同的SqlSession对象去执行相同查询条件的查询，第二次查询时不会再发送SQL语句，而是直接从缓存中取出数据
	 */
	@Test
	public void testCache2() {
		String statement = "com.inforshuttle.mybatis.dao.UserDao.getUser";
		SqlSessionFactory factory = MyBatisUtil.getSqlSessionFactory();
		// 开启两个不同的SqlSession
		SqlSession session1 = factory.openSession();
		SqlSession session2 = factory.openSession();
		// 使用二级缓存时，User类必须实现一个Serializable接口===> User implements Serializable
		UserDto user = session1.selectOne(statement, 1);
		session1.commit();// 不懂为啥，这个地方一定要提交事务之后二级缓存才会起作用
		logger.debug(user.toString());

		// 由于使用的是两个不同的SqlSession对象，所以即使查询条件相同，一级缓存也不会开启使用
		// 两个会话的情况下能够命中缓存，证明使用了二级缓存
		user = session2.selectOne(statement, 1);
		// session2.commit();
		logger.debug(user.toString());
	}
}