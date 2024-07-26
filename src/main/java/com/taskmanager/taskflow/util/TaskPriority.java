package com.taskmanager.taskflow.util;

public enum TaskPriority {
    LOW ("BAIXA"),
    MEDIUM("MÉDIA"),
    HIGH("ALTA");

    private final String status;

    TaskPriority(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

