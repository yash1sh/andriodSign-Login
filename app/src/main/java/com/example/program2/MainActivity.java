package com.example.program2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

//     public Connection con;
////    private MainActivity Connections;
//
//    public  Connection getcon()
//    {
//        try
//        {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root", "root");
//        }
//        catch(ClassNotFoundException e)
//        {
//            System.out.println("Class Not Found Exeption");
//        }
//        catch(SQLException e)
//        {
//            System.out.println("SQL Exception");
//        }
//        return con;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EditText email,passwd;
        Button Signup;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=(EditText)findViewById(R.id.email);
        passwd=(EditText)findViewById(R.id.passwd);
        Signup= (Button) findViewById(R.id.Signup);



        Signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String usr=email.getText().toString();
                String pass=passwd.getText().toString();

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root", "root");
                    PreparedStatement ps= conn.prepareStatement("insert into users values(?,?);");
                    ps.setString(1, usr);
                    ps.setString(2, pass);
                    ps.execute();
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if(usr.length()==0)
                {
                    email.setError("USername Empty");
                    email.requestFocus();

                }
                else if(pass.length()==0){
                    passwd.setError("Pass Empty");
                    passwd.requestFocus();
                }
                else
                {
                    if(!isValidpassword(pass))
                        Toast.makeText(MainActivity.this ,"pass not proper",Toast.LENGTH_LONG).show();
                    else{
                        Intent intent = new Intent(MainActivity.this, Login.class);
                        intent.putExtra("Username",usr);
                        intent.putExtra("password", pass);
                        startActivity(intent);

                    }
                }
            }
    });
    }


    Pattern lowercase=Pattern.compile("^.*[a-z].*$");
    Pattern uppercase=Pattern.compile("^.*[A-Z]*.$");
    Pattern number=Pattern.compile("^.*[0-9]*.$");
    Pattern special=Pattern.compile("^.*[a-zA-Z0-9]*.*$");

    private boolean isValidpassword(String pass) {
        if(pass.length()<=0)
        {
            return false;
        }
        if(!lowercase.matcher(pass).matches())
        {
            return false;
        }
        if(!uppercase.matcher(pass).matches())
        {
            return  false;
        }
        if(!number.matcher(pass).matches())
        {
            return false;
        }
        if(!special.matcher(pass).matches())
        {
            return false;
        }
        return true;
    }
}