package com.example.yao.pojo;

import com.google.gson.JsonArray;

import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 89551 on 2016-08-31.
 */
public class question implements Serializable {

    private String content;
    private int id;
    private long pubTime;
    private int typeid;
    private String answer;
    private int cataid ;
    private String options=null;


    public question(String content, int id, long pubTime, int typeid, String answer, int cataid, String options) {
        this.content = content;
        this.id = id;
        this.pubTime = pubTime;
        this.typeid = typeid;
        this.answer = answer;
        this.cataid = cataid;
        this.options = options;
    }

    public question() {
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTime() {
        return pubTime;
    }

    public void setTime(long pubTime) {

        this.pubTime=pubTime;

    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getCataid() {
        return cataid;
    }

    public void setCataid(int cataid) {
        this.cataid = cataid;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "question{" +
                "content='" + content + '\'' +
                ", id=" + id +
                ", pubTime=" + pubTime +
                ", typeid=" + typeid +
                ", answer='" + answer + '\'' +
                ", cataid=" + cataid +
                ", options='" + options + '\'' +
                '}';
    }
}
