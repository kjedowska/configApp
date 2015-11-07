package com.example.klaudia.configapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View generalSettingsBtn = findViewById(R.id.generalBtn);
        View resBtn = findViewById(R.id.resBtn);

        generalSettingsBtn.setOnClickListener(this);
        resBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.generalBtn:
                onGeneralSettingsSelected();
            case R.id.resBtn:
                onResBtnSelected();
        }
    }

    public boolean onGeneralSettingsSelected() {
        Intent intent = new Intent(this, GeneralSettingsActivity.class);
        startActivity(intent);
        return true;
    }

    public boolean onResBtnSelected() {
        Intent intent = new Intent(this, ResourcesActivity.class);
        startActivity(intent);
        return true;
    }
}
