package com.practice.spring.SpEL;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class TESTSpEL {
	
	ExpressionParser parser;
	
	@Before
	public void beforeTest(){
		parser = new SpelExpressionParser(); 
	}

	@Test
	public void AndOrNot(){
	    String expression1 = "2>1 and (!true or !false)";  
	    boolean result1 = parser.parseExpression(expression1).getValue(boolean.class);  
	    Assert.assertEquals(true, result1);  
	       
	    String expression2 = "2>1 and (NOT true or NOT false)";  
	    boolean result2 = parser.parseExpression(expression2).getValue(boolean.class);  
	    Assert.assertEquals(true, result2);  
	}
	
	@Test  
	@SuppressWarnings("unchecked")
    public void testClassTypeExpression() {  
        //java.lang包类访问  
        Class<String> result1 = parser.parseExpression("T(String)").getValue(Class.class);  
        Assert.assertEquals(String.class, result1);  
        //其他包类访问  
        String expression2 = "T(cn.javass.spring.chapter5.SpELTest)";  
        Class<String> result2 = parser.parseExpression(expression2).getValue(Class.class);    
        Assert.assertEquals(TESTSpEL.class, result2);  
        //类静态字段访问  
        int result3=parser.parseExpression("T(Integer).MAX_VALUE").getValue(int.class);  
        Assert.assertEquals(Integer.MAX_VALUE, result3);  
        //类静态方法调用  
        int result4 = parser.parseExpression("T(Integer).parseInt('1')").getValue(int.class);  
        Assert.assertEquals(1, result4);  
    }
    
    @Test  
    public void testConstructorExpression() {  
        ExpressionParser parser = new SpelExpressionParser();  
        String result1 = parser.parseExpression("new String('haha')").getValue(String.class);  
        Assert.assertEquals("haha", result1);  
        Date result2 = parser.parseExpression("new java.util.Date()").getValue(Date.class);  
        Assert.assertNotNull(result2);  
    }
    
    @Test  
    public void testVariableExpression() {  
        ExpressionParser parser = new SpelExpressionParser();  
        EvaluationContext context = new StandardEvaluationContext();  
        context.setVariable("variable", "haha");  
        context.setVariable("variable", "haha");  
        String result1 = parser.parseExpression("#variable").getValue(context, String.class);  
        Assert.assertEquals("haha", result1);  
       
        context = new StandardEvaluationContext("haha");  
        String result2 = parser.parseExpression("#root").getValue(context, String.class);  
        Assert.assertEquals("haha", result2);  
        String result3 = parser.parseExpression("#this").getValue(context, String.class);  
        Assert.assertEquals("haha", result3);  
    }
    
    @Test  
    public void testFunctionExpression() throws SecurityException, NoSuchMethodException {  
        ExpressionParser parser = new SpelExpressionParser();  
        StandardEvaluationContext context = new StandardEvaluationContext();  
        Method parseInt = Integer.class.getDeclaredMethod("parseInt", String.class);  
        context.registerFunction("parseInt", parseInt);  
        context.setVariable("parseInt2", parseInt);  
        String expression1 = "#parseInt('3') == #parseInt2('3')";  
        boolean result1 = parser.parseExpression(expression1).getValue(context, boolean.class);  
        Assert.assertEquals(true, result1);         
    }
    
    @Test  
    public void testAssignExpression() {  
        ExpressionParser parser = new SpelExpressionParser();  
        //1.给root对象赋值  
        EvaluationContext context = new StandardEvaluationContext("aaaa");  
        String result1 = parser.parseExpression("#root='aaaaa'").getValue(context, String.class);  
        Assert.assertEquals("aaaaa", result1);  
        String result2 = parser.parseExpression("#this='aaaa'").getValue(context, String.class);  
        Assert.assertEquals("aaaa", result2);  
       
        //2.给自定义变量赋值  
        context.setVariable("#variable", "variable");  
        String result3 = parser.parseExpression("#variable=#root").getValue(context, String.class);  
        Assert.assertEquals("aaaa", result3);  
    }
    
    @Test  
    public void testBeanExpression() {  
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext();  
        ctx.refresh();  
        ExpressionParser parser = new SpelExpressionParser();  
        StandardEvaluationContext context = new StandardEvaluationContext();  
        context.setBeanResolver(new BeanFactoryResolver(ctx));  
        Properties result1 = parser.parseExpression("@systemProperties").getValue(context, Properties.class);  
        Assert.assertEquals(System.getProperties(), result1);  
    }  
}
