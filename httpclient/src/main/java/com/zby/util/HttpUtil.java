package com.zby.util;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.RequestLine;
import org.apache.http.client.CookieStore;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;

import com.zby.entity.RequestData;
import com.zby.handler.DefaultResponseHandler;

public class HttpUtil {
	public static <T> T doGet(String uri, boolean printRequest) throws Exception {
		return doGet(uri, null, null, null, null, printRequest);
	}

	public static <T> T doGet(RequestData requestData, boolean printRequest) throws Exception {
		return doGet(requestData.getUri(), requestData.getHeaders(), requestData.getParams(), requestData.getCookies(), null, printRequest);
	}

	public static <T> T doGet(RequestData requestData, ResponseHandler<T> responseHandler, boolean printRequest) throws Exception {
		return doGet(requestData.getUri(), requestData.getHeaders(), requestData.getParams(), requestData.getCookies(), responseHandler,
				printRequest);
	}

	@SuppressWarnings("unchecked")
	public static <T> T doGet(String uri, Map<String, String> headers, Map<String, String> params, Map<String, String> cookies,
			ResponseHandler<T> responseHandler, boolean printRequest) throws Exception {
		if (null == uri) {
			return null;
		}
		uri = OtherUtil.fixUriPrefix(uri);
		URIBuilder builder = new URIBuilder(uri);

		if (null != params && !params.isEmpty()) {
			for (Entry<String, String> entry : params.entrySet()) {
				builder.setParameter(entry.getKey(), entry.getValue());
			}
		}
		URI requestURI = builder.build();
		HttpGet request = new HttpGet(requestURI);

		if (null != headers && !headers.isEmpty()) {
			for (Entry<String, String> entry : headers.entrySet()) {
				request.setHeader(entry.getKey(), entry.getValue());
			}
		}
		CookieStore cookieStore = new BasicCookieStore();
		if (null != cookies && !cookies.isEmpty()) {
			for (Entry<String, String> entry : cookies.entrySet()) {
				BasicClientCookie cookie = new BasicClientCookie(entry.getKey(), entry.getValue());
				cookie.setDomain("localhost");
				cookieStore.addCookie(cookie);
			}
		}

		CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		if (printRequest) {
			RequestLine requestLine = request.getRequestLine();
			System.out.println("请求行：" + requestLine);
			Header[] allHeaders = request.getAllHeaders();
			System.out.println("请求头：" + Arrays.toString(allHeaders));
			System.out.println("Cookie:" + cookieStore);
		}
		if (null == responseHandler) {
			return (T) httpClient.execute(request, new DefaultResponseHandler());
		}
		return httpClient.execute(request, responseHandler);
	}

	public static <T> T doPost(String uri, String json, boolean printRequest) throws Exception {
		return doPost(uri, null, json, null, null, printRequest);
	}

	@SuppressWarnings("unchecked")
	public static <T> T doPost(String uri, Map<String, String> headers, String json, Map<String, String> cookies,
			ResponseHandler<T> responseHandler, boolean printRequest) throws Exception {
		if (null == uri) {
			return null;
		}
		uri = OtherUtil.fixUriPrefix(uri);
		HttpPost request = new HttpPost(uri);

		if (null != headers && !headers.isEmpty()) {
			for (Entry<String, String> entry : headers.entrySet()) {
				request.setHeader(entry.getKey(), entry.getValue());
			}
		}
		StringEntity entity = new StringEntity(json, "UTF-8");
		entity.setContentType("application/json");
		request.setEntity(entity);

		CookieStore cookieStore = new BasicCookieStore();
		if (null != cookies && !cookies.isEmpty()) {
			for (Entry<String, String> entry : cookies.entrySet()) {
				BasicClientCookie cookie = new BasicClientCookie(entry.getKey(), entry.getValue());
				cookie.setDomain("localhost");
				cookieStore.addCookie(cookie);
			}
		}
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		if (printRequest) {
			RequestLine requestLine = request.getRequestLine();
			System.out.println("请求行：" + requestLine);
			Header[] allHeaders = request.getAllHeaders();
			System.out.println("请求头：" + Arrays.toString(allHeaders));
			System.out.println("Cookie:" + cookieStore);
			System.out.println("请求体：" + entity);
		}
		if (null == responseHandler) {
			return (T) httpClient.execute(request, new DefaultResponseHandler());
		}
		return httpClient.execute(request, responseHandler);

	}

	public static <T> T doPost(String uri, Map<String, String> params, boolean printRequest) throws Exception {
		return doPost(uri, null, params, null, null, printRequest);
	}

	public static <T> T doPost(RequestData requestData, boolean printRequest) throws Exception {
		return doPost(requestData.getUri(), requestData.getHeaders(), requestData.getParams(), requestData.getCookies(), null,
				printRequest);
	}

	public static <T> T doPost(RequestData requestData, ResponseHandler<T> responseHandler, boolean printRequest) throws Exception {
		return doPost(requestData.getUri(), requestData.getHeaders(), requestData.getParams(), requestData.getCookies(), responseHandler,
				printRequest);
	}

	@SuppressWarnings("unchecked")
	public static <T> T doPost(String uri, Map<String, String> headers, Map<String, String> params, Map<String, String> cookies,
			ResponseHandler<T> responseHandler, boolean printRequest) throws Exception {
		if (null == uri) {
			return null;
		}
		uri = OtherUtil.fixUriPrefix(uri);
		HttpPost request = new HttpPost(uri);

		if (null != headers && !headers.isEmpty()) {
			for (Entry<String, String> entry : headers.entrySet()) {
				request.setHeader(entry.getKey(), entry.getValue());
			}
		}

		if (null != params && !params.isEmpty()) {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (Entry<String, String> entry : params.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(), params.get(entry.getKey())));
			}
			request.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		}

		CookieStore cookieStore = new BasicCookieStore();
		if (null != cookies && !cookies.isEmpty()) {
			for (Entry<String, String> entry : cookies.entrySet()) {
				BasicClientCookie cookie = new BasicClientCookie(entry.getKey(), entry.getValue());
				cookie.setDomain("localhost");
				cookieStore.addCookie(cookie);
			}
		}
		CloseableHttpClient httpClient = HttpClients.custom().build();
		if (printRequest) {
			RequestLine requestLine = request.getRequestLine();
			System.out.println("请求行：" + requestLine);
			Header[] allHeaders = request.getAllHeaders();
			System.out.println("请求头：" + Arrays.toString(allHeaders));
			System.out.println("Cookie:" + cookieStore);
			System.out.println("请求体：" + request.getEntity());
		}
		if (null == responseHandler) {
			return (T) httpClient.execute(request, new DefaultResponseHandler());
		}
		return httpClient.execute(request, responseHandler);
	}

}
