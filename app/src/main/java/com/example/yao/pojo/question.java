package com.example.yao.pojo;

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

    public question(String content, int id, long pubTime, int typeid, String answer, int cataid) {
        this.content = content;
        this.id = id;


        this.pubTime=pubTime;
        this.typeid=typeid;

        this.answer = answer;
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

    public long getTime() {
        return pubTime;
    }

    public void setTime(long pubTime) {

        this.pubTime=pubTime;

//        Date data = new Date(pubTime);
//        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
//        this.pubTime = format.format(pubTime);
    }

    public int getType() {
        return typeid;
    }

    public void setType(int typeid) {

        this.typeid=typeid;
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
                ", type=" + typeid +
                ", answer='" + answer + '\'' +
                ", cataid=" + cataid +
                '}';
    }
}
