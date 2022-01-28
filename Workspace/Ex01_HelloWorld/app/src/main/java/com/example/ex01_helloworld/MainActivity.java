package com.example.ex01_helloworld;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //소스작성은 onCreate메소드에서 한다.
    //AppCompatActivity를 항상 상속받았다 . (화면 하나의 단위를 Activity라고 함)
    //위젯을 디자인단에서 또는 그림파일을 drawble에 넣었을때
    //즉 , res에 어떤 내용이 있다면 모든것들은 R이라는 클래스에서 관리가 된다.
    //R이라는것은 Resoucese를 클래스로 만드는것,=>R
    Button btn_naver , btn_call  , btn_new ;
    EditText edt_call ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //소스와 Layout을 연결하는 부분 ↑
        //네이밍룰 java파일은 MainActivity => 디자인 activity_main 앞뒤를 바꾼다.
        //1.버튼을 사용하기 위해서는 버튼을 실제로 찾는 코드를 작성해야 한다.
        Button btn1 = findViewById(R.id.button1);
        Button btn2 = findViewById(R.id.button2);

        btn_naver = findViewById(R.id.btn_naver);
        btn_call = findViewById(R.id.btn_call);
        btn_new = findViewById(R.id.btn_new);
        edt_call = findViewById(R.id.edt_call);

        //묵시적 , 암시적 Intent 사용해보기
        btn_naver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent 어떤 값 또는 액션을 지정해놓고 새창을 띄우거나
                //어떤 메세지를 전달하는 목적으로 사용하는 객체.
                Intent intent = new Intent(Intent.ACTION_VIEW
                    , Uri.parse("http://m.naver.com"));
                //Itent에 액션(view새로운 화면을 보여줌 , Uri.parse 띄울 창을 지정함
                //초기화 완료.
                startActivity(intent);
            }
        });

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tel: <-구분자가 필요함 전화번호를 식별하기 위한 구분자
                //ex)tel:010123456789
                //ACTION.DIAL
                //String tel_num = edt_call.getText() + "";
                String tel_num ="tel:" + edt_call.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL
                    , Uri.parse(tel_num) ) ;
                startActivity(intent);
                //제어권을 내가 가지고 있는 화면에 접근을 했는지(명시적)
                //제어권을 내가 가지고 있지 않은 화면에 접근을 해야하는지(묵시,암시적)
            }
        });
        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        MainActivity.this
                        , SubActivity.class);
                startActivity(intent);
            }
        });

        //위에서 setContentView에서 지정한 화면에 있는 컨트롤(위젯) 외에 id를 찾게되면
        //어플리케이션이 강제 종료 된다.
        //버튼을 사용자가 클릭했을때 이벤트
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //실제로 버튼 클릭이 일어났을때 실행되는 코드 영역.
                Toast.makeText(MainActivity.this , "HelloWrold입니다.", Toast.LENGTH_LONG).show();
            }
            //?
        });//OnClickListener ..btn

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edt_id = findViewById(R.id.edt_id);
                EditText edt_pw = findViewById(R.id.edt_pw);
                Toast.makeText(MainActivity.this, edt_id.getText()+"", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, edt_pw.getText()+"", Toast.LENGTH_SHORT).show();
                //ID: admin , PW: admin1234 인경우에는
                String id = edt_id.getText()+"";
                String pw = edt_pw.getText()+"";
                login(id , pw );
                //Toast : 로그인 되었습니다.
                //Toast : 아이디 비밀번호를 확인하세요

            }
        });
        

    }
    public void login(String id , String pw){
        if (id.equals("admin") && pw.equals("admin1234")){
            Toast.makeText(this, "로그인 되었습니다.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "아이디 또는 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
        }
    }

}