package com.example.genshinimpactgachasimulator.database;

public class History {

    private String item_name, item_type, wish_type, time_received;
    private Integer item_rarity;

    public History(String item_name, String item_type, Integer item_rarity, String wish_type, String time_received) {
        this.item_name = item_name;
        this.item_type = item_type;
        this.wish_type = wish_type;
        this.time_received = time_received;
        this.item_rarity = item_rarity;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getItem_type() {
        return item_type;
    }

    public String getWish_type() {
        return wish_type;
    }

    public String getTime_received() {
        return time_received;
    }

}
