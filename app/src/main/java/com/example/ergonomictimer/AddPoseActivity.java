package com.example.ergonomictimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class AddPoseActivity extends MainMenu {

    EditText nameInput;
    EditText sanskritNameInput;
    EditText chakraInput;
    EditText durationInput;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pose);

        nameInput = findViewById(R.id.nameInput);
        sanskritNameInput = findViewById(R.id.sanskritNameInput);
        chakraInput = findViewById(R.id.chakraInput);
        durationInput = findViewById(R.id.durationInput);
    }

    public void addPose(View button){
        dbHelper = new DBHelper(this);
        String name = nameInput.getText().toString();
        if(TextUtils.isEmpty(name)) {
            nameInput.setError("Please add");
            return;
        }
        String sanskritName = sanskritNameInput.getText().toString();
        if(TextUtils.isEmpty(sanskritName)) {
            sanskritNameInput.setError("Please add");
            return;
        }
        String chakra = chakraInput.getText().toString();
        if(TextUtils.isEmpty(chakra)) {
            chakraInput.setError("Please add");
            return;
        }

        String input = durationInput.getText().toString();
        if(input.trim().equals("")) {
            durationInput.setError("Please add");
            return;
        }

        final Integer duration = Integer.parseInt(durationInput.getText().toString());
        Integer image = R.drawable.lotus3;

        Pose pose = new Pose(name, sanskritName, chakra, duration, image);
        pose.save(dbHelper);

        Intent intent = new Intent(this, TopPosesActivity.class);
        startActivity(intent);
    }

}