package com.example.klaudia.configapp;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.view.ContextMenu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResourcesActivity extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    List<Category> nCategories;
    List<Category> vCategories;

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
    }

    void initDB() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        DBAdapter db = new DBAdapter(getApplicationContext());
        db.openDB();
        nCategories = db.getCategoriesFromType("rzeczowniki");
        //vCategories = db.getCategoriesFromType("czasowniki");

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
//Only create a context menu for child items

             menu.setHeaderTitle("11");
             menu.add(0, 1, 0, "Read page");
             menu.add(0, 1, 0, "Edit page");


         }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle()=="Action 1"){function1(item.getItemId());}
        else if(item.getTitle()=="Action 2"){function2(item.getItemId());}
        else {return false;}
        return true;
    }

    public void function1(int id){
        Toast.makeText(this, "function 1 called", Toast.LENGTH_SHORT).show();
    }
    public void function2(int id){
        Toast.makeText(this, "function 2 called", Toast.LENGTH_SHORT).show();
    }
}
