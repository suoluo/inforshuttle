package com.inforshuttle.mybatis.dao;

import com.inforshuttle.mybatis.dto.OrderDto;

public interface OrderDao {
	public OrderDto getOrderById(Integer id) throws Exception;
	public OrderDto selectOrder(Integer id) throws Exception;
	public OrderDto selectOrderResultMap(Integer id) throws Exception;
}
