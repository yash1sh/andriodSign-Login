package com.example.program2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText email2,passwd2;
    Button login;
    TextView text;
    int counter=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email2=(EditText)findViewById(R.id.email2);
        passwd2=(EditText)findViewById(R.id.passwd2);
        login=(Button)findViewById(R.id.login);
        text=(TextView)findViewById(R.id.txtview);

        String emailgot=getIntent().getStringExtra("Username");
        String passwordgot=getIntent().getStringExtra("password");
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=email2.getText().toString();
                String pass=passwd2.getText().toString();

                if(emailgot.equals(email) && passwordgot.equals(pass)) {

                    Intent intent = new Intent(Login.this, AfterLogin.class);
                    startActivity(intent);
                    text.setVisibility(View.VISIBLE);
                    text.setBackgroundColor(Color.RED);
                    counter--;
                    text.setText(Integer.toString(counter));
                }
                else
                {
                    Toast.makeText(Login.this,"invalid",Toast.LENGTH_LONG).show();
                    text.setVisibility(View.VISIBLE);
                    text.setBackgroundColor(Color.RED);
                    counter--;
                    text.setText(Integer.toString(counter));
                }
                if(counter==0){
                    Toast.makeText(Login.this,"failed login attempts",Toast.LENGTH_LONG).show();
                    login.setEnabled(false);
                }

            }
        });





    }
}