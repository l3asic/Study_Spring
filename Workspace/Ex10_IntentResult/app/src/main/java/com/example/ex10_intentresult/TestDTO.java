package com.example.ex10_intentresult;

import java.io.Serializable;

public class TestDTO implements Serializable {
    String field1 ;
    int field2 ;
    double field3;
    //intent가 객체를 보낼때는 반드시 Serializable을 해줘야하는 규칙이 있다.
    /* alt + insert*/

    public TestDTO(String field1, int field2, double field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public int getField2() {
        return field2;
    }

    public void setField2(int field2) {
        this.field2 = field2;
    }

    public double getField3() {
        return field3;
    }

    public void setField3(double field3) {
        this.field3 = field3;
    }
}
