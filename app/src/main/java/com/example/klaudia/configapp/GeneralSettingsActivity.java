package com.example.klaudia.configapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class GeneralSettingsActivity extends AppCompatActivity {

    private XmlHandler xmlHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_settings);

        setRadioGroupListeners();
        xmlHandler = new XmlHandler(new Configuration("noun"), new Configuration("verb"), "config_app.xml");
    }

    private void setRadioGroupListeners() {
        RadioGroup verbGroup = (RadioGroup)findViewById(R.id.RadioGroupCzasownikiMode);
        // This overrides the radiogroup onCheckListener
        verbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup rGroup, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton) rGroup.findViewById(checkedId);
                boolean isChecked = checkedRadioButton.isChecked();
                if (isChecked) {
                    if (checkedRadioButton.getText()=="@string/therapist") xmlHandler.verbConfig.setMode("therapist");
                    else xmlHandler.nounConfig.setMode("auto");
                }
            }
        });

        RadioGroup nounGroup = (RadioGroup)findViewById(R.id.RadioGroupRzeczownikiMode);
        // This overrides the radiogroup onCheckListener
        nounGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup rGroup, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton) rGroup.findViewById(checkedId);
                boolean isChecked = checkedRadioButton.isChecked();
                if (isChecked) {
                    if (checkedRadioButton.getText()=="@string/therapist") xmlHandler.nounConfig.setMode("therapist");
                    else xmlHandler.nounConfig.setMode("auto");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_general_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
