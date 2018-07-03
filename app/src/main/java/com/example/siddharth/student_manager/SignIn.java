package com.example.siddharth.student_manager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.siddharth.student_manager.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

 public class SignIn extends AppCompatActivity {
    EditText edtphone,edtPassword;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);




        edtPassword=(MaterialEditText)findViewById(R.id.edtPassword);
        edtphone=(MaterialEditText)findViewById(R.id.edtphone);
        btnSignIn=(Button)findViewById(R.id.btnSignIn);


        //init firebase
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference table_user=database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog=new ProgressDialog(SignIn.this);
                mDialog.setMessage("Processing......");
                mDialog.show();;


                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //check if user does not present in the database
                        if (dataSnapshot.child(edtphone.getText().toString()).exists()) {

                            //get user information
                            mDialog.dismiss();
                            User user = dataSnapshot.child(edtphone.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(edtPassword.getText().toString())) {

                                Toast.makeText(SignIn.this, "Sign in Successfully!!!!", Toast.LENGTH_SHORT).show();
                                Intent ht = new Intent(SignIn.this, AppBase.class);
                                startActivity(ht);

                            } else {
                                Toast.makeText(SignIn.this, "WRONG PASSWORD!!!!", Toast.LENGTH_SHORT).show();
                            }
                        } else
                        {
                            mDialog.dismiss();
                            Toast.makeText(SignIn.this, "User does not exist!!!!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }


                });
            }
        });
    }
}
