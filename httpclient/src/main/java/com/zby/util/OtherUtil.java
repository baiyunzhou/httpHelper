package com.zby.util;

public class OtherUtil {
	private static final String HTTP_PREFIX = "http://";

	public static String fixUriPrefix(String uri) {
		if (!uri.startsWith(HTTP_PREFIX)) {
			return HTTP_PREFIX + uri;
		}
		return uri;
	}
}
