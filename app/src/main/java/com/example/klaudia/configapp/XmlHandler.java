package com.example.klaudia.configapp;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * Created by Klaudia on 2015-10-27.
 */
public class XmlHandler {
    public Configuration nounConfig;
    public Configuration verbConfig;
    private String path;

    XmlHandler(Configuration noun, Configuration verb, String path) {
        this.nounConfig = noun;
        this.verbConfig = verb;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Configuration getNounConfig() {
        return nounConfig;
    }

    public void setNounConfig(Configuration nounConfig) {
        this.nounConfig = nounConfig;
    }

    public Configuration getVerbConfig() {
        return verbConfig;
    }

    public void setVerbConfig(Configuration verbConfig) {
        this.verbConfig = verbConfig;
    }

    public void serialize(){
        try {
            File newxmlfile = new File(path);
            newxmlfile.createNewFile();
            FileOutputStream fileos = null;
            fileos = new FileOutputStream(newxmlfile);

            XmlSerializer xmlSerializer = Xml.newSerializer();
            StringWriter writer = new StringWriter();
            xmlSerializer.setOutput(writer);
            xmlSerializer.startDocument("UTF-8", true);
            xmlSerializer.startTag(null, "configs");
            xmlSerializer.startTag(null, "configuration");
            xmlSerializer.startTag(null, "mode");
            xmlSerializer.text(nounConfig.getMode());
            xmlSerializer.endTag(null, "mode");
            xmlSerializer.startTag(null,"displayCount");
            xmlSerializer.text(String.valueOf(nounConfig.getDisplayCount()));
            xmlSerializer.endTag(null, "displayCount");
            xmlSerializer.startTag(null, "responseTime");
            xmlSerializer.text(String.valueOf(nounConfig.getResponseTime()));
            xmlSerializer.endTag(null, "responseTime");
            xmlSerializer.endTag(null, "configuration");

            xmlSerializer.startTag(null, "configuration");
            xmlSerializer.startTag(null, "mode");
            xmlSerializer.text(verbConfig.getMode());
            xmlSerializer.endTag(null, "mode");
            xmlSerializer.startTag(null,"displayCount");
            xmlSerializer.text(String.valueOf(verbConfig.getDisplayCount()));
            xmlSerializer.endTag(null, "displayCount");
            xmlSerializer.startTag(null, "responseTime");
            xmlSerializer.text(String.valueOf(verbConfig.getResponseTime()));
            xmlSerializer.endTag(null, "responseTime");
            xmlSerializer.endTag(null, "configuration");
            xmlSerializer.endTag(null, "configs");
            xmlSerializer.endDocument();
            xmlSerializer.flush();
            String dataWrite = writer.toString();
            fileos.write(dataWrite.getBytes());
            fileos.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parse() {
        //InputStream is = getResources().openRawResource(R.raw.test);
    }
}
