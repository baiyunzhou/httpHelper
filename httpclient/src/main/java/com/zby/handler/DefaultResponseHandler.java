package com.zby.handler;

import java.io.IOException;
import java.util.Arrays;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

public class DefaultResponseHandler implements ResponseHandler<String> {

	public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
		StatusLine statusLine = response.getStatusLine();
		System.out.println("响应行：" + statusLine);
		Header[] allHeaders = response.getAllHeaders();
		System.out.println("响应头：" + Arrays.toString(allHeaders));
		HttpEntity httpEntity = response.getEntity();
		String result = EntityUtils.toString(httpEntity, "UTF-8");
		System.out.println("响应体：" + result);
		return result;
	}

}
