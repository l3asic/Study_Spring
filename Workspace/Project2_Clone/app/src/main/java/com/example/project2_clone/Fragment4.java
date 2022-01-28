package com.example.project2_clone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Fragment4 extends Fragment {
    //Fragment 수명주기 LifeCycle을 가진다.
    //onCreateView <= Fragment
    //※※※※getContext() <= 주도권을 가진 화면의 Context를 return을 못해주는 경우가
    //빈번함, 따라서 다른 방법으로 더 안전하게 Context를 받아올수있다(OS)
    //더안전한 방법이라는 말은 우리가 화면을 지금 띄우고있는 Activity를 정확히
    //알고있을때. 해당하는 Activity를 명시해서 activity자체를 받아올수있음.
    ListView cus_listView;
    MainActivity activity;
    UserAdapter adapter;
    ArrayList<UserDTO> list = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //ViewGroup <-
        //setContetView (Activity == rootView)
        ViewGroup rootView  = (ViewGroup)
                inflater.inflate(R.layout.fragment4 , container , false);
        //↑ 디자인과 붙일 컨테이너(layout) xml <->java 연결
       activity = (MainActivity) getActivity();
       //getActivty를 하게되면 현재 fragment가 떠있는(inflate)되어있는
        //상위 Activity를 가지고올수가있다. (상위에 있는 Activity를 정확히 인지했다면
        //가장 안전한 방법 ) cast
        TextView tv1 = rootView.findViewById(R.id.tv1);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Common클래스 자체를 인스턴스화해서 사용하면 HttpClient기능을 다 사용할수가없음.
                //StringServlet <- AsyncTask 상속받은 객체만 HttpClient <-
                StringServlet ss = new StringServlet();
                try {
                    ss.execute().get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        cus_listView = rootView.findViewById(R.id.cus_listview);
        //Adapter를 기본적으로 제공해주는 형태를 사용하는게 아니라 커스터마이징이
        //필요하다면 DTO구조부터 새로 만들어야한다 (커스터마이징)
        //↑DTO구조를 Element(요소로 가지는 어댑터를 만드는것)

        adapter = new UserAdapter(activity.getApplicationContext()
                                ,list
        );
        adapter.addDTO(
                new UserDTO(R.drawable.img1,"블랙핑크" , "뭐해?")
        );
        adapter.addDTO(
                new UserDTO(R.drawable.img2,"박진영" , "뭐해?")
        );
        adapter.addDTO(
                new UserDTO(R.drawable.img3,"귤" , "뭐해?")
        );
        adapter.addDTO(
                new UserDTO(R.drawable.splashlogo,"로고" , "뭐해?")
        );
        adapter.addDTO(
                new UserDTO(R.drawable.gear,"기계" , "뭐해?")
        );
        cus_listView.setAdapter(adapter);
        return rootView;
    }
}