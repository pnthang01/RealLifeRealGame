package com.rlrg.webserver.frontend.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MethodUtils {

	public static String encodedString(String base, String encoder) throws UnsupportedEncodingException{
		return URLEncoder.encode(base, encoder);
	}
}
