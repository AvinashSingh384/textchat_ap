package com.example.textchat_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class RegistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_regist );

    }

    public void show(View view) {
        if (view.getId() == R.id.rg_btn) {
            Intent regintent = new Intent( RegistActivity.this, RegistrationActivity.class );
            startActivity( regintent );
        }
        else
            {
            Intent logintent = new Intent( RegistActivity.this, LoginActivity.class );
            startActivity( logintent );
        }
    }
    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Alert Dilog");
        builder.setMessage("Do you want to close it");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.
                OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(RegistActivity.this,
                        "Back to home", Toast.LENGTH_SHORT).show();
                RegistActivity.this.finish();
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.
                OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(RegistActivity.this,
                        "Wrong click", Toast.LENGTH_SHORT).show();
                builder.setCancelable(true);
            }
        });
        builder.show();
    }
}
