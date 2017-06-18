package com.ljr.note.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by LinJiaRong on 2017/6/16.
 * TODO：
 */

public class Note extends DataSupport{
    private String date;
    private String title;
    private String content;

    public Note(String date, String title, String content) {
        this.date = date;
        this.title = title;
        this.content = content;
    }

    public Note() {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Note{" +
                "date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
