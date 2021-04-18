package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.Calendar;
import android.widget.Toast;
import android.text.Editable;
import android.text.TextWatcher;

public class MainActivity extends AppCompatActivity {
    DatePickerDialog picker;
    private EditText eText;
    private EditText userText;
    private EditText fullName;
    private EditText etEmail;
    private EditText validAge;
    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signinViews();
        userText = findViewById(R.id.et_user);

        eText=(EditText) findViewById(R.id.dobtext);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(v -> {
            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);
            // date picker dialog
            picker = new DatePickerDialog(MainActivity.this,
                    (view, year1, monthOfYear, dayOfMonth) ->
                            eText.setText(String.format(getString(R.string.month_day_year),
                                    monthOfYear + 1, dayOfMonth, year1)), year, month, day);
            picker.show();
        });
    }

    private void signinViews() {
        fullName = (EditText) findViewById(R.id.et_name);
        // TextWatcher would let us check validation error on the fly
        fullName.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(fullName);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        etEmail = (EditText) findViewById(R.id.et_email);
        etEmail.addTextChangedListener(new TextWatcher() {
            // after every change has been made to this editText, we would like to check validity
            public void afterTextChanged(Editable s) {
                Validation.isEmailAddress(etEmail, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        userText = (EditText) findViewById(R.id.et_user);
        // TextWatcher would let us check validation error on the fly
        userText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(userText);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        validAge = (EditText) findViewById(R.id.dobtext);
        // TextWatcher would let us check validation error on the fly
        validAge.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.isValidAge(validAge, "Must be 18 or older", true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        submitBtn = (Button) findViewById(R.id.btn_submit);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Validation class will check the error and display the error on respective fields
                but it won't resist the form submission, so we need to check again before submit
                 */
                if ( checkValidation() )
                    goToSubmit(view);
                else
                    Toast.makeText(MainActivity.this, "Form contains errors", Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean checkValidation() {
        boolean ret = true;

        if (!Validation.hasText(fullName)) ret = false;
        if (!Validation.isEmailAddress(etEmail, true)) ret = false;
        if (!Validation.hasText(userText)) ret = false;
        if (!Validation.isValidAge(validAge, "Must be 18 or older", true)) ret = false;

        return ret;
    }

    public void goToSubmit(View view) {
        Intent intent = new Intent(MainActivity.this, SignInActivity.class);
        intent.putExtra(Constants.USER_NAME, userText.getText().toString());
        startActivity(intent);
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey(Constants.TEXTVIEW_TEXT)) {
            userText.setText((String)savedInstanceState.get(Constants.TEXTVIEW_TEXT));
        }

        if (savedInstanceState.containsKey(Constants.BUTTON_TEXT)) {
            submitBtn.setText((String) savedInstanceState.get(Constants.BUTTON_TEXT));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.USER_NAME, userText.getText().toString());
        outState.putString(Constants.BUTTON_TEXT, submitBtn.getText().toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}