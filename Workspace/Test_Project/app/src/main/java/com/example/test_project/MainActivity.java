package com.example.test_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
TextView tv_join ;
TextView tv_login ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //AskTest test = new AskTest();
        //test.execute();//doInbackground Method 동작 ( 결과를 받아야하는 상황 .get() )
        //ex) 14.어싱크 실행 -> 이클립스 -> db->이클->안드
        //listview adapter ↑
        tv_join = findViewById(R.id.tv_join);
        tv_login = findViewById(R.id.tv_login);

        tv_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,JoinActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this   , "aa", Toast.LENGTH_SHORT).show();
            }
        });

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this   , "aa", Toast.LENGTH_SHORT).show();
            }
        });
    }
}