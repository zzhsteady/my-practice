package com.practice.nomal;

import org.junit.Test;

public class TestClasspath {

	@Test
	public void test(){
		String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		System.out.println(path);
		System.out.println(System.getProperty("user.dir"));
	}
}
