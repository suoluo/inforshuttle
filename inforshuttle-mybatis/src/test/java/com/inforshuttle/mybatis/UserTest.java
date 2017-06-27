package com.inforshuttle.mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import com.inforshuttle.mybatis.dao.UserDao;
import com.inforshuttle.mybatis.dto.UserDto;

public class UserTest {
	private static final Log logger = LogFactory.getLog(UserTest.class);
	private static SqlSessionFactoryBuilder sqlSessionFactoryBuilder;
	private static SqlSessionFactory sqlSessionFactory;

	@BeforeClass
	public static void init(){
		String resource = "config/mybatis-config.xml";
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
			sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
			sqlSessionFactory = sqlSessionFactoryBuilder.build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testQueryList() {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			UserDto user1 = session.selectOne("com.inforshuttle.mybatis.dao.UserDao.getUser", 1);
			logger.debug("username:" + user1.getUsername());
			UserDao userDao = session.getMapper(UserDao.class);
			UserDto user = new UserDto();
			user.setUsername("zhaoam");
			List<UserDto> users = userDao.getUsers(user);
			if (null != users) {
				logger.debug("Find " + users.size() + " users named zhaoam.");
			}
			session.commit(true);
		} catch (Exception e) {
			logger.error("", e);
			session.rollback(true);
		} finally {
			session.close();
		}
	}
	@Test
	public void testAdd() {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			String statement = "com.inforshuttle.mybatis.dao.UserDao.addUser";// 映射sql的标识字符串
			UserDto user = new UserDto();
			user.setUserid(5);
			user.setUsername("lisi");
			user.setPassword("123456");
			user.setAddress("河南郑州");
			user.setCreatetime(new Date());
			int retResult = session.insert(statement, user);
			logger.debug("retResult:" + retResult);
			session.commit(true);
		} catch (Exception e) {
			logger.error("", e);
			session.rollback(true);
		} finally {
			session.close();
		}
	}
	@Test
	public void testUpdate() {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			String statement = "com.inforshuttle.mybatis.dao.UserDao.updateUser";// 映射sql的标识字符串
			UserDto user = new UserDto();
			user.setUserid(2);
			user.setUsername("zhaozq");
			user.setPassword("123456");
			user.setAddress("河南新乡");
			int retResult = session.update(statement, user);
			logger.debug("retResult:" + retResult);
			session.commit(true);
		} catch (Exception e) {
			logger.error("", e);
			session.rollback(true);
		} finally {
			session.close();
		}
	}
	@Test
    public void testDelete(){
    	SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			String statement = "com.inforshuttle.mybatis.dao.UserDao.deleteUser";//映射sql的标识字符串
	        int retResult = session.delete(statement,5);
			logger.debug("retResult:" + retResult);
			session.commit(true);
		} catch (Exception e) {
			logger.error("", e);
			session.rollback(true);
		} finally {
			session.close();
		}
    }
	@Test
    public void testGetUser(){
//		init();
    	SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			String statement = "com.inforshuttle.mybatis.dao.UserDao.getUser";
	        UserDto user = session.selectOne(statement,2);
	        if(null!=user){	        	
	        	logger.debug("username:" + user.getUsername());
	        }
			session.commit(true);
		} catch (Exception e) {
			logger.error("", e);
			session.rollback(true);
		} finally {
			session.close();
		}
    }
	@Test
    public void testGetAll(){
    	SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			String statement = "com.inforshuttle.mybatis.dao.UserDao.getAllUsers";//映射sql的标识字符串
	        List<UserDto> lstUsers = session.selectList(statement);
	        if (null != lstUsers) {
				// Mybatis的日志级别竟然没得info，只好用error代替了
				logger.debug("Find " + lstUsers.size() + " users named zhaoam.");
			}
			session.commit(true);
		} catch (Exception e) {
			logger.error("", e);
			session.rollback(true);
		} finally {
			session.close();
		}
    }
}
