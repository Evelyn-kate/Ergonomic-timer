package com.example.ergonomictimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class TopSessionsActivity extends MainMenu {

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_sessions_list);
        dbHelper = new DBHelper(this);
        ArrayList<Session> sessionList = Session.all(dbHelper);

        TopSessionsAdapter sessionsAdapter = new TopSessionsAdapter(this, sessionList);
        ListView listView = (ListView)findViewById(R.id.top_sessions_list);
        listView.setAdapter(sessionsAdapter);
        dbHelper.close();
    }
}