package com.example.siddharth.student_manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Basic extends AppCompatActivity {
    Button btnsignup,btnsignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        btnsignin= (Button) findViewById(R.id.btnSignIn);
        btnsignup= (Button) findViewById(R.id.btnSignUp);


        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Basic.this,SignUp.class);
                startActivity(intent);

            }
        });
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Basic.this,SignIn.class);
                startActivity(intent);
            }
        });



    }
}
