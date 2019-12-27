package com.example.textchat_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
  TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        textView = findViewById( R.id.text );
        Thread thread = new Thread(  ) {

            @Override
            public void run() {
                super.run();

                try {
                    sleep( 2000 );
                }
                catch
                    (Exception e){

                }
                finally {
                    Intent intent = new Intent( MainActivity.this,RegistActivity.class );
                    startActivity( intent );
                }
            }
        };thread.start();
    }

    @Override
    protected void onPause() {
        super.finish();
        super.onPause();
    }
}
