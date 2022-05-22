package com.example.genshinimpactgachasimulator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.genshinimpactgachasimulator.adapter.DetailAdapter;
import com.example.genshinimpactgachasimulator.database.Item;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{

    private ArrayList<Item> arrayOfBannerContents;

    private AppCompatButton btnExit;
    private RecyclerView rvDetails;

    DetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        arrayOfBannerContents = (ArrayList<Item>) getIntent().getSerializableExtra("Banner Data");

        btnExit = findViewById(R.id.detail_btn_exit);
        btnExit.setOnClickListener(this);

        rvDetails = findViewById(R.id.rv_details);
        adapter = new DetailAdapter(arrayOfBannerContents);
        rvDetails.setAdapter(adapter);
        rvDetails.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    public void onClick(View v) {
        if(v == btnExit) {
            Intent i = new Intent(DetailActivity.this, MainActivity.class);
            startActivity(i);
        }
    }
}