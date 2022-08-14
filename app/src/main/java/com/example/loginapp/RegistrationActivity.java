package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    //variables created
    private TextInputLayout userName, userEmail, userPassword;
    private TextInputEditText etRegisterName, etRegisterEmail, etRegisterPassword;
    private Button SignUp;
    private TextView userLogin;

    //instance of firebase authenticator
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        uiViews();  //referencing the method we cerated to assign id to variables

        //this wiil get the instance and assign it to the variable
        firebaseAuth = FirebaseAuth.getInstance();

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    //upload data to the database
                    //here we are getting whatever the user has entered
                    //the trim() method will remove all the whitespace the user had given
                    String user_email = etRegisterEmail.getText().toString().trim();
                    String user_password = etRegisterPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(RegistrationActivity.this, "SignUp SucessFull", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(RegistrationActivity.this, "SignUp Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });
    }

    //seperate method to assign variables to id
    private void uiViews(){
        userName = (TextInputLayout) findViewById(R.id.TILname);
        userEmail = (TextInputLayout) findViewById(R.id.TILemail);
        userPassword = (TextInputLayout) findViewById(R.id.TILpassword);

        etRegisterName = (TextInputEditText)findViewById(R.id.tietName);
        etRegisterEmail = (TextInputEditText)findViewById(R.id.tietEmail);
        etRegisterPassword = (TextInputEditText)findViewById(R.id.tietPassword);

        SignUp = (Button)findViewById(R.id.btnSignUp);
        userLogin = (TextView)findViewById(R.id.tvBackToLogin);
    }

    private Boolean validate(){
        Boolean result = false;

        String name = etRegisterName.getText().toString();
        String email = etRegisterEmail.getText().toString();
        String password = etRegisterPassword.getText().toString();

        if(name.isEmpty() || email.isEmpty() || password.isEmpty()){
            Toast.makeText(this,"Please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }
        return result;
    }
}