package com.example.noahvalbakaardestrup.myplans;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noahvalbakaardestrup.myplans.model.PlanCollection;
import com.example.noahvalbakaardestrup.myplans.model.PlanItem;

public class CreateActivity extends AppCompatActivity {

    SeekBar seekbar;

    EditText title;
    TextView importance;
    EditText starts;
    EditText location;
    EditText note;
    ImageView backButton;
    Button createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        title = findViewById(R.id.Title);
        starts = findViewById(R.id.Starts);
        location = findViewById(R.id.Location);
        note = findViewById(R.id.Note);

        createButton = findViewById(R.id.CreateButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePlan();
            }
        });

        seekbar = findViewById(R.id.seekBar);
        importance = findViewById(R.id.Importance);
        importance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekbar.setVisibility(View.VISIBLE);
            }
        });

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                importance.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void savePlan() {

        String planTitle = title.getText().toString();
        String planStarts = starts.getText().toString();
        String planLocation = location.getText().toString();
        String planNote = note.getText().toString();

        int planImportance = seekbar.getProgress();

        PlanItem planItem = new PlanItem(planTitle, planStarts, planLocation, planImportance, planNote);

        // Read plans
        PlanCollection planCollection = StorageHelper.readPlanCollection();
        planCollection.addPlan(planItem);

        // Save again
        boolean isSaved = StorageHelper.savePlanCollection(planCollection);

        if (isSaved) {
            onBackPressed();
        } else {
            Toast.makeText(this, "Failed to save plan", Toast.LENGTH_LONG).show();
        }
    }
}
