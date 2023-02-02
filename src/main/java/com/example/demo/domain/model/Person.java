package com.example.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Person {
    private UUID Id;
    private String Name;

    public Person(@JsonProperty("id") UUID id, @JsonProperty("name") String name) {
        Id = id;
        Name = name;
    }

    public UUID getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }
}
