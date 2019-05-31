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

import java.util.HashMap;
import java.util.regex.Pattern;

public class Home extends AppCompatActivity {
     Button logout;
     EditText text;
    private DbHelper db;
    Button rev;
    TextView t1;
    SessionManager session;
    Intent intent;
    SharedPreferences prf;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        text=(EditText) findViewById(R.id.edt);
        t1=(TextView) findViewById(R.id.t1);
         rev=(Button) findViewById(R.id.btnReverse);

        db = new DbHelper(this);


        rev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=  text.getText().toString();
                    String[] words = s.split("\\s");

                    String outputString = "";

                    for (int i = words.length-1; i >= 0; i--)
                    {
                        outputString = outputString + words[i] + " ";

                }
                Toast.makeText(getApplicationContext(),outputString,Toast.LENGTH_SHORT).show();
                    t1.setText(outputString);


            }
        });

        logout=(Button)findViewById(R.id.btnLogout);
        prf = getSharedPreferences("details",MODE_PRIVATE);
        intent = new Intent(Home.this,MainActivity.class);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = prf.edit();
                editor.clear();
                editor.commit();
                startActivity(intent);
            }
        });
    }


}
