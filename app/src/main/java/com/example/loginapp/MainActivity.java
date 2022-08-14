package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //Creating variables for all the widgets we created in the visual layout editor

    //Material design TextInputlayout & TextInputEditText
    private TextInputLayout Name, Password;
    private TextInputEditText etName, etPassword;
    private Button Login;
    private TextView SignUp;

    //declaring object firebaseAuth of class FirebaseAuth, to import libraries of the authentication of firebase
    private FirebaseAuth firebaseAuth;

    //adding progress dialog
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assigning variables to the id defined in the layout
        //findViewById assigns the variable to the respectible id in the xml layout

        Name = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        Password = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        etName = (TextInputEditText)findViewById(R.id.textInputetName);
        etPassword = (TextInputEditText)findViewById(R.id.textInputetPassword);
        Login = (Button)findViewById(R.id.btnLogin);
        SignUp = (TextView)findViewById(R.id.tvRegister);

        //getting instance of FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        //getting instance of progress dialog
        progressDialog = new ProgressDialog(this);

        //checking if user has already loged in to our app
        FirebaseUser user = firebaseAuth.getCurrentUser();

        //if user already logedin, case is not null
        if(user != null){
            finish();  //destroyes the activity
            startActivity(new Intent(MainActivity.this, SecondActivty.class));
        }



        //Creating onClickListner to run the validate method
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(etName.getText().toString(), etPassword.getText().toString());
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    //This whole function should work only when the button login is clicked, so we need to set onClickListner
    //writing a function for validating if our username and password is write or wrong
    //writing this function outside oncreate() method

    private void validate(String userName, String userPassword){

        progressDialog.setMessage("Just a moment");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Login Sucessfull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, SecondActivty.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
