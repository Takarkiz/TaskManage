package com.takhaki.todaystaskhack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class MemoAdapter extends ArrayAdapter<Memo> {

    public LayoutInflater layoutInflater;

    MemoAdapter(Context context, int textViewResourceId, List<Memo> object) {
        super(context, textViewResourceId, object);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Memo memo = getItem(position);

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.layout_item_memo, null);
        }

        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.taskCheck);
        TextView timeTextView = (TextView) convertView.findViewById(R.id.timeText);

        checkBox.setText(memo.tittle);
        checkBox.setChecked(memo.isDone);
        timeTextView.setText(memo.time);

        return convertView;
    }
}
