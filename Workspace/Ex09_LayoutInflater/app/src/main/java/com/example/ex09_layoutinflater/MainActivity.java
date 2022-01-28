package com.example.ex09_layoutinflater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearLayout; //리니어 레이아웃 자바에서 사용하기위해서 선언
    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView가 레이아웃을 찾아서 액티비티 화면에 붙는과정
        //레이아웃 인플레이션과정이 필요한상태.
        //레이아웃 인플레이션이 되기전에 위젯을 찾아서 사용을 하려고하면
        //위젯을 찾을수가없다 .
        //빈번하게 발생하는 오류. 해결: 파란색으로 나온 오류코드를 반드시 확인
        //NullPointerExceiption이 위젯의 종류나 아이디를 가르키고있으면 90%확률

        setContentView(R.layout.activity_main);
        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout = findViewById(R.id.linear);//디자인.xml <=> .java
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //↑ 안드로이드 OS에서 제공해주는 기능을 사용하기만 하기때문에 안드로이드 프로그래밍이 어려움
                inflater.inflate(R.layout.sub1 , linearLayout , true);
                linearLayout.findViewById(R.id.btn1_sub).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "dd", Toast.LENGTH_SHORT).show();
                    }
                });
                //↑ 리니어 레이아웃에 sub1
                relativeLayout = findViewById(R.id.relative);
                inflater.inflate(R.layout.sub2 , relativeLayout , true);
                relativeLayout.findViewById(R.id.btn1_rel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "rrrrr", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        //붙일 레이아웃 , 붙어질Main에 있는 레이아웃 , true

        //레이아웃 인플레이트 시키기 버튼을 눌렀을때 , main . LinearLayout에 레이아웃이 인플레이트되게
        //처리해보기. !

    }
}