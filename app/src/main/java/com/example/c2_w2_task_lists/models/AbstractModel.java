package com.example.c2_w2_task_lists.models;

/*
в модели должны быть поля (и геттеры), из которых заполнятся свойства элементов холдера
 */

import java.io.Serializable;

public class AbstractModel implements Serializable {
    String uid;

    public AbstractModel(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }
}
