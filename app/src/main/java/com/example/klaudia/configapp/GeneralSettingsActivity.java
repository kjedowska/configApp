package com.example.klaudia.configapp;

import android.content.Intent;
import android.support.annotation.ArrayRes;
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
    private Storage storage;
    public Configuration nounConfig;
    public Configuration verbConfig;

    Mapper mapper = new Mapper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_settings);

        nounConfig = new Configuration("noun");
        verbConfig = new Configuration("verb");
        // String filePath = getFilesDir().getPath().toString() + "/config_app.xml";
        // xmlHandler = new XmlHandler(nounConfig, verbConfig, filePath);
        storage = new Storage(this, nounConfig, verbConfig);

//        try {
//            xmlHandler.parse();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (XmlPullParserException e) {
//            e.printStackTrace();
//        }

        storage.read();

        fillSpinner(findViewById(R.id.nounHintType), R.array.hint_array);
        fillSpinner(findViewById(R.id.verbHintType), R.array.hint_array);
        fillSpinner(findViewById(R.id.nounDisplayCount), R.array.display_array);
        fillSpinner(findViewById(R.id.verbDisplayCount), R.array.display_array);
        fillSpinner(findViewById(R.id.nounLevel), R.array.level_array);
        fillSpinner(findViewById(R.id.verbLevel), R.array.level_array);
        fillSpinner(findViewById(R.id.nounProportions), R.array.proportions_array);
        fillSpinner(findViewById(R.id.verbProportions), R.array.proportions_array);
        View saveSettingsBtn = findViewById(R.id.saveBtn);
        saveSettingsBtn.setOnClickListener(this);

        updateGUI();
    }

    private void fillSpinner(View view, @ArrayRes int arr) {
        Spinner spinner = (Spinner) view;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, arr,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private String getRadioGroupCheckedVal(View group) {
        RadioGroup rGroup = (RadioGroup)group;
        RadioButton checkedRadioButton = (RadioButton) rGroup.findViewById(rGroup.getCheckedRadioButtonId());
        if ("terapeuty".equals(checkedRadioButton.getText().toString()))
           return "therapist";
        else return "auto";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveBtn:
                updateConfig();
                //xmlHandler.serialize();
                storage.save();
        }
    }

    public void updateConfig() {
        Spinner nounDisplayCount = (Spinner)findViewById(R.id.nounDisplayCount);
        Spinner verbDisplayCount = (Spinner)findViewById(R.id.verbDisplayCount);
        EditText nounResponseTime = (EditText)findViewById(R.id.nounResponseTime);
        EditText verbResponseTime = (EditText)findViewById(R.id.verbResponseTime);
        Spinner nounHintType = (Spinner)findViewById(R.id.nounHintType);
        Spinner verbHintType = (Spinner)findViewById(R.id.verbHintType);
        Spinner nounLevel = (Spinner)findViewById(R.id.nounLevel);
        Spinner verbLevel = (Spinner)findViewById(R.id.verbLevel);
        Spinner nounProportions = (Spinner)findViewById(R.id.nounProportions);
        Spinner verbProportions = (Spinner)findViewById(R.id.verbProportions);

//        xmlHandler.nounConfig.setDisplayCount(Integer.parseInt(nounDisplayCount.getText().toString()));
//        xmlHandler.verbConfig.setDisplayCount(Integer.parseInt(verbDisplayCount.getText().toString()));
//
//        xmlHandler.nounConfig.setResponseTime(Integer.parseInt(nounResponseTime.getText().toString()));
//        xmlHandler.verbConfig.setResponseTime(Integer.parseInt(verbResponseTime.getText().toString()));
//
//        xmlHandler.nounConfig.setMode(getRadioGroupCheckedVal(findViewById(R.id.RadioGroupNounMode)));
//        xmlHandler.verbConfig.setMode(getRadioGroupCheckedVal(findViewById(R.id.RadioGroupVerbMode)));
//
//        xmlHandler.nounConfig.setHintType(mapper.getHint(nounHintType.getSelectedItem().toString()));
//        xmlHandler.verbConfig.setHintType(mapper.getHint(verbHintType.getSelectedItem().toString()));

        nounConfig.setDisplayCount(Integer.parseInt(nounDisplayCount.getSelectedItem().toString()));
        verbConfig.setDisplayCount(Integer.parseInt(verbDisplayCount.getSelectedItem().toString()));

        nounConfig.setResponseTime(Integer.parseInt(nounResponseTime.getText().toString()));
        verbConfig.setResponseTime(Integer.parseInt(verbResponseTime.getText().toString()));

        nounConfig.setMode(getRadioGroupCheckedVal(findViewById(R.id.RadioGroupNounMode)));
        verbConfig.setMode(getRadioGroupCheckedVal(findViewById(R.id.RadioGroupVerbMode)));

        nounConfig.setHintType(mapper.getHint(nounHintType.getSelectedItem().toString()));
        verbConfig.setHintType(mapper.getHint(verbHintType.getSelectedItem().toString()));

        nounConfig.setLevel(nounLevel.getSelectedItem().toString());
        verbConfig.setLevel(verbLevel.getSelectedItem().toString());
    }

    private void updateGUI() {
        Spinner nounDisplayCount = (Spinner)findViewById(R.id.nounDisplayCount);
        Spinner verbDisplayCount = (Spinner)findViewById(R.id.verbDisplayCount);
        EditText nounResponseTime = (EditText)findViewById(R.id.nounResponseTime);
        EditText verbResponseTime = (EditText)findViewById(R.id.verbResponseTime);
        RadioGroup nounRg = (RadioGroup)findViewById(R.id.RadioGroupNounMode);
        RadioGroup verbRg = (RadioGroup)findViewById(R.id.RadioGroupVerbMode);
        Spinner nounHintType = (Spinner)findViewById(R.id.nounHintType);
        Spinner verbHintType = (Spinner)findViewById(R.id.verbHintType);
        Spinner nounLevel = (Spinner)findViewById(R.id.nounLevel);
        Spinner verbLevel = (Spinner)findViewById(R.id.verbLevel);
        Spinner nounProportions = (Spinner)findViewById(R.id.nounProportions);
        Spinner verbProportions = (Spinner)findViewById(R.id.verbProportions);

//        nounDisplayCount.setText("" + xmlHandler.nounConfig.getDisplayCount());
//        verbDisplayCount.setText("" + xmlHandler.verbConfig.getDisplayCount());
//        nounResponseTime.setText("" + xmlHandler.nounConfig.getResponseTime());
//        verbResponseTime.setText("" + xmlHandler.verbConfig.getResponseTime());
//
//        if ("auto".equals(xmlHandler.nounConfig.getMode()))
//            nounRg.check(findViewById(R.id.autoR).getId());
//        else if ("therapist".equals(xmlHandler.nounConfig.getMode()))
//            nounRg.check(findViewById(R.id.therapistR).getId());
//
//        if ("auto".equals(xmlHandler.verbConfig.getMode()))
//            verbRg.check(findViewById(R.id.autoC).getId());
//        else if ("therapist".equals(xmlHandler.verbConfig.getMode()))
//            verbRg.check(findViewById(R.id.therapistC).getId());
//
//        nounHintType.setSelection(mapper.getHintId(xmlHandler.nounConfig.getHintType()));
//        verbHintType.setSelection(mapper.getHintId(xmlHandler.verbConfig.getHintType()));

        nounDisplayCount.setSelection(mapper.getDisplayId(nounConfig.getDisplayCount()));
        verbDisplayCount.setSelection(mapper.getDisplayId(verbConfig.getDisplayCount()));
        nounResponseTime.setText("" + nounConfig.getResponseTime());
        verbResponseTime.setText("" + verbConfig.getResponseTime());

        if ("auto".equals(nounConfig.getMode()))
            nounRg.check(findViewById(R.id.autoR).getId());
        else if ("therapist".equals(nounConfig.getMode()))
            nounRg.check(findViewById(R.id.therapistR).getId());

        if ("auto".equals(verbConfig.getMode()))
            verbRg.check(findViewById(R.id.autoC).getId());
        else if ("therapist".equals(verbConfig.getMode()))
            verbRg.check(findViewById(R.id.therapistC).getId());

        nounHintType.setSelection(mapper.getHintId(nounConfig.getHintType()));
        verbHintType.setSelection(mapper.getHintId(verbConfig.getHintType()));

        nounLevel.setSelection(mapper.getLevelId(nounConfig.getLevel()));
        verbLevel.setSelection(mapper.getLevelId(verbConfig.getLevel()));

        nounProportions.setSelection(mapper.getProportionsId(nounConfig.getProportions()));
        verbProportions.setSelection(mapper.getProportionsId(verbConfig.getProportions()));

    }
}
