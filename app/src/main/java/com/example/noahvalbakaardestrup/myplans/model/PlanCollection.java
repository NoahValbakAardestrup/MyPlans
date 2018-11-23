package com.trackunit.noahvalbakaardestrup.myplans.model;

import com.trackunit.noahvalbakaardestrup.myplans.StorageHelper;

import java.util.ArrayList;
import java.util.List;

public class PlanCollection {

    private List<PlanItem> items;

    public PlanCollection() {
        items = new ArrayList<>();
    }

    public PlanCollection(List<PlanItem> items) {
        this.items = items;
    }

    public List<PlanItem> getPlans() {
        return items;
    }

    public void addPlan(PlanItem item) {
        items.add(item);
    }

    public boolean removePlan(String title) {
        PlanItem foundPlan = locatePlanItem(title);
        if (foundPlan != null) {
            return removePlan(foundPlan);
        }
        return false;
    }

    public boolean removePlan(PlanItem item) {
        if (items.contains(item)) {
            return items.remove(item);
        }
        return false;
    }

    public PlanItem locatePlanItem(String title) {
        for (PlanItem item : items) {
            if (item.getTitle().equals(title)) {
                return item;
            }
        }
        return null;
    }
}
