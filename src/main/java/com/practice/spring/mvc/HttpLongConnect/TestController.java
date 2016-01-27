package com.practice.spring.mvc.HttpLongConnect;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

	private String interval = "1"; 
	
	@RequestMapping("/connect")
	public void HttpLongConnect(HttpServletRequest request,HttpServletResponse response) throws IOException{
		for (int i = 0; i < 10; i++) {  
            try {  
                Thread.sleep(1000 * Integer.valueOf(interval));  
            } catch (NumberFormatException e) {  
                e.printStackTrace();  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
              
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss E");  
            String date_str = df.format(new Date());  
              
            writerResponse(response, date_str, i);// msg是test.jsp中的那个js方法的名称  
        }  
        return;  
	}
	
	protected void writerResponse(HttpServletResponse response, String body, Integer num) throws IOException {  
  
        response.setContentType("text/html;charset=GBK");  
        response.addHeader("Pragma", "no-cache");  
        response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");  
        response.setHeader("Cache-Control", "pre-check=0,post-check=0");  
        if(num==9){
            response.setHeader("Connection", "close");  
        }else{
            response.setHeader("Connection", "keep-alive");  
        }
        response.setDateHeader("Expires", 0);  
        response.getWriter().write(body);  
        response.flushBuffer();  
    }  
}
