package com.lifemichael.samples.mvvm.model;

public interface IModel {
    public void saveData(String file, String content) throws ModelException;
    public String loadData(String file) throws ModelException;
}
