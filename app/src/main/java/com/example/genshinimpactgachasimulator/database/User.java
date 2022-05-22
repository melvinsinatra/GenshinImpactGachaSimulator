package com.example.genshinimpactgachasimulator.database;

public class User {

    private Integer id, primogems, acquaintFate, intertwinedFate;

    public User(Integer id, Integer primogems, Integer acquaintFate, Integer intertwinedFate) {
        this.id = id;
        this.primogems = primogems;
        this.acquaintFate = acquaintFate;
        this.intertwinedFate = intertwinedFate;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPrimogems() {
        return primogems;
    }

    public Integer getAcquaintFate() {
        return acquaintFate;
    }

    public Integer getIntertwinedFate() {
        return intertwinedFate;
    }

    public void setAcquaintFate(Integer acquaintFate) {
        this.acquaintFate = acquaintFate;
    }

    public void setIntertwinedFate(Integer intertwinedFate) {
        this.intertwinedFate = intertwinedFate;
    }
}
