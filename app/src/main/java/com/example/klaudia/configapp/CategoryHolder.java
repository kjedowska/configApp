package com.example.klaudia.configapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.view.ContextMenu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Toast;

import com.unnamed.b.atv.model.TreeNode;

/**
 * Created by Klaudia on 2015-11-23.
 */
public class CategoryHolder extends TreeNode.BaseNodeViewHolder<CategoryHolder.IconTreeItem> {

    Activity a;

    public CategoryHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(TreeNode node, IconTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.layout_cat_node, null, false);
        TextView tvValue = (TextView) view.findViewById(R.id.cat_value);
        tvValue.setText(value.text);
        return view;
    }

    public void getView(Activity a){
        this.a = a;
    }

    public static class IconTreeItem {
        public int icon;
        public String text;

        public IconTreeItem(String text, int icon) {
            this.text = text;
            this.icon = icon;
        }
    }

  //  protected void onLongClickListener

   // @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenu.ContextMenuInfo menuInfo) {
        a.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Menu");
        menu.add(0, v.getId(), 0, "Action 1");
        menu.add(0, v.getId(), 0, "Action 2");
    }

    //@Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle()=="Action 1"){function1(item.getItemId());}
        else if(item.getTitle()=="Action 2"){function2(item.getItemId());}
        else {return false;}
        return true;
    }

    public void function1(int id){
        Toast.makeText(a, "function 1 called", Toast.LENGTH_SHORT).show();
    }
    public void function2(int id){
        Toast.makeText(a, "function 2 called", Toast.LENGTH_SHORT).show();
    }
}
