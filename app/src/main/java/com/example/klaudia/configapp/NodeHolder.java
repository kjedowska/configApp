package com.example.klaudia.configapp;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.unnamed.b.atv.model.TreeNode;

/**
 * Created by Klaudia on 2015-11-23.
 */
public class NodeHolder extends TreeNode.BaseNodeViewHolder<NodeHolder.IconTreeItem> {

    public NodeHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(TreeNode node, IconTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.layout_profile_node, null, false);
        TextView tvValue = (TextView) view.findViewById(R.id.node_value);
        tvValue.setText(value.text);

        return view;
    }

    public static class IconTreeItem {
        public int icon;
        public String text;

        public IconTreeItem(String text, int icon) {
            this.text = text;
            this.icon = icon;
        }
    }


}
