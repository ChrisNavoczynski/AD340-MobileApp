package com.example.helloworld;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;

public class SignInActivity extends AppCompatActivity {
    private TextView textView;
    private Button goBackBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        textView = findViewById(R.id.SignedIn);
        goBackBtn = (Button) findViewById(R.id.goback);

        StringBuilder hello = new StringBuilder("Thanks for Signing Up, ");
        Intent intent = getIntent();
        Bundle uName = intent.getExtras();

        String username =  "Default";

        if (uName != null) {
            if (uName.containsKey(Constants.USER_NAME)) {
                username = uName.getString(Constants.USER_NAME);
            }
        }
        hello.append(username).append("!\n");
        textView.setText(hello);

        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
