package com.example.ergonomictimer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class PersonalInformation extends AppCompatActivity {

    TextInputLayout textInputLayout, textInputLayout1, textInputLayout2, textInputLayout3, textInputLayout4;
    TextInputEditText textInputEditText, textInputEditText1, textInputEditText2, textInputEditText3, textInputEditText4;
    ExtendedFloatingActionButton extendedFloatingActionButton;

    SharedPreferences sharedPreferences;

    String name, contact, profession, email, sex;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);

        sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);

        String loginStatus = sharedPreferences.getString(getResources().getString(R.string.prefLoginState), "");
        if (loginStatus.equals("loggedin")){
            startActivity(new Intent(PersonalInformation.this, Home.class));
            finish();
        }

        textInputLayout = findViewById(R.id.textInputLayout);
        textInputLayout1 = findViewById(R.id.textInputLayout1);
        textInputLayout2 = findViewById(R.id.textInputLayout2);
        textInputLayout3 = findViewById(R.id.textInputLayout3);
        textInputLayout4 = findViewById(R.id.textInputLayout4);

        textInputEditText = findViewById(R.id.textInputEditText);
        textInputEditText1 = findViewById(R.id.textInputEditText1);
        textInputEditText2 = findViewById(R.id.textInputEditText2);
        textInputEditText3 = findViewById(R.id.textInputEditText3);
        textInputEditText4 = findViewById(R.id.textInputEditText4);

        extendedFloatingActionButton = findViewById(R.id.extendedFloatingActionButton);

        extendedFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = textInputEditText.getText().toString();
                contact = textInputEditText1.getText().toString();
                profession = textInputEditText2.getText().toString();
                email = textInputEditText3.getText().toString();
                sex = textInputEditText4.getText().toString();

                if (validate(name, contact, profession, email, sex)){
                    saveInformation(name, contact, profession, email, sex);
                }
            }
        });
    }

    private boolean validate(String name, String contact, String profession, String email, String sex) {
        boolean status = false;

        //Validate name
        if (TextUtils.isEmpty(name)){
            status = false;
            textInputLayout.setError("Full name is required");
        }else if (name.length() < 6 ){
            status = false;
            textInputLayout.setError("Full name must be greater than 6 characters");
        }else{
            status = true;
            textInputLayout.setError(null);
        }

        //Validate contact
        if (TextUtils.isEmpty(contact)){
            status = false;
            textInputLayout1.setError("Contact is required");
        }else if (name.length() == 9 ){
            status = false;
            textInputLayout1.setError("Contact must be 9 characters");
        }else{
            status = true;
            textInputLayout1.setError(null);
        }

        //Validate profession
        if (TextUtils.isEmpty(profession)){
            status = false;
            textInputLayout2.setError("Profession is required");
        }else{
            status = true;
            textInputLayout2.setError(null);
        }

        //Validate email
        if (TextUtils.isEmpty(email)){
            status = false;
            textInputLayout3.setError("Email is required");
        }else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            status = false;
            textInputLayout3.setError("Please enter a valid email address");
        }else{
            status = true;
            textInputLayout3.setError(null);
        }

        //Validate sex
        if (TextUtils.isEmpty(sex)){
            status = false;
            textInputLayout4.setError("Sex is required");
        }else{
            status = true;
            textInputLayout4.setError(null);
        }

        return status;
    }

    private void saveInformation(String name, String contact, String profession, String email, String sex) {
        pDialog = new ProgressDialog(PersonalInformation.this);
        pDialog.setCancelable(false);
        pDialog.setIndeterminate(false);
        pDialog.setMessage("Saving information...");
        pDialog.show();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getResources().getString(R.string.prefLoginState), "loggedin");
        editor.putString(getResources().getString(R.string.name), name);
        editor.putString(getResources().getString(R.string.contact), contact);
        editor.putString(getResources().getString(R.string.profession), profession);
        editor.putString(getResources().getString(R.string.email), email);
        editor.putString(getResources().getString(R.string.sex), sex);
        editor.apply();

        startActivity(new Intent(PersonalInformation.this, Home.class));
        finish();

        pDialog.dismiss();


    }



}