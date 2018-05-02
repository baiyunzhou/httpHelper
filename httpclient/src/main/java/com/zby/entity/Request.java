package com.zby.entity;

import java.util.Map;

public class Request {
	private String host;
	private Integer port;
	private String path;
	private String execute;
	private String method;
	private Map<String, String> headers;
	private Map<String, String> cookies;
	private Map<String, String> params;
	private String jsonData;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getExecute() {
		return execute;
	}

	public void setExecute(String execute) {
		this.execute = execute;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public Map<String, String> getCookies() {
		return cookies;
	}

	public void setCookies(Map<String, String> cookies) {
		this.cookies = cookies;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	@Override
	public String toString() {
		return "Request [host=" + host + ", port=" + port + ", path=" + path + ", execute=" + execute + ", method=" + method + ", headers="
				+ headers + ", cookies=" + cookies + ", params=" + params + ", jsonData=" + jsonData + "]";
	}

}
