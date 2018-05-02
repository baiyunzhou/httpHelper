package com.zby.entity;

import java.util.LinkedHashMap;
import java.util.Map;

public class RequestData {

	private String uri;
	private String method;
	private Map<String, String> headers;
	private Map<String, String> params;
	private Map<String, String> cookies;

	public RequestData(String uri) {
		this.uri = uri;
	}

	public RequestData(String uri, String method, Map<String, String> headers, Map<String, String> params, Map<String, String> cookies) {
		super();
		this.uri = uri;
		this.method = method;
		this.headers = headers;
		this.params = params;
		this.cookies = cookies;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public Map<String, String> getCookies() {
		return cookies;
	}

	public void setCookies(Map<String, String> cookies) {
		this.cookies = cookies;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public RequestData addHeader(String key, String value) {
		if (null == this.headers) {
			this.headers = new LinkedHashMap<String, String>();
		}
		this.headers.put(key, value);
		return this;
	}

	public RequestData addParam(String key, String value) {
		if (null == this.params) {
			this.params = new LinkedHashMap<String, String>();
		}
		this.params.put(key, value);
		return this;
	}

	public RequestData addCookie(String key, String value) {
		if (null == this.cookies) {
			this.cookies = new LinkedHashMap<String, String>();
		}
		this.cookies.put(key, value);
		return this;
	}

	@Override
	public String toString() {
		return "RequestData [uri=" + uri + ", method=" + method + ", headers=" + headers + ", params=" + params + ", cookies=" + cookies
				+ "]";
	}

}
