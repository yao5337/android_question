package com.example.yao.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 89551 on 2016-08-31.
 */
public class question {

    private String content;
    private int id;
    private String pubTime;
    private int typeid;
    private String answer;
    private int cataid ;

    public question(String content, int id, String pubTime, String answer, int typeid, int cataid) {
        this.content = content;
        this.id = id;

        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        this.pubTime = format.format(pubTime);

        this.answer = answer;
        this.typeid = typeid;
        this.cataid = cataid;
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

    public String getTime() {
        return pubTime;
    }

    public void setTime(String pubTime) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        this.pubTime = format.format(pubTime);
    }

    public int getType() {
        return typeid;
    }

    public void setType(int typeid) {
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


    @Override
    public String toString() {
        return "question{" +
                "content='" + content + '\'' +
                ", id=" + id +
                ", pubTime='" + pubTime + '\'' +
                ", typeid=" + typeid +
                ", answer='" + answer + '\'' +
                ", cataid=" + cataid +
                '}';
    }
}
