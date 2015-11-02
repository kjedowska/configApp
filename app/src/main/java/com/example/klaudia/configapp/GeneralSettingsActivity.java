package com.example.klaudia.configapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class GeneralSettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private XmlHandler xmlHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_settings);
        String filePath = getFilesDir().getPath().toString() + "/config_app.xml";
        xmlHandler = new XmlHandler(new Configuration("noun"), new Configuration("verb"), filePath);

        fillSpinner(findViewById(R.id.nounHintType));
        fillSpinner(findViewById(R.id.verbHintType));
        View saveSettingsBtn = findViewById(R.id.saveBtn);
        saveSettingsBtn.setOnClickListener(this);
    }

    private void fillSpinner(View view) {
        Spinner spinner = (Spinner) view;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.hint_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private String getRadioGroupCheckedVal(View group) {
        RadioGroup rGroup = (RadioGroup)group;
        RadioButton checkedRadioButton = (RadioButton) rGroup.findViewById(rGroup.getCheckedRadioButtonId());
        if (checkedRadioButton.getText()=="@string/therapist")
           return "therapist";
        else return "auto";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveBtn:
                updateConfig();
                xmlHandler.serialize();
        }
    }

    public void updateConfig() {
        EditText nounDisplayCount = (EditText)findViewById(R.id.nounDisplayCount);
        EditText verbDisplayCount = (EditText)findViewById(R.id.verbDisplayCount);
        EditText nounResponseTime = (EditText)findViewById(R.id.nounResponseTime);
        EditText verbResponseTime = (EditText)findViewById(R.id.verbResponseTime);

        xmlHandler.nounConfig.setDisplayCount(Integer.parseInt(nounDisplayCount.getText().toString()));
        xmlHandler.nounConfig.setResponseTime(Integer.parseInt(nounResponseTime.getText().toString()));
        xmlHandler.verbConfig.setDisplayCount(Integer.parseInt(verbDisplayCount.getText().toString()));
        xmlHandler.verbConfig.setResponseTime(Integer.parseInt(verbResponseTime.getText().toString()));
        xmlHandler.nounConfig.setMode(getRadioGroupCheckedVal(findViewById(R.id.RadioGroupNounMode)));
        xmlHandler.verbConfig.setMode(getRadioGroupCheckedVal(findViewById(R.id.RadioGroupVerbMode)));
    }

}
