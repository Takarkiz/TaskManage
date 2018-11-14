package com.takhaki.todaystaskhack;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Memo extends RealmObject {

    private String tittle;
    private String time;
    private String content;
    private Boolean isDone;
    @PrimaryKey
    private String id;

    public void addData(final String title, final String time, final String content, final Boolean isDone) {
        this.tittle = title;
        this.time = time;
        this.content = content;
        this.isDone = isDone;
        id = UUID.randomUUID().toString();
    }

}
