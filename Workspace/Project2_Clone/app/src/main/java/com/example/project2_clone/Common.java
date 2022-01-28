package com.example.project2_clone;

import android.net.http.AndroidHttpClient;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Common {

    private static final String TAG = "common";
    HttpClient httpClient;//접속을 위한객체
    HttpPost httpPost; //url을 담을 객체
    HttpResponse httpResponse;//Middle <->And //미사용
    HttpEntity httpEntity; //속성을 정의함
    MultipartEntityBuilder builder;//파라메터,파일 등등을 보내기위한 객체
    final String HTTPIP = "http://192.168.0.60";//IP
    final String SVRPATH = "/mid/"; //
    private String postUrl ;
    //postUrl = http://192.168.0.60/mid/adfad.and;

    public Common(String mapping){
        postUrl = HTTPIP + SVRPATH + mapping;//크롬창에 요청했을때 되는 mapping
        builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        //MultipartEntityBuilder를 사용하기위해서 초기화함.
    }
    // void  , void 가 아니냐,
    // return x , return
    // 파라메터가 있음 , 없음?
    public void testConn() throws IOException {
        //실제로 url매핑을 접근해서 응답(res)을 준값을 그대로 가지고옴.
        //InputStream형태로 받아옴.
       try{
        //UserDTO dto = new UserDTO(10,"and_userId" , "and_usermsg");
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
        InputStream in = httpClient.execute(httpPost).getEntity().getContent();
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
        }else{
            Log.d(TAG, "testConn: fail");
        }
       }catch (Exception e){
           e.printStackTrace();
       }finally {
           if(httpEntity != null) httpEntity =null;
           if(httpResponse != null) httpResponse =null;
           if(httpPost != null) httpPost =null;
           if(httpClient != null) httpClient =null;

       }

    }

    public String rtnStr(InputStream in)  {
        StringBuilder stringBuilder = null;
        try {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(in,"UTF-8")
        );
         stringBuilder = new StringBuilder();
        String line = null;
        while ( (line = bufferedReader.readLine()) != null  ){
            stringBuilder.append(line + "\n");
        }

       }catch (IOException e){

       }
        return  stringBuilder.toString();
    }

}
