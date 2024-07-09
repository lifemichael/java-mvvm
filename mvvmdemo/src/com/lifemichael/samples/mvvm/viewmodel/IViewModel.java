package com.lifemichael.samples.mvvm.viewmodel;

import com.lifemichael.samples.mvvm.model.IModel;
import com.lifemichael.samples.mvvm.model.ModelException;
import com.lifemichael.samples.mvvm.view.IView;

public interface IViewModel {
    public void saveData(String file, String content);
    public void loadData(String file);
    public void setModel(IModel model);
    public void setView(IView view);
}
