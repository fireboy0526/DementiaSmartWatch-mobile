package main.java.com.agileninjas.dementiasmartwatch;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import com.agileninjas.dementiasmartwatch.R;

import android.util.Log;

public class EmailPost {

	public void postEmail(String s_subject, String s_message) {
		//Send data
		final HttpClient httpclient = new DefaultHttpClient();
		final HttpPost httppost = new HttpPost("http://hungpohuang.com/agile/include/mail.php");
		
		//set data
		String to = "agileninjas2014@gmail.com";
		String subject = s_subject;
		String message = s_message;
		
		//Adding data
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
		nameValuePairs.add(new BasicNameValuePair("to", to));
		nameValuePairs.add(new BasicNameValuePair("subject", subject));
		nameValuePairs.add(new BasicNameValuePair("message", message));

		//Encoding data
		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "utf-8"));
		} catch (UnsupportedEncodingException e) {
            // log exception
            e.printStackTrace();
            Log.e("EmailPost Encoding Error:", e.getMessage());
        }
		
		//making Post Request
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				try {
					ResponseHandler<String> responseHandler=new BasicResponseHandler();
			        String responseBody = httpclient.execute(httppost, responseHandler);
					Log.d("Response of POST: ", responseBody);
				} catch (ClientProtocolException e) {
				    e.printStackTrace();
				    Log.e("EmailPost ClientProtocol Error: ", e.getMessage());
				} catch (IOException e) {
					e.printStackTrace();
					Log.e("EmailPost IOException Error: ", e.getMessage());
				}
			}
		};
		new Thread(runnable).start();
	}
	
}
