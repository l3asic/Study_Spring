package com.example.newlastproject.member;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.newlastproject.MainActivity;
import com.example.newlastproject.R;
import com.example.newlastproject.async.AskParam;
import com.example.newlastproject.async.CommonAsk;
import com.example.newlastproject.async.CommonMethod;
import com.example.newlastproject.async.CommonVal;
import com.example.newlastproject.transactivity.TransActivity;
import com.google.gson.Gson;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.Account;
import com.kakao.sdk.user.model.Profile;

import java.io.InputStreamReader;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class LoginActivity extends AppCompatActivity {
    EditText edt_id , edt_pw;
    Button btn_login;
    ImageView imgv_kakaologin;
    CheckBox chk_auto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        KakaoSdk.init(this, "e766cd38a872d87544668acaec7d0407");

        Intent intent = new Intent(LoginActivity.this, TransActivity.class);
        startActivity(intent);
        chk_auto = findViewById(R.id.chk_auto);
        edt_id = findViewById(R.id.edt_id);
        edt_pw = findViewById(R.id.edt_pw);
        btn_login = findViewById(R.id.btn_login);
        imgv_kakaologin = findViewById(R.id.imgv_kakaologin);

        findViewById(R.id.btn_join).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,JoinActivity.class);
                startActivity(intent);
                //????????? ????????? ( ??????????????? ?????? ????????? ????????? ????????? ????????? ?????? ???????????????)
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonAsk ask = new CommonAsk("login");
               ask.addParams("id" , edt_id.getText() + "");
               ask.addParams("pw" , edt_id.getText() + "");
                Gson gson = new Gson();
                CommonVal.loginInfo =
                        gson.fromJson(new InputStreamReader(CommonMethod.excuteAsk(ask))
                        , MemberVO.class);
                if(CommonVal.loginInfo != null){
                    goMain();
                }else{
                    Toast.makeText(LoginActivity.this, "?????? ?????? ????????????", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //https://developers.kakao.com/docs/latest/ko/kakaologin/android
        //kotlin ??? ????????? ??????
        Function2<OAuthToken , Throwable , Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                if(oAuthToken != null){
                    Toast.makeText(LoginActivity.this, "????????? ?????????", Toast.LENGTH_SHORT).show();
                    getKakaoinfo();
                }
                if(throwable != null){
                    Toast.makeText(LoginActivity.this, "?????? ????????????.", Toast.LENGTH_SHORT).show();
                }
                
                return null;
            }
        };

        imgv_kakaologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ??????????????? ???????????? ????????? ?????????????????? ?????????, ????????? ????????????????????? ?????????
                if(UserApiClient.getInstance().isKakaoTalkLoginAvailable(LoginActivity.this)){
                    Toast.makeText(LoginActivity.this, "???????????? ?????? ???", Toast.LENGTH_SHORT).show();
                    UserApiClient.getInstance().loginWithKakaoTalk(LoginActivity.this,callback);
                }else{
                    Toast.makeText(LoginActivity.this, "???????????? ?????? ??????", Toast.LENGTH_SHORT).show();
                    UserApiClient.getInstance().loginWithKakaoAccount(LoginActivity.this,callback);
                }
            }
        });

        SharedPreferences preferences = getPreferences(LoginActivity.MODE_PRIVATE);
        String id = preferences.getString("id" , null);
        String pw = preferences.getString("pw" , null);
        if(id != null && pw != null){
            edt_id.setText(id);
            edt_pw.setText(pw);
            btn_login.performClick();
        }

    }

    public void getKakaoinfo(){
        UserApiClient.getInstance().me( (user, throwable) -> {
            if(throwable != null){
                //????????? ????????? ?????? ???????????? ??????????????? ?????? . KOE + ??????
            }else{
                // [ {  }  ] json ???????????? ?????? ???????????? ????????? ????????? Account?????? ?????? ????????? ????????????
                //??????????????? ??? profile????????? ????????? ??? ?????? .
                Account kakaoAcount = user.getKakaoAccount();
                if(kakaoAcount != null){
                    Profile profile = kakaoAcount.getProfile();
                    if(profile != null){
                        Toast.makeText(LoginActivity.this, profile.getNickname()+"??? ??????", Toast.LENGTH_SHORT).show();
                        goMain();
                    }
                }
            }

            return  null;
        }) ;
    }
    //?????? ?????? -> ?????? ????????? 1 , Kakao ????????? 2 , Google Login 3 , FaceBook Login 4..
    //Naver Login 5
    public void goMain(){
        //is<- boolean??? ???????????? ???????????? ????????????
        //?????????????????? ?????? ?????????????????? ???????????? ??????????????? ?????? ????????? ????????????.
        //SharedPreferences <- ?????? ??????????????? id , pw <- ?
        //?????????????????? ?????? Edittext??? ?????? ?????? , ??????????????? ?
        //DB ??????id , pw .... kakao , naver
        //select * from member where kakao = ? => id , pw

        if(chk_auto.isChecked()){
            Toast.makeText(LoginActivity.this, "????????? ??????????????????.", Toast.LENGTH_SHORT).show();
            SharedPreferences preferences = getPreferences(LoginActivity.MODE_PRIVATE);
            //????????? ????????? ??????????????? ?????? ???????????? key??? ??????????????? ???????????? ?????? ???????????? ??????????????? ?????????
            //????????? ?????? ???????????? ??????
            SharedPreferences.Editor editor = preferences.edit();//Editor ???????????? ?????????.
            editor.putString("id", edt_id.getText() + "");
            editor.putString("pw", edt_pw.getText() + "");
            editor.apply(); // commit?????? ?????? ?????? ???????????? ?????? ????????? ????????????
        }else{
            //2?????? ?????? ( ????????? ) - key??? ???????????? ?????? ???????????? ???????????? ??????
            //                      - ??????????????? ?????? ?????? ???????????? ???????????? ??????
            //                      - ?????? ???????????? ????????? ??????,???????????? ??? ?????? ??????????????? ?????? ?????? ??????.
            SharedPreferences preferences = getPreferences(LoginActivity.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            //1 remove ( key )
            editor.remove("id");
            editor.remove("pw");
            editor.apply();
            //2. clear <- ?????? ????????? ?????? ??????????????? ?????? ?????????.
            //editor.clear();
           // editor.apply();

            Toast.makeText(LoginActivity.this, "????????? X??????????????????.", Toast.LENGTH_SHORT).show();

        }


        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

//// ?????????????????? ?????????
//UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
//        if (error != null) {
//            Log.e(TAG, "????????? ??????", error)
//        }
//        else if (token != null) {
//            Log.i(TAG, "????????? ?????? ${token.accessToken}")
//        }
//    }




}