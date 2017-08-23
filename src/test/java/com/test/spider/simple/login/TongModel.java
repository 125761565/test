package com.test.spider.simple.login;

/***
 * Model类——封装了要抓取的那些字段
 * @author Administrator
 *
 */
public class TongModel {
    String fullName;
    String shortName;
    String time;
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}