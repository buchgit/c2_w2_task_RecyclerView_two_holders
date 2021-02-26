package com.example.c2_w2_task_lists.models;

public class FirstModel extends AbstractModel{

    private String name;

    public FirstModel(String uid, String name) {
        super(uid);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
