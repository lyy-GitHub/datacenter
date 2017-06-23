package request;

import util.RequestUtil;

public class Login {
	static String url="http://fanyi.youdao.com/openapi.do?keyfrom=JKXY-test&key=343166845&type=data&doctype=xml&version=1.1&q=welcome";
Login()
{
	RequestUtil.HttpClientGetResponse(url, "UTF-8");
}
public static void getCookies()
{
	RequestUtil.HttpClientGetResponse(url, "UTF-8");
}

public static void main(String[] args) {
	String s=RequestUtil.HttpClientGetResponse(url, "UTF-8");
	System.out.println(s);
}
}
