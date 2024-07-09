package com.lifemichael.samples.mvvm.view;

import com.lifemichael.samples.mvvm.viewmodel.IViewModel;

public interface IView {
    public void setViewModel(IViewModel viewModel);
    public void setMessage(String message);
    public void start();
}
