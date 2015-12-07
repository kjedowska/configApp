package com.example.klaudia.configapp;

import android.support.annotation.ArrayRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.Map;

public class GeneralSettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private PreferencesManager preferencesManager;
    public Configuration config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_settings);

        config = new Configuration();
        preferencesManager = new PreferencesManager(this, config);
        preferencesManager.read();

        fillSpinner(findViewById(R.id.hintType), R.array.hint_array);
        fillSpinner(findViewById(R.id.displayCount), R.array.display_array);
        fillSpinner(findViewById(R.id.level), R.array.level_array);
        fillSpinner(findViewById(R.id.proportions), R.array.proportions_array);
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
                preferencesManager.save();
        }
    }

    public void updateConfig() {
        Mapper mapper = Mapper.getInstance();
        Spinner displayCount = (Spinner)findViewById(R.id.displayCount);
        EditText responseTime = (EditText)findViewById(R.id.responseTime);
        Spinner hintType = (Spinner)findViewById(R.id.hintType);
        Spinner level = (Spinner)findViewById(R.id.level);
        Spinner proportions = (Spinner)findViewById(R.id.proportions);

        config.setDisplayCount(Integer.parseInt(displayCount.getSelectedItem().toString()));
        config.setResponseTime(Integer.parseInt(responseTime.getText().toString()));
        config.setMode(getRadioGroupCheckedVal(findViewById(R.id.radioGroupMode)));
        config.setHintType(mapper.getHint(hintType.getSelectedItem().toString()));
        config.setLevel(level.getSelectedItem().toString());
        config.setProportions(proportions.getSelectedItem().toString());
    }

    private void updateGUI() {
        Mapper mapper = Mapper.getInstance();
        Spinner displayCount = (Spinner)findViewById(R.id.displayCount);
        EditText responseTime = (EditText)findViewById(R.id.responseTime);
        RadioGroup rg = (RadioGroup)findViewById(R.id.radioGroupMode);
        Spinner hintType = (Spinner)findViewById(R.id.hintType);
        Spinner level = (Spinner)findViewById(R.id.level);
        Spinner proportions = (Spinner)findViewById(R.id.proportions);

        displayCount.setSelection(mapper.getDisplayId(config.getDisplayCount()));
        responseTime.setText("" + config.getResponseTime());

        if ("auto".equals(config.getMode()))
            rg.check(findViewById(R.id.auto).getId());
        else if ("therapist".equals(config.getMode()))
            rg.check(findViewById(R.id.therapist).getId());

        hintType.setSelection(mapper.getHintId(config.getHintType()));
        level.setSelection(mapper.getLevelId(config.getLevel()));
        proportions.setSelection(mapper.getProportionsId(config.getProportions()));

    }
}
