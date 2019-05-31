package com.example.user.logindemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrActivity extends AppCompatActivity {
   EditText name,mobile,email,passw;
   TextView login;
    Button register;
    private DbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DbHelper(this);
        setContentView(R.layout.activity_registr);
        name=(EditText)findViewById(R.id.etName);
        mobile=(EditText)findViewById(R.id.etMobile);
        email=(EditText)findViewById(R.id.etEmail);
        passw=(EditText)findViewById(R.id.etPass);
        register=(Button) findViewById(R.id.btnReg);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {

                    String sname = name.getText().toString();
                    String semail = email.getText().toString();
                    String smobile = mobile.getText().toString();
                    String spass = passw.getText().toString();
                    validate();
                        db.addUser(sname,semail,smobile,spass);
                    Toast.makeText(getApplicationContext(),"Registered Successfully", Toast.LENGTH_SHORT).show();

                    }
                }


        });
        login=(TextView) findViewById(R.id.tvLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(RegistrActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }



    private boolean validate() {
        boolean valid = true;
        String sname = name.getText().toString();
        String semail = email.getText().toString();
        String smobile = mobile.getText().toString();
        String spass = passw.getText().toString();
        if (sname.isEmpty()) {
            name.setError("Enter valid Name");
            valid = false;
        } else {
            if (semail.isEmpty() || !isValidEmail(semail)) {
                email.setError("Enter valid email");
                valid = false;
            } else {
                email.setError(null);
            }
            if (spass.isEmpty() || spass.length() < 2|| spass.length() > 7) {
                passw.setError("between 2 and 7 alphanumeric characters");
                valid = false;
            } else {
                passw.setError(null);
            }
            if (smobile.isEmpty() || smobile.length() != 10) {
                mobile.setError("Enter valid number");
                valid = false;
            }
        }
        return valid;
    }

    private boolean isValidEmail(String semail) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(semail);
        return matcher.matches();
    }


}
