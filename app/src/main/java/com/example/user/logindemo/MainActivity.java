package com.example.user.logindemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    EditText email,pass;
    Button Login;
    TextView register;
    private DbHelper db;
    SharedPreferences pref;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DbHelper(this);
        email=(EditText)findViewById(R.id.etEmail);
        pass=(EditText)findViewById(R.id.etPass);
        Login=(Button) findViewById(R.id.btnLogin);
        pref = getSharedPreferences("details",MODE_PRIVATE);
        intent = new Intent(MainActivity.this,Home.class);
        if(pref.contains("username") && pref.contains("password")){
            startActivity(intent);
        }
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String semail = email.getText().toString();
                String spass = pass.getText().toString();

                if (semail.equals("admin") && spass.equals("admin")) {
                    intent = new Intent(MainActivity.this,AdminLogin.class);
                    Toast.makeText(getApplicationContext(), "Login Successfully",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    email.setText("");
                    pass.setText("");
                }
               if(db.getUser(semail,spass)){
                   SharedPreferences.Editor editor = pref.edit();
                   editor.putString("username",semail);
                   editor.putString("password",spass);
                   editor.commit();
                   Toast.makeText(getApplicationContext(), "Login Successfully",Toast.LENGTH_SHORT).show();
                   startActivity(intent);
                   email.setText("");
                   pass.setText("");
                }
            }
                });

        register=(TextView) findViewById(R.id.tvRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,RegistrActivity.class);
                startActivity(i);
                finish();
            }
        });

    }


}
