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
        initRes();


  //      TreeNode nParent = new TreeNode("Rzeczowniki");
    //    TreeNode nChild0 = new TreeNode("ChildNode0");
      //  TreeNode nChild1 = new TreeNode("ChildNode1");
        //nParent.addChildren(nChild0, nChild1);
   //     root.addChild(nParent);




    //    TreeNode vParent = new TreeNode("Czasowniki");
    //    TreeNode vChild0 = new TreeNode("ChildNode0");
    //    TreeNode vChild1 = new TreeNode("ChildNode1");
     //   vParent.addChildren(vChild0, vChild1);
    //    root.addChild(vParent);
    }

    void initDB() {
        TextView tv = (TextView)findViewById(R.id.textView);
        DBAdapter db = new DBAdapter(getApplicationContext());
        db.openDB();
        nCategories = db.getCategoriesFromType("rzeczowniki");
        //vCategories = db.getCategoriesFromType("czasowniki");
        TreeNode nParent = new TreeNode("Rzeczowniki");
        for (Category c: nCategories) {
            TreeNode catNode = new TreeNode(c.getName());
            c.setNodes(db.getNodesFromCategory(c.getName()));
            for (Node n: c.getNodes()) {
                TreeNode node = new TreeNode(c.getName() + n.getId());
                catNode.addChild(node);
            }
            nParent.addChild(catNode);
        }
        root.addChild(nParent);
        ScrollView nounTab = (ScrollView)findViewById(R.id.scrollView);
        nounTab.addView(tView.getView());
    }

    void initRes() {

    }
}
