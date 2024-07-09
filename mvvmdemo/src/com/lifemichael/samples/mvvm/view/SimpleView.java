package com.lifemichael.samples.mvvm.view;

import com.lifemichael.samples.mvvm.model.IModel;
import com.lifemichael.samples.mvvm.model.Model;
import com.lifemichael.samples.mvvm.viewmodel.IViewModel;
import com.lifemichael.samples.mvvm.viewmodel.SimpleViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleView implements IView {

    private IViewModel viewModel;

    private JFrame frame;
    private JTextField messagesTf;
    private JPanel panelCenter;
    private JPanel panelLeft;
    private JPanel panelRight;
    private JButton saveBt;
    private JTextField fileNameTf;
    private JButton loadBt;
    private JTextField filePathTf;

    public SimpleView() {
        frame = new JFrame();
        messagesTf = new JTextField();
        panelCenter = new JPanel();
        panelLeft = new JPanel();
        panelRight = new JPanel();
        saveBt = new JButton("Save");
        fileNameTf = new JTextField(10);
        loadBt = new JButton("Load");
        filePathTf = new JTextField(10);

    }
    public void start() {
        frame.add(panelCenter, BorderLayout.CENTER);
        frame.add(messagesTf, BorderLayout.SOUTH);
        panelCenter.setLayout(new GridLayout(1, 2));
        panelCenter.add(panelLeft);
        panelCenter.add(panelRight);
        panelLeft.add(saveBt);
        panelLeft.add(fileNameTf);
        panelRight.add(loadBt);
        panelRight.add(filePathTf);
        frame.setSize(800,900);
        frame.setVisible(true);
        saveBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewModel.saveData(fileNameTf.getText(),messagesTf.getText());
            }
        });
        loadBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewModel.loadData(filePathTf.getText());
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //creating the view
                IView view = new SimpleView();
                view.start();
                //creating the viewmodel
                IViewModel vm = new SimpleViewModel();
                //creating the model
                IModel model = new Model();
                //connecting the viewmodel with the model and the view
                vm.setModel(model);
                vm.setView(view);
                //connecting view with the viewmodel
                view.setViewModel(vm);
            }
        });
    }

    @Override
    public void setViewModel(IViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void setMessage(String message) {
        if(SwingUtilities.isEventDispatchThread()) {
            messagesTf.setText(message);
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    messagesTf.setText(message);
                }
            });
        }
    }
}
