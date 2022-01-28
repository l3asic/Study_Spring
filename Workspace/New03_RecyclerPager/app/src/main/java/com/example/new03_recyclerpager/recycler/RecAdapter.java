package com.example.new03_recyclerpager.recycler;

//Recycler Adapter 마스터 ==> veiwPager2 ==>(Recycler Adapter )
//viewpager <가로만됨 > viewpager2 세로도됨.
//ViewHolder를 강제한다. ( 우리가 xml에 만들어놓은 위젯의 묶음이 되는 클래스 )
// Imageview , TextView <-묶어놓은 클래스를 반드시 만들고 이벤트 핸들링을 여기서해라.

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.new03_recyclerpager.R;

import java.util.ArrayList;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder> {
//2.아이템마다 보여줄 정보를 가지고있는 DTO를 하나로 묶은 Collection 구조를 받을수있게
//필드로 선언 , LayoutInflater 사용을 위한 준비를 함. <-(1).Activity로부터 받아옴(Context)
    //(2) static으로 있는 getApplicationContext로 사용 <- 지원안하는 기능들도있음 ↑
    //context<- 토스트를 띄우기 , LayoutInflater만들기 , 등등 화면에 떠있는 객체에서
    //할수있는 기능들을 처리할수가있다. (일반 클래스에도 context를 넘기면 화면 제어를 할수가있음)
    //RecAdapter<- 일반 클래스임

    //메인에서 넘겨 받을 필드
    Context context;
    ArrayList<RecDTO> list;
    LayoutInflater inflater;
    //생성자 메소드를 만듬 ↑ 세개를 입력받거나 또는 두개를 입력받아서 LayoutInflater까지 null이 아니게 ..
    //생성자 메소드 호출해보기 . ( RecyclerActivity )


    public RecAdapter(Context context, ArrayList<RecDTO> list) {
        this.context = context;
        this.list = list;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //액티비티의 소스 줄을 조금 줄일수가있음.
        //context 자체를 받아왔기때문에 context를 이용하는것이 메모리에 더 효율적이다.
    }

    public RecAdapter(Context context, ArrayList<RecDTO> list, LayoutInflater inflater) {
        this.context = context;
        this.list = list;
        this.inflater = inflater;
    }


    //3.화면을 인플레이트 시킨다. ( 리사이클러의 아이템 하나마다 붙을 xml을 연결시킨다 )
    // xml 로 연결시킨 view자체를 Viewholder에 넘겨줌 ( == ViewHolder에서는 view를 통해서 find )
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = inflater.inflate(R.layout.recycleritem , parent , false );
       //1. ViewHolder holder = new ViewHolder(itemview);
       // return holder;
        return new ViewHolder(itemview);
    }
    //4. 아이템이 ↑ 세팅되고 나서의 처리를 의미함↓
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Toast.makeText(context, list.get(position).getTextStr(), Toast.LENGTH_SHORT).show();

    }
    //5.↓ 총 아이템의 갯수를 지정함.
    @Override
    public int getItemCount() {
        return list.size();
    }

    //1. RecyclerView.ViewHolder 상속을 받은 클래스 ViewHolder를 만들어줌
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgv1;
        TextView tv1; //xml에 있는 위젯들을 전역변수로 선언.
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgv1 = itemView.findViewById(R.id.imgv1);
            tv1 = itemView.findViewById(R.id.tv1);

        }
    }
}
