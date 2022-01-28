package com.example.test01_layout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn1, btn2;
    ImageView imgv1, imgv2, imgv3, imgv4;
   // ArrayList<ImageView> listimg = new ArrayList<>();
   // ImageView[] arrimg = { findViewById(R.id.imgv1) };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        imgv1 = findViewById(R.id.imgv1);
        imgv2 = findViewById(R.id.imgv2);
        imgv3 = findViewById(R.id.imgv3);
        imgv4 = findViewById(R.id.imgv4);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼 1을 눌렀을때 코딩을 작성하면 되는 부분
                //visibility속성을 이용하는 방법
                if (imgv1.getVisibility() == View.VISIBLE){
                    imgv1.setVisibility(View.GONE);
                    imgv2.setVisibility(View.VISIBLE);
                }else if(imgv2.getVisibility() == View.VISIBLE ){
                    imgv1.setVisibility(View.VISIBLE);
                    imgv2.setVisibility(View.GONE);
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}