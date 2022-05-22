package com.example.genshinimpactgachasimulator.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Parcelable;
import android.widget.Toast;

import java.sql.SQLInput;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private Random rand = new Random();
    ArrayList<Item> arrayOfCharacterEventItem = new ArrayList<>();
    ArrayList<Item> arrayOfWeaponEventItem = new ArrayList<>();
    ArrayList<Item> arrayOfStandardItem = new ArrayList<>();

    public static final String SQL_CREATE_USER_TABLE
            = "CREATE TABLE IF NOT EXISTS user(id INTEGER PRIMARY KEY, primogems INTEGER, acquaint_fate INTEGER, intertwined_fate INTEGER)";
    public static final String SQL_DROP_USER_TABLE
            = "DROP TABLE IF EXISTS user";

    public static final String SQL_CREATE_CHARACTER_EVENT_BANNER_TABLE
            = "CREATE TABLE IF NOT EXISTS character_event(item_name TEXT, item_type TEXT, item_rarity INTEGER)";
    public static final String SQL_DROP_CHARACTER_EVENT_BANNER_TABLE
            = "DROP TABLE IF EXISTS character_event";

    public static final String SQL_CREATE_WEAPON_EVENT_BANNER_TABLE
            = "CREATE TABLE IF NOT EXISTS weapon_event(item_name TEXT, item_type TEXT, item_rarity INTEGER)";
    public static final String SQL_DROP_WEAPON_EVENT_BANNER_TABLE
            = "DROP TABLE IF EXISTS weapon_event";

    public static final String SQL_CREATE_STANDARD_TABLE
            = "CREATE TABLE IF NOT EXISTS standard(item_name TEXT, item_type TEXT, item_rarity INTEGER)";
    public static final String SQL_DROP_STANDARD_TABLE
            = "DROP TABLE IF EXISTS standard";

    public static final String SQL_CREATE_HISTORY_TABLE
            = "CREATE TABLE IF NOT EXISTS history(item_name TEXT, item_type TEXT, item_rarity INTEGER, wish_type TEXT, time_received TEXT)";
    public static final String SQL_DROP_HISTORY_TABLE
            = "DROP TABLE IF EXISTS history";

    public DatabaseHelper(Context context) {
        super(context, "GenshinImpactGachaSimulator", null, 10);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_USER_TABLE);
        insertUserData(sqLiteDatabase);

        sqLiteDatabase.execSQL(SQL_CREATE_CHARACTER_EVENT_BANNER_TABLE);
        insertCharacterEventData(sqLiteDatabase);
        sqLiteDatabase.execSQL(SQL_CREATE_WEAPON_EVENT_BANNER_TABLE);
        insertWeaponEventData(sqLiteDatabase);
        sqLiteDatabase.execSQL(SQL_CREATE_STANDARD_TABLE);
        insertStandardData(sqLiteDatabase);

        sqLiteDatabase.execSQL(SQL_CREATE_HISTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DROP_USER_TABLE);
        sqLiteDatabase.execSQL(SQL_DROP_CHARACTER_EVENT_BANNER_TABLE);
        sqLiteDatabase.execSQL(SQL_DROP_WEAPON_EVENT_BANNER_TABLE);
        sqLiteDatabase.execSQL(SQL_DROP_HISTORY_TABLE);
        sqLiteDatabase.execSQL(SQL_DROP_STANDARD_TABLE);

        arrayOfCharacterEventItem.clear();
        arrayOfWeaponEventItem.clear();
        arrayOfStandardItem.clear();

        onCreate(sqLiteDatabase);
    }

    private void insertUserData(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put("id", 1);
        cv.put("primogems", 1600);
        cv.put("acquaint_fate", 15);
        cv.put("intertwined_fate", 10);
        db.insert("user", null, cv);
    }

    public User getUserData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user", null);

        User user = null;
        while (cursor.moveToNext()) {
            Integer id = cursor.getInt(0);
            Integer primogems = cursor.getInt(1);
            Integer acquaint_fate = cursor.getInt(2);
            Integer intertwined_fate = cursor.getInt(3);
            user = new User(id, primogems, acquaint_fate, intertwined_fate);
        }
        return user;
    }

    public void updateUserData(Integer id, Integer primogems, Integer acquaint_fate, Integer intertwined_fate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("primogems", primogems);
        cv.put("acquaint_fate", acquaint_fate);
        cv.put("intertwined_fate", intertwined_fate);
        db.update("user", cv, "id=?", new String[]{String.valueOf(id)});
    }

    private void insertCharacterEventData(SQLiteDatabase db) {
        arrayOfCharacterEventItem.add(new Item("Kamisato Ayaka (5☆)", "Character", 5));
        arrayOfCharacterEventItem.add(new Item("Mona (5☆)", "Character", 5));
        arrayOfCharacterEventItem.add(new Item("Keqing (5☆)", "Character", 5));
        arrayOfCharacterEventItem.add(new Item("Diluc (5☆)", "Character", 5));
        arrayOfCharacterEventItem.add(new Item("Qiqi (5☆)", "Character", 5));
        arrayOfCharacterEventItem.add(new Item("Jean (5☆)", "Character", 5));
        arrayOfCharacterEventItem.add(new Item("Sayu (4☆)", "Character", 4));
        arrayOfCharacterEventItem.add(new Item("Rosaria (4☆)", "Character", 4));
        arrayOfCharacterEventItem.add(new Item("Razor (4☆)", "Character", 4));
        arrayOfCharacterEventItem.add(new Item("Yun Jin (4☆)", "Character", 4));
        arrayOfCharacterEventItem.add(new Item("Kujou Sara (4☆)", "Character", 4));
        arrayOfCharacterEventItem.add(new Item("Gorou (4☆)", "Character", 4));
        arrayOfCharacterEventItem.add(new Item("Thoma (4☆)", "Character", 4));
        arrayOfCharacterEventItem.add(new Item("Yanfei (4☆)", "Character", 4));
        arrayOfCharacterEventItem.add(new Item("Xinyan (4☆)", "Character", 4));
        arrayOfCharacterEventItem.add(new Item("Sucrose (4☆)", "Character", 4));
        arrayOfCharacterEventItem.add(new Item("Diona (4☆)", "Character", 4));
        arrayOfCharacterEventItem.add(new Item("Chongyun (4☆)", "Character", 4));
        arrayOfCharacterEventItem.add(new Item("Noelle (4☆)", "Character", 4));
        arrayOfCharacterEventItem.add(new Item("Bennett (4☆)", "Character", 4));
        arrayOfCharacterEventItem.add(new Item("Fischl (4☆)", "Character", 4));
        arrayOfCharacterEventItem.add(new Item("Ningguang (4☆)", "Character", 4));
        arrayOfCharacterEventItem.add(new Item("Xingqiu (4☆)", "Character", 4));
        arrayOfCharacterEventItem.add(new Item("Beidou (4☆)", "Character", 4));
        arrayOfCharacterEventItem.add(new Item("Xiangling (4☆)", "Character", 4));
        arrayOfCharacterEventItem.add(new Item("Barbara (4☆)", "Character", 4));
        arrayOfCharacterEventItem.add(new Item("The Stringless (4☆)", "Weapon", 4));
        arrayOfCharacterEventItem.add(new Item("Favonius Warbow (4☆)", "Weapon", 4));
        arrayOfCharacterEventItem.add(new Item("Eye of Perception (4☆)", "Weapon", 4));
        arrayOfCharacterEventItem.add(new Item("Sacrificial Fragments (4☆)", "Weapon", 4));
        arrayOfCharacterEventItem.add(new Item("The Widsith (4☆)", "Weapon", 4));
        arrayOfCharacterEventItem.add(new Item("Favonius Codex (4☆)", "Weapon", 4));
        arrayOfCharacterEventItem.add(new Item("Favonius Lance (4☆)", "Weapon", 4));
        arrayOfCharacterEventItem.add(new Item("Dragon's Bane (4☆)", "Weapon", 4));
        arrayOfCharacterEventItem.add(new Item("Rainslasher (4☆)", "Weapon", 4));
        arrayOfCharacterEventItem.add(new Item("Sacrificial Greatsword (4☆)", "Weapon", 4));
        arrayOfCharacterEventItem.add(new Item("The Bell (4☆)", "Weapon", 4));
        arrayOfCharacterEventItem.add(new Item("Favonius Greatsword (4☆)", "Weapon", 4));
        arrayOfCharacterEventItem.add(new Item("Lion's Roar (4☆)", "Weapon", 4));
        arrayOfCharacterEventItem.add(new Item("Sacrificial Sword (4☆)", "Weapon", 4));
        arrayOfCharacterEventItem.add(new Item("The Flute (4☆)", "Weapon", 4));
        arrayOfCharacterEventItem.add(new Item("Favonius Sword (4☆)", "Weapon", 4));
        arrayOfCharacterEventItem.add(new Item("Slingshot", "Weapon", 3));
        arrayOfCharacterEventItem.add(new Item("Sharpshooter's Oath", "Weapon", 3));
        arrayOfCharacterEventItem.add(new Item("Emerald Orb", "Weapon", 3));
        arrayOfCharacterEventItem.add(new Item("Raven Bow", "Weapon", 3));
        arrayOfCharacterEventItem.add(new Item("Thrilling Tales of Dragon Slayer", "Weapon", 3));
        arrayOfCharacterEventItem.add(new Item("Magic Guide", "Weapon", 3));
        arrayOfCharacterEventItem.add(new Item("Debate Club", "Weapon", 3));
        arrayOfCharacterEventItem.add(new Item("Black Tassel", "Weapon", 3));
        arrayOfCharacterEventItem.add(new Item("Bloodstained Greatsword", "Weapon", 3));
        arrayOfCharacterEventItem.add(new Item("Skyrider Sword", "Weapon", 3));
        arrayOfCharacterEventItem.add(new Item("Ferrous Shadow", "Weapon", 3));
        arrayOfCharacterEventItem.add(new Item("Harbinger of Dawn", "Weapon", 3));
        arrayOfCharacterEventItem.add(new Item("Cool Steel", "Weapon", 3));

        ContentValues cv = new ContentValues();
        for (Item i: arrayOfCharacterEventItem) {
            cv.put("item_name", i.getItemName());
            cv.put("item_type", i.getItemType());
            cv.put("item_rarity", i.getItemRarity());
            db.insert("character_event", null, cv);
        }

    }

    private void insertWeaponEventData(SQLiteDatabase db) {
        arrayOfWeaponEventItem.add(new Item("The Unforged (5☆)", "Weapon", 5));
        arrayOfWeaponEventItem.add(new Item("Mistsplitter Reforged (5☆)", "Weapon", 5));
        arrayOfWeaponEventItem.add(new Item("Amos' Bow (5☆)", "Weapon", 5));
        arrayOfWeaponEventItem.add(new Item("Skyward Harp (5☆)", "Weapon", 5));
        arrayOfWeaponEventItem.add(new Item("Lost Prayer to the Sacred Winds (5☆)", "Weapon", 5));
        arrayOfWeaponEventItem.add(new Item("Skyward Atlas (5☆)", "Weapon", 5));
        arrayOfWeaponEventItem.add(new Item("Primordial Jade Winged-Spear (5☆)", "Weapon", 5));
        arrayOfWeaponEventItem.add(new Item("Skyward Spine (5☆)", "Weapon", 5));
        arrayOfWeaponEventItem.add(new Item("Skyward Pride (5☆)", "Weapon", 5));
        arrayOfWeaponEventItem.add(new Item("Wolf's Gravestone (5☆)", "Weapon", 5));
        arrayOfWeaponEventItem.add(new Item("Skyward Blade (5☆)", "Weapon", 5));
        arrayOfWeaponEventItem.add(new Item("Aquila Favonia (5☆)", "Weapon", 5));
        arrayOfWeaponEventItem.add(new Item("Favonius Warbow (4☆)", "Weapon", 4));
        arrayOfWeaponEventItem.add(new Item("Favonius Lance (4☆)", "Weapon", 4));
        arrayOfWeaponEventItem.add(new Item("Favonius Sword (4☆)", "Weapon", 4));
        arrayOfWeaponEventItem.add(new Item("Favonius Codex (4☆)", "Weapon", 4));
        arrayOfWeaponEventItem.add(new Item("The Bell (4☆)", "Weapon", 4));
        arrayOfWeaponEventItem.add(new Item("Yun Jin (4☆)", "Character", 4));
        arrayOfWeaponEventItem.add(new Item("Kujou Sara (4☆)", "Character", 4));
        arrayOfWeaponEventItem.add(new Item("Gorou (4☆)", "Character", 4));
        arrayOfWeaponEventItem.add(new Item("Sayu (4☆)", "Character", 4));
        arrayOfWeaponEventItem.add(new Item("Thoma (4☆)", "Character", 4));
        arrayOfWeaponEventItem.add(new Item("Yanfei (4☆)", "Character", 4));
        arrayOfWeaponEventItem.add(new Item("Rosaria (4☆)", "Character", 4));
        arrayOfWeaponEventItem.add(new Item("Xinyan (4☆)", "Character", 4));
        arrayOfWeaponEventItem.add(new Item("Sucrose (4☆)", "Character", 4));
        arrayOfWeaponEventItem.add(new Item("Diona (4☆)", "Character", 4));
        arrayOfWeaponEventItem.add(new Item("Chongyun (4☆)", "Character", 4));
        arrayOfWeaponEventItem.add(new Item("Noelle (4☆)", "Character", 4));
        arrayOfWeaponEventItem.add(new Item("Bennett (4☆)", "Character", 4));
        arrayOfWeaponEventItem.add(new Item("Fischl (4☆)", "Character", 4));
        arrayOfWeaponEventItem.add(new Item("Ningguang (4☆)", "Character", 4));
        arrayOfWeaponEventItem.add(new Item("Xingqiu (4☆)", "Character", 4));
        arrayOfWeaponEventItem.add(new Item("Beidou (4☆)", "Character", 4));
        arrayOfWeaponEventItem.add(new Item("Razor (4☆)", "Character", 4));
        arrayOfWeaponEventItem.add(new Item("Xiangling (4☆)", "Character", 4));
        arrayOfWeaponEventItem.add(new Item("Barbara (4☆)", "Character", 4));
        arrayOfWeaponEventItem.add(new Item("Sacrificial Bow( 4☆)", "Weapon", 4));
        arrayOfWeaponEventItem.add(new Item("The Stringless (4☆)", "Weapon", 4));
        arrayOfWeaponEventItem.add(new Item("Sacrificial Fragments (4☆)", "Weapon", 4));
        arrayOfWeaponEventItem.add(new Item("The Widsith (4☆)", "Weapon", 4));
        arrayOfWeaponEventItem.add(new Item("Dragon's Bane (4☆)", "Weapon", 4));
        arrayOfWeaponEventItem.add(new Item("Sacrificial Greatsword (4☆)", "Weapon", 4));
        arrayOfWeaponEventItem.add(new Item("Rust(4☆)", "Weapon", 4));
        arrayOfWeaponEventItem.add(new Item("Rainslasher (4☆)", "Weapon", 4));
        arrayOfWeaponEventItem.add(new Item("Favonius Greatsword (4☆)", "Weapon", 4));
        arrayOfWeaponEventItem.add(new Item("Lion's Roar (4☆)", "Weapon", 4));
        arrayOfWeaponEventItem.add(new Item("Sacrificial Sword (4☆)", "Weapon", 4));
        arrayOfWeaponEventItem.add(new Item("The Flute (4☆)", "Weapon", 4));
        arrayOfWeaponEventItem.add(new Item("Eye of Perception (4☆)", "Weapon", 4));
        arrayOfWeaponEventItem.add(new Item("Slingshot", "Weapon", 3));
        arrayOfWeaponEventItem.add(new Item("Sharpshooter's Oath", "Weapon", 3));
        arrayOfWeaponEventItem.add(new Item("Emerald Orb", "Weapon", 3));
        arrayOfWeaponEventItem.add(new Item("Raven Bow", "Weapon", 3));
        arrayOfWeaponEventItem.add(new Item("Thrilling Tales of Dragon Slayer", "Weapon", 3));
        arrayOfWeaponEventItem.add(new Item("Magic Guide", "Weapon", 3));
        arrayOfWeaponEventItem.add(new Item("Black Tassel", "Weapon", 3));
        arrayOfWeaponEventItem.add(new Item("Debate Club", "Weapon", 3));
        arrayOfWeaponEventItem.add(new Item("Bloodstained Greatsword", "Weapon", 3));
        arrayOfWeaponEventItem.add(new Item("Ferrous Shadow", "Weapon", 3));
        arrayOfWeaponEventItem.add(new Item("Skyrider Sword", "Weapon", 3));
        arrayOfWeaponEventItem.add(new Item("Harbinger of Dawn", "Weapon", 3));
        arrayOfWeaponEventItem.add(new Item("Cool Steel", "Weapon", 3));

        ContentValues cv = new ContentValues();
        for (Item i: arrayOfWeaponEventItem) {
            cv.put("item_name", i.getItemName());
            cv.put("item_type", i.getItemType());
            cv.put("item_rarity", i.getItemRarity());
            db.insert("weapon_event", null, cv);
        }
    }

    private void insertStandardData(SQLiteDatabase db) {
        arrayOfStandardItem.add(new Item("Keqing (5☆)", "Character", 5));
        arrayOfStandardItem.add(new Item("Mona (5☆)", "Character", 5));
        arrayOfStandardItem.add(new Item("Diluc (5☆)", "Character", 5));
        arrayOfStandardItem.add(new Item("Qiqi (5☆)", "Character", 5));
        arrayOfStandardItem.add(new Item("Jean (5☆)", "Character", 5));
        arrayOfStandardItem.add(new Item("Skyward Harp (5☆)", "Weapon", 5));
        arrayOfStandardItem.add(new Item("Lost Prayer to the Sacred Winds (5☆)", "Weapon", 5));
        arrayOfStandardItem.add(new Item("Skyward Atlas (5☆)", "Weapon", 5));
        arrayOfStandardItem.add(new Item("Primordial Jade Winged-Spear (5☆)", "Weapon", 5));
        arrayOfStandardItem.add(new Item("Wolf's Gravestone (5☆)", "Weapon", 5));
        arrayOfStandardItem.add(new Item("Skyward Spine (5☆)", "Weapon", 5));
        arrayOfStandardItem.add(new Item("Skyward Pride (5☆)", "Weapon", 5));
        arrayOfStandardItem.add(new Item("Skyward Blade (5☆)", "Weapon", 5));
        arrayOfStandardItem.add(new Item("Aquila Favonia (5☆)", "Weapon", 5));
        arrayOfStandardItem.add(new Item("Amos' Bow (5☆)", "Weapon", 5));
        arrayOfStandardItem.add(new Item("Yunjin (4☆)", "Character", 4));
        arrayOfStandardItem.add(new Item("Kujou Sara (4☆)", "Character", 4));
        arrayOfStandardItem.add(new Item("Sayu (4☆)", "Character", 4));
        arrayOfStandardItem.add(new Item("Gorou (4☆)", "Character", 4));
        arrayOfStandardItem.add(new Item("Thoma (4☆)", "Character", 4));
        arrayOfStandardItem.add(new Item("Yanfei (4☆)", "Character", 4));
        arrayOfStandardItem.add(new Item("Xinyan (4☆)", "Character", 4));
        arrayOfStandardItem.add(new Item("Rosaria (4☆)", "Character", 4));
        arrayOfStandardItem.add(new Item("Sucrose (4☆)", "Character", 4));
        arrayOfStandardItem.add(new Item("Diona (4☆)", "Character", 4));
        arrayOfStandardItem.add(new Item("Chongyun (4☆)", "Character", 4));
        arrayOfStandardItem.add(new Item("Noelle (4☆)", "Character", 4));
        arrayOfStandardItem.add(new Item("Bennett (4☆)", "Character", 4));
        arrayOfStandardItem.add(new Item("Fischl (4☆)", "Character", 4));
        arrayOfStandardItem.add(new Item("Ningguang (4☆)", "Character", 4));
        arrayOfStandardItem.add(new Item("Xingqiu (4☆)", "Character", 4));
        arrayOfStandardItem.add(new Item("Xiangling (4☆)", "Character", 4));
        arrayOfStandardItem.add(new Item("Beidou (4☆)", "Character", 4));
        arrayOfStandardItem.add(new Item("Amber (4☆)", "Character", 4));
        arrayOfStandardItem.add(new Item("Razor (4☆)", "Character", 4));
        arrayOfStandardItem.add(new Item("Kaeya (4☆)", "Character", 4));
        arrayOfStandardItem.add(new Item("Barbara (4☆)", "Character", 4));
        arrayOfStandardItem.add(new Item("Lisa (4☆)", "Character", 4));
        arrayOfStandardItem.add(new Item("Rust (4☆)", "Weapon", 4));
        arrayOfStandardItem.add(new Item("The Stringless (4☆)", "Weapon", 4));
        arrayOfStandardItem.add(new Item("Sacrificial Bow (4☆)", "Weapon", 4));
        arrayOfStandardItem.add(new Item("Favonius Warbow (4☆)", "Weapon", 4));
        arrayOfStandardItem.add(new Item("Eye of Perception (4☆)", "Weapon", 4));
        arrayOfStandardItem.add(new Item("The Widsith (4☆)", "Weapon", 4));
        arrayOfStandardItem.add(new Item("Sacrificial Fragments (4☆)", "Weapon", 4));
        arrayOfStandardItem.add(new Item("Favonius Codex (4☆)", "Weapon", 4));
        arrayOfStandardItem.add(new Item("Favonius Lance (4☆)", "Weapon", 4));
        arrayOfStandardItem.add(new Item("Dragon's Bane (4☆)", "Weapon", 4));
        arrayOfStandardItem.add(new Item("Rainslasher (4☆)", "Weapon", 4));
        arrayOfStandardItem.add(new Item("Sacrificial Greatsword (4☆)", "Weapon", 4));
        arrayOfStandardItem.add(new Item("The Bell (4☆)", "Weapon", 4));
        arrayOfStandardItem.add(new Item("Favonius Greatsword (4☆)", "Weapon", 4));
        arrayOfStandardItem.add(new Item("Lion's Roar (4☆)", "Weapon", 4));
        arrayOfStandardItem.add(new Item("Sacrificial Sword (4☆)", "Weapon", 4));
        arrayOfStandardItem.add(new Item("The Flute (4☆)", "Weapon", 4));
        arrayOfStandardItem.add(new Item("Favonius Sword (4☆)", "Weapon", 4));
        arrayOfStandardItem.add(new Item("Slingshot", "Weapon", 3));
        arrayOfStandardItem.add(new Item("Sharpshooter's Oath", "Weapon", 3));
        arrayOfStandardItem.add(new Item("Emerald Orb", "Weapon", 3));
        arrayOfStandardItem.add(new Item("Raven Bow", "Weapon", 3));
        arrayOfStandardItem.add(new Item("Thrilling Tales of Dragon Slayer", "Weapon", 3));
        arrayOfStandardItem.add(new Item("Magic Guide", "Weapon", 3));
        arrayOfStandardItem.add(new Item("Black Tassel", "Weapon", 3));
        arrayOfStandardItem.add(new Item("Debate Club", "Weapon", 3));
        arrayOfStandardItem.add(new Item("Bloodstained Greatsword", "Weapon", 3));
        arrayOfStandardItem.add(new Item("Ferrous Shadow", "Weapon", 3));
        arrayOfStandardItem.add(new Item("Skyrider Sword", "Weapon", 3));
        arrayOfStandardItem.add(new Item("Harbinger of Dawn", "Weapon", 3));
        arrayOfStandardItem.add(new Item("Cool Steel", "Weapon", 3));

        ContentValues cv = new ContentValues();
        for (Item i: arrayOfStandardItem) {
            cv.put("item_name", i.getItemName());
            cv.put("item_type", i.getItemType());
            cv.put("item_rarity", i.getItemRarity());
            db.insert("standard", null, cv);
        }
    }

    public ArrayList<Item> getBannerData(String banner) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + banner, null);

        ArrayList<Item> arrayOfBannerData = new ArrayList<>();
        arrayOfBannerData.clear();
        while (cursor.moveToNext()) {
            String item_name = cursor.getString(0);
            String item_type = cursor.getString(1);
            Integer item_rarity = cursor.getInt(2);
            arrayOfBannerData.add(new Item(item_name, item_type, item_rarity));
        }
        return arrayOfBannerData;
    }

    public Item getCharacterEventSSRGuaranteed() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM character_event WHERE item_name =?", new String[]{"Kamisato Ayaka"});

        Item item = null;
        while (cursor.moveToNext()) {
            String item_name = cursor.getString(0);
            String item_type = cursor.getString(1);
            int item_rarity = cursor.getInt(2);
            item = new Item(item_name, item_type, item_rarity);
        }
        return item;
    }

    public Item getCharacterEventSSRFiftyFifty() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM character_event WHERE item_rarity=?", new String[]{"5"});

        Item item1, item2;
        ArrayList<Item> arrayOfItems = new ArrayList<>();
        while(cursor.moveToNext()) {
            String item_name = cursor.getString(0);
            String item_type = cursor.getString(1);
            int item_rarity = cursor.getInt(2);
            arrayOfItems.add(new Item(item_name, item_type, item_rarity));
        }
        item2 = arrayOfItems.get(0);
        arrayOfItems.remove(0);
        int randomNumber = rand.nextInt(arrayOfItems.size());
        item1 = arrayOfItems.get(randomNumber);

        int a = rand.nextInt(2);

        if (a==0) {
            return item2;
        } else {
            return item1;
        }
    }

    public Item gacha(String banner, String rarity) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + banner  + " WHERE item_rarity =?", new String[]{rarity});

        Item item;
        ArrayList<Item> arrayOfItems = new ArrayList<>();
        while (cursor.moveToNext()) {
            String item_name = cursor.getString(0);
            String item_type = cursor.getString(1);
            int item_rarity = cursor.getInt(2);
            item = new Item(item_name, item_type, item_rarity);
            arrayOfItems.add(item);
        }
        int randomNumber = rand.nextInt(arrayOfItems.size());
        item = arrayOfItems.get(randomNumber);

        return item;
    }

    public void insertToHistory(Item item, String wish_type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date currentTime = Calendar.getInstance().getTime();
        String formattedDate = dt.format(currentTime);

        cv.put("item_name", item.getItemName());
        cv.put("item_type", item.getItemType());
        cv.put("item_rarity", item.getItemRarity());
        cv.put("wish_type", wish_type);
        cv.put("time_received", formattedDate);
        db.insert("history", null, cv);
    }

    public void deleteHistory() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("history", null, null);
    }

    public ArrayList<History> getHistoryData() {
        ArrayList<History> arrayOfHistory = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM history", null);

        while (cursor.moveToNext()) {
            String item_name = cursor.getString(0);
            String item_type = cursor.getString(1);
            Integer item_rarity = cursor.getInt(2);
            String wish_type = cursor.getString(3);
            String time_received = cursor.getString(4);
            arrayOfHistory.add(new History(item_name, item_type, item_rarity, wish_type, time_received));
        }
        return arrayOfHistory;
    }

}
