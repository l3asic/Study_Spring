package com.example.ex10_intentresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1); //
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //암시적(묵시) , 명시적
                //명시적 인텐트 , 정확한 위치를 알고있을때 사용.★★★
                //ex) 액티비티 (회원가입) 회원의 정보들 (itent) -> 액티비티(로그인) -> itent
                //묵시 ex) 액티비티 -> 외부 서비스(전화걸기 , naverWeb ....)
                Intent intent = new Intent(MainActivity.this , SubActivity.class);
                //intent를 지금위치와 이동할 위치를 지정을하고 초기화.
                intent.putExtra("test" , "내용");
                intent.putExtra("testint" , 1);
                //intent에 key , value를 통해서 보내줄 데이터를 넣어둠.

                TestDTO dto = new TestDTO("field1" , 10 , 20 );
                //객체를 생성해서 intent에 담음
                intent.putExtra("dto" , dto);

                startActivity(intent);

            }
        });
    }
}