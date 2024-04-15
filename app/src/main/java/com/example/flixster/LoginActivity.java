package com.example.flixster;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout emailInputLayout,passwordInputLayout;
    private EditText emailEditText,passwordEditText;
    MySQLConnectionClass connectionClass;
    Connection con;
    ResultSet rs;
    String name, str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailInputLayout = findViewById(R.id.email_login_input_layout);
        emailEditText = findViewById(R.id.email_editText);
        passwordInputLayout = findViewById(R.id.password_login_input_layout);
        passwordEditText = findViewById(R.id.password_editText);

        // check if email is in valid format
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if (verifyEmail(emailEditText.getText().toString())){
                    emailInputLayout.setError(null);
                } else{
                    emailInputLayout.setError("invalid format");
                }
            }
        });
    }



    public boolean verifyEmail(String email) {
        String emailRegex = "^[A-Za-z0-9._]+\\@[a-zA-Z]+(\\.[a-zA-z]+)*(\\.com|\\.edu)$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(email);
        return emailMatcher.find();
    }

    public void loginToSignUpBtnClick(View view) {
        Intent loginToSignUpIntent = new Intent(this, SignUpActivity.class);
        startActivity(loginToSignUpIntent);
    }

    public void loginBtnclick(View view) {}
}