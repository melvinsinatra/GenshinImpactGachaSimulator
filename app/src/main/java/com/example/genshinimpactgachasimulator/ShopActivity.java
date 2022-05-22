package com.example.genshinimpactgachasimulator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.genshinimpactgachasimulator.database.DatabaseHelper;
import com.example.genshinimpactgachasimulator.database.User;

public class ShopActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatButton btnIntertwinedFate, btnAcquaintFate, btnExit, btnConfirm, btnCancel;
    TextView tvTitle, tvAmount, tvCost, tvPrimogems;
    EditText etAmount;
    ImageView imgFate;
    Dialog dialogShop;
    DatabaseHelper db;
    User user;

    Integer fateAmount, primogemsCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        getSupportActionBar().hide(); // Hide ActionBar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //Hide StatusBar
        initWidgets();
        db = new DatabaseHelper(ShopActivity.this);
        user = db.getUserData();

        if(tvPrimogems.getText().equals("")) {
            tvPrimogems.setText(user.getPrimogems().toString());
        }

        etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    fateAmount = Integer.parseInt(etAmount.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                primogemsCost = 160*fateAmount;

                tvCost.setText(primogemsCost.toString());
            }
        });
    }

    private void initWidgets() {
        tvPrimogems = findViewById(R.id.tv_shop_primogems);
        btnIntertwinedFate = findViewById(R.id.btn_buy_intertwined_fates);
        btnIntertwinedFate.setOnClickListener(this);
        btnAcquaintFate = findViewById(R.id.btn_buy_acquaint_fates);
        btnAcquaintFate.setOnClickListener(this);
        btnExit = findViewById(R.id.shop_btn_exit);
        btnExit.setOnClickListener(this);

        dialogShop = new Dialog(this);
        dialogShop.setContentView(R.layout.custom_shop_dialog);
        dialogShop.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        btnConfirm = dialogShop.findViewById(R.id.btn_shop_confirm);
        btnConfirm.setOnClickListener(this);
        btnCancel = dialogShop.findViewById(R.id.btn_shop_cancel);
        btnCancel.setOnClickListener(this);
        etAmount = dialogShop.findViewById(R.id. et_shop_amount);
        imgFate = dialogShop.findViewById(R.id.img_shop_fate);
        tvTitle = dialogShop.findViewById(R.id.tv_shop_title);
        tvAmount = dialogShop.findViewById(R.id.tv_shop_amount);
        tvCost = dialogShop.findViewById(R.id.tv_primogems_cost);
    }

    @Override
    public void onClick(View v) {
        if (v == btnExit) {
            Intent i = new Intent(ShopActivity.this, MainActivity.class);
            startActivity(i);
        }

        else if(v == btnIntertwinedFate) {
            imgFate.setImageResource(R.drawable.ic_intertwined_fate_2);
            tvTitle.setText("Buy Intertwined Fates");
            tvAmount.setText("Intertwined Fates amount:");

            dialogShop.show();
        }

        else if (v == btnAcquaintFate) {
            imgFate.setImageResource(R.drawable.ic_acquaint_fate_2);
            tvTitle.setText("Buy Acquaint Fates");
            tvAmount.setText("Acquaint Fates amount:");

            dialogShop.show();
        }

        else if (v == btnCancel) {
            dialogShop.dismiss();
            etAmount.setText("1");
        }

        else if (v == btnConfirm) {
            fateAmount = Integer.parseInt(etAmount.getText().toString());
            primogemsCost = Integer.parseInt(tvCost.getText().toString());
            Integer updatedPrimogems;
            updatedPrimogems = user.getPrimogems()-primogemsCost;

            if(user.getPrimogems()<primogemsCost) {
                Toast.makeText(ShopActivity.this, "Insufficient Primogems!", Toast.LENGTH_SHORT).show();
                dialogShop.dismiss();
                etAmount.setText("1");
                return;
            }

            if(tvTitle.getText().equals("Buy Intertwined Fates")) {
                db.updateUserData(user.getId(), updatedPrimogems, user.getAcquaintFate(), user.getIntertwinedFate()+fateAmount);
                Toast.makeText(ShopActivity.this, "Successfully bought " + fateAmount + " Intertwined Fates for " + primogemsCost + " Primogems", Toast.LENGTH_SHORT).show();
            } else if(tvTitle.getText().equals("Buy Acquaint Fates")) {
                db.updateUserData(user.getId(), updatedPrimogems, user.getAcquaintFate()+fateAmount, user.getIntertwinedFate());
                Toast.makeText(ShopActivity.this, "Successfully bought " + fateAmount + " Acquaint Fates for " + primogemsCost + " Primogems", Toast.LENGTH_SHORT).show();
            }
            tvPrimogems.setText(updatedPrimogems.toString());
            etAmount.setText("1");
            user = db.getUserData();
            dialogShop.dismiss();
        }
    }
}