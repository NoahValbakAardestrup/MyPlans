package com.example.noahvalbakaardestrup.myplans;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.noahvalbakaardestrup.myplans.model.PlanCollection;
import com.example.noahvalbakaardestrup.myplans.model.PlanItem;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PlanAdapter adapter;
    private RecyclerView list;

    private PlanCollection planCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView buttonCreate = findViewById(R.id.CreateButton);
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openCreatePlan();
            }
        });


        // Hamburger
        final DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ImageView hamburgerButton = findViewById(R.id.hamburgerButton);
        hamburgerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // Adapter
        adapter = new PlanAdapter(new ArrayList<PlanItem>());
        list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();

        loadPlan();
    }

    private void openCreatePlan() {
        Intent I = new Intent(this, CreateActivity.class);
        String textval = "oi oi";
        startActivity(I);
    }

    private void loadPlan() {

        planCollection = StorageHelper.readPlanCollection();
        adapter.setData(planCollection.getPlans());
    }

    // Holder of data
    private class PlanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        List<PlanItem> items;

        public PlanAdapter(List<PlanItem> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new PlanItemVH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.plan_list_item, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {

            final PlanItem item = items.get(i);
            PlanItemVH itemVH = (PlanItemVH) viewHolder;
            itemVH.name.setText(item.getTitle());
            itemVH.importance.setProgress(item.getImportance());
            itemVH.importance.setEnabled(false);
            itemVH.doneButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Locate and remove plan from list
                    if(planCollection.removePlan(item)) {

                        // Save modified list
                        StorageHelper.savePlanCollection(planCollection);

                        // Signal to update UI on list
                        notifyDataSetChanged();
                    }
                }
            });



            // clicking on entire element
            itemVH.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Package planitem
                    String dataToSend = new Gson().toJson(item);

                     // Go to Details
                    Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                    intent.putExtra("PLANDATA", dataToSend);
                    startActivity(intent);
                }
            });


        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public void setData(List<PlanItem> items) {
            this.items = items;
            notifyDataSetChanged();
        }
    }

    public class PlanItemVH extends RecyclerView.ViewHolder {

        private TextView name;
        private SeekBar importance;
        private Button doneButton;

        public PlanItemVH(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            importance = itemView.findViewById(R.id.seekBar);
            doneButton = itemView.findViewById(R.id.buttonDone);
        }
    }


}
