package com.example.textchat_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import android.widget.Button;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    EditText edtlogEmail,edtlogPass;
    Button logbtn;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        mAuth = FirebaseAuth.getInstance();
        edtlogEmail = findViewById( R.id.Lemail );
        edtlogPass = findViewById( R.id.Lpass );
        logbtn = findViewById( R.id.Lbtn );

        logbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String loginEmail = edtlogEmail.getText().toString().trim();
                String loginPass = edtlogPass.getText().toString();

                loginUser(loginEmail,loginPass);
            }
        } );

    }

    private void loginUser(String loginEmail, String loginPass) {

        mAuth.signInWithEmailAndPassword( loginEmail,loginPass )
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText( LoginActivity.this, "Login Successful",
                                    Toast.LENGTH_SHORT ).show();
                            Intent intent = new Intent( LoginActivity
                                    .this,TextChatActivity.class );
                            intent.putExtra( "email"
                                    ,mAuth.getCurrentUser().getEmail() );

                            startActivity( intent );
                        }
                        else {

                            Toast.makeText( LoginActivity.this, ""+task
                                    .getException(), Toast.LENGTH_SHORT ).show();

                        }
                    }
                } );

    }
}
