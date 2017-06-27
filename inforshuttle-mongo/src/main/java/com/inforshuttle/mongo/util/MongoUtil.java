package com.inforshuttle.mongo.util;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;

/**
 * 
 * @author zhaoam
 * @date 2017年6月23日 下午3:47:12
 * 
 */
public class MongoUtil {
	public static void main(String[] args) {
		ServerAddress serverAddress = new ServerAddress("127.0.0.1", 27017);
		List<ServerAddress> seeds = new ArrayList<ServerAddress>();
		seeds.add(serverAddress);
		MongoCredential credentials = MongoCredential.createScramSha1Credential("muser", "datamining",
				"123456".toCharArray());
		List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();
		credentialsList.add(credentials);
		// 连接到 mongodb 服务
		MongoClient client = new MongoClient(seeds, credentialsList);
		// 连接到数据库
		MongoDatabase db = client.getDatabase("datamining");
		//获取所有的集合
		MongoIterable<Document> collections = db.listCollections();
		for (Document document : collections) {
			System.out.println(document);
		}
		MongoCollection<Document> airmin = db.getCollection("T_DATA_AIR_MIN-2017");
		List<Document> foundDocument = airmin.find().into(new ArrayList<Document>());
		System.out.println(foundDocument);
		
		// 创建集合
		// db.createCollection("suoluo");
		// System.out.println("创建集合成功");
		MongoCollection<Document> suoluo = db.getCollection("suoluo");
		System.out.println("集合选择成功:" + suoluo.count());
		
		//插入文档  
        /** 
        * 1. 创建文档 org.bson.Document 参数为key-value的格式 
        * 2. 创建文档集合List<Document> 
        * 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>) 插入单个文档可以用 mongoCollection.insertOne(Document) 
        * */
		Document document1 = new Document("title", "MongoDB1").append("description", "database1").append("likes", 101).append("by", "Suoluo1");
		Document document2 = new Document("title", "MongoDB2").append("description", "database2").append("likes", 102).append("by", "Suoluo2");
		Document document3 = new Document("title", "MongoDB3").append("description", "database3").append("likes", 103).append("by", "Suoluo3");
		suoluo.insertOne(document1);
		List<Document> documents = new ArrayList<Document>();
		documents.add(document2);
		documents.add(document3);
		suoluo.insertMany(documents);
		System.out.println("文档插入成功");
		
		//检索所有文档  
        /** 
        * 1. 获取迭代器FindIterable<Document> 
        * 2. 获取游标MongoCursor<Document> 
        * 3. 通过游标遍历检索出的文档集合 
        * */  
		FindIterable<Document> findIterable = suoluo.find();
		MongoCursor<Document> mongoCursor = findIterable.iterator();
        while(mongoCursor.hasNext()){  
           System.out.println(mongoCursor.next());  
        }
        //更新文档   将文档中likes=100的文档修改为likes=200   
		suoluo.updateMany(Filters.eq("likes", 101), new Document("$set", new Document("likes", 201)));
		// 删除符合条件的第一个文档
		suoluo.deleteOne(Filters.eq("likes", 102));
		// 删除所有符合条件的文档
		suoluo.deleteMany(Filters.eq("likes", 102));
		client.close();
	}
}
