package com.example.project2_clone;

import android.os.AsyncTask;

import java.io.IOException;

public class StringServlet extends AsyncTask<String,String,String> {
    @Override
    protected String doInBackground(String... strings) {
        Common common = new Common("list.and");//Controller로 요청
        try {
            common.testConn();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    //Common클래스에서 메소드를 바로 호출하면 HttpClient가 접속시도를 못한다.
    //Android 보안 규칙.
    //외부 HttpClient(미들웨어)를 요청할때는 비동기 처리가 들어간.Service
    //AsyncTask <- 라는것을 사용해야함.
    //Class를 만들고 extends AsyncTask <오브젝트,오브젝트,오브젝트>
}
