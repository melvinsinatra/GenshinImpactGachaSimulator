package com.example.genshinimpactgachasimulator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.genshinimpactgachasimulator.adapter.WishResultAdapter;
import com.example.genshinimpactgachasimulator.database.Item;

import java.util.ArrayList;

public class WishResultActivity extends AppCompatActivity {

    private ArrayList<Item> arrayOfItems;
    private RecyclerView rvWishResult;
    private WishResultAdapter adapter;
    private AppCompatButton btnExit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_result);
        getSupportActionBar().hide(); // Hide ActionBar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //Hide StatusBar

        arrayOfItems = new ArrayList<>();
        arrayOfItems = (ArrayList<Item>) getIntent().getSerializableExtra("Wish Result");
        rvWishResult = findViewById(R.id.rv_wish_result);
        adapter = new WishResultAdapter(arrayOfItems);
        rvWishResult.setAdapter(adapter);
        rvWishResult.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        btnExit = findViewById(R.id.wish_result_btn_exit);
        btnExit.setOnClickListener(v -> {
            arrayOfItems.clear();
            finish();
        });


    }
}