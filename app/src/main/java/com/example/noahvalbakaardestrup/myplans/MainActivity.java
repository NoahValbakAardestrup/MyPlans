package com.example.noahvalbakaardestrup.myplans;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textTitle = findViewById(R.id.textTitle);
            Button buttonCreate = findViewById(R.id.buttonCreate);
        buttonCreate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textTitle.setText("YO");

                    int val = 4;
                    textTitle.setText(String.valueOf(val));

                    f();
                }
        });
    }


    private void f(){
        Intent I = new Intent(this, CreateActivity.class);
        String textval = "oi oi";
        startActivity(I);
        textTitle.setText(textval);
    }
}
