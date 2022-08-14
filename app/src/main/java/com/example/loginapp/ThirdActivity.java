package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ThirdActivity extends AppCompatActivity {

    private TextView title, total, average;
    private TextInputLayout stud_Name, mark_1, mark_2, mark_3;
    private TextInputEditText tietName, tietMark1, tietMark2, tietMark3;
    private TextView grade;
    private Button calculate, save;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference().child("Student");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        assignVarToVal();

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calMarks();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studInfo();

            }
        });
    }

    private void assignVarToVal(){
        title = (TextView)findViewById(R.id.tvTitle);

        stud_Name = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        mark_1 = (TextInputLayout) findViewById(R.id.textInputLayoutMark1);
        mark_2 = (TextInputLayout) findViewById(R.id.textInputLayoutMark2);
        mark_3 = (TextInputLayout) findViewById(R.id.textInputlayoutMark3);

        tietName = (TextInputEditText)findViewById(R.id.textInputetName);
        tietMark1 = (TextInputEditText)findViewById(R.id.textInputetMark1);
        tietMark2 = (TextInputEditText)findViewById(R.id.textInputetMark2);
        tietMark3 = (TextInputEditText)findViewById(R.id.textInputetMark3);

        total = (TextView) findViewById(R.id.tvTotal);
        average = (TextView)findViewById(R.id.tvAverage);
        grade = (TextView)findViewById(R.id.tvGrade);
        calculate = (Button)findViewById(R.id.btnCalculate);
        save = (Button)findViewById(R.id.btnSave);
    }

    private void calMarks(){

        int mark1, mark2, mark3, tot;
        double avg;


        mark1 = Integer.parseInt(tietMark1.getText().toString());
        mark2 = Integer.parseInt(tietMark2.getText().toString());
        mark3 = Integer.parseInt(tietMark3.getText().toString());

        tot = mark1 + mark2 + mark3;
        //displaying the tot value in total text box
        total.setText(String.valueOf("Total: " + tot));

        avg = tot/3;
        average.setText(String.valueOf("Average: " + avg));

        if(avg>75){
            grade.setText("Grade: " + "A");
        }else if(avg > 65){
            grade.setText("Grade: " + "B");
        }else if(avg > 55){
            grade.setText("Grade: " + "C");
        }else if(avg > 45){
            grade.setText("Grade: " +"D");
        }else {
            grade.setText("Grade: " +"F");
        }


    }

    private void studInfo(){

        String Name = tietName.getText().toString();
        String Mark1 = tietMark1.getText().toString();
        String Mark2 = tietMark2.getText().toString();
        String Mark3 = tietMark3.getText().toString();
        String Total = total.getText().toString();
        String Average = average.getText().toString();
        String Grade = grade.getText().toString();

        //HashMap
        HashMap<String, String> userInfo = new HashMap<>();
        userInfo.put("Name", Name);
        userInfo.put("Java", Mark1);
        userInfo.put("IOT", Mark2);
        userInfo.put("Python", Mark3);
        userInfo.put("Total", Total);
        userInfo.put("Avg", Average);
        userInfo.put("Grade", Grade);

        databaseReference.push().setValue(userInfo);
        Toast.makeText(ThirdActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();

    }
}