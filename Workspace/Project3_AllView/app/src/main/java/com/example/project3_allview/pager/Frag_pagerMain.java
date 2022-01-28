package com.example.project3_allview.pager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.project3_allview.R;

import java.util.ArrayList;


public class Frag_pagerMain extends Fragment {
    //activity = onCreate();
    //Fragment = onCreateView();
    ViewPager pager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.frag_pagermain
                , container
                , false);
        //rootView로 부터 widget(Control)들을 찾는다.
        pager = rootView.findViewById(R.id.pager);
        Frag_pager1 frag1 = new Frag_pager1();
        Frag_pager2 frag2 = new Frag_pager2();
        Frag_pager3 frag3 = new Frag_pager3();
        //pager<- 바로 Fragment가 전환되게 넣어놓으면 편하지만,
        //Adapter를 이용해서 view,widget을 넣어줘야함.
        //Context를 가진애들이 가지고있음
        PagerAdapter adapter = new PagerAdapter(getActivity().getSupportFragmentManager() );
        adapter.addItem(frag1);
        adapter.addItem(frag2);
        adapter.addItem(frag3);
        //item을 추가함 ArrayList↑
        pager.setAdapter(adapter);
        return rootView;
    }



    class PagerAdapter extends FragmentStatePagerAdapter{
        ArrayList<Fragment> items = new ArrayList<>();
        //우리가 어떤식으로 Fragment를 핸들링할껀지에 대한 ,
        //변수를 초기화해서 놔둔다.

        //기본적으로 생성자가 필요하기 때문에 놔둠.
        public PagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }
        //Fragment를 어데이 Add시켜서 사용할건지 , 메소드로 만듬.
        //ArrayList<Fragment> items에 추가해서 사용함 (현재)
        public void addItem(Fragment item){
            items.add(item);
        }

        public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return items.get(position);//Fragment return됨;
        }
        //Adapter를 사용할때는 collection 자료구조를 이용하는것이 좋음.
        @Override
        public int getCount() {
            return items.size();
        }
    }


}//Frag_pagerMain <- Class