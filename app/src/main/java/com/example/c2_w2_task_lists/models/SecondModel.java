package com.example.c2_w2_task_lists.models;

public class SecondModel extends AbstractModel{

    private int number;

    public SecondModel(String uid, int number) {
        super(uid);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
