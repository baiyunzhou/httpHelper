package com.zby.util;

import java.io.IOException;
import java.io.InputStream;

import com.alibaba.fastjson.JSON;
import com.zby.entity.RequestJsonData;

public class JsonParser {

	public static RequestJsonData parse(String jsonPath) {
		InputStream inputStream = JsonParser.class.getResourceAsStream(jsonPath);
		try {
			return JSON.parseObject(inputStream, RequestJsonData.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		RequestJsonData requestJsonData = parse("/request.json");
		System.out.println(requestJsonData);
	}
}
