package com.practice;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BeanToMap {

	public Map<String,Object> convert(Object o){
		Map<String,Object> map =  new HashMap<String, Object>();
		Field[] fields = o.getClass().getFields();
		for(Field field:fields){
//			Method method = 
//			map.put(field.getName(), o.getCla)
		}
		return null;
	}
}
