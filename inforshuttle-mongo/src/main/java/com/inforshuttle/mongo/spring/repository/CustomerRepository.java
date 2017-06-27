package com.inforshuttle.mongo.spring.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.inforshuttle.mongo.spring.entity.Customer;


public interface CustomerRepository extends MongoRepository<Customer, ObjectId> {

	List<Customer> findByLastname(String lastname, Sort sort);

	GeoResults<Customer> findByAddressLocationNear(Point point, Distance distance);
}