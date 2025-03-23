package com.example.quizzapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainPage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter Adapter;
    private List<Item> testList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        testList = new ArrayList<>();
        testList.add(new Item("Practice Test", R.drawable.img));
        testList.add(new Item("Real Test", R.drawable.img));
        testList.add(new Item("Test 3", R.drawable.img));

        Adapter = new Adapter(testList);
        recyclerView.setAdapter(Adapter);

        Adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(Item item) {
                if (item.getItemName().equals("Practice Test")) {
                    startActivity(new Intent(MainPage.this, PracticeTest.class));
                } else if (item.getItemName().equals("Real Test")) {
                    startActivity(new Intent(MainPage.this, RealTest.class));
                }
            }
        });

        Button btnReminder = findViewById(R.id.btnReminder);
        btnReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainPage.this, Reminder.class));
            }
        });
    }
}
