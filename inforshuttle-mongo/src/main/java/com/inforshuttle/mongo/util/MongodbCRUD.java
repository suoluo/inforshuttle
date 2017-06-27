
package com.inforshuttle.mongo.util;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

/**
 * java mongodb的数据插入、读取、更新、删除
 */
public class MongodbCRUD {
	private static MongoClient client = null;
	private static MongoDatabase db = null;
	private static final String COLLECTION_NAME = "infor";

	public static void main(String[] args) {
		// 获取数据库连接
		startMongoDBConn();
		// 保存数据
		createColData();
		// 读取数据
		readColData();
		// 更新数据
		updateColData();
		// 读取数据
		readColData();
		// 删除数据
		deleteColData();
		// 读取数据
		readColData();
		// 删除数据集
		db.getCollection(COLLECTION_NAME).drop();
		// 关闭数据库连接
		stopMondoDBConn();
	}

	private static void createColData() {
		MongoCollection<Document> collection = db.getCollection(COLLECTION_NAME);
		System.out.println("向数据集中插入数据开始：");
		List<Document> documents = new ArrayList<Document>();
		Document document1 = new Document("name", "小李").append("age", 30).append("address", "江苏南京");
		documents.add(document1);
		Document document2 = new Document("name", "小张").append("age", 25).append("address", "江苏苏州");
		documents.add(document2);
		collection.insertMany(documents);
		System.out.println("向数据集中插入数据完成！");
		System.out.println("------------------------------");
	}

	private static void readColData() {
		MongoCollection<Document> collection = db.getCollection(COLLECTION_NAME);
		FindIterable<Document> findIterable = collection.find();
		MongoCursor<Document> mongoCursor = findIterable.iterator();
		System.out.println("从数据集中读取数据：");
		while (mongoCursor.hasNext()) {
			Document document = (Document) mongoCursor.next();
			if (document != null) {
				System.out.println("name:" + document.getString("name"));
				System.out.println("age:" + document.getInteger("age"));
				System.out.println("address:" + document.getString("address"));
			}
		}
	}

	private static void updateColData() {
		System.out.println("------------------------------");
		MongoCollection<Document> collection = db.getCollection(COLLECTION_NAME);
		collection.updateMany(new Document(), new Document("$set", new Document("age", 40)));
		System.out.println("更新数据完成！");
		System.out.println("------------------------------");
	}

	private static void deleteColData() {
		System.out.println("------------------------------");
		MongoCollection<Document> collection = db.getCollection(COLLECTION_NAME);
		System.out.println("删除【小李】！");
		collection.deleteOne(Filters.eq("name", "小李"));
		System.out.println("------------------------------");
	}

	/**
	 * 获取mongodb数据库连接
	 */
	private static void startMongoDBConn() {
		try {
			ServerAddress serverAddress = new ServerAddress("127.0.0.1", 27017);
			List<ServerAddress> seeds = new ArrayList<ServerAddress>();
			seeds.add(serverAddress);
			MongoCredential credentials = MongoCredential.createScramSha1Credential("muser", "datamining",
					"123456".toCharArray());
			List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();
			credentialsList.add(credentials);
			// 连接到 mongodb 服务
			client = new MongoClient(seeds, credentialsList);
			db = client.getDatabase("datamining");
		} catch (MongoException e) {
			e.printStackTrace();
		}
	}

	private static void stopMondoDBConn() {
		if (null != client) {
			try {
				client.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				client = null;
				db = null;
			}
		}
	}
}
