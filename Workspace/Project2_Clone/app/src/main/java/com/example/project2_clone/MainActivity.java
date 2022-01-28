package com.example.project2_clone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "main:";
    ActionBar actionBar; //전역변수로 Actionbar를 선언만 해둠.
    BottomNavigationView bottom_nav ;
/*    Fragment1 frag1 = new Fragment1();
    Fragment2 frag2 = new Fragment2();
    Fragment3 frag3 = new Fragment3();
    Fragment4 frag4 = new Fragment4();
    Fragment5 frag5 = new Fragment5();*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //actionbar는 android os가 제공함 따라서 os를 이용해서 초기화 하면됨.
        actionBar = getSupportActionBar();//os가 return
        actionBar.setTitle("채팅");


        //android에서 fragment를 붙일때 이용할수있는 기능을 getSupportFragmentManger 에서 제공을하고
        //이용해서 fragment를 '붙이는' 처리를 한다 .




        //전체 화면으로 내용을 더 보여주고싶은데 액션바가 공간을 차지 하고있을때,
        //또는 액션바 자체가 없어도 되는 화면,
        //values.themes. NoactionBar로 바꾸는 방법1. -비추 .
        //actionBar.hide();
        bottom_nav = findViewById(R.id.bottom_nav);
        //xml<->java
        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.tab1){
                    changeFragment(new Fragment1() , "tab1이 선택됨");
                    return true;
                }else if(item.getItemId() == R.id.tab2){
                    changeFragment(new Fragment2() , "tab2이 선택됨");
                    return true;
                }else if(item.getItemId() == R.id.tab3){
                    changeFragment(new Fragment3() , "tab3이 선택됨");
                    return true;
                }else if(item.getItemId() == R.id.tab4){
                    changeFragment(new Fragment4() , "tab4이 선택됨");
                    return true;
                }else if(item.getItemId() == R.id.tab5){
                    changeFragment(new Fragment5() , "tab5이 선택됨");
                    return true;
                }


                return false;
            }
        });


    } // onCreate

    public void changeFragment(Fragment fragment , String msg){
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().beginTransaction().replace(R.id.container , fragment).commit();
    }


    //xml <-> actionbar 연결하는 코드
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option , menu);
        return true;
    }
    //option메뉴를 눌렀을때 이벤트


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d(TAG, " 이벤트가 실행됨");
        Log.d(TAG, " item:속성중에 id " + item.getItemId()  );
        Log.d(TAG, " item:속성중에 id " + R.id.menu_search  );
        Log.d(TAG, " item:속성중에 title " + item.getTitle() );
        Log.d(TAG, " item:속성중에 icon " + item.getIcon() );
        Log.d(TAG, " item:속성중에 menuinfo " + item.getMenuInfo() );
        
//        if(item.getTitle().equals("검색")){
//            Toast.makeText(MainActivity.this, "검색 기능을 실행합니다.", Toast.LENGTH_SHORT).show();
//        } <= 이방법은 나중에 title이 수정되었을때 마다 같이 바꿔줘야되는 불편함이있음.
        if(item.getItemId() == R.id.menu_search){
            Toast.makeText(MainActivity.this, item.getTitle()+"기능을 합니다.", Toast.LENGTH_SHORT).show();
            //Intent를 통해서 페이지전환 또는 어떤 기능들의 코드 ...
        }else if (item.getItemId() == R.id.menu_option){
            Toast.makeText(MainActivity.this, item.getTitle()+"기능을 합니다.", Toast.LENGTH_SHORT).show();
        }else if (item.getItemId() == R.id.menu_music){
            Toast.makeText(MainActivity.this, item.getTitle()+"기능을 합니다.", Toast.LENGTH_SHORT).show();
        }else if (item.getItemId() == R.id.menu_talk){
            Toast.makeText(MainActivity.this, item.getTitle()+"기능을 합니다.", Toast.LENGTH_SHORT).show();

        }
        
        
        
        //각 메뉴별로 클릭 시 토스트창이 뜨면서 검색기능이 시작됩니다.
        // ...대화기능이 시작됩니다.
        return super.onOptionsItemSelected(item);
    }
}