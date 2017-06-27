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

import com.inforshuttle.mybatis.dao.UserMapperI;
import com.inforshuttle.mybatis.dto.UserDto;

public class UserTestByAnnotationMapper {
	private static final Log logger = LogFactory.getLog(UserTestByAnnotationMapper.class);
	private static SqlSessionFactoryBuilder sqlSessionFactoryBuilder;
	private static SqlSessionFactory sqlSessionFactory;

	public static void main(String args[]) {
		try {
			init();
		} catch (IOException e) {
			logger.error("", e);
		}
		testAdd();
		testUpdate();
//		testDelete();
		testGetUser();
		testGetAll();
	}

	private static void init() throws IOException {
		String resource = "config/mybatis-config.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
		sqlSessionFactory = sqlSessionFactoryBuilder.build(reader);
	}

	public static void testAdd() {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			//得到UserMapperI接口的实现类对象
			UserMapperI mapper = session.getMapper(UserMapperI.class);
			UserDto user = new UserDto();
			user.setUserid(6);
			user.setUsername("lisi");
			user.setPassword("123456");
			user.setAddress("河南郑州");
			user.setCreatetime(new Date());
			int retResult = mapper.add(user);
			logger.debug("retResult:" + retResult);
			session.commit(true);
		} catch (Exception e) {
			logger.error("", e);
			session.rollback(true);
		} finally {
			session.close();
		}
	}
	
	public static void testUpdate() {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			//得到UserMapperI接口的实现类对象
			UserMapperI mapper = session.getMapper(UserMapperI.class);
			UserDto user = new UserDto();
			user.setUserid(2);
			user.setUsername("zhaozq");
			user.setPassword("123456");
			user.setAddress("河南新乡");
			int retResult = mapper.update(user);
			logger.debug("retResult:" + retResult);
			session.commit(true);
		} catch (Exception e) {
			logger.error("", e);
			session.rollback(true);
		} finally {
			session.close();
		}
	}
    
    public static void testDelete(){
    	SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			//得到UserMapperI接口的实现类对象
			UserMapperI mapper = session.getMapper(UserMapperI.class);
	        int retResult = mapper.deleteById(5);
			logger.debug("retResult:" + retResult);
			session.commit(true);
		} catch (Exception e) {
			logger.error("", e);
			session.rollback(true);
		} finally {
			session.close();
		}
    }

	public static void testGetUser() {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			// 得到UserMapperI接口的实现类对象
			UserMapperI mapper = session.getMapper(UserMapperI.class);
			UserDto user = mapper.getById(8);
			session.commit(true);
			if (null != user) {
				logger.debug("username:"+user.getUsername());
			}
		} catch (Exception e) {
			logger.error("", e);
			session.rollback(true);
		} finally {
			session.close();
		}
	}

	public static void testGetAll() {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			// 得到UserMapperI接口的实现类对象
			UserMapperI mapper = session.getMapper(UserMapperI.class);
			List<UserDto> lstUsers = mapper.getAll();
			if (null != lstUsers) {
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
