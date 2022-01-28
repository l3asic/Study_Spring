package com.example.project2_clone;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.concurrent.ExecutionException;

public class Fragment1 extends Fragment {
    private static final String TAG = "fragment";
    //Fragment 수명주기 LifeCycle을 가진다.
    //onCreateView <= Fragment
    Button button1 , button2 , button3 ;
    ImageView imgv1 , imgv2 , imgv3;
    TextView tv1;
    Animation flowAnim , scaleAnim;//from 100%P > 0%P ?

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //ViewGroup <-
        //setContetView (Activity == rootView)
        ViewGroup rootView  = (ViewGroup)
                inflater.inflate(R.layout.fragment1 , container , false);
        button1 = rootView.findViewById(R.id.frag_btn1);
        button2 = rootView.findViewById(R.id.frag_btn2);
        button3 = rootView.findViewById(R.id.frag_btn3);

        imgv1 = rootView.findViewById(R.id.frag1_imgv1);
        imgv2 = rootView.findViewById(R.id.frag1_imgv2);
        imgv3 = rootView.findViewById(R.id.frag1_imgv3);

        tv1 = rootView.findViewById(R.id.frag1_tv);
        //↑ 디자인과 붙일 컨테이너(layout) xml <->java 연결
        // rootView로 부터 (rootview는 fragment 디자인을 의미함 )
        //애니메이션 초기화
        flowAnim = AnimationUtils.loadAnimation(getContext() , R.anim.flow );
        scaleAnim = AnimationUtils.loadAnimation(getContext() , R.anim.scale );
        //getContext() , Fragment 자체는 Context라는 화면에 권한이 없다.
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv1.startAnimation(flowAnim);
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgv2.startAnimation(scaleAnim);
                imgv3.startAnimation(scaleAnim);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation rotateAnim = AnimationUtils.loadAnimation(getContext() , R.anim.rotate );
                imgv2.startAnimation(rotateAnim);
                imgv3.startAnimation(rotateAnim);

            }
        });


        return rootView;
    }
}