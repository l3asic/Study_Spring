package com.example.project2_clone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.concurrent.ExecutionException;

public class Fragment2 extends Fragment {
    //Fragment 수명주기 LifeCycle을 가진다.
    //onCreateView <= Fragment
    public static String data = " ";
    Button btn1 ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //ViewGroup <-
        //setContetView (Activity == rootView)
        ViewGroup rootView  = (ViewGroup)
                inflater.inflate(R.layout.fragment2 , container , false);
        //↑ 디자인과 붙일 컨테이너(layout) xml <->java 연결
        // Button Onclick 만들기.
        btn1 = rootView.findViewById(R.id.btn1_frag2);
        EditText edt1 = rootView.findViewById(R.id.edt_1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Common클래스에 접근하는게 x
                //어씽크테스크 <= 접근 O
                StringServlet ss = new StringServlet();
                try {
                    ss.execute().get();// < - Server와 연동을함. get을 사용하면 비동기지만
                                       //결과를 받고나서 다음 스택으로 넘어감(프로그램)
                                        //어떤 결과를 반드시 받고나서 프로그램이 진행되어야 하는경우는
                                        //※ 반드시 get을 써줘야함.
                    edt1.setText(data);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //excute뒤에 get을 안붙이면 바로 결과를 받을수가없음.
                //background에서 비동기로 계속해서 작업을함.
            }
        });
        return rootView;
    }
}