package com.example.genshinimpactgachasimulator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.res.ResourcesCompat;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.genshinimpactgachasimulator.database.DatabaseHelper;
import com.example.genshinimpactgachasimulator.database.Item;
import com.example.genshinimpactgachasimulator.database.User;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Random rand = new Random();
    private double wishPercentage;
    private double ssrRarityCharEvent = 2.1;
    private double ssrRarityWeaponEvent = 2.1;
    private double ssrRarityStandard = 2.1;
    private double srRarityCharEvent = 17.1;
    private double srRarityWeaponEvent = 17.1;
    private double srRarityStandard = 17.1;
    private int ssrPityCharEvent = 1;
    private int srPityCharEvent = 1;
    private int ssrPityWeaponEvent = 1;
    private int srPityWeaponEvent = 1;
    private int ssrPityStandard = 1;
    private int srPityStandard = 1;
    private boolean guaranteed = false;
    private Item itemReceived = null;

    ImageButton btnCharEvent, btnWeapEvent, btnStandard;
    AppCompatButton btnPrimogems, btnAddCancel, btnAddConfirm, btnOneWish, btnTenWish, btnAlertCancel, btnAlertConfirm, btnHistory, btnDetails, btnShop;
    ImageView banner;
    TextView tvFates, tvDialogMessage;
    EditText etAddPrimogems;

    int bannerImage, requiredFateCount, requiredPrimogemsCount;
    String requiredFate;

    Dialog dialogAlert, dialogAdd;

    DatabaseHelper db;
    User user;

    ArrayList<Item> bannerData = new ArrayList<>();
    ArrayList<Item> arrayOfWishResults = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide(); // Hide ActionBar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //Hide StatusBar
        initWidgets();
        db = new DatabaseHelper(MainActivity.this);
        user = db.getUserData();

        banner.setTag(Integer.valueOf(R.drawable.character_event_banner));

        //Setting initial Primogem & Fate Values
        if(btnPrimogems.getText().equals("") && tvFates.getText().equals("")) {
            setFateImage(R.drawable.ic_intertwined_fate);
            btnPrimogems.setText(user.getPrimogems().toString());
            tvFates.setText(user.getIntertwinedFate().toString());
        }
    }

    private void initWidgets() {
        btnCharEvent = findViewById(R.id.btn_character_event);
        btnCharEvent.setOnClickListener(this);
        btnWeapEvent = findViewById(R.id.btn_weapon_event);
        btnWeapEvent.setOnClickListener(this);
        btnStandard = findViewById(R.id.btn_standard_banner);
        btnStandard.setOnClickListener(this);

        btnHistory = findViewById(R.id.btn_history);
        btnHistory.setOnClickListener(this);
        btnShop = findViewById(R.id.btn_shop);
        btnShop.setOnClickListener(this);
        btnDetails = findViewById(R.id.btn_details);
        btnDetails.setOnClickListener(this);

        btnPrimogems = findViewById(R.id.btn_primogems);
        btnPrimogems.setOnClickListener(this);

        banner = findViewById(R.id.banner_image);
        tvFates = findViewById(R.id.tv_fates);

        btnOneWish = findViewById(R.id.btn_one_wish);
        btnOneWish.setOnClickListener(this);
        btnTenWish = findViewById(R.id.btn_ten_wish);
        btnTenWish.setOnClickListener(this);

        dialogAlert = new Dialog(this);
        dialogAlert.setContentView(R.layout.custom_alert_dialog);
        dialogAlert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        tvDialogMessage = dialogAlert.findViewById(R.id.tv_dialog_message);
        btnAlertCancel = dialogAlert.findViewById(R.id.btn_alert_cancel);
        btnAlertCancel.setOnClickListener(this);
        btnAlertConfirm = dialogAlert.findViewById(R.id.btn_alert_confirm);
        btnAlertConfirm.setOnClickListener(this);

        dialogAdd = new Dialog(this);
        dialogAdd.setContentView(R.layout.custom_topup_dialog);
        dialogAdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        btnAddCancel = dialogAdd.findViewById(R.id.btn_add_cancel);
        btnAddCancel.setOnClickListener(this);
        btnAddConfirm = dialogAdd.findViewById(R.id.btn_add_confirm);
        btnAddConfirm.setOnClickListener(this);
        etAddPrimogems = dialogAdd.findViewById(R.id.et_add_primogems);
    }

    private void pitySystem() {
        //SSR Char Event Pity System
        if (ssrPityCharEvent == 76) {
            ssrRarityCharEvent = 20.6;
        } else if (ssrPityCharEvent == 77) {
            ssrRarityCharEvent = 34.5;
        } else if (ssrPityCharEvent == 78) {
            ssrRarityCharEvent = 43.9;
        } else if (ssrPityCharEvent == 79) {
            ssrRarityCharEvent = 50.3;
        } else if (ssrPityCharEvent >= 80) {
            ssrRarityCharEvent = 54.7;
        } else if (ssrPityCharEvent == 90) {
            ssrRarityCharEvent = 100;
        }
        //SR Char Event Pity System
        if (srPityCharEvent == 10) {
            srRarityCharEvent = 100;
        }

        //SSR Weapon Event Pity System
        if (ssrPityWeaponEvent == 76) {
            ssrRarityWeaponEvent = 20.6;
        } else if (ssrPityWeaponEvent == 77) {
            ssrRarityWeaponEvent = 34.5;
        } else if (ssrPityWeaponEvent == 78) {
            ssrRarityWeaponEvent = 43.9;
        } else if (ssrPityWeaponEvent == 79) {
            ssrRarityWeaponEvent = 50.3;
        } else if (ssrPityWeaponEvent >= 80) {
            ssrRarityWeaponEvent = 54.7;
        } else if (ssrPityWeaponEvent == 90) {
            ssrRarityWeaponEvent = 100;
        }
        //SR Weapon Event Pity System
        if (srPityWeaponEvent == 10) {
            srRarityWeaponEvent = 100;
        }

        //SSR Standard Pity System
        if (ssrPityStandard == 76) {
            ssrRarityStandard = 20.6;
        } else if (ssrPityStandard == 77) {
            ssrRarityStandard = 34.5;
        } else if (ssrPityStandard == 78) {
            ssrRarityStandard = 43.9;
        } else if (ssrPityStandard == 79) {
            ssrRarityStandard = 50.3;
        } else if (ssrPityStandard >= 80) {
            ssrRarityStandard = 54.7;
        } else if (ssrPityStandard == 90) {
            ssrRarityStandard = 100;
        }
        //SR Standard Pity System
        if (srPityStandard == 10) {
            srRarityStandard = 100;
        }
    }

    private void setFateImage(int drawable) {
        tvFates.setCompoundDrawables(null, null, null, null);
        Drawable img = ResourcesCompat.getDrawable(getResources(), drawable, null);
        img.setBounds(0, 0, 53, 53);
        tvFates.setCompoundDrawables(img, null, null, null);
        btnOneWish.setCompoundDrawables(img, null, null, null);
        btnTenWish.setCompoundDrawables(img, null, null, null);

        if(drawable == R.drawable.ic_intertwined_fate) {
            tvFates.setText(user.getIntertwinedFate().toString());
        } else if (drawable == R.drawable.ic_acquaint_fate) {
            tvFates.setText(user.getAcquaintFate().toString());
        }
    }

    private void getSSRCharEvent() {
        if (!guaranteed) {
            guaranteed = true;
            itemReceived = db.getCharacterEventSSRFiftyFifty();
        } else if (guaranteed) {
            guaranteed = false;
            itemReceived = db.getCharacterEventSSRGuaranteed();
        }
        db.insertToHistory(itemReceived, "Character Event Wish");
        ssrRarityCharEvent = 2.1;
        ssrPityCharEvent = 1;
    }

    private void getSRCharEvent() {
        itemReceived = db.gacha("character_event", "4");
        db.insertToHistory(itemReceived, "Character Event Wish");
        srPityCharEvent = 1;
        srRarityCharEvent = 17.1;
        ssrPityCharEvent++;
    }

    private void getThreeStar(String banner) {
        String wishType = null;
        if (banner.equals("character_event")) {
            wishType = "Character Event Wish";
            ssrPityCharEvent++;
            srPityCharEvent++;
        } else if (banner.equals("weapon_event")) {
            wishType = "Weapon Event Wish";
            ssrPityWeaponEvent++;
            srPityWeaponEvent++;
        } else if (banner.equals("standard")) {
            wishType = "Standard Wish";
            ssrPityStandard++;
            srPityStandard++;
        }
        itemReceived = db.gacha(banner, "3");
        db.insertToHistory(itemReceived, wishType);
    }

    private void wish() {
        bannerData.clear();
        bannerImage = (Integer) banner.getTag();
        wishPercentage = 100 * rand.nextDouble();
        pitySystem();

        switch (bannerImage) {
            case R.drawable.character_event_banner:
                bannerData = db.getBannerData("character_event");

                db.updateUserData(user.getId(), user.getPrimogems(), user.getAcquaintFate(), Integer.parseInt(tvFates.getText().toString())-1);
                user = db.getUserData();
                tvFates.setText(user.getIntertwinedFate().toString());

                if (wishPercentage<=ssrRarityCharEvent) {
                    getSSRCharEvent();
                } else if (wishPercentage<=srRarityCharEvent && wishPercentage>ssrRarityCharEvent) {
                    getSRCharEvent();
                } else {
                    getThreeStar("character_event");
                }
                break;

            case R.drawable.weapon_event_banner:
                bannerData = db.getBannerData("weapon_event");

                db.updateUserData(user.getId(), user.getPrimogems(), user.getAcquaintFate(), Integer.parseInt(tvFates.getText().toString())-1);
                user = db.getUserData();
                tvFates.setText(user.getIntertwinedFate().toString());

                if (wishPercentage<=ssrRarityWeaponEvent) {
                    itemReceived = db.gacha("weapon_event", "5");
                    ssrRarityWeaponEvent = 2.1;
                    ssrPityWeaponEvent = 1;
                } else if (wishPercentage<=srRarityWeaponEvent && wishPercentage>ssrRarityWeaponEvent) {
                    itemReceived = db.gacha("weapon_event", "4");
                    srPityWeaponEvent = 1;
                    srRarityWeaponEvent = 17.1;
                    ssrPityWeaponEvent++;
                } else {
                    getThreeStar("weapon_event");
                }

                break;

            case R.drawable.standard_banner:
                bannerData = db.getBannerData("standard");

                db.updateUserData(user.getId(), user.getPrimogems(), Integer.parseInt(tvFates.getText().toString())-1, user.getIntertwinedFate());
                user = db.getUserData();
                tvFates.setText(user.getAcquaintFate().toString());

                if (wishPercentage<=ssrRarityStandard) {
                    itemReceived = db.gacha("standard", "5");
                    ssrRarityStandard = 2.1;
                    ssrPityStandard = 1;
                } else if (wishPercentage<=srRarityStandard && wishPercentage>ssrRarityStandard) {
                    itemReceived = db.gacha("standard", "4");
                    srPityStandard = 1;
                    ssrPityStandard++;
                } else {
                    getThreeStar("standard");
                }
        }
        arrayOfWishResults.add(itemReceived);
    }

    private void sendWishResult() {
        Intent i = new Intent(MainActivity.this, WishResultActivity.class);
        i.putExtra("Wish Result", arrayOfWishResults);
        startActivity(i);
        arrayOfWishResults.clear();
    }

    @Override
    public void onClick(View view) {

        if(view == btnCharEvent) {
            btnCharEvent.setImageResource(R.drawable.character_event_button_focused);
            btnWeapEvent.setImageResource(R.drawable.weapon_event_button);
            btnStandard.setImageResource(R.drawable.standard_button);

            banner.setImageResource(R.drawable.character_event_banner);
            banner.setTag(Integer.valueOf(R.drawable.character_event_banner));
            setFateImage(R.drawable.ic_intertwined_fate);
        }

        else if (view == btnWeapEvent) {
            btnCharEvent.setImageResource(R.drawable.character_event_button);
            btnWeapEvent.setImageResource(R.drawable.weapon_event_button_focused);
            btnStandard.setImageResource(R.drawable.standard_button);

            banner.setImageResource(R.drawable.weapon_event_banner);
            banner.setTag(Integer.valueOf(R.drawable.weapon_event_banner));
            setFateImage(R.drawable.ic_intertwined_fate);
        }

        else if (view == btnStandard) {
            btnCharEvent.setImageResource(R.drawable.character_event_button);
            btnWeapEvent.setImageResource(R.drawable.weapon_event_button);
            btnStandard.setImageResource(R.drawable.standard_button_focused);

            banner.setImageResource(R.drawable.standard_banner);
            banner.setTag(R.drawable.standard_banner);
            setFateImage(R.drawable.ic_acquaint_fate);
        }

        else if (view == btnPrimogems) {
            dialogAdd.show();
        }

        else if (view == btnAddCancel) {
            dialogAdd.dismiss();
            etAddPrimogems.setText("1");
        }

        else if (view == btnAddConfirm) {
            Integer newPrimogems = user.getPrimogems() + Integer.parseInt(etAddPrimogems.getText().toString());

            db.updateUserData(user.getId(), newPrimogems, user.getAcquaintFate(), user.getIntertwinedFate());
            user = db.getUserData();
            btnPrimogems.setText(newPrimogems.toString());
            etAddPrimogems.setText("1");
            dialogAdd.dismiss();
        }

        else if(view == btnHistory) {
            Intent i = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(i);
        }

        else if (view == btnOneWish) {

            requiredFateCount = 1;
            requiredPrimogemsCount = 160;

            bannerImage = (Integer) banner.getTag();
            if (bannerImage == R.drawable.character_event_banner || bannerImage == R.drawable.weapon_event_banner) {
                requiredFate = "Intertwined Fate";
            } else if (bannerImage == R.drawable.standard_banner) {
                requiredFate = "Acquaint Fate";
            }
            tvDialogMessage.setText("An additional " + requiredFateCount + " " + requiredFate + " are needed.\nPurchase with " + requiredPrimogemsCount +" Primogems?");

            if (Integer.parseInt(tvFates.getText().toString())<requiredFateCount) {
                dialogAlert.show();
            } else {
                wish();
                sendWishResult();
            }
        }

        else if (view == btnTenWish) {

            requiredFateCount = 10 - Integer.parseInt(tvFates.getText().toString());
            requiredPrimogemsCount = 160 * requiredFateCount;

            bannerImage = (Integer) banner.getTag();
            if (bannerImage == R.drawable.character_event_banner || bannerImage == R.drawable.weapon_event_banner) {
                requiredFate = "Intertwined Fate";
            } else if (bannerImage == R.drawable.standard_banner) {
                requiredFate = "Acquaint Fate";
            }
            tvDialogMessage.setText("An additional " + requiredFateCount + " " + requiredFate + " are needed.\nPurchase with " + requiredPrimogemsCount +" Primogems?");

            if(Integer.parseInt(tvFates.getText().toString())<requiredFateCount) {
                dialogAlert.show();
            } else {
                for(int i=0; i<10; i++) {
                    wish();
                }
                sendWishResult();
            }
        }

        else if (view == btnAlertCancel) {
            dialogAlert.dismiss();
        }

        else if (view == btnAlertConfirm) {
            if (user.getPrimogems()-requiredPrimogemsCount<0) {
                Toast.makeText(MainActivity.this, "Insufficient Primogems", Toast.LENGTH_SHORT).show();
                dialogAlert.dismiss();
                return;
            }
            String dialogMessage = tvDialogMessage.getText().toString();
            if (dialogMessage.contains("Intertwined Fate")) {
                user.setIntertwinedFate(Integer.parseInt(tvFates.getText().toString()));
                db.updateUserData(user.getId(), user.getPrimogems()-requiredPrimogemsCount, user.getAcquaintFate(), user.getIntertwinedFate()+requiredFateCount);
                user = db.getUserData();
                btnPrimogems.setText(user.getPrimogems().toString());
                tvFates.setText(user.getIntertwinedFate().toString());
            }
            if (dialogMessage.contains("Acquaint Fate")) {
                user.setAcquaintFate(Integer.parseInt(tvFates.getText().toString()));
                db.updateUserData(user.getId(), user.getPrimogems()-requiredPrimogemsCount, user.getAcquaintFate()+requiredFateCount, user.getIntertwinedFate());
                user = db.getUserData();
                btnPrimogems.setText(user.getPrimogems().toString());
                tvFates.setText(user.getAcquaintFate().toString());
            }
            dialogAlert.dismiss();
        }

        else if (view == btnShop) {
            Intent i = new Intent(MainActivity.this, ShopActivity.class);
            startActivity(i);
        }

        else if (view == btnDetails) {
            bannerData.clear();
            bannerImage = (Integer) banner.getTag();

            if(banner.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.character_event_banner).getConstantState()) {
                bannerData = db.getBannerData("character_event");
            } else if (banner.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.weapon_event_banner).getConstantState()) {
                bannerData = db.getBannerData("weapon_event");
            } else if (banner.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.standard_banner).getConstantState()) {
                bannerData = db.getBannerData("standard");
            }

            Intent i = new Intent(MainActivity.this, DetailActivity.class);
            i.putExtra("Banner Data", bannerData);
            startActivity(i);
        }
    }
}