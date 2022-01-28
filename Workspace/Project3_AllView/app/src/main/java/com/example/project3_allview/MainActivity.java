package com.example.project3_allview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.project3_allview.Atask.TestConn;
import com.example.project3_allview.pager.Frag_pager1;
import com.example.project3_allview.pager.Frag_pagerMain;
import com.example.project3_allview.tab.Frag_GridView;
import com.example.project3_allview.tab.Frag_Listview;
import com.example.project3_allview.tab.Frag_RecyclerView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "main:";
    TabLayout tab_layout;
    ArrayList<TabDTO> list ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //↓ 화면 연결을 먼저하고 나서 그다음에 widget을 찾아야함.
        setContentView(R.layout.activity_main);
        MainFragment fragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().replace(
                R.id.container , fragment
        ).commit();      //붙일 레이아웃 , 붙을 view(Fragment) , ctrl + p

        TestConn testConn = new TestConn("list.vw");
        try {
            testConn.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        list = new ArrayList<>();
        list.add(new TabDTO("Pager" , new Frag_pagerMain()));
        list.add(new TabDTO("ListView" , new Frag_Listview()));
        list.add(new TabDTO("GridView" , new Frag_GridView()));
        list.add(new TabDTO("RecyclerView" , new Frag_RecyclerView()));


        tab_layout = findViewById(R.id.tab_layout);
        for (int i = 0 ; i<list.size(); i++){
            tab_layout.addTab(tab_layout.newTab().setText(list.get(i).tabName));
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.container,
                list.get(0).fragment
        ).commit();

        tab_layout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                //String + int = String
                Log.d(TAG, "onTabSelected: " + position);
                if(position == 0 ){

                list.get(position).setFragment(new Frag_pagerMain() );
                }
                Toast.makeText(MainActivity.this, ""+position, Toast.LENGTH_SHORT).show();
                 getSupportFragmentManager().beginTransaction().replace(R.id.container,
                       list.get(position).fragment
                 ).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}