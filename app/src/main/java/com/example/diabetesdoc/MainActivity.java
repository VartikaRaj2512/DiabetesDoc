package com.example.diabetesdoc;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

public class MainActivity extends AppCompatActivity {
    Button btnScore;
    AppCompatEditText etAge, etHeight, etBP, etApetite, etThirst,etVision, etBS, etweight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnScore = findViewById(R.id.btnScore);
        etAge = findViewById(R.id.edtAge);
        etHeight = findViewById(R.id.edtHeight);
        etBP = findViewById(R.id.edtBP);
        etBS = findViewById(R.id.edtBloodSugar);
        etApetite = findViewById(R.id.edtApetite);
        etThirst=findViewById(R.id.edtThirst);
        etVision=findViewById(R.id.edtVision);
        etweight = findViewById(R.id.edtWeight);
        btnScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(etAge.getText())) {
                    showMsg("Please add age, it's mandatory");
                    return;
                }
                if(TextUtils.isEmpty(etHeight.getText())) {
                    showMsg("Please add height, it's mandatory");
                    return;
                }
                if(TextUtils.isEmpty(etBP.getText())) {
                    showMsg("Please add BP, it's mandatory");
                    return;
                }
                if(TextUtils.isEmpty(etBS.getText())) {
                    showMsg("Please add Blood Sugar, it's mandatory");
                    return;
                }
                if(TextUtils.isEmpty(etApetite.getText())) {
                    showMsg("Please add Apetite, it's mandatory");
                    return;
                }
                if(TextUtils.isEmpty(etThirst.getText())) {
                    showMsg("Please add Thirst, it's mandatory");
                    return;
                }
                if(TextUtils.isEmpty(etVision.getText())) {
                    showMsg("Please add Vision, it's mandatory");
                    return;
                }
                if(TextUtils.isEmpty(etweight.getText())) {
                    showMsg("Please add Weight, it's mandatory");
                    return;
                }
                int age= Integer.parseInt(etAge.getText().toString());
                int height= Integer.parseInt(etHeight.getText().toString());
                int BP= Integer.parseInt(etBP.getText().toString());
                int BS= Integer.parseInt(etBS.getText().toString());
                int apetite= Integer.parseInt(etApetite.getText().toString());
                int thirst= Integer.parseInt(etThirst.getText().toString());
                int vision=Integer.parseInt(etVision.getText().toString());
                int weight= Integer.parseInt(etweight.getText().toString());
                double BMI=calculateBMI(weight,height);
                double score = calculateDiabetesScore(BMI,age, BP, BS,apetite,thirst,vision);
                if (score >= 8.0) {
                    System.out.println("High risk of diabetes");
                    showMsg("High risk of diabetes");
                } else {
                    System.out.println("Low risk of diabetes");
                    showMsg("Low risk of diabetes");
                }

            }
        });


    }
    private void showMsg(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    private double calculateBMI(double weight,double height) {
        double bmi=weight / (height * height);
        return bmi;
    }

    private double calculateDiabetesScore(double bmi, int age, int bloodPressure, int bloodSugar, int apetite,int thirst,int vision) {
        double score = 0.0;
        score += bmi >= 25 ? 1.5 : 0;
        score += age >= 40 ? 1.5 : 0;
        score += bloodPressure >= 140 / 90 ? 1 : 0;
        score += bloodSugar >= 100 ? 1 : 0;
        score += apetite >= 0 ? 1 : 0;
        score+= thirst >= 0 ? 1 : 0;
        score += vision >= 0 ? 1 : 0;
        return score;
    }

}

