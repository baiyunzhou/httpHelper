package com.zby;

import java.util.Iterator;
import java.util.List;

import com.zby.entity.Request;
import com.zby.entity.RequestJsonData;
import com.zby.util.HttpUtil;
import com.zby.util.JsonParser;

public class Application {
	private static final String DEFAULT_JSONPATH = "/request.json";
	private static final String SKIP_SIGN = "#";
	private static final String DEFAULT_HOST = "localhost";
	private static final Integer DEFAULT_PORT = 8080;
	private static final String DEFAULT_METHOD = "GET";
	private static final String SEPRATOR_LINE = "----------^^^^^----------华丽丽的分隔符----------^^^^^----------";

	public static void main(String[] args) throws Exception {
		String jsonPath = "";
		if (null != args && args.length > 0 && null != args[0]) {
			jsonPath = args[0].startsWith("/") ? args[0] : "/" + args[0];
		} else {
			jsonPath = DEFAULT_JSONPATH;
		}
		RequestJsonData requestJsonData = JsonParser.parse(jsonPath);
		String basehost = requestJsonData.getBasehost() == null ? DEFAULT_HOST : requestJsonData.getBasehost();
		Integer basePort = requestJsonData.getBasePort() == null ? DEFAULT_PORT : requestJsonData.getBasePort();
		String basePath = requestJsonData.getBasePath() == null ? "" : requestJsonData.getBasePath();
		String defaultMethod = requestJsonData.getDefaultMethod() == null ? DEFAULT_METHOD : requestJsonData.getDefaultMethod();
		List<Request> requests = requestJsonData.getRequests();

		for (Iterator<Request> iterator = requests.iterator(); iterator.hasNext();) {
			Request request = iterator.next();
			if (request.getExecute() != null && SKIP_SIGN.equals(request.getExecute())) {
				continue;
			}
			String hostToUse = request.getHost() == null ? basehost : request.getHost();
			Integer portToUse = request.getPort() == null ? basePort : request.getPort();
			String path = request.getPath() == null ? "" : request.getPath();
			String uri = "http://" + hostToUse + ":" + portToUse + basePath + path;
			String method = request.getMethod() == null ? defaultMethod : request.getMethod();
			if (method.equalsIgnoreCase("POST")) {
				if (null != request.getParams() && null != request.getJsonData()) {
					System.out.println("Not support both params and jsonData attribute!");
					System.out.println(request);
					System.out.println(SEPRATOR_LINE);
					continue;
				}
				if (null != request.getParams()) {
					HttpUtil.doPost(uri, request.getHeaders(), request.getParams(), request.getCookies(), null, true);
				} else {
					HttpUtil.doPost(uri, request.getHeaders(), request.getJsonData(), request.getCookies(), null, true);
				}
			} else if (method.equalsIgnoreCase("GET")) {
				HttpUtil.doGet(uri, request.getHeaders(), request.getParams(), request.getCookies(), null, true);
			} else {
				System.out.println("Not support!");
				continue;
			}
			if (iterator.hasNext()) {
				System.out.println(SEPRATOR_LINE);
			}
		}
	}

}
