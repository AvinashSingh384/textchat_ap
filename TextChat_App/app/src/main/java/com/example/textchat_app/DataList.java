package com.example.textchat_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class DataList extends ArrayAdapter<DataD> {
    List<DataD> dataList;
    private Activity context;

    public DataList(@NonNull Activity context, @NonNull List<DataD>
            dataList ) {
        super( context,R.layout.activity_data_list,dataList);
        this.context=context;
        this.dataList=dataList;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View
            convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View listViweItem=inflater.inflate(R.layout
                .activity_data_list,null,true);
        TextView textViewdatad=listViweItem
                .findViewById(R.id.DataD);

        DataD dataD=dataList.get(position);
        textViewdatad.setText(dataD.getDatad());
        return listViweItem;
    }
}
