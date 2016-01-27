package com.practice.nomal.datatype;

import net.sf.json.JSONObject;

import org.junit.Test;

public class StringTest {

	@Test
	public void test(){
//		String test = "-112";-112
//		System.out.println(test.equals("-112"));
		
		JSONObject respJson = JSONObject.fromObject("{\"test\":\"-112\"}");
		String test2 = respJson.getString("test");
		System.out.println(test2.equals("-112"));
		
	}
	
	@Test
	public void test2(){
		String str1 = "hello quanjizhu";
		String str2 =str1+"haha";
		String str3 = new String("hello quanjizhu");

		System.out.println(str1.equals(str2)); 
		System.out.println(str1.equals(str3)); 
		
		
	}
	@Test
	public void test3(){
		String str1 = "hello quanjizhu";
		String str2 =str1+"haha";
		String str3 = new String("hello quanjizhu");

		System.out.println(str1 == str2 ); 
		System.out.println(str1 == str3 ); 
		
	}


	@Test
	public void test4(){
		springPeriodPeriodPeriod("123","456","7890");
		springPeriodPeriodPeriod();
	}
	private void springPeriodPeriodPeriod(String... paramters){
		System.out.println(paramters.toString());
		for (String paramter:paramters){
			System.out.println(paramter);
		}
	}


	@Test
	public void testFormat(){
		String s = String.format("aa %1$s, bb %2$s,  cc%3$s","a","b","c");
		System.out.println(s);
	}
}
