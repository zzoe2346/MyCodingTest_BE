package com.mycodingtest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AlgorithmTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    protected AlgorithmTag() {
    }

    public AlgorithmTag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
