package com.practice.nomal.datatype;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTest {

	@Test
	public void sqlDateTest(){
		System.out.println(new Date());
		System.out.println(new java.sql.Date(new Date().getTime()));
	}
	@Test
	public void compareTo(){
		System.out.println(new java.sql.Date(0).compareTo(new java.sql.Date(new Date().getTime())));
	}
	
	@Test
	public void format(){
		Calendar c = Calendar.getInstance();
		System.out.println(new SimpleDateFormat("HH:mm").format(c.getTime()));
	}

	@Test
	public void parse(){
		try {
			Date date = new SimpleDateFormat("yyyyMMdd").parse("20160116");
//			SimpleDateFormat.getInstance().parse()
//			new Date().
			String theDay = new SimpleDateFormat("yyyy-MM-dd").format(date);
			Assert.assertEquals("2016-01-16",theDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void hhmm(){
		Date date = new Date();
		String theTime = new SimpleDateFormat("HH:mm:ss").format(date);
		String theHH = new SimpleDateFormat("HH").format(date);

//		Assert.assertEquals("12:12:12",theTime);
//		Assert.assertTrue(theTime.compareTo(  "19:00:00") > 0);
		Assert.assertEquals("12",theHH);
	}
}
