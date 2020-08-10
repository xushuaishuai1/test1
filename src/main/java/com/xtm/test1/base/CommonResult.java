package com.xtm.test1.base;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * 自定义响应结构
 *
 * @author zhuxiao
 * @date 2018年9月17日
 */
public class CommonResult {
	
	//定义jackson对象
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	//响应业务状态
	private Integer status;

	//兼容layuiTable
	private Integer code;
	
	//响应消息
	private String message;
	
	//响应中的数据
	private Object data;
	
	public CommonResult(){
		
	}
	
	public CommonResult(Object data){
		this.status = 200;
		this.message = "OK";
		this.data = data;
		this.code = 200;
	}
	
	public CommonResult(Integer status, String message, Object data){
		this.status = status;
		this.code = status;
		this.message = message;
		this.data = data;
	}


	public static CommonResult build(Integer status, String message){
		return new CommonResult(status,message,null);
	}

	public static CommonResult build(Integer status, String message, Object data){
		return new CommonResult(status,message,data);
	}
	
	public static CommonResult ok(){
		return new CommonResult(null);
	}
	
	public static CommonResult ok(Object data){
		return new CommonResult(data);
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getmessage() {
		return message;
	}

	public void setmessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * 将json结果集转化为EhsResult对象
	 * @param jsonData
	 * @param clazz
	 * @return
	 */
	public static CommonResult formatToPojo(String jsonData, Class<?> clazz){
		try {
			if(clazz == null){
				return MAPPER.readValue(jsonData, CommonResult.class);
			}
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if(clazz != null){
				if(data.isObject()){
					obj = MAPPER.readValue(data.traverse(), clazz);
				}else if(data.isTextual()){
					obj = MAPPER.readValue(data.asText(), clazz);
				}
			}
			return build(jsonNode.get("status").intValue(), jsonNode.get("message").asText(), obj);
		} catch (Exception e) {
			return null;
		} 
	}
	
	/**
	 * 
	 * @param json
	 * @return
	 */
	public static CommonResult format(String json){
		try {
			return MAPPER.readValue(json, CommonResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static CommonResult formatToList(String jsonData, Class<?> clazz){
		try {
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if(data.isArray() && data.size() > 0){
				obj = MAPPER.readValue(data.traverse(), 
						MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
			}
			return build(jsonNode.get("status").intValue(), jsonNode.get("message").asText(), obj); 
		} catch (Exception e) {
			return null;
		}
	}
	
}
