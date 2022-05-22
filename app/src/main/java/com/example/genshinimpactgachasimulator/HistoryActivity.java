package com.example.genshinimpactgachasimulator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.genshinimpactgachasimulator.adapter.HistoryAdapter;
import com.example.genshinimpactgachasimulator.database.DatabaseHelper;
import com.example.genshinimpactgachasimulator.database.History;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<History> arrayOfHistory;

    AppCompatButton btnExit, btnDeleteHistory, btnDialogCancel, btnDialogConfirm;
    RecyclerView rvHistory;
    Dialog dialog;
    TextView tvDialogTitle, tvDialogMessage;

    HistoryAdapter adapter;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initWidgets();

        db = new DatabaseHelper(HistoryActivity.this);
        arrayOfHistory = db.getHistoryData();
        adapter = new HistoryAdapter(arrayOfHistory);
        rvHistory.setAdapter(adapter);
        rvHistory.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }

    private void initWidgets() {
        btnExit = findViewById(R.id.btn_exit);
        btnExit.setOnClickListener(this);
        btnDeleteHistory = findViewById(R.id.btn_delete_history);
        btnDeleteHistory.setOnClickListener(this);
        rvHistory = findViewById(R.id.rv_history);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_alert_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        btnDialogCancel = dialog.findViewById(R.id.btn_alert_cancel);
        btnDialogCancel.setOnClickListener(this);
        btnDialogConfirm = dialog.findViewById(R.id.btn_alert_confirm);
        btnDialogConfirm.setOnClickListener(this);
        tvDialogTitle = dialog.findViewById(R.id.tv_dialog_title);
        tvDialogTitle.setText("Alert!");
        tvDialogMessage = dialog.findViewById(R.id.tv_dialog_message);
        tvDialogMessage.setText("Are you sure you want to clear the history data?");

    }

    @Override
    public void onClick(View view) {
        if (view == btnExit) {
            Intent i = new Intent(HistoryActivity.this, MainActivity.class);
            startActivity(i);
        }

        else if (view == btnDeleteHistory) {
            dialog.show();
        }

        else if (view == btnDialogCancel) {
            dialog.dismiss();
        }

        else if (view == btnDialogConfirm) {
            db.deleteHistory();
            arrayOfHistory.clear();
            adapter.notifyDataSetChanged();
            dialog.dismiss();
        }
    }
}