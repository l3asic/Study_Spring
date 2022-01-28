package com.example.my10_intentresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText edt_id , edt_pw , edt_name , edt_age , edt_addr , edt_nick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_id = findViewById(R.id.edt_id);
        edt_pw = findViewById(R.id.edt_pw);
        edt_name = findViewById(R.id.edt_name);
        edt_age = findViewById(R.id.edt_age);
        edt_addr = findViewById(R.id.edt_addr);
        edt_nick = findViewById(R.id.edt_nick);
        findViewById(R.id.btn_join).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    int age = Integer.parseInt(edt_age.getText()+"");

                    MemberDTO dto = new MemberDTO(
                            edt_id.getText()+"",
                            edt_pw.getText()+"",
                            edt_name.getText()+"",
                            edt_addr.getText()+"",
                            edt_nick.getText()+"",
                            age
                    );
                    Intent intent = new Intent(MainActivity.this, SubActivity.class);
                    intent.putExtra("dto", dto);
                    startActivity(intent);


                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "숫자값이아님 (나이)", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}