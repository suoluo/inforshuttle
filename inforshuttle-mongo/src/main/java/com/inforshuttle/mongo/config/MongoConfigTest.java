package com.inforshuttle.mongo.config;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

/**
* 
* @author zhaoam
* @date 2017年6月24日 下午1:41:10
* 
*/
public class MongoConfigTest {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MongoConfig.class);
		MongoDatabase db = context.getBean(MongoDatabase.class);
		// 连接到数据库
		MongoIterable<Document> collections = db.listCollections();
		for (Document document : collections) {
			System.out.println(document);
		}
		MongoCollection<Document> suoluo = db.getCollection("suoluo");
		System.out.println("集合选择成功:" + suoluo.count());
		
		MongoCollection<Document> mongoCollection = context.getBean(MongoCollection.class);
		System.out.println(mongoCollection.count());
		List<Document> foundDocument = mongoCollection.find().into(new ArrayList<Document>());
		System.out.println(foundDocument);
		context.close();
	}

}
