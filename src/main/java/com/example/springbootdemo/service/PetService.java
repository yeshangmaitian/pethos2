package com.example.springbootdemo.service;

public interface PetService {
    void add(String name, String other, String type, String symptom, String result, String date);

    void delete(String name, String type);

    void update(String name, String other, String type, String symptom, String result);

    void select();
}
