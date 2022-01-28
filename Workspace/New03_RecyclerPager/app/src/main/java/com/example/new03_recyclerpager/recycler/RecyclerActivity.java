package com.example.new03_recyclerpager.recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.new03_recyclerpager.R;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {
    //1.xml에 리사클러뷰 추가.
    //2.java <-> xml 연결 id로 찾아서
    //(- 리사클러뷰에 붙일 디자인 xml만들기 -) ex)표현할 정보가 TextView를 몇개쓸껀지,ImageView ,....아이템만들기
    //↑한칸마다 보여줄 정보
    //DB에서 가져온 정보를 칸마다 어떻게 표현할지를 정한것 ( item ) == Collection.size()
    // ex) 카카오톡 채팅목록 ( DB ) select * from kakaoMember where id = 'kakaoid' == 100 == item
    //3.어댑터 만들기
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        recyclerView = findViewById(R.id.rycyclerview);
        //메소드 - 정의 : 받을 데이터 타입이나 데이터 갯수 , 순서 , 등을 정의해놓음
        //메소드 - 호출 : 메소드의 정의 된 데이터 타입과 갯수 , 순서등을 맞춰서 값을 보내주는것
        ArrayList<RecDTO> list = new ArrayList<>();
        list.add(new RecDTO(R.drawable.ic_launcher_background , "test1"));
        list.add(new RecDTO(R.drawable.ic_launcher_foreground , "test2"));
        //LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        //LayoutInflater inflater2 = LayoutInflater.from(RecyclerActivity.this);
        //RecAdapter adapter = new RecAdapter(RecyclerActivity.this , list , inflater);
        RecAdapter adapter2 = new RecAdapter(RecyclerActivity.this , list);
        //ListView와 차이점 ( 가로 세로 ,지그재그x , 를 지정함 )
        //Adapter에 Manager를 넘겨주면 가로 세로를 선택하는 기능도 구현이 가능함....
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                RecyclerActivity.this , RecyclerView.VERTICAL , false
        );
        recyclerView.setLayoutManager(layoutManager);//리사이클러뷰가 세로 모드로 붙게끔 설정.
        recyclerView.setAdapter(adapter2);

    }
}