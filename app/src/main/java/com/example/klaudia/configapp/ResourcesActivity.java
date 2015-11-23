package com.example.klaudia.configapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.view.ContextMenu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Toast;

import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.List;

public class ResourcesActivity extends AppCompatActivity {

    TreeNode root;
    List<Category> nCategories;
    List<Category> vCategories;
    AndroidTreeView tView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
        root = TreeNode.root();
        tView = new AndroidTreeView(this, root);
        initDB();
    }

    void initDB() {

        DBAdapter db = new DBAdapter(getApplicationContext());
        db.openDB();
        nCategories = db.getCategoriesFromType("rzeczowniki");
        //vCategories = db.getCategoriesFromType("czasowniki");
        TreeNode nParent = new TreeNode("Rzeczowniki");
       // nParent.se
        for (Category c: nCategories) {
            CategoryHolder.IconTreeItem catItem = new CategoryHolder.IconTreeItem(c.getName(), 1);
            TreeNode catNode = new TreeNode(catItem).setViewHolder(new CategoryHolder(this));
            c.setNodes(db.getNodesFromCategory(c.getName()));
            for (Node n: c.getNodes()) {
                NodeHolder.IconTreeItem nodeItem = new NodeHolder.IconTreeItem(""+n.getId(), 1);
                TreeNode child1 = new TreeNode(nodeItem).setViewHolder(new NodeHolder(this));
                catNode.addChild(child1);
            }
            nParent.addChild(catNode);
        }
        root.addChild(nParent);
        ScrollView nounTab = (ScrollView)findViewById(R.id.scrollView);
        nounTab.addView(tView.getView());
    }

     @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo) {
        onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Menu");
        menu.add(0, v.getId(), 0, "Action 1");
        menu.add(0, v.getId(), 0, "Action 2");
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
