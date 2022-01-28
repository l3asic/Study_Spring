package com.example.project1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
   // public static boolean isDown = false;
    Button btn_alert;
    ScrollView scroll1;
    View view1 ,view2;
    TextView tv1;//view <= 손을 대거나 눌렀을때 등등의 이벤트가 일어났을때 보여지게끔.
    //제스처를 사용하기위한 객체
    GestureDetector detector;

//progreesbar <- MainActivity 를이용하는방법
    //static . method , static progressbar
    public static ProgressBar progressBar ;
//thrad가 작업중일때 또 스타트가 안되게막기

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_alert = findViewById(R.id.btn_alert);
        progressBar = findViewById(R.id.progress_bar);
        //제스처용 위젯↓
        scroll1 = findViewById(R.id.scroll1);
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        tv1 = findViewById(R.id.tv1);


        view1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //손가락이 눌렸는지 , 움직였는지 action 1.움직임 2.눌림
                int action = event.getAction();
                float curX = event.getX();//좌우
                float curY = event.getY();//상하
                if(action == MotionEvent.ACTION_DOWN){
                    println("손가락이 눌림 : x " + curX + " -y :" + curY);
                }else if(action == MotionEvent.ACTION_MOVE){
                    println("손가락이 움직임 : x " + curX + " -y :" + curY);
                }else if(action == MotionEvent.ACTION_UP){
                    println("손가락이 떼어짐 : x " + curX + " -y :" + curY);
                }
                return true;
            }
        });

        view2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                detector.onTouchEvent(event);
                return true;
            }
        });

        detector = new GestureDetector(MainActivity.this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                println("눌림 : x " + e.getX() + " -y :" + e.getY() );
                return true;
            }
            //떼지는 경우↓
            @Override
            public void onShowPress(MotionEvent e) {
                println("떼어짐 : x " + e.getX() + " -y :" + e.getY() );
            }
            //한손가락으로 눌렸다가 떼어지는 경우 ( 판단하기 애매함 )
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                println("onSinleTapup : x " + e.getX() + " -y :" + e.getY() );
                return true;
            }
            //화면이 눌린채 일정한 속도와 방향으로 움직였다 떼어지는 경우
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                println("일정함 : x " + distanceX + " -y :" + distanceY );

                return true;
            }
            //오래 눌렀을때 onLongPress
            @Override
            public void onLongPress(MotionEvent e) {
                println("오래눌림 : x " + e.getX() + " -y :" + e.getY() );

            }
        //화면이 눌려진채 가속도를 붙여서 손가락을 움진인 경우↓
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                println("가속도 : x " + velocityX + " -y :" + velocityY );

                return true;
            }
        });








        //alert ↓
        btn_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AlertDilog Custom 인터넷 예제보고 공부해보기.
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("안내");
                builder.setMessage("Service를 이용해서 파일 다운로드");
                builder.setIcon(android.R.drawable.ic_dialog_alert);//android자체에서 제공하는 icon모양.
                //builder를 이용해서 생성을 하고있지만 실제로 띄우는 코드를 넣지 않음.
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //syso logd 오류남
                      //  if(!isDown) {
                         //   isDown =true;
                            Log.d(TAG, "MainActivity : which" + which);
                            Toast.makeText(MainActivity.this, "예 눌림", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, FileDownLoadService.class);
                            intent.putExtra("command", "작업시작");
                            startService(intent);
                     //   }
                        //Service라는것을 만들고 메인액티비티와 별개로 작업을 하게끔 만듬.
                    }
                });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "아니오 눌림", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "아니오 눌림", Toast.LENGTH_SHORT).show();
                    }
                });
                // builder.nagative == 아니오눌림 Toast
                //setNeutral == 취소눌림 Toast
                
                
                AlertDialog dialog = builder.create();
                dialog.show();
                
                
            }
        });

    }//onCreate

    public void println(String data){
        tv1.append(data + "\n");
        scroll1.fullScroll(View.FOCUS_DOWN);//스크롤뷰가 영역이있으면 맨 아래로 내리는 처리
    }

    //★ ↑ 중요함 키를 눌렀을때 이벤트

    //key를 눌렀을때 동작하는 메소드
    //C# winform<- , java / keycode if문으로 분기해서 사용하는거랑 거의 똑같음.
    //알아두면 개발에 매우도움됨
    //ex) 뒤로가기를 두번눌렀을때 체크, =>AlertDilag(정말종료하시겠습니까?)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //keycode -> 0101010 -> int 형으로 바꿔서줌. 우리는 int형 keyCode만 알면됨.
        //미리 상수로 정의 되어있음 ( final )
        if(keyCode == KeyEvent.KEYCODE_BACK ){
            //alert창을 띄워서 정말 뒤로 가시겠습니까/
            //예를 누르면 Splash가 뜸
            //아니오를 누르면 그대로 화면이 유지
            return  true;
        }else if(keyCode == KeyEvent.KEYCODE_VOLUME_UP){
            println("볼륨이 업됨 ");
        }else if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
            println("볼륨이 다운됨 ");
        }


        return super.onKeyDown(keyCode, event);
    }
}//Class