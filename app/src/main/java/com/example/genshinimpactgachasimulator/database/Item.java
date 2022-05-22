package com.example.genshinimpactgachasimulator.database;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Item implements Serializable, Parcelable {

    private String itemName, itemType;
    private Integer itemRarity;

    public Item(String itemName, String itemType, Integer itemRarity) {
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemRarity = itemRarity;
    }

    protected Item(Parcel in) {
        itemName = in.readString();
        itemType = in.readString();
        if (in.readByte() == 0) {
            itemRarity = null;
        } else {
            itemRarity = in.readInt();
        }
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public String getItemName() {
        return itemName;
    }


    public String getItemType() {
        return itemType;
    }


    public Integer getItemRarity() {
        return itemRarity;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(itemName);
        dest.writeString(itemType);
        if (itemRarity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(itemRarity);
        }
    }
}
