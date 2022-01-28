package com.example.test_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

public class JoinActivity extends AppCompatActivity {
Button join_btn , back_btn;
EditText edt_id , edt_pw , edt_name , edt_birth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        join_btn = findViewById(R.id.join_btn_join);
        back_btn = findViewById(R.id.join_btn_back);
        edt_id = findViewById(R.id.join_edt_id);
        edt_pw = findViewById(R.id.join_edt_pw);
        edt_name = findViewById(R.id.join_edt_name);
        edt_birth = findViewById(R.id.join_edt_birth);

        join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_id.getText().length() < 1
                || edt_pw.getText().length() < 1){
                    Toast.makeText(JoinActivity.this,
                            "아이디와 비밀번호는 필수 입력값입니다.", Toast.LENGTH_SHORT).show();
                }else{


                MemberDTO dto = new MemberDTO(
                        edt_id.getText()+"",
                        edt_pw.getText()+"",
                        edt_name.getText()+"",
                        edt_birth.getText()+""
                        );
                    joinConnect(dto);
                }
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }//onCreate

    public void joinConnect(MemberDTO dto){
        AskTest askTest1 = new AskTest("join.test");
        Gson gson = new Gson();
        String data = gson.toJson(dto);
        askTest1.addItem(data);//list size = 0 , addItem size 1 => param1
        askTest1.execute();
    }

}