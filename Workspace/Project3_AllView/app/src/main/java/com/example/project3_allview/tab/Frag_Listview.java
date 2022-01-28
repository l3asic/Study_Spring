package com.example.project3_allview.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.project3_allview.R;


public class Frag_Listview extends Fragment {
    //activity = onCreate();
    //Fragment = onCreateView();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.frag_list
                , container
                , false);
        //rootView로 부터 widget(Control)들을 찾는다.

        return rootView;
    }
}