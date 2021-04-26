package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import java.util.Calendar;
import java.util.Date;
import android.widget.Spinner;
import android.widget.Toast;
import android.text.Editable;
import android.text.TextWatcher;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    DatePickerDialog picker;
    private EditText eText;
    private EditText profileName;
    private EditText validAge;
    private String idAs;
    private String seeking;
    private EditText occupation;
    private EditText description;
    private Button submitBtn;
    private Spinner spin1;
    private Spinner spin2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signinViews();
        eText=(EditText) findViewById(R.id.dobtext);
        spin1.setOnItemSelectedListener(this);
        spin2.setOnItemSelectedListener(this);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(this::onClick);
    }

    private void signinViews() {
        profileName = (EditText) findViewById(R.id.et_name);
        // TextWatcher would let us check validation error on the fly
        profileName.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(profileName);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        validAge = (EditText) findViewById(R.id.dobtext);
        // TextWatcher would let us check validation error on the fly
        validAge.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.isValidAge(validAge, getString(R.string.age_consent), true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        spin1 = (Spinner) findViewById(R.id.gender);
        spin2 = (Spinner) findViewById(R.id.gendertwo);
        if (spin1.getSelectedItem() != null) {
            idAs = spin1.getSelectedItem().toString();
            seeking = spin2.getSelectedItem().toString();
        }

        occupation = (EditText) findViewById(R.id.occupation);
        // TextWatcher would let us check validation error on the fly
        occupation.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(occupation);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        description = (EditText) findViewById(R.id.description);
        // TextWatcher would let us check validation error on the fly
        description.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(description);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        submitBtn = (Button) findViewById(R.id.btn_submit);
        submitBtn.setOnClickListener(view -> {
            /*
            Validation class will check the error and display the error on respective fields
            but it won't resist the form submission, so we need to check again before submit
             */
            if ( checkValidation() )
                goToSubmit(view);
            else
                Toast.makeText(MainActivity.this, getString(R.string.form_errors), Toast.LENGTH_LONG).show();
        });
    }

    private boolean checkValidation() {
        boolean ret = true;

        if (!Validation.hasText(profileName)) ret = false;
        if (!Validation.isValidAge(validAge, getString(R.string.age_consent), true)) ret = false;
        if (!Validation.hasText(validAge)) ret = false;
        if (!Validation.hasText(occupation)) ret = false;
        if (!Validation.hasText(description)) ret = false;

        return ret;
    }

    public void goToSubmit(View view) {
        Intent intent = new Intent(MainActivity.this, SignInActivity.class);
        intent.putExtra(Constants.PROFILE_NAME, profileName.getText().toString());
        intent.putExtra(Constants.AGE, Validation.calculateAge(validAge));
        intent.putExtra(Constants.ID_AS, idAs);
        intent.putExtra(Constants.SEEKING, seeking);
        intent.putExtra(Constants.OCCUPATION, occupation.getText().toString());
        intent.putExtra(Constants.ABOUT_ME, description.getText().toString());
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
            profileName.setText((String)savedInstanceState.get(Constants.TEXTVIEW_TEXT));
        }

        if (savedInstanceState.containsKey(Constants.BUTTON_TEXT)) {
            submitBtn.setText((String) savedInstanceState.get(Constants.BUTTON_TEXT));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.PROFILE_NAME, profileName.getText().toString());
        outState.putString(Constants.AGE, Validation.calculateAge(validAge));
        outState.putString(Constants.OCCUPATION, occupation.getText().toString());
        outState.putString(Constants.ABOUT_ME, description.getText().toString());
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

    private void onClick(View v) {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        picker = new DatePickerDialog(MainActivity.this,
                (view, year1, monthOfYear, dayOfMonth) ->
                        eText.setText(String.format(getString(R.string.month_day_year),
                                monthOfYear + 1, dayOfMonth, year1)), year, month, day);
        picker.getDatePicker().setMaxDate(new Date().getTime());
        picker.show();

        Spinner spin1 = (Spinner) findViewById(R.id.gender);
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.select_gender,
                        android.R.layout.simple_spinner_item);
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(staticAdapter);

        Spinner spin2 = (Spinner) findViewById(R.id.gendertwo);
        ArrayAdapter<CharSequence> staticAdapter2 = ArrayAdapter
                .createFromResource(this, R.array.select_gender,
                        android.R.layout.simple_spinner_item);
        staticAdapter2
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(staticAdapter2);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        if(adapterView.getId() == R.id.gender) {
            idAs = adapterView.getItemAtPosition(position).toString();
        }
        if(adapterView.getId() == R.id.gendertwo) {
            seeking = adapterView.getItemAtPosition(position).toString();
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
