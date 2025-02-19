package com.seung.order_api.domain.enums;

public enum Status {
    ORDERED("ordered"),
    PROCESSING("processing"),
    SUCCESS("success"),
    CANCELLED("cancelled"),
    ;

    private final String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
