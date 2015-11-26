package com.example.klaudia.configapp;

import android.content.Intent;
import android.support.annotation.ArrayRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.thinkti.android.filechooser.FileChooser;

public class AddCategory extends AppCompatActivity implements View.OnClickListener {

    private EditText chosenView;
   // DBAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        Intent intent = getIntent();
        //db = (DBAdapter) intent.getSerializableExtra("db");

        fillSpinner(findViewById(R.id.statusSpinner), R.array.status_array);

        View addAudio1Btn = findViewById(R.id.addAudioBtn);
        addAudio1Btn.setOnClickListener(this);
        View addAudio2Btn = findViewById(R.id.addAudio2Btn);
        addAudio2Btn.setOnClickListener(this);
        View saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addAudioBtn:
                chosenView =  (EditText)findViewById(R.id.catAudio1Path);
                onAddAudioBtnSelected();
            case R.id.addAudio2Btn:
                chosenView =  (EditText)findViewById(R.id.catAudio2Path);
                onAddAudioBtnSelected();
            case R.id.saveBtn:
                onAddSaveBtnSelected();
        }
    }

    public boolean onAddSaveBtnSelected(){
        Category cat = new Category();
        DBAdapter db = new DBAdapter(getApplicationContext());
        db.openDB();

        EditText name = (EditText)findViewById(R.id.catNameEditText);
        Spinner status = (Spinner)findViewById(R.id.statusSpinner);
        EditText audio1 = (EditText)findViewById(R.id.catAudio1Path);
        EditText audio2 = (EditText)findViewById(R.id.catAudio2Path);

        cat.setName(name.getText().toString());
        cat.setStatus(status.getSelectedItem().toString());
        cat.setAudio1(audio1.getText().toString());
        cat.setAudio2(audio2.getText().toString());

        db.addCategory(cat);
        Toast.makeText(this, "Dodano kategorię", Toast.LENGTH_SHORT).show();
        return true;
    }

    private void fillSpinner(View view, @ArrayRes int arr) {
        Spinner spinner = (Spinner) view;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, arr,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
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
            chosenView.setText(fileSelected);
            Toast.makeText(this, fileSelected, Toast.LENGTH_SHORT).show();
        }
    }
}
