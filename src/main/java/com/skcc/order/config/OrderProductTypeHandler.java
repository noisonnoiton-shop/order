package com.skcc.order.config;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skcc.order.domain.OrderProduct;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class OrderProductTypeHandler extends BaseTypeHandler<Object>{

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType)
			throws SQLException {
		
		String Json = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Json = objectMapper.writeValueAsString(parameter);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		ps.setObject(i, Json);

	}

	@Override
	public List<OrderProduct> getNullableResult(ResultSet rs, String columnName) throws SQLException {
		
		String d = (String) rs.getObject(columnName);
		if(d == null) return null;
		
		List<OrderProduct> cps = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			cps = objectMapper.readValue(d, new TypeReference<List<OrderProduct>>() {});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return cps;
		
	}

	@Override
	public List<OrderProduct> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {

		Object d = rs.getObject(columnIndex);
		if(d == null) return null;
		
		List<OrderProduct> cps = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			cps = objectMapper.readValue(d.toString(), new TypeReference<List<OrderProduct>>() {});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return cps;
		
	}

	@Override
	public List<OrderProduct> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {

		Object d = cs.getObject(columnIndex);
		if(d == null) return null;
		
		List<OrderProduct> cps = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			cps = objectMapper.readValue(d.toString(), new TypeReference<List<OrderProduct>>() {});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return cps;
		
	}

}
