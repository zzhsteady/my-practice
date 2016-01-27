package com.practice.spring.mvc.HttpLongConnect;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration("classpath:applicationContext.xml")
public class TestControllerTest {

//    @Autowired
//    private WebApplicationContext wac;

//    private MockMvc mockMvc;

    @Before
    public void setup() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    	HttpSender.getInstance().setProxyIp("192.168.2.88");
    	HttpSender.getInstance().setProxyPort(40002);
    }

    @Test
    public void testConnect() throws Exception {
//        this.mockMvc.perform(get("/connect").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType("application/json"));
    	CloseableHttpResponse response = HttpSender.getInstance().keepAlive("http://localhost:8080/practice/connect", null);
    	
    	HttpEntity entity = null;
    	try{
    		while(true){
//    			int statusCode = response.getStatusLine().getStatusCode();
//    			if (statusCode != 200) {
//    				throw new RuntimeException("HttpClient,error status code :" + statusCode);
//    			}
    			entity = response.getEntity();
    			String result = null;
    			if (entity != null) {
    				ContentType contentType = ContentType.getOrDefault(entity);
    				String charset = null;
    				if (contentType.getCharset() != null) {
    					charset = contentType.getCharset().toString();
    				}
    				result = EntityUtils.toString(entity, charset);
    				System.out.println(result);
    			}
    			if(response.getLastHeader("Connect").getValue().equals("close")){
    				EntityUtils.consume(entity);
//            if(response.getLastHeader("Connect").getValue())
    				response.close();
    				break;
    			}
    		}
    	}finally {
    		if(entity != null)
    		EntityUtils.consume(entity);
//          if(response.getLastHeader("Connect").getValue())
  				response.close();
    	}
    }

}
