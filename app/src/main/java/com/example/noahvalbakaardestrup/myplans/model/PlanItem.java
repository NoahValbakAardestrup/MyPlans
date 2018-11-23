package com.trackunit.noahvalbakaardestrup.myplans.model;

public class PlanItem {
    private String title;
    private String starts;
    private String location;
    private int importance;
    private String note;

    public PlanItem(String title, String starts, String location, int importance, String note) {
        this.title = title;
        this.starts = starts;
        this.location = location;
        this.importance = importance;
        this.note = note;
    }

    public String getTitle(){
        return title;
    }

    public String getStarts() {
        return starts;
    }

    public String getLocation() {
        return location;
    }

    public int getImportance() {
        return importance;
    }

    public String getNote() {
        return note;
    }
}