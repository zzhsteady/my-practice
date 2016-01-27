package com.practice.nomal;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Test;


public class URLTest {

	@Test
	public void testURLEncoder() throws UnsupportedEncodingException{
		String url="";
		URLEncoder.encode(url,"");
	}
}
