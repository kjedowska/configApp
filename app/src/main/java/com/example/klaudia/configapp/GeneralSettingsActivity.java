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

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class GeneralSettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private XmlHandler xmlHandler;
    Mapper mapper = new Mapper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_settings);
        String filePath = getFilesDir().getPath().toString() + "/config_app.xml";
        xmlHandler = new XmlHandler(new Configuration("noun"), new Configuration("verb"), filePath);
        try {
            xmlHandler.parse();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        fillSpinner(findViewById(R.id.nounHintType));
        fillSpinner(findViewById(R.id.verbHintType));
        View saveSettingsBtn = findViewById(R.id.saveBtn);
        saveSettingsBtn.setOnClickListener(this);

        updateGUI();
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
        if (checkedRadioButton.getText()=="terapeuty")
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
        Spinner nounHintType = (Spinner)findViewById(R.id.nounHintType);
        Spinner verbHintType = (Spinner)findViewById(R.id.verbHintType);

        xmlHandler.nounConfig.setDisplayCount(Integer.parseInt(nounDisplayCount.getText().toString()));
        xmlHandler.verbConfig.setDisplayCount(Integer.parseInt(verbDisplayCount.getText().toString()));

        xmlHandler.nounConfig.setResponseTime(Integer.parseInt(nounResponseTime.getText().toString()));
        xmlHandler.verbConfig.setResponseTime(Integer.parseInt(verbResponseTime.getText().toString()));

        xmlHandler.nounConfig.setMode(getRadioGroupCheckedVal(findViewById(R.id.RadioGroupNounMode)));
        xmlHandler.verbConfig.setMode(getRadioGroupCheckedVal(findViewById(R.id.RadioGroupVerbMode)));

        xmlHandler.nounConfig.setHintType(mapper.getHint(nounHintType.getSelectedItem().toString()));
        xmlHandler.verbConfig.setHintType(mapper.getHint(verbHintType.getSelectedItem().toString()));
    }

    private void updateGUI() {
        EditText nounDisplayCount = (EditText)findViewById(R.id.nounDisplayCount);
        EditText verbDisplayCount = (EditText)findViewById(R.id.verbDisplayCount);
        EditText nounResponseTime = (EditText)findViewById(R.id.nounResponseTime);
        EditText verbResponseTime = (EditText)findViewById(R.id.verbResponseTime);
        RadioGroup nounRg = (RadioGroup)findViewById(R.id.RadioGroupNounMode);
        RadioGroup verbRg = (RadioGroup)findViewById(R.id.RadioGroupVerbMode);
        Spinner nounHintType = (Spinner)findViewById(R.id.nounHintType);
        Spinner verbHintType = (Spinner)findViewById(R.id.verbHintType);

        nounDisplayCount.setText("" + xmlHandler.nounConfig.getDisplayCount());
        verbDisplayCount.setText("" + xmlHandler.verbConfig.getDisplayCount());
        nounResponseTime.setText("" + xmlHandler.nounConfig.getResponseTime());
        verbResponseTime.setText("" + xmlHandler.verbConfig.getResponseTime());

        if ("auto".equals(xmlHandler.nounConfig.getMode()))
            nounRg.check(findViewById(R.id.autoR).getId());
        else if ("therapist".equals(xmlHandler.nounConfig.getMode()))
            nounRg.check(findViewById(R.id.therapistR).getId());

        if ("auto".equals(xmlHandler.verbConfig.getMode()))
            verbRg.check(findViewById(R.id.autoC).getId());
        else if ("therapist".equals(xmlHandler.verbConfig.getMode()))
            verbRg.check(findViewById(R.id.therapistC).getId());

        nounHintType.setSelection(mapper.getHintId(xmlHandler.nounConfig.getHintType()));
        verbHintType.setSelection(mapper.getHintId(xmlHandler.verbConfig.getHintType()));

    }
}
