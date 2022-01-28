package com.example.project3_allview.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project3_allview.R;
import com.example.project3_allview.dto.GridDTO;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    private static final String TAG = "aa";
    Context context; // <- foreGround  == frontGround
                     // ,  ↑ BackGround ( Context )==생성자
    ArrayList<GridDTO> list;
    LayoutInflater inflater;
    //GridAdapter <= FragmentGrid.Class(java) 생성해보기
    public GridAdapter(Context context, ArrayList<GridDTO> list) {
        this.context = context;
        this.list = list;
        //this.inflater = inflater;←만들어서 넘겨준거 세팅, ↓직접 만들기
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public void addItem(GridDTO dto){
        list.add(dto);
    }
    public void addItem(ArrayList<GridDTO> list){
        this.list = list;
    }
    @Override
    public int getCount(){
        return list.size();
    }
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    //커스터마이징이 가장 많이 되는 부분
    //직접 GridView에 보여지는 Layout처리를 해야함. java<->xml
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GridViewHolder viewHolder;
        if (convertView == null){//아직 초기 디자인이 없는상태의 view라면
            convertView =            //layout        , parent , 제어권 false
                    inflater.inflate(R.layout.griditem,parent ,false);
            viewHolder = new GridViewHolder();
            viewHolder.imgv1 = convertView.findViewById(R.id.imgv1);
            viewHolder.tv1 = convertView.findViewById(R.id.tv1);
            viewHolder.tv2 = convertView.findViewById(R.id.tv2);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (GridViewHolder) convertView.getTag();
        }
        //if(list.get(position).getImgresId() == 1)
        viewHolder.tv1.setText(list.get(position).getTitle());
        viewHolder.tv2.setText(list.get(position).getContent());
        //viewHolder.imgv1.setImageResource(list.get(position).getImgresId());
        viewHolder.tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
            }
        });
        return convertView;
    }
    //ViewHolder <- 위젯들을 하나로 묶은 클래스를 만들고 사용.
    //Imagev1 , tv1 , tv2 (Class)
    public class GridViewHolder{
        public ImageView imgv1;
        public TextView tv1 , tv2;
    }

}
