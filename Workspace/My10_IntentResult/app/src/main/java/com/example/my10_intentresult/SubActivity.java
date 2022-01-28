package com.example.my10_intentresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Intent intent = getIntent();
        MemberDTO dto = (MemberDTO) intent.getSerializableExtra("dto");
        if(dto != null){
            TextView tv_info = findViewById(R.id.tv_info);
            tv_info.setText(dto.getId() + "\r\n");
            tv_info.append(dto.getPw() + "\r\n");
            tv_info.append(dto.getName() + "\r\n");
            tv_info.append(dto.getNick() + "\r\n");
            tv_info.append(dto.getAge() + "\r\n");
            tv_info.append(dto.getAddr() + "\r\n");
        }
    }
}