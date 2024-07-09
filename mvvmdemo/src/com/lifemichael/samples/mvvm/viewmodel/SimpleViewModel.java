package com.lifemichael.samples.mvvm.viewmodel;

import com.lifemichael.samples.mvvm.model.IModel;
import com.lifemichael.samples.mvvm.model.ModelException;
import com.lifemichael.samples.mvvm.view.IView;

import java.util.concurrent.*;

public class SimpleViewModel implements IViewModel {

    private ExecutorService service;
    private IView view;
    private IModel model;

    public SimpleViewModel() {
        service = Executors.newFixedThreadPool(3);
    }

    @Override
    public void saveData(String file, String content) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.saveData(file,content);
                    view.setMessage("saving data succeeded");
                } catch (ModelException e) {
                    view.setMessage("error... " + e.getMessage());
                }
            }
        });
    }

    @Override
    public void loadData(String file) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    String text = model.loadData(file);
                    view.setMessage(text);
                } catch (ModelException e) {
                    view.setMessage("error... " + e.getMessage());
                }
            }
        });
    }

    @Override
    public void setModel(IModel model) {
        this.model = model;
    }

    @Override
    public void setView(IView view) {
        this.view = view;
    }
}
