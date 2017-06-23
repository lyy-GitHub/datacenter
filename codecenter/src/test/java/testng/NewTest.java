package testng;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import util.RequestUtil;

public class NewTest {
	  static String url="http://fanyi.youdao.com/openapi.do?keyfrom=JKXY-test&key=343166845&type=data&doctype=xml&version=1.1&q=welcome";
  @Test
  public void f() {
	
	  	String result=RequestUtil.HttpClientGetResponse(url, "UTF-8");
	  	assertTrue(result.contains("welcome"));
	  
  }
}
