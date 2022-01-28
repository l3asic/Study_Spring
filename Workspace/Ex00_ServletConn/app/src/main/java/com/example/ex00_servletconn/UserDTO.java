package com.example.ex00_servletconn;

public class UserDTO {
    int refid; //img가 자동적으로 .dex code로 바뀌면서 리소스로 관리되는 번호(=?)
    String user_id , user_msg;
    // alt + insert
    public UserDTO(int refid, String user_id, String user_msg) {
        this.refid = refid;
        this.user_id = user_id;
        this.user_msg = user_msg;
    }

    public int getRefid() {
        return refid;
    }

    public void setRefid(int refid) {
        this.refid = refid;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_msg() {
        return user_msg;
    }

    public void setUser_msg(String user_msg) {
        this.user_msg = user_msg;
    }
}
