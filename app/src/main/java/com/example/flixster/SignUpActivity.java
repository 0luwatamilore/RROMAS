package com.example.flixster;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;


public class SignUpActivity extends AppCompatActivity {

    private TextInputLayout firstNameInputLayout, lastNameInputLayout;
    private TextInputLayout phoneNumberInputLayout, emailInputLayout, passwordInputLayout;
    private EditText firstNameEditText, lastNameEditText, phoneNumberEditText;
    private EditText emailEditText, passwordEditText, dobEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firstNameInputLayout = findViewById(R.id.first_name_sign_up_input_layout);
        firstNameEditText = findViewById(R.id.first_name_editText);
        lastNameInputLayout = findViewById(R.id.last_name_sign_up_input_layout);
        lastNameEditText = findViewById(R.id.last_name_editText);
        phoneNumberInputLayout = findViewById(R.id.phone_number_sign_up_input_layout);
        phoneNumberEditText = findViewById(R.id.phone_number_editText);
        emailInputLayout = findViewById(R.id.email_sign_up_input_layout);
        emailEditText = findViewById(R.id.email_editText);
        passwordInputLayout = findViewById(R.id.password_sign_up_login_input_layout);
        passwordEditText = findViewById(R.id.password_editText);
        dobEditText = findViewById(R.id.dob_editText);
        // check if first name is valid
        firstNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                String firstname = editable.toString();
                // Log to check if the listener is triggered
                Log.d("SignUpActivity", "first name changed: " + firstname);
                if (firstname.isEmpty()) {
                    firstNameInputLayout.setError(null);
                    return;
                }
                if (!firstname.matches("^[a-zA-z]+$")) {
                    firstNameInputLayout.setError("Invalid format");
                } else {
                    firstNameInputLayout.setError(null);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        });
        // check if first name is valid
        lastNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                String lastName = editable.toString();
                // Log to check if the listener is triggered
                Log.d("SignUpActivity", "last name changed: " + lastName);
                if (lastName.isEmpty()) {
                    lastNameInputLayout.setError(null);
                    return;
                }
                if (!lastName.matches("^[a-zA-z]+$")) {
                    lastNameInputLayout.setError("Invalid format");
                } else {
                    lastNameInputLayout.setError(null);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        });
        // check if phone number is valid
        phoneNumberEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                String phoneNumber = editable.toString();
                // Log to check if the listener is triggered
                Log.d("SignUpActivity", "Phone number changed: " + phoneNumber);
                if (phoneNumber.isEmpty()) {
                    phoneNumberInputLayout.setError(null);
                    return;
                }
                if (!phoneNumber.matches("^[1-9][0-9]+$")) {
                    phoneNumberInputLayout.setError("Invalid format digits only");
                } else {
                    phoneNumberInputLayout.setError(null);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        });
        // check if email is valid
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                String email = editable.toString();
                // Log to check if the listener is triggered
                Log.d("SignUpActivity", "email changed: " + email);
                if (email.isEmpty()) {
                    emailInputLayout.setError(null);
                    return;
                }
                if (!email.matches("^[A-Za-z0-9._]+\\@[a-zA-Z]+(\\.[a-zA-z]+)*(\\.com|\\.edu)$")) {
                    emailInputLayout.setError("Invalid format");
                } else {
                    emailInputLayout.setError(null);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
        });
    }

}