package com.example.textchat_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    EditText edtemail,edtpass;
    Button btnreg;
    FirebaseAuth mAuth;
    ProgressDialog mprogressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_registration );

        mAuth = FirebaseAuth.getInstance();
        edtemail = findViewById( R.id.reg_email );
        edtpass = findViewById( R.id.reg_pass );
        btnreg = findViewById( R.id.reg_btn );

        mprogressDialog = new ProgressDialog(this);
        mprogressDialog.setTitle( "Registering" );
        mprogressDialog.setMessage( "Please Wait" );

        btnreg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regEmail = edtemail.getText().toString().trim();
                String regPass = edtpass.getText().toString().trim();

                RegisterUser( regEmail, regPass );
            }
        });
    }
    private void RegisterUser(String regEmail, String regPass) {

        mprogressDialog.show();
        mAuth.createUserWithEmailAndPassword( regEmail, regPass ).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Toast.makeText( RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT ).show();
                    mprogressDialog.dismiss();
                }

                else {
                    Toast.makeText( RegistrationActivity.this, ""+task.getException(), Toast.LENGTH_SHORT ).show();
                    mprogressDialog.dismiss();
                }
            }
        } );
    }
}
