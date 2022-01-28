package com.example.project2_clone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Fragment3 extends Fragment {
    //Fragment 수명주기 LifeCycle을 가진다.
    //onCreateView <= Fragment
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //ViewGroup <-
        //setContetView (Activity == rootView)
        ViewGroup rootView  = (ViewGroup)
                inflater.inflate(R.layout.fragment3 , container , false);
        //↑ 디자인과 붙일 컨테이너(layout) xml <->java 연결
        listView = rootView.findViewById(R.id.listview);
        //제일 사용빈도가 낮은 ( Android Studio에서 기본형태로 제공해주는 어댑터)
        ArrayList<String> list = new ArrayList<>();
        //View(ListView)에 데이터를 바인딩 시킬때는 Adapter가 필요함.
        //Adapter의 종류는 생각보다 엄청 많음(필요에 따라서 인터넷이나
        //개발자 문서를 참고해서 만드는 것)

        //안드로이드는 외우는 공부를 하면 초급자 수준을 벗어날수가없음.
        //ctrl + p (메소드에 넘겨줘야할 인수(인자,파라메터)를 확인하는 단축키)
        //Context?????==fragemnt자체는 inflate된 주도권을 가진 화면이 x
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,//Adapter(ListView,Item)
                list
        );
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "" + position, Toast.LENGTH_SHORT).show();
                //getContext<= MainActivity에 infalte 화면을(붙여놓음) Fragment
                //Fragment자체는 화면에 제어권이없음 ( == Context 가 없음)
                //getContext()<= 지금 내가 붙어있는 제어권이 있는 화면에서 Context를
                //얻어온다.
            }
        });


        listView.setAdapter(adapter);//Adapter <-> listview
        list.add("아무값1");
        list.add("아무값2");
        list.add("아무값3");
        list.add("아무값4");
        list.add("아무값5");
        list.add("아무값6");
        list.add("아무값7");
        list.add("아무값8");
        list.add("아무값9");
        list.add("아무값10");
        list.add("아무값11");
        list.add("아무값12");
        list.add("아무값13");
        list.add("아무값14");
        list.add("아무값15");
        list.add("아무값16");
        list.add("아무값17");
        list.add("아무값18");
        list.add("아무값19");
        list.add("아무값20");
        return rootView;
    }
}