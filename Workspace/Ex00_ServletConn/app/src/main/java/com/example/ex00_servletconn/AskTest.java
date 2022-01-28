package com.example.ex00_servletconn;

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

public class AskTest extends AsyncTask<String,String,String> {
    private static final String TAG = "common";
    HttpClient httpClient;//접속을 위한객체
    HttpPost httpPost; //url을 담을 객체
    HttpResponse httpResponse;//Middle <->And //미사용
    HttpEntity httpEntity; //속성을 정의함
    MultipartEntityBuilder builder;//파라메터,파일 등등을 보내기위한 객체
    final String HTTPIP = "http://192.168.0.60";//IP
    final String SVRPATH = "/mid/"; //
    private String postUrl ;

    @Override
    protected String doInBackground(String... strings) {
        //반복되는 소스가 어디서부터 어디까지 생기고
        //어떻게하면 줄일수있을까?
        //무조건 InputStream으로 무조건 return을 받음.

        postUrl = HTTPIP + SVRPATH + "list.and";
        builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        //===========================================
        ArrayList<UserDTO> list = new ArrayList<>();
        list.add(new UserDTO(1,"a1","데이터"));
        list.add(new UserDTO(2,"a1","b2"));
        list.add(new UserDTO(3,"a1","b2"));
        list.add(new UserDTO(4,"a1","b2"));
        list.add(new UserDTO(5,"a1","b2"));
        Gson gson = new Gson();
        String data = gson.toJson(list);
        builder.addTextBody("dto" , data ,
                ContentType.create("Multipart/related" , "UTF-8"));
        httpClient = AndroidHttpClient.newInstance("Android");
        //conn , ps<-// ps( sql ) , ps.setInt , ps.setString
        httpPost = new HttpPost(postUrl);
        httpPost.setEntity(builder.build());//파라메터를 추가할수있는부분.
        InputStream in = null;
        try {
            in = httpClient.execute(httpPost).getEntity().getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(in != null){

            ArrayList<UserDTO> revList
                    = gson.fromJson(new InputStreamReader(in) ,
                    new  TypeToken<List<UserDTO>>() {}.getType());
            for (UserDTO dto: revList) {
                Log.d(TAG, "testConn: succ" + dto.getUser_id());
                Log.d(TAG, "testConn: succ" + dto.getUser_msg());
                Log.d(TAG, "testConn: succ" + dto.getRefid());
            }

            // String data = rtnStr(in);
            //  Fragment2.data = data;
            Log.d(TAG, "testConn: succ" + data);
        }
        return null;
    }
}
