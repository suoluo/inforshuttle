package com.inforshuttle.mybatis;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.inforshuttle.mybatis.dto.UserDto;

/**
 * @author gacl
 * 测试一级缓存
 */
public class OneLevelCacheTest {
	private static final Log logger = LogFactory.getLog(OneLevelCacheTest.class);
    /*
     * 一级缓存: 也就Session级的缓存(默认开启)
     */
    @Test
    public void testCache1() {
        SqlSession session = MyBatisUtil.getSqlSession();
        String statement = "com.inforshuttle.mybatis.dao.UserDao.getUser";
        UserDto user = session.selectOne(statement, 1);
        logger.debug("1:"+user.toString());
        
        /*
         * 一级缓存默认就会被使用
         */
        user = session.selectOne(statement, 1);
        logger.debug("2:"+user.toString());
        session.close();
        /*
         1. 必须是同一个Session,如果session对象已经close()过了就不可能用了 
         */
        session = MyBatisUtil.getSqlSession();
        user = session.selectOne(statement, 1);
        logger.debug("3:"+user.toString());
        
        /*
         2. 查询条件是一样的
         */
        user = session.selectOne(statement, 2);
        logger.debug("4:"+user.toString());
        
        /*
         3. 没有执行过session.clearCache()清理缓存
         */
        //session.clearCache(); 
        user = session.selectOne(statement, 2);
        logger.debug("5:"+user.toString());
        
        /*
         4. 没有执行过增删改的操作(这些操作都会清理缓存)
         */
        UserDto user1 = new UserDto();
        user1.setUserid(2);
        user1.setUsername("zhaozq");
        user1.setPassword("123456");
        user1.setAddress("河南新乡");
        session.update("com.inforshuttle.mybatis.dao.UserDao.updateUser",user1);
        user = session.selectOne(statement, 2);
        logger.debug("6:"+user.toString());
    }
}