package com.example.yao.pojo;

import java.io.File;
import java.io.Serializable;

/**
 * Created by 89551 on 2016-09-01.
 */
public class leibie implements Serializable {

    private int id;
    private String icon;
    private String name;

    public leibie(int id, String icon, String name) {

        this.id = id;
        this.icon = icon;
        this.name = name;
    }

    public leibie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "leibie{" +
                "id=" + id +
                ", icon='" + icon + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
