package util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;



public class RequestUtil {
	
	public static String url="http://fanyi.youdao.com/openapi.do";
    public static String body="keyfrom=JKXY-test&key=343166845&type=data&doctype=xml&version=1.1&q=welcome";
	public static Logger logger=Logger.getLogger(RequestUtil.class);
//	public static String url="http://ustus-search-int-westus.cloudapp.net/services/api/search";
//    public static String body="{\"ClientId\":\"A52686EA-098B-47C6-BE05-D9ADF0B8CC71\",\"Markets\":[\"en-us\"],\"Query\":\"office\",\"Requests\": [{\"Source\": \"Dynamics\",\"Filter\":{ \"Operation\":0,\"PropertyFilters\":[{\"PropertyName\":\"Ctg\",\"PropertyValue\":\"DynamicsPresales\",\"Operator\":0,\"SearchMode\":0}]}}]}";
	/**
	 * 
	 * @param url
	 * @param encoding
	 * @return
	 */
	public static String getHtmlResponse(String urlString, String encoding) {
		StringBuilder sb = new StringBuilder();
		try {

			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();

			InputStreamReader isr = new InputStreamReader(conn.getInputStream(), encoding);
			BufferedReader br = new BufferedReader(isr);
			String line = "";

			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static String postHtmlResponse(String urlString,String body, String ContentType,String encoding) {
		StringBuilder sb=new StringBuilder();
		try {		
			
			URL url=new URL(urlString);
			HttpURLConnection conn=(HttpURLConnection) url.openConnection();
			conn.setRequestProperty("encoding", encoding);
			conn.setDoInput(true);
			conn.setDoOutput(true);			
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", ContentType);

			
			OutputStreamWriter osw=new OutputStreamWriter(conn.getOutputStream());
			BufferedWriter bw=new BufferedWriter(osw);
			bw.write(body);
			bw.flush();
			
			InputStream is=null;
			if (conn.getResponseCode() >= 400) {
			    is = conn.getErrorStream();
			} else {
			    is = conn.getInputStream();
			}			
			InputStreamReader isr=new InputStreamReader(is);
			BufferedReader br=new BufferedReader(isr);	
			
			
			String line="";
			while((line=br.readLine())!=null)
			{
				sb.append(line+"\n");
			}
			bw.close();
			osw.close();
			br.close();
			isr.close();
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static String HttpClientGetResponse(String urlString, String encoding)
	{
		String result="";
		HttpClient client=HttpClients.createDefault();
		
		HttpGet get=new HttpGet(urlString);
		try {
			
			HttpResponse response=client.execute(get);
			
			HttpEntity entity=response.getEntity();
			result=EntityUtils.toString(entity,encoding);
			  log(urlString,"get",body,result);
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String HttpClientPostResponse(String urlString,String body,String ContentType, String encoding)
	{
		String result="";
		HttpClient client=HttpClients.createDefault();
		HttpPost post=new HttpPost(urlString);
		
		try {			
			StringEntity str=new StringEntity(body);
			str.setContentType(ContentType);	      
	        post.setEntity(str);
	        HttpResponse response= client.execute(post);	        
	        HttpEntity entity=response.getEntity();	      
	        result=EntityUtils.toString(entity,"UTF-8");	
	        log(urlString,"post",body,result);
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static  void log(String url,String method,String queryString,String response)
	{
		
		logger.info("Test class: " +new Throwable().getStackTrace()[2].getClassName());
		logger.info("Test method: "+new Throwable().getStackTrace()[2].getMethodName());
		logger.info("Test url:"+url);
		logger.info("Test parameter:"+queryString);
		logger.info("Response: "+response);
	}
	
public static void main(String[] args) {
	
}
}
