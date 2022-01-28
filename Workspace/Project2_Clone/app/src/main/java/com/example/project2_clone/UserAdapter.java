package com.example.project2_clone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

//일반적인 Class => Adapter
//바뀌는 순간 Extends
//제일 기본적인 형태의 커스터마이징을 제공하는 BaseAdapter
public class UserAdapter extends BaseAdapter {
    Context context ; //Fragment로 부터 받아올예정 ( Fragment <= Activity )
    ArrayList<UserDTO> dtos;
    LayoutInflater inflater; //Class파일은 가지고 있을수가 없음

    public UserAdapter(Context context, ArrayList<UserDTO> dtos) {
        this.context = context;
        this.dtos = dtos;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }//
    //커스터마이징해서 우리가 원하는형태의 데이터를 넣을수있게 만듬.↓
    public void addDTO(UserDTO dto){
        dtos.add(dto);
    }
    //ArrayList로 데이터를 받음 ( 총 데이터의 건수를 의미하는 메소드 )
    @Override
    public int getCount() {
        return dtos.size();
    }
    // list  = [ dto ]0  [ dto ]1
    @Override
    public Object getItem(int position) {
        return dtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    //실제로 제일 코딩이 많이 들어가는부분 ↓ (커스터마이징시 제일 어려운부분)
    //직접 ListView에 들어가는 아이템을 디자인 처리하고 이벤트 처리도 넣는곳.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UserViewHolder viewHolder;
        //converView <= 디자인 처리가 완료가 되었으면 null이아닌 완료된 디자인
        //listItemView를 주고 , 그게아니라면 null값을 준다.
        //따라서 converView가 null일때 우리는 디자인 연결 작업을 해줘야함.
        if(convertView == null){
            //실제 디자인xml <-> java 코딩을 해야하는분
            convertView = inflater.inflate(R.layout.listitemview,parent,false);
            viewHolder = new UserViewHolder();
            viewHolder.list_imgv1 = convertView.findViewById(R.id.list_imgv1);
            viewHolder.user_id = convertView.findViewById(R.id.user_id);
            viewHolder.user_msg = convertView.findViewById(R.id.user_msg);
            //태그 ( 모든 위젯이 있고 위젯을 구분하기위한 frag값 )
            convertView.setTag(viewHolder);//나중에 conterview null이 아니라면 viewHoler(아이템을 return)
        }else{
            viewHolder = (UserViewHolder) convertView.getTag();
        }

        UserDTO dto = dtos.get(position);
        String user_id = dto.getUser_id();
        String user_msg = dto.getUser_msg();
        int ref_id = dto.getRefid();

        viewHolder.user_id.setText(user_id);
        viewHolder.user_msg.setText(user_msg);
        viewHolder.list_imgv1.setImageResource(ref_id);//이미지를 세팅하는 메소드
        
        viewHolder.user_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dtos.getPostion == list.position 매칭이 됨.
                Toast.makeText(context, dtos.get(position).getUser_id(), Toast.LENGTH_SHORT).show();
            }
        });
        
        return convertView;
    }

    //ViewHolder라는 개념이 필요함 ※ ※
    //우리가 Layout에 listItemview에서 지정해놓은 widget을 하나로 묶어놓은
    //Class
    public class UserViewHolder{
        public ImageView list_imgv1;
        public TextView user_id , user_msg;
    }


}
