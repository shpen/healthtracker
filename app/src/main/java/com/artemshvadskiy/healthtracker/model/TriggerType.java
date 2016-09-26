package com.artemshvadskiy.healthtracker.model;

public enum TriggerType {
    ALARM("Alarm"),
    ACTIVE("Active");

    private final String mName;

    private TriggerType(String name) {
        mName = name;
    }
}
