package com.example.test_project;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AskTest extends AsyncTask<String,String,InputStream> {
    private static final String TAG = "common";
    HttpClient httpClient;//접속을 위한객체
    HttpPost httpPost; //url을 담을 객체
    HttpResponse httpResponse;//Middle <->And //미사용
    HttpEntity httpEntity; //속성을 정의함
    MultipartEntityBuilder builder;//파라메터,파일 등등을 보내기위한 객체
    final String HTTPIP = "http://192.168.0.60";//IP
    final String SVRPATH = "/mid/"; //
    private String postUrl ;
    private String mapping ;
    private MemberDTO dto;
    ArrayList<String> list = new ArrayList<>();
    public AskTest(String mapping) {
        list = new ArrayList<>();//새로비움
        this.mapping = mapping;
    }
    public void addItem(String data){
        list.add(data); //<- json으로 만들어진 String값을 추가함. (1건 ~)
    }

    @Override
    protected InputStream doInBackground(String... strings) {
        postUrl = HTTPIP + SVRPATH + mapping;
        builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.addTextBody("size" , list.size()+"" ,
                ContentType.create("Multipart/related" , "UTF-8"));
        for (int i = 0 ; i < list.size(); i++){
        builder.addTextBody("param"+(i+1) , list.get(i) ,
                ContentType.create("Multipart/related" , "UTF-8"));
        //=================================
        }
        httpClient = AndroidHttpClient.newInstance("Android");
        httpPost = new HttpPost(postUrl);
        httpPost.setEntity(builder.build());//파라메터를 추가할수있는부분.
        InputStream in =null;
        ///////////==============================================
       try {
           in = httpClient.execute(httpPost).getEntity().getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return in;
    }
}
