package com.example.klaudia.configapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResourcesActivity extends Activity implements View.OnClickListener  {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listCategories;
    HashMap<String, List<String>> listChildren;
    DBAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        initDB();
        fillLists();

        listAdapter = new ExpandableListAdapter(this, listCategories, listChildren);
        expListView = (ExpandableListView) findViewById(R.id.list1);
        expListView.setAdapter(listAdapter);
        registerForContextMenu(expListView);

        View addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(this);
    }

    void initDB() {
        db = new DBAdapter(getApplicationContext());
        db.openDB();
    }

    void fillLists() {
        List<Category> categories;
        listCategories = new ArrayList<String>();
        listChildren = new HashMap<String, List<String>>();

        categories = db.getCategories();

        for (Category c: categories) {
            listCategories.add(c.getName());
            c.setChildren(db.getChildrenFromCategory(c.getName()));
            ArrayList<String> listNodes = new ArrayList<String>();
            for (Child n: c.getChildren()) {
                listNodes.add(""+n.getId());
            }
            listChildren.put(c.getName(), listNodes);
        }
    }

     @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo) {
         super.onCreateContextMenu(menu, v, menuInfo);
         ExpandableListView.ExpandableListContextMenuInfo info =
                 (ExpandableListView.ExpandableListContextMenuInfo) menuInfo;
         int type =
                 ExpandableListView.getPackedPositionType(info.packedPosition);
         int group =
                 ExpandableListView.getPackedPositionGroup(info.packedPosition);
         int child =
                 ExpandableListView.getPackedPositionChild(info.packedPosition);
         String group_name = listCategories.get(group);

         if (type == 1) {
             String child_name = listChildren.get(group_name).get(child);
             menu.setHeaderTitle("Obraz " + group_name + child_name);
             menu.add(0, 0, 0, "Edytuj");
             menu.add(0, 1, 0, "Usuń");
             menu.add(0, 2, 0, "Podgląd");
         }
         else {
             menu.setHeaderTitle("Kategoria " + group_name);
             menu.add(0, 0, 0, "Edytuj");
             menu.add(0, 1, 0, "Usuń");
             menu.add(0, 2, 0, "Dodaj obraz");
         }
    }

    @Override
    public boolean onContextItemSelected(MenuItem menuItem) {
        ExpandableListView.ExpandableListContextMenuInfo info =
                (ExpandableListView.ExpandableListContextMenuInfo) menuItem.getMenuInfo();
        int type =
                ExpandableListView.getPackedPositionType(info.packedPosition);
        int group =
                ExpandableListView.getPackedPositionGroup(info.packedPosition);
        int child =
                ExpandableListView.getPackedPositionChild(info.packedPosition);
        String group_name = listCategories.get(group);

        if (type == 1) {
            String child_name = listChildren.get(group_name).get(child);
            if (menuItem.getTitle()=="Edytuj"){ editChild(child_name, group_name); }
            else if (menuItem.getTitle()=="Usuń"){ deleteChild(child_name); }
            else if (menuItem.getTitle()=="Podgląd"){ viewImage(child_name); }
            else { return false; }
            return true;
        }
        else {
            if (menuItem.getTitle()=="Edytuj"){ editCategory(group_name); }
            else if (menuItem.getTitle()=="Usuń"){ deleteCategory(group_name); }
            else if (menuItem.getTitle()=="Dodaj obraz"){ addChild(group_name); }
            else {return false;}
            return true;
        }
    }

    public void deleteCategory(String category){
        db.deleteCategory(category);
        Toast.makeText(this, "Usunięto kategorię "+category, Toast.LENGTH_SHORT).show();
    }

    public boolean addChild(String category) {
        Intent intent = new Intent(this, AddChild.class);
        intent.putExtra("category", category);
        startActivity(intent);
        return true;
    }

    public void deleteChild(String child){
        db.deleteChild(child);
        Toast.makeText(this, "Usunięto obraz o id = "+child, Toast.LENGTH_SHORT).show();
    }

    public void editChild(String child, String category){
        Intent intent = new Intent(this, EditChild.class);
        intent.putExtra("category", category);
        intent.putExtra("id", child);
        startActivity(intent);
    }

    public void editCategory(String category){
        Intent intent = new Intent(this, EditCategory.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }

    public void viewImage(String id){
        Child child = db.getChild(id);
        try {
            showImage(Uri.parse(new File(child.getImage()).toString()));
        } catch (Exception ex) {
            Toast.makeText(this, "Nie można wyświetlić obrazu.", Toast.LENGTH_SHORT).show();
        }

    }

    public void showImage(Uri imageUri) {
        Dialog builder = new Dialog(this);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
            }
        });

        ImageView imageView = new ImageView(this);
        imageView.setImageURI(imageUri);
        builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        builder.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addBtn:
                onAddBtnSelected();
        }
    }

    public boolean onAddBtnSelected() {
        Intent intent = new Intent(this, AddCategory.class);
        startActivity(intent);
        return true;
    }
}
