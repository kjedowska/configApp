package com.example.klaudia.configapp;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;

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

    public Configuration getConfiguration(String forr){
        if ("noun".equals(forr))
            return nounConfig;
        else if ("verb".equals(forr))
            return verbConfig;
        return new Configuration();
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
            xmlSerializer.text(String.valueOf(nounConfig.getMode()));
            xmlSerializer.endTag(null, "mode");
            xmlSerializer.startTag(null,"displayCount");
            xmlSerializer.text(String.valueOf(nounConfig.getDisplayCount()));
            xmlSerializer.endTag(null, "displayCount");
            xmlSerializer.startTag(null, "responseTime");
            xmlSerializer.text(String.valueOf(nounConfig.getResponseTime()));
            xmlSerializer.endTag(null, "responseTime");
            xmlSerializer.startTag(null, "hintType");
            xmlSerializer.text(String.valueOf(nounConfig.getHintType()));
            xmlSerializer.endTag(null, "hintType");
            xmlSerializer.endTag(null, "configuration");

            xmlSerializer.startTag(null, "configuration");
            xmlSerializer.startTag(null, "mode");
            xmlSerializer.text(String.valueOf(verbConfig.getMode()));
            xmlSerializer.endTag(null, "mode");
            xmlSerializer.startTag(null,"displayCount");
            xmlSerializer.text(String.valueOf(verbConfig.getDisplayCount()));
            xmlSerializer.endTag(null, "displayCount");
            xmlSerializer.startTag(null, "responseTime");
            xmlSerializer.text(String.valueOf(verbConfig.getResponseTime()));
            xmlSerializer.endTag(null, "responseTime");
            xmlSerializer.startTag(null, "hintType");
            xmlSerializer.text(String.valueOf(verbConfig.getHintType()));
            xmlSerializer.endTag(null, "hintType");
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

    public void parse() throws IOException, XmlPullParserException {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();

        URL input = new URL(path);
        xpp.setInput(input.openStream(), null);

        int eventType = xpp.getEventType();
        String currentTag = null;
        String forr = null;
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {
                currentTag = xpp.getName();
            } else if (eventType == XmlPullParser.TEXT) {
                if ("configuration".equals(currentTag)){
                    forr = xpp.getAttributeValue(null, "for");
                }
                if ("mode".equals(currentTag)){
                    getConfiguration(forr).setMode(xpp.getText());
                }
                if ("displayCount".equals(currentTag)){
                    getConfiguration(forr).setDisplayCount(Integer.parseInt(xpp.getText()));
                }
                if ("responseTime".equals(currentTag)){
                    getConfiguration(forr).setResponseTime(Integer.parseInt(xpp.getText()));
                }
                if ("hintType".equals(currentTag)){
                    getConfiguration(forr).setHintType(xpp.getText());
                }
            }
            eventType = xpp.next();
        }
    }
}
