package com.example.quizzapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainPage extends AppCompatActivity {

    private static final String PREF_NAME = "UserPrefs";
    private static final String QUESTIONS_PREFIX = "question_";

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
        testList.add(new Item("Mistakes Test", R.drawable.img));

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

        loadQuestionsFromJson();
    }

    private void loadQuestionsFromJson() {
        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        if (!prefs.contains(QUESTIONS_PREFIX + "1")) {
            try {
                InputStream is = getAssets().open("questions.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                String json = new String(buffer, StandardCharsets.UTF_8);

                JSONArray jsonArray = new JSONArray(json);
                SharedPreferences.Editor editor = prefs.edit();

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    String id = obj.getString("id");
                    String question = obj.getString("question");
                    JSONArray answersArray = obj.getJSONArray("answers");

                    StringBuilder answers = new StringBuilder();
                    for (int j = 0; j < answersArray.length(); j++) {
                        answers.append(answersArray.getString(j)).append(",");
                    }
                    answers.append("normal");

                    editor.putString(QUESTIONS_PREFIX + id, question + "," + answers.toString());
                }

                editor.apply();
                Log.d("MainPage", "Questions loaded from JSON and saved in SharedPreferences.");

            } catch (Exception e) {
                e.printStackTrace();
                Log.e("MainPage", "Error loading questions from JSON.");
            }
        } else {
            Log.d("MainPage", "Questions already exist in SharedPreferences. Skipping JSON load.");
        }
    }
}
