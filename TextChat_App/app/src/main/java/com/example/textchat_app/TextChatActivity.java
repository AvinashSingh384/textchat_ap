package com.example.textchat_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TextChatActivity extends AppCompatActivity {

    EditText editText;
    ListView listView;
    Button bt;
    List<DataD> list;
    DatabaseReference referencel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_text_chat );

        bt = findViewById( R.id.btn );
        editText = findViewById( R.id.edit );
        listView = findViewById( R.id.listView );
        referencel = FirebaseDatabase.getInstance()
                .getReference( "http" );
        list = new ArrayList<>();

        bt.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddData();
            }
        } );
    }

    private void AddData() {
        String datad = editText.getText().toString().trim();
        if (!TextUtils.isEmpty( datad )) {
            String da = referencel.push().getKey();
            DataD d = new DataD( datad );
            referencel.child( da ).setValue( d );
            editText.setText( "" );
            Toast.makeText( getApplicationContext(), "Data Send", Toast.
                    LENGTH_SHORT ).show();
        } else {
            Toast.makeText( getApplicationContext(),
                    "Data Not Send", Toast.LENGTH_SHORT )
                    .show();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        referencel.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot
                                             dataSnapshot) {
                list.clear();
                for (DataSnapshot postSnapshot :
                        dataSnapshot.getChildren()) {
                    DataD dataD = postSnapshot
                            .getValue( DataD.class );
                    list.add( dataD );
                }
                DataList dataListAdapter =
                        new DataList(
                                TextChatActivity
                                        .this, list );
                listView.setAdapter( dataListAdapter );
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        } );
    }

}
