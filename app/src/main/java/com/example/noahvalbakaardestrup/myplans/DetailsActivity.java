package com.example.noahvalbakaardestrup.myplans;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.noahvalbakaardestrup.myplans.model.PlanItem;
import com.google.gson.Gson;

public class DetailsActivity extends AppCompatActivity {

    SeekBar seekbar;

    TextView title;
    TextView importance;
    TextView starts;
    TextView location;
    TextView note;
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Catch ui ref
        title = findViewById(R.id.Title);
        starts = findViewById(R.id.Starts);
        location = findViewById(R.id.Location);
        note = findViewById(R.id.Note);

        seekbar = findViewById(R.id.seekBar);
        importance = findViewById(R.id.Importance);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        // Set fields
        String dataReceived = getIntent().getStringExtra("PLANDATA");

        PlanItem item = new Gson().fromJson(dataReceived, PlanItem.class);

        title.setText(item.getTitle());
        note.setText(item.getNote());
        location.setText(item.getLocation());
        seekbar.setProgress(item.getImportance());
        starts.setText(item.getStarts());

    }
}
