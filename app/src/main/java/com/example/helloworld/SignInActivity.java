package com.example.helloworld;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;

public class SignInActivity extends AppCompatActivity {
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private Button goBackBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        textView1 = findViewById(R.id.SignedIn);
        textView2 = findViewById(R.id.age);
        textView3 = findViewById(R.id.identified_as);
        textView4 = findViewById(R.id.seeking);
        textView5 = findViewById(R.id.occupation);
        textView6 = findViewById(R.id.about);
        goBackBtn = (Button) findViewById(R.id.goback);

        StringBuilder input1 = new StringBuilder(getString(R.string.thanks_signed_in));
        StringBuilder input2 = new StringBuilder(getString(R.string.show_age));
        StringBuilder input3 = new StringBuilder(getString(R.string.identify_as));
        StringBuilder input4 = new StringBuilder(getString(R.string.seeking_id));
        StringBuilder input5 = new StringBuilder(getString(R.string.occupation_stat));
        StringBuilder input6 = new StringBuilder();
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        String pName=getString(R.string.default_name);
        String pAge=getString(R.string.default_age);
        String pIdAs=getString(R.string.default_ider);
        String pSeekId=getString(R.string.default_seek);
        String pJob=getString(R.string.default_job);
        String pAbout=getString(R.string.default_about);

        if (b != null) {
            if (b.containsKey(Constants.PROFILE_NAME)) {
                pName = b.getString(Constants.PROFILE_NAME);
            }
            if (b.containsKey(Constants.AGE)) {
                pAge = b.getString(Constants.AGE);
            }
            if (b.containsKey(Constants.ID_AS)) {
                pIdAs = b.getString(Constants.ID_AS);
            }
            if (b.containsKey(Constants.SEEKING)) {
                pSeekId=b.getString(Constants.SEEKING);
            }
            if (b.containsKey(Constants.OCCUPATION)) {
                pJob = b.getString(Constants.OCCUPATION);
            }
            if (b.containsKey(Constants.ABOUT_ME)) {
                pAbout = b.getString(Constants.ABOUT_ME);
            }
        }
        input1.append(pName).append("!\n");
        input2.append(pAge).append("\n");
        input3.append(pIdAs).append(" ");
        input4.append(pSeekId).append("\n");
        input5.append(pJob).append("\n");
        input6.append(pAbout).append("\n");

        textView1.setText(input1);
        textView2.setText(input2);
        textView3.setText(input3);
        textView4.setText(input4);
        textView5.setText(input5);
        textView6.setText(input6);

        goBackBtn.setOnClickListener(view -> finish());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
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
