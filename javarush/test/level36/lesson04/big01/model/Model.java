package com.javarush.test.level36.lesson04.big01.model;

/**
 * Created by sas on 29.12.16.
 */
public interface Model {
    ModelData getModelData();
    void loadUsers();
    void loadDeletedUsers();
}
