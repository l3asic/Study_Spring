package com.example.project2_clone;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment5 extends Fragment {
    //Fragment 수명주기 LifeCycle을 가진다.
    //onCreateView <= Fragment


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //ViewGroup <-
        //setContetView (Activity == rootView)
        ViewGroup rootView  = (ViewGroup)
                inflater.inflate(R.layout.fragment5 , container , false);
        //↑ 디자인과 붙일 컨테이너(layout) xml <->java 연결
        return rootView;
    }
}