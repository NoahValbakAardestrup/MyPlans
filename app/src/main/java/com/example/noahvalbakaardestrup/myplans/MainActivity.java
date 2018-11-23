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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PlanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView buttonCreate = findViewById(R.id.CreateButton);
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                f();
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
        adapter = new PlanAdapter();
        RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        loadPlan();
    }

    private void f() {
        Intent I = new Intent(this, CreateActivity.class);
        String textval = "oi oi";
        startActivity(I);
    }

    private void loadPlan() {

        List<PlanItem> items = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            items.add(new PlanItem("Item " + i));
        }
        adapter.setData(items);
    }

    // Holder of data
    private class PlanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        List<PlanItem> items;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new PlanItemVH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.plan_list_item, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

            PlanItem item = items.get(i);
            PlanItemVH itemVH = (PlanItemVH) viewHolder;
            itemVH.name.setText(item.name);
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

        public PlanItemVH(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
        }
    }

    public class PlanItem {
        private String name;

        public PlanItem(String name) {
            this.name = name;
        }
    }
}
