package com.inforshuttle.mybatis.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inforshuttle.mybatis.encrypt.TripleDes;

/**
 * md5加密 数据库密码
 * 
 * @author gogogo119
 * 
 */
public class EncryptedDataSource extends org.apache.tomcat.jdbc.pool.DataSource {
    private static Logger log = LoggerFactory.getLogger(EncryptedDataSource.class);
    private final TripleDes des = new TripleDes();

    @Override
    public void setPassword(String password) {
        super.setPassword(decrptPassword(password));
    }

    private String decrptPassword(String password) {
        final String key = "QE!@^&0(J6H#$%DRN*$v7rnt";
        String dePassword = null;
        try {
            dePassword = des.decrypt(password, key);
        } catch (Exception e) {
            log.error("", e);
        }
        return dePassword;
    }
}
