package com.example.ex10_intentresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        //↑ 디자인 로딩이 완료가 되고나서 위젯을 찾는다.
        TextView tv2 = findViewById(R.id.tv2);
        //MainActivity Intent=> SubActivity
        Intent intent = getIntent(); // 모델
        String test = intent.getStringExtra("test");
        int testInt = intent.getIntExtra("testint" , -1);
        TestDTO dto = (TestDTO) intent.getSerializableExtra("dto");

        tv2.setText(test + testInt);
        tv2.append(dto.field1 + dto.field2 + dto.field3);//기존내용 + 새로운 글자

    }
}