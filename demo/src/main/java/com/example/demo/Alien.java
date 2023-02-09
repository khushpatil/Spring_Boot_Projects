package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class Alien {
    private int aid;
    private String aname;

    Alien() {
        super();
        System.out.println("Alien created");
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String show() {
        return "This is an alien";
    }
}
