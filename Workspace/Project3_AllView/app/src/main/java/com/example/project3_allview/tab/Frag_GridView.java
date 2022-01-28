package com.example.project3_allview.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.project3_allview.Atask.AtakConn;
import com.example.project3_allview.R;
import com.example.project3_allview.adapter.GridAdapter;
import com.example.project3_allview.dto.GridDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class Frag_GridView extends Fragment {
    //activity = onCreate();
    //Fragment = onCreateView();
    //getActivy <- 현재 액티비티를 받와서 그리고 나서 Context.
    GridView gridView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.frag_grid
                , container
                , false);
        //rootView로 부터 widget(Control)들을 찾는다.
        ArrayList<GridDTO> list = new ArrayList<>();
        GridAdapter adapter = new GridAdapter(getContext(),list);
        gridView = rootView.findViewById(R.id.gridv);
        AtakConn conn = new AtakConn("list.vw");
        try {
            InputStream is = conn.execute().get();
            Gson gson = new Gson();
            list = gson.fromJson(new InputStreamReader(is) ,
                    new TypeToken<List<GridDTO>>(){}.getType());
            adapter.addItem(list);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        gridView.setAdapter(adapter);
        return rootView;
    }
}