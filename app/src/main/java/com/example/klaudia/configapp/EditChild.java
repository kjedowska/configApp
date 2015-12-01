package com.example.klaudia.configapp;

import android.content.DialogInterface;
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

import java.io.Serializable;
import java.util.ArrayList;

import br.com.thinkti.android.filechooser.FileChooser;

public class EditChild extends AppCompatActivity implements View.OnClickListener {
    String category;
    String id;
    DBAdapter db;
    Child child;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_child);

        Intent intent = getIntent();
        category = intent.getExtras().getString("category");
        id = intent.getExtras().getString("id");

        fillSpinner(findViewById(R.id.setSpinner), R.array.set_array);

        View addImageBtn = findViewById(R.id.addImageBtn);
        addImageBtn.setOnClickListener(this);
        View saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(this);

        db = new DBAdapter(getApplicationContext());
        db.openDB();

        fillFields();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addImageBtn:
                onAddImageBtnSelected();
                break;
            case R.id.saveBtn:
                onAddSaveBtnSelected();
                break;
        }
    }

    public void fillFields(){
        Mapper mapper = new Mapper();
        Spinner set = (Spinner)findViewById(R.id.setSpinner);
        EditText image = (EditText)findViewById(R.id.imagePath);

        child = db.getChild(id);
        set.setSelection(mapper.getSetId(child.getSet()));
        image.setText(child.getImage());
    }

    public boolean onAddSaveBtnSelected(){
        Child child = new Child();

        Spinner set = (Spinner)findViewById(R.id.setSpinner);
        EditText image = (EditText)findViewById(R.id.imagePath);

        child.setId(Integer.parseInt(id));
        child.setCategory(category);
        child.setSet(set.getSelectedItem().toString());
        child.setImage(image.getText().toString());
        try {
            db.editChild(child);
            Toast.makeText(this, "Edytowano obraz", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Toast.makeText(this, "Nie udało się.", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    private void fillSpinner(View view, @ArrayRes int arr) {
        Spinner spinner = (Spinner) view;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, arr,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public boolean onAddImageBtnSelected() {
        Intent intent = new Intent(this, FileChooser.class);
        ArrayList<String> extensions = new ArrayList<String>();
        extensions.add(".jpg");
        extensions.add(".jpeg");
        extensions.add(".gif");
        extensions.add(".png");
        extensions.add(".bmp");
        intent.putStringArrayListExtra("filterFileExtension", extensions);
        startActivityForResult(intent, 1);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == 1) && (resultCode == -1)) {
            String fileSelected = data.getStringExtra("fileSelected");
            EditText v = (EditText)findViewById(R.id.imagePath);
            v.setText(fileSelected);
            Toast.makeText(this, fileSelected, Toast.LENGTH_SHORT).show();
        }
    }
}
