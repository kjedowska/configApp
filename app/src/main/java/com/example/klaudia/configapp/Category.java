package com.example.klaudia.configapp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Klaudia on 2015-11-15.
 */
public class Category {
    private String name = "";
    private String status = "";
    private String audio1 = "";
    private String audio2 = "";
    private List<Child> children;

    public String getAudio1() {
        return audio1;
    }

    public void setAudio1(String audio1) {
        this.audio1 = audio1;
    }

    public String getAudio2() {
        return audio2;
    }

    public void setAudio2(String audio2) {
        this.audio2 = audio2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public void addNode(Child child) {
        this.children.add(child);
    }

    public void removeNode(Child child) {
        this.children.remove(child);
    }
}
