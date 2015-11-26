package com.example.klaudia.configapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResourcesActivity extends Activity implements View.OnClickListener  {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    List<Category> nCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.list1);

        initDB();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        registerForContextMenu(expListView);

        View addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(this);
    }

    void initDB() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        DBAdapter db = new DBAdapter(getApplicationContext());
        db.openDB();
        nCategories = db.getCategories();

        for (Category c: nCategories) {
            listDataHeader.add(c.getName());
            c.setNodes(db.getNodesFromCategory(c.getName()));
            ArrayList<String> listNodes = new ArrayList<String>();
            for (Node n: c.getNodes()) {
                listNodes.add(""+n.getId());
            }
            listDataChild.put(c.getName(), listNodes);
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
         String group_name = listDataHeader.get(group);

         if (type == 1) {
             String child_name = listDataChild.get(group_name).get(child);
             menu.setHeaderTitle(child_name);
             menu.add(0, 0, 0, "Edit");
             menu.add(0, 1, 0, "Delete");
         }
         else {
             menu.setHeaderTitle(group_name);
             menu.add(0, 0, 0, "Edit");
             menu.add(0, 1, 0, "Delete");
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
        String group_name = listDataHeader.get(group);

        if (type == 1) {
            String child_name = listDataChild.get(group_name).get(child);
            if(menuItem.getTitle()=="Edit"){function1(child_name + "edit");}
            else if(menuItem.getTitle()=="Delete"){function2(child_name + "del");}
            else {return false;}
            return true;
        }
        else {
            if(menuItem.getTitle()=="Edit"){function1(group_name + " edit");}
            else if(menuItem.getTitle()=="Delete"){function2(group_name + " del");}
            else {return false;}
            return true;
        }
    }

    public void function1(String id){

        Toast.makeText(this, "function 1 called "+id, Toast.LENGTH_SHORT).show();
    }
    public void function2(String id){
        Toast.makeText(this, "function 2 called "+id, Toast.LENGTH_SHORT).show();
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
       // intent.putExtra("db", db);
        startActivity(intent);
        return true;
    }
}
