package com.example.klaudia.configapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.thinkti.android.filechooser.FileChooser;

public class AddCategory extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        View addAudio1Btn = findViewById(R.id.addAudioBtn);
        addAudio1Btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addAudioBtn:
                onAddAudioBtnSelected();
        }
    }

    public boolean onAddAudioBtnSelected() {
        Intent intent = new Intent(this, FileChooser.class);
        ArrayList<String> extensions = new ArrayList<String>();
        extensions.add(".mp3");
        intent.putStringArrayListExtra("filterFileExtension", extensions);
        startActivityForResult(intent, 1);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == 1) && (resultCode == -1)) {
            String fileSelected = data.getStringExtra("fileSelected");
            Toast.makeText(this, fileSelected, Toast.LENGTH_SHORT).show();
        }
    }
}
